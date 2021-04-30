package com.example.pisane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pisane.adapter.GamesTableRecyclerViewAdapter
import com.example.pisane.data.DataSource
import com.example.pisane.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameScoresList = DataSource().loadGameNames()
        binding.resultsRecyclerView.adapter = GamesTableRecyclerViewAdapter(this, gameScoresList)
    }
}
