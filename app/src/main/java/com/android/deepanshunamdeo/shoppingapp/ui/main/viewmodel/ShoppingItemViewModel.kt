package com.android.deepanshunamdeo.shoppingapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.deepanshunamdeo.shoppingapp.data.model.ShoppingItem
import com.android.deepanshunamdeo.shoppingapp.data.repository.ShoppingItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingItemViewModel @Inject constructor(val shoppingItemRepository: ShoppingItemRepository) :
    ViewModel() {

    val getAllShoppingItem: LiveData<List<ShoppingItem>> = shoppingItemRepository.getAllShoppingItem

    fun insertShoppingItems(shoppingItem: ShoppingItem) = viewModelScope.launch(Dispatchers.IO) {
        shoppingItemRepository.insert(shoppingItem)
    }

}