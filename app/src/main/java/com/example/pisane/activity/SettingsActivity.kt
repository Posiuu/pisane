package com.example.pisane.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.controler.shared_preferences_manager.PREF_GAME
import com.example.pisane.controler.shared_preferences_manager.PREF_USERNAME
import com.example.pisane.controler.shared_preferences_manager.SharedPreferencesManager
import com.example.pisane.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private val activity = this@SettingsActivity
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogOut.alpha = 0f;
        binding.btnLogOut.animate().alpha(1f).duration = 1500;

        binding.btnLogOut.setOnClickListener {
            binding.btnLogOut.alpha = 0f;
            binding.btnLogOut.animate().alpha(1f).duration = 1500;
            logout()
        }
    }

    private fun logout() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val emptyUsername = ""
        sharedPreferencesManager.putObject(emptyUsername, PREF_USERNAME)

        // delete saved game so new logged in person won't be able to access it
        sharedPreferencesManager.putObject(null, PREF_GAME)

        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Wylogowano poprawnie.", Toast.LENGTH_LONG).show()
    }
}