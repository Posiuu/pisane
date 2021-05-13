package com.example.pisane

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pisane.databinding.ActivityGameBinding

import com.example.pisane.adapter.*
import com.example.pisane.model.card.*
import com.example.pisane.controler.game.*

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private inner class CardImageButton(private val imageButton: ImageButton, private val index: Int) {
        private var isSelected: Boolean = false
        lateinit var card: Card

        fun click() {
            if (isSelected) {
                moveDown()
                selectedCardsIndices.remove(index)
                isSelected = false
            }
            else {
                moveUp()
                selectedCardsIndices.add(index)
                isSelected = true
            }
        }

        private fun moveUp() {
            val params = this.imageButton.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = binding.gTop81Guideline.id
            params.bottomToBottom = binding.gTop94Guideline.id
            imageButton.requestLayout()
        }

        fun moveDown() {
            val params = this.imageButton.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = binding.gTop85Guideline.id
            params.bottomToBottom = binding.gTop98Guideline.id
            imageButton.requestLayout()
        }

        fun updateCard() {
            card = game.currentHand.currentCards[index]
            imageButton.setImageResource(card.image)
        }
    }

    private lateinit var cardsImageButtons: List<CardImageButton>

    private val selectedCardsIndices: MutableList<Int> = mutableListOf()

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardsImageButtons = listOf(
                CardImageButton(binding.gCard1ImageButton, 0),
                CardImageButton(binding.gCard2ImageButton, 1),
                CardImageButton(binding.gCard3ImageButton, 2),
                CardImageButton(binding.gCard4ImageButton, 3),
                CardImageButton(binding.gCard5ImageButton, 4)
        )

        startGame()

        binding.gResultsRecyclerView.adapter = GamesTableRecyclerViewAdapter(this,
                game.gamesTable.data, this::chooseGameButtonHandling)

        binding.gDrawImageButton.setOnClickListener {
            drawButtonHandling()
        }

        binding.gCard1ImageButton.setOnClickListener {
            cardsImageButtons[0].click()
        }

        binding.gCard2ImageButton.setOnClickListener {
            cardsImageButtons[1].click()
        }

        binding.gCard3ImageButton.setOnClickListener {
            cardsImageButtons[2].click()
        }

        binding.gCard4ImageButton.setOnClickListener {
            cardsImageButtons[3].click()
        }

        binding.gCard5ImageButton.setOnClickListener {
            cardsImageButtons[4].click()
        }
    }

    private fun drawButtonHandling() {
        val hand = game.currentHand
        val isDrawSuccessful = hand.draw(selectedCardsIndices)
        updateCardImageButtons()

        if (!isDrawSuccessful) {
            Toast.makeText(this,
                    "Wykorzystano już 2 wymiany. Wybierz grę z tabeli gier.", Toast.LENGTH_LONG).show()
            return
        }

        game.updateGamesTable()
        binding.gResultsRecyclerView.adapter!!.notifyDataSetChanged()

    }

    private fun chooseGameButtonHandling(gamePosition: Int) {
        game.gamesTable.chooseGameScore(gamePosition)

        game.startHand()
        updateCardImageButtons()
        binding.gResultsRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun startGame() {
        game = Game()
        game.startHand()

        updateCardImageButtons()
    }

    private fun updateCardImageButtons() {
        cardsImageButtons.forEach { cardImageButton ->
            cardImageButton.updateCard()
        }

        selectedCardsIndices.clear()
        moveDownAllImageButtons()
    }

    private fun moveDownAllImageButtons() {
        cardsImageButtons.forEach { cardImageButton ->
            cardImageButton.moveDown()
        }
    }
}
