package com.example.pisane.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.data.shared_preferences_manager.PREF_USERNAME
import com.example.pisane.data.shared_preferences_manager.SharedPreferencesManager
import com.example.pisane.databinding.ActivityMainMenuBinding


class MainMenuActivity : AppCompatActivity() {

    private val activity = this@MainMenuActivity
    private lateinit var binding: ActivityMainMenuBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferencesManager = SharedPreferencesManager(this)
        val logedInUsername = sharedPreferencesManager.getObject<String>(PREF_USERNAME)

        if (logedInUsername == null) {
            binding.mmWelcomeTextView.text = "Witaj nieznany"
        }
        else {
            binding.mmWelcomeTextView.text = "Witaj $logedInUsername"
        }

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
        startActivity(intent)
    }

    private fun cardsSetsButtonHandling() {
        Toast.makeText(
                this, "Tryb gry zestawy kart będzie wkrótce dostępny",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun scoresButtonHandling() {
        Toast.makeText(
                this, "Wyniki będą wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
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
}