package com.pisane.pisane.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.consts.GAME_SET_ID
import com.pisane.pisane.consts.RANDOM_CARDS_ID
import com.pisane.pisane.controler.shared_preferences.PREF_USERNAME
import com.pisane.pisane.controler.shared_preferences.SharedPreferencesManager
import com.pisane.pisane.databinding.ActivityMainMenuBinding


class MainMenuActivity : AppCompatActivity() {

    private val activity = this@MainMenuActivity
    private lateinit var binding: ActivityMainMenuBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUsername()

        binding.mmSettingsImageButton.setOnClickListener {
            settingsButtonHandling()
        }

        binding.mmRandomCardsButton.setOnClickListener {
            randomCardsButtonHandling()
        }

        binding.mmCardsSetsButton.setOnClickListener {
            cardsSetsButtonHandling()
        }

        binding.mmScoresButton.setOnClickListener {
            scoresButtonHandling()
        }

        binding.mmRulesButton.setOnClickListener {
            rulesButtonHandling()
        }

        binding.mmCollectFreeChipsButton.setOnClickListener {
            collectFreeChipsButtonHandling()
        }

        binding.mmShopImageButton.setOnClickListener {
            shopButtonHandling()
        }
    }

    private fun settingsButtonHandling() {
        val intent = Intent(activity, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun randomCardsButtonHandling() {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GAME_SET_ID, RANDOM_CARDS_ID)
        startActivity(intent)
    }

    private fun cardsSetsButtonHandling() {
        val intent = Intent(this, ChooseCardSetActivity::class.java)
        startActivity(intent)
    }

    private fun scoresButtonHandling() {
        val intent = Intent(this, ChooseHighscoresActivity::class.java)
        startActivity(intent)
    }

    private fun rulesButtonHandling() {
        Toast.makeText(
                this, "Zasady będą wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun collectFreeChipsButtonHandling() {
        Toast.makeText(
                this, "Odbieranie i zbieranie rzetonów będzie wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun shopButtonHandling() {
        Toast.makeText(
                this, "Sklep będzie wkrótce dostępny",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun setUsername() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val logedInUsername = sharedPreferencesManager.getObject<String>(PREF_USERNAME)

        if (logedInUsername == null) {
            binding.mmWelcomeTextView.text = "Witaj nieznany"
        }
        else {
            binding.mmWelcomeTextView.text = "Witaj $logedInUsername"
        }
    }
}