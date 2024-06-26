package com.pisane.pisane.controler.game

import android.content.Context
import com.pisane.pisane.consts.RANDOM_CARDS_ID
import com.pisane.pisane.controler.daos.CardSetsDAO
import com.pisane.pisane.controler.card_sets.CardSetsManager
import com.pisane.pisane.controler.shared_preferences.PREF_USER_ID
import com.pisane.pisane.controler.shared_preferences.SharedPreferencesHelper.Companion.gamePrefsList
import com.pisane.pisane.controler.shared_preferences.SharedPreferencesHelper.Companion.getPrefStrBySetId
import com.pisane.pisane.controler.shared_preferences.SharedPreferencesManager
import com.pisane.pisane.data.cards
import com.pisane.pisane.data.gamesCount
import com.pisane.pisane.model.Card
import com.pisane.pisane.model.CardSetComponent

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

                val sharedPreferencesManager = SharedPreferencesManager(context)
                val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)
                CardSetsDAO.newSetPlayed(context, userId.toString(), setId.toString())
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