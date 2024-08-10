package com.pisane.pisane.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.consts.GAME_SET_ID
import com.pisane.pisane.consts.RANDOM_CARDS_ID
import com.pisane.pisane.daos.ExperienceDAO
import com.pisane.pisane.daos.TokensDAO
import com.pisane.pisane.data.LevelExperience
import com.pisane.pisane.databinding.ActivityMainMenuBinding
import com.pisane.pisane.helpers.DatetimeHelper
import com.pisane.pisane.shared_preferences.PREF_USERNAME
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesManager
import com.pisane.pisane.R


class MainMenuActivity : AppCompatActivity() {
    private val activity = this@MainMenuActivity
    private lateinit var binding: ActivityMainMenuBinding

    private var userId: Int? = null
    private val secondsBetweenActivation: Long = 10 * 60 * 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferencesManager = SharedPreferencesManager(this)
        userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        setProgressBar()
        setUsername()
        setCollectTokensTimer()
        setChipsCount()

        binding.mmSettingsImageButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_button_click).start()
            settingsButtonHandling()
        }

        binding.mmRandomCardsButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_button_click).start()
            randomCardsButtonHandling()
        }

        binding.mmCardsSetsButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_button_click).start()
            cardsSetsButtonHandling()
        }

        binding.mmScoresButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_button_click).start()
            scoresButtonHandling()
        }

        binding.mmRulesButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_button_click).start()
            rulesButtonHandling()
        }

        binding.mmCollectFreeChipsButton.setOnClickListener {
            collectFreeChipsButtonHandling()
        }

        binding.mmShopImageButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_button_click).start()
            shopButtonHandling()
        }
    }

    override fun onResume() {
        super.onResume()
        setProgressBar()
        setChipsCount()
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
        val secondsDiff = getActivationSecondsDiff()
        if (secondsDiff != null && secondsDiff <= secondsBetweenActivation) {
            MediaPlayer.create(this, R.raw.sound_error).start()
            Toast.makeText(
                this, "Żetony odbierać możesz co 10 godzin",
                Toast.LENGTH_SHORT
            ).show()
        }
        else {
            MediaPlayer.create(this, R.raw.sound_collect_chips).start()
            val chipsCount = TokensDAO.updateUserTokens(10000, userId!!)
            binding.mmChipCountTextView.text = chipsCount.toString()
            TokensDAO.newTokenActivation(userId!!)
            Toast.makeText(
                this, "Otrzymujesz 10 000 żetonów",
                Toast.LENGTH_SHORT
            ).show()
            startTimer(secondsBetweenActivation * 1000)
        }

    }

    private fun shopButtonHandling() {
        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
    }

    private fun setProgressBar() {
        val userExperience = ExperienceDAO.getUserExperience(userId!!)
        val userLevel = LevelExperience.entries.first{ userExperience < it.value }
        val previousLevelValue = if (userLevel.key != 1) LevelExperience[userLevel.key - 1] ?: 0 else 0
        val levelProgress = (userExperience - previousLevelValue).toDouble() / (userLevel.value - previousLevelValue).toDouble()
        val levelProgressPercent = (levelProgress * 100).toInt()

        binding.mmProgressBarLevelTextView.text = "Poziom ${userLevel.key}"
        binding.mmLevelProgressBar.progress = levelProgressPercent
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
        val secondsDiff = getActivationSecondsDiff()
        if (secondsDiff != null && secondsDiff <= secondsBetweenActivation) {
            val millisecondsToCount = (secondsBetweenActivation - secondsDiff) * 1000
            startTimer(millisecondsToCount)
        }
        else {
            binding.mmCollectChipsTimer.text= "Odbierz darmowe\n10000 rzetonów!"
        }
    }

    private fun getActivationSecondsDiff(): Long? {
        val activationDatetimeStr = TokensDAO.getLastTokensActivation(userId!!)?.activation_datetime
        return if (activationDatetimeStr != null) DatetimeHelper.secondsDiff(activationDatetimeStr) else null
    }

    private fun setChipsCount() {
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