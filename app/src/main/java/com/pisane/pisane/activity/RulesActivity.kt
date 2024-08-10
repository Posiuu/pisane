package com.pisane.pisane.activity

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.R
import com.pisane.pisane.daos.TokensDAO
import com.pisane.pisane.databinding.ActivityRulesBinding
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesManager

class RulesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRulesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setChipsCount()

        binding.ruBackImageButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_go_back).start()
            finish()
        }
    }

    private fun setChipsCount() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val chipsCount = TokensDAO.getTokensCount(userId!!)
        binding.ruChipCountTextView.text = chipsCount.toString()
    }
}
