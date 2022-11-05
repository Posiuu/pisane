package com.example.pisane.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.adapter.*
import com.example.pisane.controler.highscore.HighscoreControler
import com.example.pisane.databinding.ActivityHighscoresBinding

class HighscoresActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHighscoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighscoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val highscoresList = HighscoreControler.getHighscores(this)
        binding.hHighscoresRecyclerView.adapter = HighscoresTableRecyclerViewAdapter(this, highscoresList)
    }
}
