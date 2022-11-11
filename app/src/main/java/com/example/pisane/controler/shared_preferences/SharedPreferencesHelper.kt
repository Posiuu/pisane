package com.example.pisane.controler.shared_preferences

import android.content.Context
import com.example.pisane.consts.RANDOM_CARDS_ID
import com.example.pisane.controler.game.Game

class SharedPreferencesHelper {
    companion object {
        val gamePrefsList = listOf(
            PREF_GAME_RANDOM,
            PREF_GAME1,
            PREF_GAME2,
            PREF_GAME3,
            PREF_GAME4,
            PREF_GAME5,
            PREF_GAME6,
            PREF_GAME7,
            PREF_GAME8,
            PREF_GAME9,
            PREF_GAME10
        )

        fun getLoadedGame(context: Context, setId: Int): Game? {
            val loadedGame: Game?

            val sharedPreferencesManager = SharedPreferencesManager(context)

            val prefString = getPrefStrBySetId(setId)
            loadedGame = if (prefString.isNotBlank()) {
                sharedPreferencesManager.getObject<Game>(prefString)
            } else {
                null
            }

            return loadedGame
        }

        fun deleteAllSavedGames(context: Context) {
            val sharedPreferencesManager = SharedPreferencesManager(context)

            gamePrefsList.forEach { prefString ->
                sharedPreferencesManager.putObject(null, prefString)
            }
        }

        fun deleteSavedGame(context: Context, setId: Int) {
            val sharedPreferencesManager = SharedPreferencesManager(context)
            val prefString = getPrefStrBySetId(setId)
            sharedPreferencesManager.putObject(null, prefString)
        }

        fun getPrefStrBySetId(setId: Int): String {
            val prefString: String

            prefString = when (setId) {
                RANDOM_CARDS_ID -> PREF_GAME_RANDOM
                1 -> PREF_GAME1
                2 -> PREF_GAME2
                3 -> PREF_GAME3
                4 -> PREF_GAME4
                5 -> PREF_GAME5
                6 -> PREF_GAME6
                7 -> PREF_GAME7
                8 -> PREF_GAME8
                9 -> PREF_GAME9
                10 -> PREF_GAME10
                else -> ""
            }

            return prefString
        }
    }
}