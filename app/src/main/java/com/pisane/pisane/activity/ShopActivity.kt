package com.pisane.pisane.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.pisane.pisane.R
import com.pisane.pisane.daos.ShopDAO
import com.pisane.pisane.daos.TokensDAO
import com.pisane.pisane.data.ShopGroupId
import com.pisane.pisane.data.ShopItems
import com.pisane.pisane.databinding.ActivityShopBinding
import com.pisane.pisane.dtos.ShopItemPurchaseDTO
import com.pisane.pisane.model.ShopItem
import com.pisane.pisane.shared_preferences.PREF_USER_ID
import com.pisane.pisane.shared_preferences.SharedPreferencesManager

class ShopActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopBinding

    private var userId: Int? = null
    private var userTokens: Int? = null
    private var shopItemPurchases: List<ShopItemPurchaseDTO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferencesManager = SharedPreferencesManager(this)
        userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        setShopItems()
        setChipsCount()

        binding.shopDefaultBackgroundButton.setOnClickListener {
            setButtonHandling(ShopItems.BACKGROUND_DEFAULT, null, binding.shopDefaultBackgroundTick)
        }
        binding.shopGreenBackgroundButton.setOnClickListener {
            setButtonHandling(ShopItems.BACKGROUND_GREEN, binding.shopGreenBackgroundText, binding.shopGreenBackgroundTick)
        }
        binding.shopWoodBackgroundButton.setOnClickListener {
            setButtonHandling(ShopItems.BACKGROUND_WOOD, binding.shopWoodBackgroundText, binding.shopWoodBackgroundTick)
        }
        binding.shopChipsBackgroundButton.setOnClickListener {
            setButtonHandling(ShopItems.BACKGROUND_CHIPS, binding.shopChipsBackgroundText, binding.shopChipsBackgroundTick)
        }
        binding.shopDeckButton.setOnClickListener {
            setButtonHandling(ShopItems.CARDS_DEFAULT, null, binding.shopDefaultDeckTick)
        }
        binding.shopDeck1Button.setOnClickListener {
            setButtonHandling(ShopItems.CARDS1, binding.shopDeck1Text, binding.shopDeck1Tick)
        }
        binding.shopDeck2Button.setOnClickListener {
            setButtonHandling(ShopItems.CARDS2, binding.shopDeck2Text, binding.shopDeck2Tick)
        }
        binding.shopDeck3Button.setOnClickListener {
            setButtonHandling(ShopItems.CARDS3, binding.shopDeck3Text, binding.shopDeck3Tick)
        }
        binding.shopBackImageButton.setOnClickListener {
            MediaPlayer.create(this, R.raw.sound_go_back).start()
            finish()
        }
    }

    private fun setShopItems() {
        userTokens = TokensDAO.getTokensCount(userId!!)
        shopItemPurchases = ShopDAO.getShopItemPurchases(userId!!)

        setShopItem(ShopItems.BACKGROUND_DEFAULT, null, binding.shopDefaultBackgroundTick)
        setShopItem(ShopItems.BACKGROUND_GREEN, binding.shopGreenBackgroundText, binding.shopGreenBackgroundTick)
        setShopItem(ShopItems.BACKGROUND_WOOD, binding.shopWoodBackgroundText, binding.shopWoodBackgroundTick)
        setShopItem(ShopItems.BACKGROUND_CHIPS, binding.shopChipsBackgroundText, binding.shopChipsBackgroundTick)
        setShopItem(ShopItems.CARDS_DEFAULT, null, binding.shopDefaultDeckTick)
        setShopItem(ShopItems.CARDS1, binding.shopDeck1Text, binding.shopDeck1Tick)
        setShopItem(ShopItems.CARDS2, binding.shopDeck2Text, binding.shopDeck2Tick)
        setShopItem(ShopItems.CARDS3, binding.shopDeck3Text, binding.shopDeck3Tick)
    }

    private fun setShopItem(shopItem: ShopItem, textView: TextView?, tick: ImageView) {
        tick.visibility = View.INVISIBLE
        textView?.text = ""
        if (textView == null && shopItemPurchases?.any{it.group_id == shopItem.groupId && it.is_selected == 1} != true){
            tick.visibility = View.VISIBLE
        }
        else if (shopItemPurchases?.any{it.item_id == shopItem.id && it.is_selected == 1} == true) {
            tick.visibility = View.VISIBLE
        }
        else if (textView != null && shopItemPurchases?.any{it.item_id == shopItem.id} != true) {
            textView.text = shopItem.price.toString()
            if (userTokens!! >= shopItem.price){
                textView.setTextColor("#008000".toColorInt())
            }
            else {
                textView.setTextColor("#FF0000".toColorInt())
            }
        }
    }

    private fun setButtonHandling(shopItem: ShopItem, textView: TextView?, tick: ImageView) {
        if (tick.visibility == View.VISIBLE){
            MediaPlayer.create(this, R.raw.sound_error).start()
            Toast.makeText(this,
                "Przedmiot jest już wybrany", Toast.LENGTH_SHORT).show()
        }
        else if (textView?.text.isNullOrEmpty()){
            MediaPlayer.create(this, R.raw.sound_button_click).start()
            ShopDAO.newShopItemSelected(shopItem.id, userId!!)

            if (shopItem.groupId == ShopGroupId.BACKGROUND){
                binding.shopDefaultBackgroundTick.visibility = View.INVISIBLE
                binding.shopGreenBackgroundTick.visibility = View.INVISIBLE
                binding.shopWoodBackgroundTick.visibility = View.INVISIBLE
                binding.shopChipsBackgroundTick.visibility = View.INVISIBLE
            }
            else if (shopItem.groupId == ShopGroupId.CARDS){
                binding.shopDefaultDeckTick.visibility = View.INVISIBLE
                binding.shopDeck1Tick.visibility = View.INVISIBLE
                binding.shopDeck2Tick.visibility = View.INVISIBLE
                binding.shopDeck3Tick.visibility = View.INVISIBLE
            }
            tick.visibility = View.VISIBLE

            Toast.makeText(this,
                "Wybrano przedmiot", Toast.LENGTH_SHORT).show()
        }
        else if (userTokens!! >= shopItem.price){
            MediaPlayer.create(this, R.raw.sound_item_purchased).start()
            ShopDAO.newShopItemSelected(shopItem.id, userId!!)
            TokensDAO.updateUserTokens(shopItem.price * -1, userId!!)
            setShopItems()
            setChipsCount()

            Toast.makeText(this,
                "Kupiono przedmiot", Toast.LENGTH_SHORT).show()
        }
        else {
            MediaPlayer.create(this, R.raw.sound_error).start()
            Toast.makeText(this,
                "Za mało żetonów", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setChipsCount() {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val userId = sharedPreferencesManager.getObject<Int>(PREF_USER_ID)

        val chipsCount = TokensDAO.getTokensCount(userId!!)
        binding.shopChipCountTextView.text = chipsCount.toString()
    }
}
