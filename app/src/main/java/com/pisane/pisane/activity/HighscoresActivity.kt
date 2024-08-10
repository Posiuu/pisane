package com.pisane.pisane.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.adapter.*
import com.pisane.pisane.consts.GAME_SET_ID
import com.pisane.pisane.consts.RANDOM_CARDS_ID
import com.pisane.pisane.daos.HighscoreDAO
import com.pisane.pisane.daos.TokensDAO
import com.pisane.pisane.databinding.ActivityHighscoresBinding
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesManager
import kotlin.properties.Delegates

class HighscoresActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHighscoresBinding

    private var setId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighscoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setId = intent.getIntExtra(GAME_SET_ID, -1)

        setChipsCount()
        setHighscoresTitle(setId)

        val highscoresList = HighscoreDAO.getHighscores(setId)
        binding.hHighscoresRecyclerView.adapter = HighscoresTableRecyclerViewAdapter(this, highscoresList)

        binding.hBackImageButton.setOnClickListener {
            finish()
        }
    }

    private fun setHighscoresTitle(setId: Int) {
        if (setId == RANDOM_CARDS_ID){
            binding.hTitleTextView.text = "Wyniki Losowe Karty"
        }
        else {
            binding.hTitleTextView.text = "Wyniki Zestaw $setId"
        }
    }

    private fun setChipsCount() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val chipsCount = TokensDAO.getTokensCount(userId!!)
        binding.hChipCountTextView.text = chipsCount.toString()
    }
}
