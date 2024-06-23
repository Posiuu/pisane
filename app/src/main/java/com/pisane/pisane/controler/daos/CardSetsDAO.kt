package com.pisane.pisane.controler.daos

import android.content.Context
import com.pisane.pisane.model.CardSetComponentAllStr
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisane.pisane.consts.card_sets_url
import com.pisane.pisane.consts.is_set_played_url
import com.pisane.pisane.consts.new_cards_set_component_url
import com.pisane.pisane.consts.new_cards_set_url
import com.pisane.pisane.consts.new_played_set_url
import com.pisane.pisane.enums.ResultStatus

class CardSetsDAO {
    companion object {
        //fun getCardsSet(context: Context, set_id: String): List<CardSetComponentAllStr> {
        //    val cardsSet: List<CardSetComponentAllStr>
//
        //    val backgroundWorker = GetCardSetByIdBackgroundWorker(context, card_sets_url, RequestMethods.POST)
        //    backgroundWorker.execute(set_id)
        //    val cardSetsResponse = backgroundWorker.get().toString()
//
        //    val gson = Gson()
        //    val cardsSetType = object : TypeToken<List<CardSetComponentAllStr>>() {}.type
        //    cardsSet = gson.fromJson(cardSetsResponse, cardsSetType)
//
        //    return cardsSet
        //}
//
        //fun newCardsSet(context: Context, set_id: String): Boolean {
        //    var isSuccess = false
//
        //    val backgroundWorker = NewCardsSetBackgroundWorker(context, new_cards_set_url, RequestMethods.POST)
        //    backgroundWorker.execute(set_id)
//
        //    val result = backgroundWorker.get().toString()
        //    when (result){
        //        ResultStatus.SUCCESS.name -> isSuccess = true
        //        else -> isSuccess = false
        //    }
//
        //    return isSuccess
        //}
//
        //fun newCardsSetComponent(context: Context, deal_number: String, cards_order: String, set_id: String): Boolean {
        //    var isSuccess = false
//
        //    val backgroundWorker = NewCardsSetCompBackgroundWorker(context, new_cards_set_component_url, RequestMethods.POST)
        //    backgroundWorker.execute(deal_number, cards_order, set_id)
//
        //    val result = backgroundWorker.get().toString()
        //    when (result){
        //        ResultStatus.SUCCESS.name -> isSuccess = true
        //        else -> isSuccess = false
        //    }
//
        //    return isSuccess
        //}
//
        //fun isSetPlayed(context: Context, user_id: String, set_id: String): Boolean {
        //    var isPlayed = false
//
        //    val backgroundWorker = IsSetPlayedBackgroundWorker(context, is_set_played_url, RequestMethods.POST)
        //    backgroundWorker.execute(user_id, set_id)
//
        //    val result = backgroundWorker.get().toString()
        //    if (result == "1"){
        //        isPlayed = true
        //    }
//
        //    return isPlayed
        //}
//
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