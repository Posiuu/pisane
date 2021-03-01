package com.example.pisane

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pisane.databinding.ActivityMainMenuBinding


class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    private class Coords{
        var x: Float = 0.0f
        var y: Float = 0.0f
    }
    private var mainMenuLastClickedCoords: Coords = Coords()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingsImageButton.setOnClickListener {
            settingsButtonHandling()
        }

        binding.mainMenuImageButton.setOnTouchListener(mainMenuTouchListener)
        binding.mainMenuImageButton.setOnClickListener {
            mainMenuButtonHandling()
        }

        binding.collectFreeChipsButton.setOnClickListener {
            collectFreeChipsButtonHandling()
        }

        binding.shopImageButton.setOnClickListener {
            shoppButtonHandling()
        }
    }

    private fun settingsButtonHandling() {
        Toast.makeText(
                this, "Ustawienia będą wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
    }

    @SuppressLint("ClickableViewAccessibility")
    private var mainMenuTouchListener: View.OnTouchListener = View.OnTouchListener { _, event ->
        mainMenuLastClickedCoords.x = event.x
        mainMenuLastClickedCoords.y = event.y

        // let the touch event pass on to whoever needs it
        false
    }

    private fun mainMenuButtonHandling() {
        //Main menu is one imageButton and We need to split it into 4 buttons.
        //This buttons areas are split by slanting lines.
        //We have to calculate each area to determine which button was clicked.
        //To do that We treat each line as a linear function.
        //We calculate each line y value given clicked x position,
        //compare this with clicked y position and see between which lines it is.

        //Main menu Image was scaled to fit screen
        //This is why We need to scale our mainMenuLastClickedCoords
        //Original image size: 720x1094
        val scalingValueX = 720.0 / binding.mainMenuImageButton.width
        val scalingValueY = 1094.0 / binding.mainMenuImageButton.height
        val clickedX = mainMenuLastClickedCoords.x * scalingValueX
        val clickedY = mainMenuLastClickedCoords.y * scalingValueY

        val line1y = ((0.135 * clickedX) + 219).toInt()
        val line2y = ((0.135 * clickedX) + 476).toInt()
        val line3y = ((0.135 * clickedX) + 739).toInt()
        val line4y = ((0.135 * clickedX) + 999).toInt()

        when(clickedY.toInt()){
            in 0..line1y -> randomCardsButtonHandling()
            in line1y..line2y -> cardsSetsButtonHandling()
            in line2y..line3y -> scoresButtonHandling()
            in line3y..line4y -> rulesButtonHandling()
        }
    }

    private fun randomCardsButtonHandling() {
        Toast.makeText(
                this, "Tryb gry losowe kary będzie wkrótce dostępny",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun cardsSetsButtonHandling() {
        Toast.makeText(
                this, "Tryb gry zestawy kart będzie wkrótce dostępny",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun scoresButtonHandling() {
        Toast.makeText(
                this, "Wyniki będą wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun rulesButtonHandling() {
        Toast.makeText(
                this, "Zasady będą wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun collectFreeChipsButtonHandling() {
        Toast.makeText(
                this, "Odbieranie i zbieranie rzetonów będzie wkrótce dostępne",
                Toast.LENGTH_SHORT
        ).show()
    }

    private fun shoppButtonHandling() {
        Toast.makeText(
                this, "Sklep będzie wkrótce dostępny",
                Toast.LENGTH_SHORT
        ).show()
    }
}