package com.pisane.pisane.controler.background_worker

import android.content.Context
import com.pisane.pisane.controler.background_worker.common.RequestMethods
import java.net.URLEncoder

class GetHighscoresBackgroundWorker(ctx: Context, urlString: String, requestMethod: RequestMethods) : AbstractBackgroundWorker(ctx, urlString, requestMethod) {
    override fun getPostDataString(params: Array<out String?>): String {
        val postData: String

        val set_id = params[0]
        postData = (URLEncoder.encode("set_id", "UTF-8") + "=" + URLEncoder.encode(set_id, "UTF-8"))

        return postData
    }
}