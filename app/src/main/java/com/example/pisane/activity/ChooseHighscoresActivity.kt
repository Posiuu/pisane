package com.example.pisane.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.consts.GAME_SET_ID
import com.example.pisane.consts.RANDOM_CARDS_ID
import com.example.pisane.databinding.ActivityChooseHighscoresBinding

class ChooseHighscoresActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseHighscoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseHighscoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chRandomSetButton.setOnClickListener {
            setButtonHandling(RANDOM_CARDS_ID)
        }

        binding.chSet1Button.setOnClickListener {
            setButtonHandling(1)
        }

        binding.chSet2Button.setOnClickListener {
            setButtonHandling(2)
        }

        binding.chSet3Button.setOnClickListener {
            setButtonHandling(3)
        }

        binding.chSet4Button.setOnClickListener {
            setButtonHandling(4)
        }

        binding.chSet5Button.setOnClickListener {
            setButtonHandling(5)
        }

        binding.chSet6Button.setOnClickListener {
            setButtonHandling(6)
        }

        binding.chSet7Button.setOnClickListener {
            setButtonHandling(7)
        }

        binding.chSet8Button.setOnClickListener {
            setButtonHandling(8)
        }

        binding.chSet9Button.setOnClickListener {
            setButtonHandling(9)
        }

        binding.chSet10Button.setOnClickListener {
            setButtonHandling(10)
        }
    }

    private fun setButtonHandling(setId: Int) {
        val intent = Intent(this, HighscoresActivity::class.java)
        intent.putExtra(GAME_SET_ID, setId)
        startActivity(intent)
    }
}
