package com.example.pisane.controler.background_worker

import android.content.Context
import com.example.pisane.controler.background_worker.common.RequestMethods
import java.net.URLEncoder

class RegisterBackgroundWorker(ctx: Context, urlString: String, requestMethod: RequestMethods) : AbstractBackgroundWorker(ctx, urlString, requestMethod) {
    override fun getPostDataString(params: Array<out String?>): String? {
        val postData: String

        val userName = params[0]
        val password = params[1]
        postData = (URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8"))

        return postData
    }
}