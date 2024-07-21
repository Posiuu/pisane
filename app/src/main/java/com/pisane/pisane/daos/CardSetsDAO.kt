package com.pisane.pisane.daos

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisane.pisane.consts.get_card_set_url
import com.pisane.pisane.consts.is_set_played_url
import com.pisane.pisane.consts.new_set_played_url
import com.pisane.pisane.enums.ResultStatus
import com.pisane.pisane.model.CardSetComponent
import com.vishnusivadas.advanced_httpurlconnection.PutData

class CardSetsDAO {
    companion object {
        fun getCardsSet(setId: Int): List<CardSetComponent>? {
            val putData = PutData(
                get_card_set_url,
                "POST",
                arrayOf("setId"),
                arrayOf(setId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                val result = putData.result
                val cardsSet: List<CardSetComponent>? = Gson().fromJson(result, object : TypeToken<List<CardSetComponent>>() {}.type)
                return cardsSet
            }

            return null
        }

        fun isSetPlayed(setId: Int, userId: Int): Boolean {
            val putData = PutData(
                is_set_played_url,
                "POST",
                arrayOf("setId", "userId"),
                arrayOf(setId.toString(), userId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                if (putData.result == ResultStatus.SUCCESS.name){
                    return true
                }
            }

            return false
        }

        fun newSetPlayed(setId: Int, userId: Int): Boolean {
            val putData = PutData(
                new_set_played_url,
                "POST",
                arrayOf("setId", "userId"),
                arrayOf(setId.toString(), userId.toString())
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