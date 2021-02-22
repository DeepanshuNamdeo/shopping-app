package com.android.deepanshunamdeo.shoppingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_item_table")
data class ShoppingItem(
    @PrimaryKey var id: Int = 0,
    val name: String,
    val description: String,
    val image_url: String,
    val price: Int
) {

}