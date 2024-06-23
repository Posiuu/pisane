package com.pisane.pisane.data

import com.pisane.pisane.model.Highscore
import com.pisane.pisane.model.HighscoreRecordType

const val PLACE_HEADER = "Miejsce"
const val NICK_HEADER = "Nick"
const val SCORE_HEADER = "Wynik"

val highscoresEmptyList = listOf(
        Highscore(HighscoreRecordType.HEADER, PLACE_HEADER, NICK_HEADER, SCORE_HEADER, "0")
)
