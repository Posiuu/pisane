package com.example.pisane.controler.game

import android.content.Context
import com.example.pisane.consts.RANDOM_CARDS_ID
import com.example.pisane.controler.card_sets.CardSetsManager
import com.example.pisane.controler.shared_preferences.SharedPreferencesHelper.Companion.gamePrefsList
import com.example.pisane.controler.shared_preferences.SharedPreferencesHelper.Companion.getPrefStrBySetId
import com.example.pisane.data.cards
import com.example.pisane.data.gamesCount
import com.example.pisane.model.Card
import com.example.pisane.model.CardSetComponent

class GameManager {
    companion object {
        fun startNewGame(context: Context, setId: Int): Game {
            val newGame: Game

            if (setId == RANDOM_CARDS_ID){
                val cardOrders = mutableListOf<List<Card>>()
                for (i in 0..gamesCount){
                    cardOrders.add(cards.shuffled())
                }

                newGame = Game(cardOrders)
            }
            else if (getPrefStrBySetId(setId) in gamePrefsList) {
                val cardsSet = CardSetsManager.getCardSetBySetId(context, setId)

                val cardOrders = mutableListOf<List<Card>>()
                cardsSet.forEach { cardSetComponent ->
                    val cardsList = cardSetComponentToCardsList(cardSetComponent)
                    cardOrders.add(cardsList)
                }

                newGame = Game(cardOrders)
            }
            else {
                throw Error("Error: SetId not in saved games sets.")
            }

            return newGame
        }

        fun cardSetComponentToCardsList(cardsSetComponent: CardSetComponent): List<Card> {
            val cardsList = mutableListOf<Card>()

            val cardsOrderStr = cardsSetComponent.cardsOrder
            val cardsStrList = cardsOrderStr.split(";")
            cardsStrList.forEach { cardStr ->
                val cardPropertiesStrings = cardStr.split(",")
                val cardFigure = cardPropertiesStrings[0].toInt()
                val cardColor = cardPropertiesStrings[1].toInt()
                val card = cards.filter {card -> card.figure == cardFigure && card.color == cardColor}.firstOrNull()
                cardsList.add(card!!)
            }

            return cardsList
        }
    }
}