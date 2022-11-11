package com.example.pisane.controler.card_sets

import android.content.Context
import com.example.pisane.controler.daos.CardSetsDAO
import com.example.pisane.data.cards
import com.example.pisane.data.gamesCount
import com.example.pisane.model.CardSetComponent

class CardSetsManager {
    companion object {
        fun getCardSetBySetId(context: Context, setId: Int): List<CardSetComponent> {
            var cardSet = mutableListOf<CardSetComponent>()

            val cardSetAllStr = CardSetsDAO.getCardsSet(context, setId.toString())

            cardSetAllStr.forEach { cardSetComponentAllStr ->
                val dealNumber = cardSetComponentAllStr.deal_number.toInt()
                val cardsOrder = cardSetComponentAllStr.cards_order
                val setId = cardSetComponentAllStr.set_id.toInt()
                val cardSetComponent = CardSetComponent(dealNumber, cardsOrder, setId)
                cardSet.add(cardSetComponent)
            }

            cardSet = cardSet.sortedBy { it.dealNumber } as MutableList<CardSetComponent>

            return cardSet
        }

        // not used anywhere but when there will be the need to add new cards sets you can call this method
        fun addNewCardsSet(context: Context, setId: Int) {
            CardSetsDAO.newCardsSet(context, setId.toString())

            val cardSetComponents = generateCardSetComponents(setId)
            cardSetComponents.forEach { cardSetComponent ->
                CardSetsDAO.newCardsSetComponent(context, cardSetComponent.dealNumber.toString(), cardSetComponent.cardsOrder, cardSetComponent.setId.toString())
            }
        }

        fun generateCardSetComponents(setId: Int): List<CardSetComponent> {
            val cardSetComponents = mutableListOf<CardSetComponent>()

            for (i in 0..gamesCount){
                val cardsOrderStr = generateCardsOrderStr()
                val cardsOrder = CardSetComponent(i + 1, cardsOrderStr, setId)
                cardSetComponents.add(cardsOrder)
            }

            return cardSetComponents
        }

        fun generateCardsOrderStr(): String {
            var cardsOrderStr = ""

            val randomCardsOrder = cards.shuffled()
            randomCardsOrder.forEach {card ->
                cardsOrderStr += "${card.figure},${card.color};"
            }
            cardsOrderStr = cardsOrderStr.dropLast(1)

            return cardsOrderStr
        }
    }
}