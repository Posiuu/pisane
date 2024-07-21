package com.pisane.pisane.daos

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisane.pisane.consts.get_highscores_url
import com.pisane.pisane.consts.new_highscore_url
import com.pisane.pisane.data.highscoresEmptyList
import com.pisane.pisane.dtos.HighscoreDTO
import com.pisane.pisane.enums.ResultStatus
import com.pisane.pisane.model.Highscore
import com.pisane.pisane.model.HighscoreRecordType
import com.vishnusivadas.advanced_httpurlconnection.PutData

class HighscoreDAO {
    companion object {
        fun getHighscores(setId: Int = 0): MutableList<Highscore> {
            val highscoresList = highscoresEmptyList.map{it.copy()} as MutableList

            val putData = PutData(
                get_highscores_url,
                "POST",
                arrayOf("setId"),
                arrayOf(setId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                val result = putData.result
                val mappedHighscoresResponse: List<HighscoreDTO>? = Gson().fromJson(result, object : TypeToken<List<HighscoreDTO>>() {}.type)

                if (mappedHighscoresResponse != null) {
                    var isBright = true
                    for ((place, highscoreDTO) in mappedHighscoresResponse.withIndex()) {
                        val recordType = if (isBright) HighscoreRecordType.BRIGHT else HighscoreRecordType.DARK
                        val placeString = (place + 1).toString()
                        val highscore = Highscore(recordType, placeString, highscoreDTO.username, highscoreDTO.score.toString(), setId.toString())
                        highscoresList.add(highscore)
                        isBright = !isBright
                    }
                }
            }

            return highscoresList
        }

        fun newHighscore(score: Int, userId: Int, setId: Int): Boolean {
            val putData = PutData(
                new_highscore_url,
                "POST",
                arrayOf("score", "userId", "setId"),
                arrayOf(score.toString(), userId.toString(), setId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                if (putData.result == ResultStatus.SUCCESS.name){
                    return true
                }
            }

            return false
        }
    }
}