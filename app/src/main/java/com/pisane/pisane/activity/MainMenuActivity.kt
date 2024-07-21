package com.pisane.pisane.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.consts.GAME_SET_ID
import com.pisane.pisane.consts.RANDOM_CARDS_ID
import com.pisane.pisane.daos.TokensDAO
import com.pisane.pisane.databinding.ActivityMainMenuBinding
import com.pisane.pisane.shared_preferences.PREF_USERNAME
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesManager
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class MainMenuActivity : AppCompatActivity() {

    private val activity = this@MainMenuActivity
    private lateinit var binding: ActivityMainMenuBinding

    private var tokensActivationTimer: CountDownTimer? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUsername()
        setCollectTokensTimer()

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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun collectFreeChipsButtonHandling() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val activationDatetimeStr = TokensDAO.getLastTokensActivation(userId!!)?.activation_datetime
        val secondsDiff = secoundsDiff(activationDatetimeStr!!)

        if (secondsDiff > (60 * 60 * 10)) {
            TokensDAO.newTokenActivation(userId)
            Toast.makeText(
                this, "Otrzymujesz 10 000 żetonów",
                Toast.LENGTH_SHORT
            ).show()
            startTimer(0)
        }
        else {
            Toast.makeText(
                this, "Żetony odbierać możesz co 10 godzin",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun shopButtonHandling() {
        setCollectTokensTimer()
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCollectTokensTimer() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val activationDatetimeStr = TokensDAO.getLastTokensActivation(userId!!)?.activation_datetime
        val secondsDiff = secoundsDiff(activationDatetimeStr!!)

        if (secondsDiff > (60 * 60 * 10)) {
            binding.mmCollectChipsTimer.text= "Odbierz darmowe \n10000 rzetonów!"
        }
        else {
            startTimer(secondsDiff)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun secoundsDiff(datetimeStr: String): Long {
        val dateFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val now = dateFormatter
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
        val from = LocalDateTime.parse(datetimeStr, dateFormatter)
        val to = LocalDateTime.parse(now, dateFormatter)

        val timezoneDiff = (60 * 60 * 2)
        return Duration.between(from, to).seconds + timezoneDiff
    }

    private fun startTimer(secondsDiff: Long) {
        tokensActivationTimer = object : CountDownTimer(((60 * 60 * 10) - secondsDiff) * 1000, 1000){
            @RequiresApi(Build.VERSION_CODES.O)
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