package com.android.deepanshunamdeo.shoppingapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem
import com.android.deepanshunamdeo.shoppingapp.data.model.ShoppingItem
import com.android.deepanshunamdeo.shoppingapp.data.model.dao.CartIemDao
import com.android.deepanshunamdeo.shoppingapp.data.model.dao.ShoppingItemDao

@Database(entities = [ShoppingItem::class, CartItem::class], version = 2, exportSchema = false)
abstract class ShoppingAppDatabase : RoomDatabase() {
    abstract fun getShoppingItemDao(): ShoppingItemDao
    abstract fun getCartItemDao(): CartIemDao
}