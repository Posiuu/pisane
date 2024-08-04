package com.pisane.pisane.daos

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pisane.pisane.consts.get_shop_item_purchases_url
import com.pisane.pisane.consts.new_shop_item_selected_url
import com.pisane.pisane.dtos.ShopItemPurchaseDTO
import com.pisane.pisane.enums.ResultStatus
import com.vishnusivadas.advanced_httpurlconnection.PutData

class ShopDAO {
    companion object {
        fun getShopItemPurchases(userId: Int): List<ShopItemPurchaseDTO>? {
            val putData = PutData(
                get_shop_item_purchases_url,
                "POST",
                arrayOf("userId"),
                arrayOf(userId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                val result = putData.result
                val shopItemPurchaseDTO: List<ShopItemPurchaseDTO>? = Gson().fromJson(result, object : TypeToken<List<ShopItemPurchaseDTO>>() {}.type)
                return shopItemPurchaseDTO
            }

            return null
        }

        fun newShopItemSelected(itemId: Int, userId: Int): Boolean {
            val putData = PutData(
                new_shop_item_selected_url,
                "POST",
                arrayOf("itemId", "userId"),
                arrayOf(itemId.toString(), userId.toString())
            )
            if (putData.startPut() && putData.onComplete()) {
                if (putData.result == ResultStatus.SUCCESS.name){
                    return true
                }
            }

            return false
        }
    }
}