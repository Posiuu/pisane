package com.example.pisane.controler.hand

import com.example.pisane.controler.cards_sets_recognizer.*
import com.example.pisane.data.cards.*
import com.example.pisane.model.card.*

class Hand {
    private var availableCards = cards.shuffled()
    lateinit var currentCards: MutableList<Card>
    private var drawsCount = 0

    init {
        deal5Cards()
    }

    private fun deal5Cards() {
        currentCards = availableCards.subList(0, 5) as MutableList<Card>
        availableCards = availableCards.drop(5)
    }

    //return true if success
    //return false if no more draws available
    fun draw(selectedCardsIndexes: List<Int>): Boolean {
        if(drawsCount == 2) {
            return false
        }

        for (i in selectedCardsIndexes) {
            currentCards[i] = availableCards[0]
            availableCards = availableCards.drop(1)
        }

        drawsCount++
        return true
    }

    fun getPossibleScores(): List<Int> {
        return CardsSetsRecognizer().getAllGamesScores(currentCards)
    }
}