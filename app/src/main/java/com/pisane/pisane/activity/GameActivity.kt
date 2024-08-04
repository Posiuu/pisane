package com.pisane.pisane.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.adapter.*
import com.pisane.pisane.anims.MyBounceInterpolator
import com.pisane.pisane.databinding.ActivityGameBinding
import com.pisane.pisane.R.anim.bounce
import com.pisane.pisane.R.string.*
import com.pisane.pisane.consts.*
import com.pisane.pisane.daos.ShopDAO
import com.pisane.pisane.data.ShopGroupId
import com.pisane.pisane.data.ShopItems
import com.pisane.pisane.data.ShopItemsMap
import com.pisane.pisane.helpers.CardImageButtonHelper
import com.pisane.pisane.model.Game
import com.pisane.pisane.helpers.GameHelper
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesHelper
import com.pisane.pisane.shared_preferences.SharedPreferencesManager
import kotlin.properties.Delegates

class GameActivity : AppCompatActivity() {

    private val activity = this@GameActivity
    private lateinit var binding: ActivityGameBinding

    private lateinit var cardImageButtonHelper: CardImageButtonHelper

    private val selectedCardsIndices: MutableList<Int> = mutableListOf()

    private var setId by Delegates.notNull<Int>()
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setId = intent.getIntExtra(GAME_SET_ID, -1)

        setShopItems()

        cardImageButtonHelper = getCardButtonManager()

        loadGameOrStartNewOne()

        binding.gResultsRecyclerView.adapter = GamesTableRecyclerViewAdapter(this,
                game.gamesTable.data, this::chooseGameButtonHandling)

        binding.gDrawImageButton.setOnClickListener {
            drawButtonHandling()
        }

        binding.gCard1ImageButton.setOnClickListener {
            cardImageButtonHandling(0)
        }

        binding.gCard2ImageButton.setOnClickListener {
            cardImageButtonHandling(1)
        }

        binding.gCard3ImageButton.setOnClickListener {
            cardImageButtonHandling(2)
        }

        binding.gCard4ImageButton.setOnClickListener {
            cardImageButtonHandling(3)
        }

        binding.gCard5ImageButton.setOnClickListener {
            cardImageButtonHandling(4)
        }
    }

    override fun onPause() {
        super.onPause()

        if (game.isOver()) {
            game.deleteGame(this, setId)
        }
        else {
            game.saveGame(this, setId)
        }
    }

    private fun getCardButtonManager(): CardImageButtonHelper {
        val cardImageButtonHelper: CardImageButtonHelper

        val imageButtons = listOf(
                binding.gCard1ImageButton,
                binding.gCard2ImageButton,
                binding.gCard3ImageButton,
                binding.gCard4ImageButton,
                binding.gCard5ImageButton,
        )

        val cardsGuidelines = hashMapOf(
                DOWN_TOP_TO_TOP to binding.gTop85Guideline.id,
                DOWN_BOT_TO_BOT to binding.gTop98Guideline.id,
                UP_TOP_TO_TOP to binding.gTop81Guideline.id,
                UP_BOT_TO_BOT to binding.gTop94Guideline.id
        )

        cardImageButtonHelper = CardImageButtonHelper(imageButtons, cardsGuidelines)

        return cardImageButtonHelper
    }

    private fun cardImageButtonHandling(index: Int) {
        cardImageButtonHelper.clickCard(index)
        val isCardSelected = cardImageButtonHelper.isCardSelected(index)

        if (isCardSelected && !selectedCardsIndices.contains(index)) {
            selectedCardsIndices.add(index)
        }
        else if (!isCardSelected && selectedCardsIndices.contains(index)) {
            selectedCardsIndices.remove(index)
        }
    }

    private fun drawButtonHandling() {
        if (selectedCardsIndices.isEmpty()) {
            Toast.makeText(this,
                    "Nie wybrałeś żadnych kart do wymiany.", Toast.LENGTH_LONG).show()
            return
        }

        val hand = game.currentHand
        val isDrawSuccessful = hand.draw(selectedCardsIndices)
        cardImageButtonHelper.updateCardImageButtons(game.currentHand.currentCards)
        selectedCardsIndices.clear()

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

        if (game.isOver()) {
            endGame()
            return
        }

        cardImageButtonHelper.updateCardImageButtons(game.currentHand.currentCards)
        selectedCardsIndices.clear()
        binding.gResultsRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun loadGameOrStartNewOne() {
        val loadedGame = SharedPreferencesHelper.getLoadedGame(this, setId)

        if (loadedGame != null){
            game = loadedGame
            cardImageButtonHelper.updateCardImageButtons(game.currentHand.currentCards)
            selectedCardsIndices.clear()
            if (setId == RANDOM_CARDS_ID){
                showGameResetAlert()
            }
        }
        else {
            startNewGame()
        }
    }

    private fun showGameResetAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(confirm_load_game)
        builder.setMessage(confirm_load_game_message)
        builder.setPositiveButton(yes) { dialog, _ ->
            dialog.cancel()
        }
        builder.setNegativeButton(no) { dialog, _ ->
            startNewGame()
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun startNewGame() {
        game = GameHelper.startNewGame(this, setId)
        game.startHand()

        cardImageButtonHelper.updateCardImageButtons(game.currentHand.currentCards)
        selectedCardsIndices.clear()
        binding.gResultsRecyclerView.adapter = GamesTableRecyclerViewAdapter(this,
                game.gamesTable.data, this::chooseGameButtonHandling)
    }

    private fun endGame() {
        game.saveHighscore(this, setId)
        SharedPreferencesHelper.deleteSavedGame(this, setId)

        val intent = Intent(activity, HighscoresActivity::class.java)
        intent.putExtra(GAME_SET_ID, setId)
        startActivity(intent)
        finish()

        Toast.makeText(this,
                "Game ended with ${game.gamesTable.tableTotalScore}", Toast.LENGTH_LONG).show()
    }

    fun didTapButton() {
        val button = binding.gDrawImageButton
        val myAnim = AnimationUtils.loadAnimation(this, bounce)

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        val interpolator = MyBounceInterpolator(0.2, 20)
        myAnim.interpolator = interpolator
        button.startAnimation(myAnim)
    }

    private fun setShopItems() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)
        val shopItemPurchases = ShopDAO.getShopItemPurchases(userId!!)

        val background = shopItemPurchases?.firstOrNull{it.group_id == ShopGroupId.BACKGROUND && it.is_selected == 1}
        val backgroundItem = ShopItemsMap[background?.item_id] ?: ShopItems.BACKGROUND_DEFAULT
        binding.gBackgroundImageView.setImageResource(backgroundItem.imageSource)

        val deck = shopItemPurchases?.firstOrNull{it.group_id == ShopGroupId.CARDS && it.is_selected == 1}
        val deckItem = ShopItemsMap[deck?.item_id] ?: ShopItems.CARDS_DEFAULT
        binding.gCardDeckImageView.setImageResource(deckItem.imageSource)
    }
}
