package com.pisane.pisane.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pisane.pisane.adapter.*
import com.pisane.pisane.consts.GAME_SET_ID
import com.pisane.pisane.controler.daos.HighscoreDAO
import com.pisane.pisane.databinding.ActivityHighscoresBinding
import kotlin.properties.Delegates

class HighscoresActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHighscoresBinding

    private var setId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighscoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setId = intent.getIntExtra(GAME_SET_ID, -1)

        val highscoresList = HighscoreDAO.getHighscores(this, setId.toString())
        binding.hHighscoresRecyclerView.adapter = HighscoresTableRecyclerViewAdapter(this, highscoresList)
    }
}
