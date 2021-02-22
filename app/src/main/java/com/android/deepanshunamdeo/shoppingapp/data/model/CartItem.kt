package com.android.deepanshunamdeo.shoppingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item_table")
data class CartItem(
    @PrimaryKey val cartItemName: String,
    val cartItemQuantity: Int,
    val cartItemPrice: Int
) {

}