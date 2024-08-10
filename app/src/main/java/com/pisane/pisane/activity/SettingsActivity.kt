package com.pisane.pisane.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.R
import com.pisane.pisane.daos.TokensDAO
import com.pisane.pisane.shared_preferences.PREF_USERNAME
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesHelper
import com.pisane.pisane.shared_preferences.SharedPreferencesManager
import com.pisane.pisane.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private val activity = this@SettingsActivity
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setChipsCount()

        binding.settingsLogOutButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_go_back).start()
            logout()
        }
        binding.settingsBackImageButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_go_back).start()
            finish()
        }
    }

    private fun logout() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val emptyUsername = ""
        sharedPreferencesManager.putObject(emptyUsername, PREF_USERNAME)
        sharedPreferencesManager.putObject(null, PREF_USER_ID)

        // delete all saved games so new logged in person won't be able to access them
        SharedPreferencesHelper.deleteAllSavedGames(this)

        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Wylogowano poprawnie.", Toast.LENGTH_LONG).show()
    }

    private fun setChipsCount() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val chipsCount = TokensDAO.getTokensCount(userId!!)
        binding.settingsChipCountTextView.text = chipsCount.toString()
    }
}
