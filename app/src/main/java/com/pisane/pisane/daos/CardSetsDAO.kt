package com.pisane.pisane.daos

import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisane.pisane.consts.get_card_set_url
import com.pisane.pisane.consts.is_set_played_url
import com.pisane.pisane.consts.signup_url
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

        //fun newSetPlayed(context: Context, user_id: String, set_id: String): Boolean {
        //    var isSuccess = false
//
        //    val backgroundWorker = NewPlayedSetBackgroundWorker(context, new_played_set_url, RequestMethods.POST)
        //    backgroundWorker.execute(user_id, set_id)
//
        //    val result = backgroundWorker.get().toString()
        //    when (result){
        //        ResultStatus.SUCCESS.name -> isSuccess = true
        //        else -> isSuccess = false
        //    }
//
        //    return isSuccess
        //}
    }
}