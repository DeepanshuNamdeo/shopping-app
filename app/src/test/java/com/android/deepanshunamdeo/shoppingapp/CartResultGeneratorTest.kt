package com.android.deepanshunamdeo.shoppingapp

import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem
import com.android.deepanshunamdeo.shoppingapp.data.model.CartResultGenerator
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CartResultGeneratorTest {

    private lateinit var cartResultGenerator: CartResultGenerator

    @Before
    fun setup() {
        cartResultGenerator = CartResultGenerator()
    }


    @Test
    fun testCartResult() {
        val name = "guitar"
        val price = 100
        val quantity = 4
        val expectedItemPrice = 400

        Assert.assertEquals(
            expectedItemPrice,
            cartResultGenerator.generateResult(CartItem(name, quantity, price))
        )
    }

}