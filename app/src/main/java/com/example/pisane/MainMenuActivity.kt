package com.example.pisane

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.databinding.ActivityMainMenuBinding


class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        Toast.makeText(
                this, "Ustawienia będą wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
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