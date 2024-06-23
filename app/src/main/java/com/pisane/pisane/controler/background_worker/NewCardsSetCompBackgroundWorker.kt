package com.pisane.pisane.controler.background_worker

import android.content.Context
import com.pisane.pisane.controler.background_worker.common.RequestMethods
import java.net.URLEncoder

class NewCardsSetCompBackgroundWorker(ctx: Context, urlString: String, requestMethod: RequestMethods) : AbstractBackgroundWorker(ctx, urlString, requestMethod) {
    override fun getPostDataString(params: Array<out String?>): String {
        val postData: String

        val deal_number = params[0]
        val cards_order = params[1]
        val set_id = params[2]
        postData = (URLEncoder.encode("deal_number", "UTF-8") + "=" + URLEncoder.encode(deal_number, "UTF-8") + "&"
                + URLEncoder.encode("cards_order", "UTF-8") + "=" + URLEncoder.encode(cards_order, "UTF-8") + "&"
                + URLEncoder.encode("set_id", "UTF-8") + "=" + URLEncoder.encode(set_id, "UTF-8"))

        return postData
    }
}