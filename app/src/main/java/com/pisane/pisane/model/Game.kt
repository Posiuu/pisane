package com.pisane.pisane.model

import android.content.Context
import com.pisane.pisane.daos.HighscoreDAO
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesHelper
import com.pisane.pisane.shared_preferences.SharedPreferencesManager

class Game(private var cardOrders: List<List<Card>>) {
    lateinit var currentHand: Hand
    var gamesTable = GamesTable()
    private var handsCount = 0

    fun startHand() {
        handsCount++
        if (!isOver()){
            currentHand = Hand(cardOrders[handsCount - 1])
            updateGamesTable()
        }
    }

    fun updateGamesTable() {
        val possibleScores = currentHand.getPossibleScores()
        gamesTable.updateWithPossibleScores(possibleScores)
    }

    fun isOver(): Boolean {
        return handsCount == gamesTable.data.size
    }

    fun saveGame(context: Context, setId: Int) {
        val sharedPreferencesManager = SharedPreferencesManager(context)

        val prefString = SharedPreferencesHelper.getPrefStrBySetId(setId)
        if (prefString.isNotBlank()) {
            sharedPreferencesManager.putObject(this, prefString)
        }
    }

    fun deleteGame(context: Context, setId: Int) {
        val sharedPreferencesManager = SharedPreferencesManager(context)

        val prefString = SharedPreferencesHelper.getPrefStrBySetId(setId)
        if (prefString.isNotBlank()) {
            sharedPreferencesManager.putObject(null, prefString)
        }
    }

    fun saveHighscore(context: Context, setId: Int) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)
        val score = gamesTable.tableTotalScore

        if (score != 0) {
            HighscoreDAO.newHighscore(score, userId!!, setId)
        }
    }
}