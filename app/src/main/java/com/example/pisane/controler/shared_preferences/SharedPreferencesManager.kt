package com.example.pisane.controler.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

const val PREF_FILE_NAME = "PREF_FILE_NAME"
const val PREF_USERNAME = "PREF_USERNAME"
const val PREF_USER_ID = "PREF_USER_ID"
const val PREF_GAME_RANDOM = "PREF_GAME_RANDOM"
const val PREF_GAME1 = "PREF_GAME1"
const val PREF_GAME2 = "PREF_GAME2"
const val PREF_GAME3 = "PREF_GAME3"
const val PREF_GAME4 = "PREF_GAME4"
const val PREF_GAME5 = "PREF_GAME5"
const val PREF_GAME6 = "PREF_GAME6"
const val PREF_GAME7 = "PREF_GAME7"
const val PREF_GAME8 = "PREF_GAME8"
const val PREF_GAME9 = "PREF_GAME9"
const val PREF_GAME10 = "PREF_GAME10"

class SharedPreferencesManager (context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,
        Context.MODE_PRIVATE)

    fun <T> putObject(objectToPut: T, key: String) {
        val objectJson = GsonBuilder().create().toJson(objectToPut)
        sharedPreferences.edit().putString(key, objectJson).apply()
    }

    inline fun <reified T> getObject(key: String): T? {
        val value = sharedPreferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}