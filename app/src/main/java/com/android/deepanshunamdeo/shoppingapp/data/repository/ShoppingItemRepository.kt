package com.android.deepanshunamdeo.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import com.android.deepanshunamdeo.shoppingapp.data.model.ShoppingItem
import com.android.deepanshunamdeo.shoppingapp.data.model.dao.ShoppingItemDao
import javax.inject.Inject

class ShoppingItemRepository @Inject constructor(val shoppingItemDao: ShoppingItemDao) {

    val getAllShoppingItem: LiveData<List<ShoppingItem>> = shoppingItemDao.getAllShoppingItem()

    suspend fun insert(shoppingItem: ShoppingItem) {
        shoppingItemDao.insert(shoppingItem)
    }

}