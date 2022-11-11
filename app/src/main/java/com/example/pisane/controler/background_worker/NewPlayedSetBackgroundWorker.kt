package com.example.pisane.controler.background_worker

import android.content.Context
import com.example.pisane.controler.background_worker.common.RequestMethods
import java.net.URLEncoder

class NewPlayedSetBackgroundWorker(ctx: Context, urlString: String, requestMethod: RequestMethods) : AbstractBackgroundWorker(ctx, urlString, requestMethod) {
    override fun getPostDataString(params: Array<out String?>): String {
        val postData: String

        val userId = params[0]
        val setId = params[1]
        postData = (URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(userId, "UTF-8") + "&"
                + URLEncoder.encode("set_id", "UTF-8") + "=" + URLEncoder.encode(setId, "UTF-8"))

        return postData
    }
}