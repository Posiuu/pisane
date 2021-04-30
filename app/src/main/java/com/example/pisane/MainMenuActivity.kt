package com.example.pisane

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.databinding.ActivityMainMenuBinding


class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingsImageButton.setOnClickListener {
            settingsButtonHandling()
        }

        binding.randomCardsButton.setOnClickListener {
            randomCardsButtonHandling()
        }

        binding.cardsSetsButton.setOnClickListener {
            cardsSetsButtonHandling()
        }

        binding.scoresButton.setOnClickListener {
            scoresButtonHandling()
        }

        binding.rulesButton.setOnClickListener {
            rulesButtonHandling()
        }

        binding.collectFreeChipsButton.setOnClickListener {
            collectFreeChipsButtonHandling()
        }

        binding.shopImageButton.setOnClickListener {
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