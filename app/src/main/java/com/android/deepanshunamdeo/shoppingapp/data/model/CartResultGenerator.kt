package com.android.deepanshunamdeo.shoppingapp.data.model

class CartResultGenerator {

    fun generateResult(cartItem: CartItem): Int {
        return cartItem.cartItemPrice * cartItem.cartItemQuantity
    }
}