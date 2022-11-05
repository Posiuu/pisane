package com.example.pisane.controler.highscore

import android.content.Context
import com.example.pisane.controler.background_worker.GetHighscoresBackgroundWorker
import com.example.pisane.controler.background_worker.NewHighscoreBackgroundWorker
import com.example.pisane.controler.background_worker.common.*
import com.example.pisane.data.highscoresEmptyList
import com.example.pisane.model.Highscore
import com.example.pisane.model.HighscoreRecordType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HighscoreControler {
    companion object {
        fun getHighscores(context: Context): MutableList<Highscore> {
            val highscoresList = highscoresEmptyList.map{it.copy()} as MutableList

            val backgroundWorker = GetHighscoresBackgroundWorker(context, highscores_url, RequestMethods.POST)
            backgroundWorker.execute()
            val highscoresResponse = backgroundWorker.get().toString()

            val gson = Gson()
            val highscoresListType = object : TypeToken<List<Highscore>>() {}.type
            val mappedHighscoresResponse: List<Highscore> = gson.fromJson(highscoresResponse, highscoresListType)
            val mappedHighscoresSorted = mappedHighscoresResponse.sortedByDescending { it.score.toInt() }

            var isBright = true
            for ((place, mappedHighscore) in mappedHighscoresSorted.withIndex()) {
                val placeString = (place + 1).toString()
                val recordType = if (isBright) HighscoreRecordType.BRIGHT else HighscoreRecordType.DARK
                val highscore = Highscore(recordType, placeString, mappedHighscore.nick, mappedHighscore.score)
                highscoresList.add(highscore)
                isBright = !isBright
            }

            return highscoresList
        }

        fun newHighscore(context: Context, nick: String, score: String): Boolean {
            var isSuccess = false

            val backgroundWorker = NewHighscoreBackgroundWorker(context, new_highscore_url, RequestMethods.POST)
            backgroundWorker.execute(nick, score)

            val result = backgroundWorker.get().toString()
            when (result){
                ResultStatus.SUCCESS.name -> isSuccess = true
                ResultStatus.FAIL.name -> isSuccess = false
            }

            return isSuccess
        }
    }
}