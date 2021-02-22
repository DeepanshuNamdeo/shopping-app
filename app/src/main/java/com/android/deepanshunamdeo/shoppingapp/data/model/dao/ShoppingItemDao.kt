package com.android.deepanshunamdeo.shoppingapp.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.deepanshunamdeo.shoppingapp.data.model.ShoppingItem

@Dao
interface ShoppingItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_item_table")
    fun getAllShoppingItem(): LiveData<List<ShoppingItem>>
}