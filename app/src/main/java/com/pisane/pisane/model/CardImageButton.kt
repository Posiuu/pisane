package com.pisane.pisane.model

import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.pisane.pisane.consts.DOWN_BOT_TO_BOT
import com.pisane.pisane.consts.DOWN_TOP_TO_TOP
import com.pisane.pisane.consts.UP_BOT_TO_BOT
import com.pisane.pisane.consts.UP_TOP_TO_TOP


class CardImageButton(private val imageButton: ImageButton, private val index: Int, private val guidelines: HashMap<String, Int>) {
    var isSelected: Boolean = false
    lateinit var card: Card

    fun click() {
        if (isSelected) {
            moveDown()
            isSelected = false
        }
        else {
            moveUp()
            isSelected = true
        }
    }

    private fun moveUp() {
        imageButton.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToTop = guidelines[UP_TOP_TO_TOP]!!
            bottomToTop = guidelines[UP_BOT_TO_BOT]!!
        }
        imageButton.requestLayout()
    }

    fun moveDown() {
        imageButton.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToTop = guidelines[DOWN_TOP_TO_TOP]!!
            bottomToTop = guidelines[DOWN_BOT_TO_BOT]!!
        }
        imageButton.requestLayout()
    }

    fun updateCard(currentCards: MutableList<Card>) {
        card = currentCards[index]
        imageButton.setImageResource(card.image)
    }
}
