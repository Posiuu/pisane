package com.example.pisane.controler.background_worker

import android.content.Context
import com.example.pisane.controler.background_worker.common.RequestMethods
import java.net.URLEncoder

class NewHighscoreBackgroundWorker(ctx: Context, urlString: String, requestMethod: RequestMethods) : AbstractBackgroundWorker(ctx, urlString, requestMethod) {
    override fun getPostDataString(params: Array<out String?>): String {
        val postData: String

        val nick = params[0]
        val score = params[1]
        val user_id = params[2]
        val set_id = params[3]
        postData = (URLEncoder.encode("nick", "UTF-8") + "=" + URLEncoder.encode(nick, "UTF-8") + "&"
                + URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode(score, "UTF-8") + "&"
                + URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&"
                + URLEncoder.encode("set_id", "UTF-8") + "=" + URLEncoder.encode(set_id, "UTF-8"))

        return postData
    }
}