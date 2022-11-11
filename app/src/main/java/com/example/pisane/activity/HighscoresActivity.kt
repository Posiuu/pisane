package com.example.pisane.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.adapter.*
import com.example.pisane.consts.GAME_SET_ID
import com.example.pisane.controler.DAOs.HighscoreDAO
import com.example.pisane.databinding.ActivityHighscoresBinding
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
