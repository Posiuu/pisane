package com.pisane.pisane.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.consts.GAME_SET_ID
import com.pisane.pisane.consts.RANDOM_CARDS_ID
import com.pisane.pisane.daos.TokensDAO
import com.pisane.pisane.databinding.ActivityMainMenuBinding
import com.pisane.pisane.helpers.DatetimeHelper
import com.pisane.pisane.shared_preferences.PREF_USERNAME
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesManager


class MainMenuActivity : AppCompatActivity() {
    private val activity = this@MainMenuActivity
    private lateinit var binding: ActivityMainMenuBinding

    private val secondsBetweenActivation: Long = 10 * 60 * 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUsername()
        setCollectTokensTimer()
        setChipsCount()

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
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val activationDatetimeStr = TokensDAO.getLastTokensActivation(userId!!)?.activation_datetime
        val secondsDiff = DatetimeHelper.secondsDiff(activationDatetimeStr!!)

        if (secondsDiff > secondsBetweenActivation) {
            val chipsCount = TokensDAO.updateUserTokens(10000, userId)
            binding.mmChipCountTextView.text = chipsCount.toString()
            TokensDAO.newTokenActivation(userId)
            Toast.makeText(
                this, "Otrzymujesz 10 000 żetonów",
                Toast.LENGTH_SHORT
            ).show()
            startTimer(secondsBetweenActivation * 1000)
        }
        else {
            Toast.makeText(
                this, "Żetony odbierać możesz co 10 godzin",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun shopButtonHandling() {
        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
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

    private fun setCollectTokensTimer() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val activationDatetimeStr = TokensDAO.getLastTokensActivation(userId!!)?.activation_datetime
        val secondsDiff = DatetimeHelper.secondsDiff(activationDatetimeStr!!)

        if (secondsDiff > secondsBetweenActivation) {
            binding.mmCollectChipsTimer.text= "Odbierz darmowe \n10000 rzetonów!"
        }
        else {
            val millisecondsToCount = (secondsBetweenActivation - secondsDiff) * 1000
            startTimer(millisecondsToCount)
        }
    }

    private fun setChipsCount() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val chipsCount = TokensDAO.getTokensCount(userId!!)
        binding.mmChipCountTextView.text = chipsCount.toString()
    }

    private fun startTimer(millisecondsToCount: Long) {
        object : CountDownTimer(millisecondsToCount, 1000){
            override fun onTick(milsUntilFinished: Long) {
                val hoursStr = (milsUntilFinished / 1000 / 60 / 60).toString().padStart(2, '0')
                val minutesStr = (milsUntilFinished / 1000 / 60 % 60).toString().padStart(2, '0')
                val secondsStr = (milsUntilFinished / 1000 % 60).toString().padStart(2, '0')
                binding.mmCollectChipsTimer.text= "$hoursStr:$minutesStr:$secondsStr"
            }

            override fun onFinish() {
                binding.mmCollectChipsTimer.text = "Odbierz darmowe \n10000 rzetonów!"
            }
        }.start()
    }
}