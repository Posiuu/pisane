package com.example.pisane.data

import com.example.pisane.model.Highscore
import com.example.pisane.model.HighscoreRecordType

const val PLACE_HEADER = "Miejsce"
const val NICK_HEADER = "Nick"
const val SCORE_HEADER = "Wynik"

val highscoresEmptyList = listOf(
        Highscore(HighscoreRecordType.HEADER, PLACE_HEADER, NICK_HEADER, SCORE_HEADER, "0")
)
