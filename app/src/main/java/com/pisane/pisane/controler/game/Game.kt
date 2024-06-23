package com.pisane.pisane.controler.game

import android.content.Context
import com.pisane.pisane.controler.games_table.*
import com.pisane.pisane.controler.hand.*
import com.pisane.pisane.controler.daos.HighscoreDAO
import com.pisane.pisane.controler.shared_preferences.*
import com.pisane.pisane.model.Card

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
        val nick = sharedPreferencesManager.getObject<String>(PREF_USERNAME)
        val user_id = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)
        val score = gamesTable.tableTotalScore

        //if (nick != null && score != 0) {
        //    HighscoreDAO.newHighscore(context, nick, score.toString(), user_id.toString(), setId.toString())
        //}
    }
}