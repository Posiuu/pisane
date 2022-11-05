package com.example.pisane.controler.game

import android.content.Context
import com.example.pisane.controler.shared_preferences_manager.PREF_GAME
import com.example.pisane.controler.shared_preferences_manager.SharedPreferencesManager

class GameManager {
    companion object {
        fun getLoadedGame(context: Context): Game? {
            val loadedGame: Game?

            val sharedPreferencesManager = SharedPreferencesManager(context)
            loadedGame = sharedPreferencesManager.getObject<Game>(PREF_GAME)

            return loadedGame
        }
    }
}