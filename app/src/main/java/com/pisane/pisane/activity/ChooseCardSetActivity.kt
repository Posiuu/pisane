package com.pisane.pisane.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.consts.GAME_SET_ID
import com.pisane.pisane.daos.CardSetsDAO
import com.pisane.pisane.daos.TokensDAO
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesHelper
import com.pisane.pisane.shared_preferences.SharedPreferencesManager
import com.pisane.pisane.databinding.ActivityChooseCardSetBinding

class ChooseCardSetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseCardSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseCardSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setChipsCount()

        binding.ccSet1Button.setOnClickListener {
            setButtonHandling(1)
        }

        binding.ccSet2Button.setOnClickListener {
            setButtonHandling(2)
        }

        binding.ccSet3Button.setOnClickListener {
            setButtonHandling(3)
        }

        binding.ccSet4Button.setOnClickListener {
            setButtonHandling(4)
        }

        binding.ccSet5Button.setOnClickListener {
            setButtonHandling(5)
        }

        binding.ccSet6Button.setOnClickListener {
            setButtonHandling(6)
        }

        binding.ccSet7Button.setOnClickListener {
            setButtonHandling(7)
        }

        binding.ccSet8Button.setOnClickListener {
            setButtonHandling(8)
        }

        binding.ccSet9Button.setOnClickListener {
            setButtonHandling(9)
        }

        binding.ccSet10Button.setOnClickListener {
            setButtonHandling(10)
        }

        binding.ccBackImageButton.setOnClickListener {
            finish()
        }
    }

    private fun setButtonHandling(setId: Int) {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val isSetPlayed = CardSetsDAO.isSetPlayed(setId, userId!!)
        val loadedGame = SharedPreferencesHelper.getLoadedGame(this, setId)

        if (isSetPlayed && loadedGame == null) {
            val intent = Intent(this, HighscoresActivity::class.java)
            intent.putExtra(GAME_SET_ID, setId)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GAME_SET_ID, setId)
            startActivity(intent)
        }
    }

    private fun setChipsCount() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val chipsCount = TokensDAO.getTokensCount(userId!!)
        binding.ccChipCountTextView.text = chipsCount.toString()
    }
}
