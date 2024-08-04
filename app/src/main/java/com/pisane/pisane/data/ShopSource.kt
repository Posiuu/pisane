package com.pisane.pisane.data

import com.pisane.pisane.R
import com.pisane.pisane.model.ShopItem

object ShopItemId {
        const val BACKGROUND_DEFAULT = 1
        const val BACKGROUND_GREEN = 2
        const val BACKGROUND_WOOD = 3
        const val BACKGROUND_CHIPS = 4
        const val CARDS_DEFAULT = 5
        const val CARDS1 = 6
        const val CARDS2 = 7
        const val CARDS3 = 8
}

object ShopGroupId {
        const val BACKGROUND = 1
        const val CARDS = 2
}

object ShopItems {
        val BACKGROUND_DEFAULT = ShopItem(ShopItemId.BACKGROUND_DEFAULT, ShopGroupId.BACKGROUND, 0, R.drawable.game_background)
        val BACKGROUND_GREEN = ShopItem(ShopItemId.BACKGROUND_GREEN, ShopGroupId.BACKGROUND, 30000, R.drawable.game_background_green)
        val BACKGROUND_WOOD = ShopItem(ShopItemId.BACKGROUND_WOOD, ShopGroupId.BACKGROUND, 60000, R.drawable.game_background_wood)
        val BACKGROUND_CHIPS = ShopItem(ShopItemId.BACKGROUND_CHIPS, ShopGroupId.BACKGROUND, 90000, R.drawable.game_background_chips)
        val CARDS_DEFAULT = ShopItem(ShopItemId.CARDS_DEFAULT, ShopGroupId.CARDS, 0, R.drawable.deck)
        val CARDS1 = ShopItem(ShopItemId.CARDS1, ShopGroupId.CARDS, 20000, R.drawable.deck1)
        val CARDS2 = ShopItem(ShopItemId.CARDS2, ShopGroupId.CARDS, 40000, R.drawable.deck2)
        val CARDS3 = ShopItem(ShopItemId.CARDS3, ShopGroupId.CARDS, 60000, R.drawable.deck3)
}

val ShopItemsMap = mapOf(
        ShopItemId.BACKGROUND_DEFAULT to ShopItems.BACKGROUND_DEFAULT,
        ShopItemId.BACKGROUND_GREEN to ShopItems.BACKGROUND_GREEN,
        ShopItemId.BACKGROUND_WOOD to ShopItems.BACKGROUND_WOOD,
        ShopItemId.BACKGROUND_CHIPS to ShopItems.BACKGROUND_CHIPS,
        ShopItemId.CARDS_DEFAULT to ShopItems.CARDS_DEFAULT,
        ShopItemId.CARDS1 to ShopItems.CARDS1,
        ShopItemId.CARDS2 to ShopItems.CARDS2,
        ShopItemId.CARDS3 to ShopItems.CARDS3,
)