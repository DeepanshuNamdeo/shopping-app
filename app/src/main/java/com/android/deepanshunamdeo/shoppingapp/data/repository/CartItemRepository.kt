package com.android.deepanshunamdeo.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem
import com.android.deepanshunamdeo.shoppingapp.data.model.dao.CartIemDao
import javax.inject.Inject

class CartItemRepository @Inject constructor(private val cartIemDao: CartIemDao) {

    val getAllCartItem: LiveData<List<CartItem>> = cartIemDao.getAllCartItems()
    val cartQuantity: LiveData<Int> = cartIemDao.cartQuantity()
    fun getQuantityFromName(name: String): LiveData<Int> = cartIemDao.getQuantityFromName(name)

    suspend fun insertSingleItemToCart(cartItem: CartItem) {
        cartIemDao.insertSingleItemToCart(cartItem)
    }

    suspend fun deleteSingleItemFromCart(cartItem: CartItem) {
        cartIemDao.deleteSingleItemFromCart(cartItem)
    }

    suspend fun deleteAllItemFromCart() {
        cartIemDao.deleteAllItemFromCart()
    }


}