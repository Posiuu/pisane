package com.example.pisane.controler.game

import com.example.pisane.controler.games_table.*
import com.example.pisane.controler.hand.*

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
        return handsCount == gamesTable.data.size - 1
    }
}