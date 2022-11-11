package com.example.pisane.controler.daos

import android.content.Context
import com.example.pisane.controler.background_worker.GetUserIdAndPassByName
import com.example.pisane.controler.background_worker.common.*
import com.example.pisane.model.UserIdAndPassword
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginDAO {
    companion object {
        fun getUserIdAndPassByName(context: Context, username: String): UserIdAndPassword {
            val userIdAndPassword: UserIdAndPassword

            val backgroundWorker = GetUserIdAndPassByName(context, passwordhash_url, RequestMethods.POST)
            backgroundWorker.execute(username)
            val response = backgroundWorker.get().toString()

            val gson = Gson()
            val responseType = object : TypeToken<UserIdAndPassword>() {}.type
            userIdAndPassword = gson.fromJson(response, responseType)

            return userIdAndPassword
        }
    }
}