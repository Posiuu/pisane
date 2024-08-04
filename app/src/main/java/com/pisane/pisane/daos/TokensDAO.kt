package com.pisane.pisane.daos

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisane.pisane.consts.get_last_tokens_activation_url
import com.pisane.pisane.consts.get_tokens_count_url
import com.pisane.pisane.consts.new_tokens_activation_url
import com.pisane.pisane.consts.update_tokens_url
import com.pisane.pisane.dtos.TokensActivationDTO
import com.pisane.pisane.enums.ResultStatus
import com.vishnusivadas.advanced_httpurlconnection.PutData

class TokensDAO {
    companion object {
        fun newTokenActivation(userId: Int): Boolean {
            val putData = PutData(
                new_tokens_activation_url,
                "POST",
                arrayOf("userId"),
                arrayOf(userId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                if (putData.result == ResultStatus.SUCCESS.name){
                    return true
                }
            }

            return false
        }

        fun getLastTokensActivation(userId: Int): TokensActivationDTO? {
            val putData = PutData(
                get_last_tokens_activation_url,
                "POST",
                arrayOf("userId"),
                arrayOf(userId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                val result = putData.result
                val tokensActivationDTO: TokensActivationDTO? = Gson().fromJson(result, object : TypeToken<TokensActivationDTO?>() {}.type)
                return tokensActivationDTO
            }

            return null
        }

        fun updateUserTokens(tokens: Int, userId: Int): Int? {
            val putData = PutData(
                update_tokens_url,
                "POST",
                arrayOf("tokens", "userId"),
                arrayOf(tokens.toString(), userId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                val result = putData.result
                return result.toInt()
            }

            return null
        }

        fun getTokensCount(userId: Int): Int {
            val putData = PutData(
                get_tokens_count_url,
                "POST",
                arrayOf("userId"),
                arrayOf(userId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                val result = putData.result
                !result.isNullOrEmpty()
                return if(!result.isNullOrEmpty()) result.toInt() else 0
            }
            return 0
        }
    }
}