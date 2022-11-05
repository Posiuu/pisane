package com.example.pisane.controler.game

import android.content.Context
import com.example.pisane.controler.games_table.*
import com.example.pisane.controler.hand.*
import com.example.pisane.controler.highscore.HighscoreControler
import com.example.pisane.controler.shared_preferences_manager.PREF_GAME
import com.example.pisane.controler.shared_preferences_manager.PREF_USERNAME
import com.example.pisane.controler.shared_preferences_manager.SharedPreferencesManager

class Game() {
    lateinit var currentHand: Hand
    var gamesTable = GamesTable()
    private var handsCount = 0

    fun startHand() {
        handsCount++
        currentHand = Hand()
        updateGamesTable()
    }

    fun updateGamesTable() {
        val possibleScores = currentHand.getPossibleScores()
        gamesTable.updateWithPossibleScores(possibleScores)
    }

    fun isOver(): Boolean {
        return handsCount == gamesTable.data.size
    }

    fun saveGame(context: Context) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        sharedPreferencesManager.putObject(this, PREF_GAME)
    }

    fun deleteGame(context: Context) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        sharedPreferencesManager.putObject(null, PREF_GAME)
    }

    fun saveHighscore(context: Context) {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val nick = sharedPreferencesManager.getObject<String>(PREF_USERNAME)
        val score = gamesTable.tableTotalScore

        if (nick != null && score != 0) {
            HighscoreControler.newHighscore(context, nick, score.toString())
        }
    }
}