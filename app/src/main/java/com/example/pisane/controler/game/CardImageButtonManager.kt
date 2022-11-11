package com.example.pisane.controler.game

import android.widget.ImageButton
import com.example.pisane.model.Card

class CardImageButtonManager(imageButtons: List<ImageButton>, guidelines: HashMap<String, Int>) {
    val cardsImageButtons = listOf(
            CardImageButton(imageButtons[0], 0, guidelines),
            CardImageButton(imageButtons[1], 1, guidelines),
            CardImageButton(imageButtons[2], 2, guidelines),
            CardImageButton(imageButtons[3], 3, guidelines),
            CardImageButton(imageButtons[4], 4, guidelines)
    )

    fun updateCardImageButtons(currentCards: MutableList<Card>) {
        cardsImageButtons.forEach { cardImageButton ->
            cardImageButton.updateCard(currentCards)
        }

        moveDownAllImageButtons()
    }

    fun moveDownAllImageButtons() {
        cardsImageButtons.forEach { cardImageButton ->
            cardImageButton.moveDown()
        }
    }

    fun clickCard(index: Int){
        cardsImageButtons[index].click()
    }

    fun isCardSelected(index: Int): Boolean {
        return cardsImageButtons[index].isSelected
    }
}
