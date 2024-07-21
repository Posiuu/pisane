package com.pisane.pisane.helpers

import android.content.Context
import com.pisane.pisane.consts.RANDOM_CARDS_ID
import com.pisane.pisane.daos.CardSetsDAO
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesHelper.Companion.gamePrefsList
import com.pisane.pisane.shared_preferences.SharedPreferencesHelper.Companion.getPrefStrBySetId
import com.pisane.pisane.shared_preferences.SharedPreferencesManager
import com.pisane.pisane.data.cards
import com.pisane.pisane.data.gamesCount
import com.pisane.pisane.model.Card
import com.pisane.pisane.model.CardSetComponent
import com.pisane.pisane.model.Game

class GameHelper {
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
                val cardsSet = CardSetsDAO.getCardsSet(setId)
                    ?: throw Error("Error: SetId not in saved games sets.")

                val cardOrders = cardsSet.map { cardSetComponent ->
                    cardSetComponentToCardsList(cardSetComponent)
                }

                newGame = Game(cardOrders)

                val sharedPreferencesManager = SharedPreferencesManager(context)
                val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)
                CardSetsDAO.newSetPlayed(setId, userId!!)
            }
            else {
                throw Error("Error: SetId not in saved games sets.")
            }

            return newGame
        }

        private fun cardSetComponentToCardsList(cardsSetComponent: CardSetComponent): List<Card> {
            val cardsList: List<Card>

            val cardIdsList = cardsSetComponent.cards_order.split(";")
            cardsList = cardIdsList.map { cardId ->
                cards[cardId.toInt()]
            }

            return cardsList
        }
    }
}