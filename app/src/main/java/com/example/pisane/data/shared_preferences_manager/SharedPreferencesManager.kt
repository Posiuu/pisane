package com.example.pisane.data.shared_preferences_manager

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

const val PREF_FILE_NAME = "PREF_FILE_NAME"
const val PREF_GAME = "PREF_GAME"
const val PREF_USERNAME = "PREF_USERNAME"

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