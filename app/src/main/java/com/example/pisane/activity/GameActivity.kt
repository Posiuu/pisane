package com.example.pisane.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pisane.R
import com.example.pisane.adapter.*
import com.example.pisane.anims.MyBounceInterpolator
import com.example.pisane.controler.game.*
import com.example.pisane.data.shared_preferences_manager.*
import com.example.pisane.databinding.ActivityGameBinding
import com.example.pisane.model.card.*
import com.example.pisane.R.anim.bounce
import com.example.pisane.R.string.*

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
            val params = imageButton.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = binding.gTop81Guideline.id
            params.bottomToBottom = binding.gTop94Guideline.id
            imageButton.requestLayout()
        }

        fun moveDown() {
            val params = imageButton.layoutParams as ConstraintLayout.LayoutParams
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

        loadGameOrStartNewOne()

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

    override fun onPause() {
        super.onPause()

        saveGame()
    }

    private fun drawButtonHandling() {
        if (selectedCardsIndices.isEmpty()) {
            Toast.makeText(this,
                    "Nie wybrałeś żadnych kart do wymiany.", Toast.LENGTH_LONG).show()
            return
        }

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

        if (game.isOver()) {
            endGame()
            return
        }

        game.startHand()
        updateCardImageButtons()
        binding.gResultsRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun saveGame() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        sharedPreferencesManager.putObject(game, PREF_GAME)
    }

    private fun loadGameOrStartNewOne() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val loadedGame = sharedPreferencesManager.getObject<Game>(PREF_GAME)

        if (loadedGame == null) {
            startNewGame()
            return
        }

        game = loadedGame
        updateCardImageButtons()
        val builder = AlertDialog.Builder(this)
        builder.setTitle(confirm_load_game)
        builder.setMessage(confirm_load_game_message)
        builder.setPositiveButton(R.string.yes, DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })
        builder.setNegativeButton(R.string.no, DialogInterface.OnClickListener { dialog, _ ->
            startNewGame()
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }

    private fun startNewGame() {
        game = Game()
        game.startHand()

        updateCardImageButtons()
        binding.gResultsRecyclerView.adapter = GamesTableRecyclerViewAdapter(this,
                game.gamesTable.data, this::chooseGameButtonHandling)
    }

    private fun endGame() {
        Toast.makeText(this,
                "Game ended with ${game.gamesTable.tableTotalScore}", Toast.LENGTH_LONG).show()
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

    fun didTapButton(view: View?) {
        val button = binding.gDrawImageButton
        val myAnim = AnimationUtils.loadAnimation(this, bounce)

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        val interpolator = MyBounceInterpolator(0.2, 20)
        myAnim.interpolator = interpolator
        button.startAnimation(myAnim)
    }
}
