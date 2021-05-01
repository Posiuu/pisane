package com.example.pisane

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pisane.adapter.GamesTableRecyclerViewAdapter
import com.example.pisane.data.cards.*
import com.example.pisane.data.game_table_records.*
import com.example.pisane.databinding.ActivityGameBinding
import com.example.pisane.model.card.*


class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    inner class CardImageButton(private val imageButton: ImageButton, private val card: Card) {
        private var isSelected: Boolean = false

        fun change(newCard: Card) {

        }

        fun click() {
            if (isSelected) {
                moveDown()
                selectedCards.remove(this)
                isSelected = false
            }
            else {
                moveUp()
                selectedCards.add(this)
                isSelected = true
            }
        }

        private fun moveUp() {
            val params = this.imageButton.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = binding.gTop81Guideline.id
            params.bottomToBottom = binding.gTop94Guideline.id
            imageButton.requestLayout()
        }

        private fun moveDown() {
            val params = this.imageButton.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = binding.gTop85Guideline.id
            params.bottomToBottom = binding.gTop98Guideline.id
            imageButton.requestLayout()
        }
    }

    private lateinit var card1: CardImageButton
    private lateinit var card2: CardImageButton
    private lateinit var card3: CardImageButton
    private lateinit var card4: CardImageButton
    private lateinit var card5: CardImageButton

    private val selectedCards: MutableList<CardImageButton> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gResultsRecyclerView.adapter = GamesTableRecyclerViewAdapter(this, gameTableScoresList)

        card1 = CardImageButton(binding.gCard1ImageButton, hA)
        card2 = CardImageButton(binding.gCard2ImageButton, s2)
        card3 = CardImageButton(binding.gCard3ImageButton, d7)
        card4 = CardImageButton(binding.gCard4ImageButton, d9)
        card5 = CardImageButton(binding.gCard5ImageButton, c10)

        binding.gDrawImageButton.setOnClickListener {
            drawButtonHandling()
        }

        binding.gCard1ImageButton.setOnClickListener {
            card1.click()
        }

        binding.gCard2ImageButton.setOnClickListener {
            card2.click()
        }

        binding.gCard3ImageButton.setOnClickListener {
            card3.click()
        }

        binding.gCard4ImageButton.setOnClickListener {
            card4.click()
        }

        binding.gCard5ImageButton.setOnClickListener {
            card5.click()
        }
    }

    private fun drawButtonHandling() {
        Toast.makeText(
                this, "Wymienianie będzie wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
    }
}
