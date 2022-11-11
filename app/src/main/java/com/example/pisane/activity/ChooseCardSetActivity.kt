package com.example.pisane.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.consts.GAME_SET_ID
import com.example.pisane.controler.daos.CardSetsDAO
import com.example.pisane.controler.shared_preferences.PREF_USER_ID
import com.example.pisane.controler.shared_preferences.SharedPreferencesHelper
import com.example.pisane.controler.shared_preferences.SharedPreferencesManager
import com.example.pisane.databinding.ActivityChooseCardSetBinding

class ChooseCardSetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseCardSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseCardSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }

    private fun setButtonHandling(setId: Int) {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val isSetPlayed = CardSetsDAO.isSetPlayed(this, userId.toString(), setId.toString())
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
}
