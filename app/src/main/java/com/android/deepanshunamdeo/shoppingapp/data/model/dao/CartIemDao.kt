package com.android.deepanshunamdeo.shoppingapp.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem

@Dao
interface CartIemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSingleItemToCart(cartItem: CartItem)

    @Delete
    suspend fun deleteSingleItemFromCart(cartItem: CartItem)

    @Query("DELETE FROM cart_item_table")
    suspend fun deleteAllItemFromCart()

    @Query("SELECT * FROM cart_item_table")
    fun getAllCartItems(): LiveData<List<CartItem>>

    @Query("select sum(cartItemQuantity) from cart_item_table  ")
    fun cartQuantity(): LiveData<Int>

    @Update
    suspend fun updateItemInCart(cartItem: CartItem)
}