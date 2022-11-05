package com.example.pisane.controler.background_worker

import android.content.Context
import com.example.pisane.controler.background_worker.common.RequestMethods

class GetHighscoresBackgroundWorker(ctx: Context, urlString: String, requestMethod: RequestMethods) : AbstractBackgroundWorker(ctx, urlString, requestMethod) {
    override fun getPostDataString(params: Array<out String?>): String? {
        return null
    }
}