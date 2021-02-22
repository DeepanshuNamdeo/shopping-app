package com.android.deepanshunamdeo.shoppingapp.ui.main.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem
import com.android.deepanshunamdeo.shoppingapp.data.repository.CartItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartItemViewModel @Inject constructor(private val cartItemRepository: CartItemRepository) :
    ViewModel() {

    val getAllCartItem: LiveData<List<CartItem>> = cartItemRepository.getAllCartItem
    val cartQuantity: LiveData<Int> = cartItemRepository.cartQuantity

    fun cartQuantity(name: String): LiveData<Int> = cartItemRepository.getQuantityFromName(name)

    fun insertSingleItemToCart(cartItem: CartItem) = viewModelScope.launch {
        cartItemRepository.insertSingleItemToCart(cartItem)
    }

    fun deleteSingleItemFromCart(cartItem: CartItem) = viewModelScope.launch {
        cartItemRepository.deleteSingleItemFromCart(cartItem)
    }

    fun deleteAllItemFromCart() = viewModelScope.launch {
        cartItemRepository.deleteAllItemFromCart()
    }


}