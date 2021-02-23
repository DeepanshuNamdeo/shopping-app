package com.android.deepanshunamdeo.shoppingapp.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.deepanshunamdeo.shoppingapp.R
import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem
import com.android.deepanshunamdeo.shoppingapp.data.model.CartResultGenerator
import com.android.deepanshunamdeo.shoppingapp.ui.main.adapter.CartItemAdapter
import com.android.deepanshunamdeo.shoppingapp.ui.main.adapter.ShoppingItemAdapter
import com.android.deepanshunamdeo.shoppingapp.ui.main.viewmodel.CartItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_cart.*

@AndroidEntryPoint
class CartActivity : AppCompatActivity(), ShoppingItemAdapter.ItemAddRemoveOnClickListener {

    private lateinit var adapter: CartItemAdapter
    private val cartItemViewModel: CartItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setupUI()


    }


    private fun setupUI() {


        recyclerView_cart_list_item.layoutManager = LinearLayoutManager(this)
        adapter = CartItemAdapter(arrayListOf(), this)
        recyclerView_cart_list_item.addItemDecoration(
            DividerItemDecoration(
                recyclerView_cart_list_item.context,
                (recyclerView_cart_list_item.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView_cart_list_item.adapter = adapter

        cartItemViewModel.getAllCartItem.observe(this, {
            if (it.isEmpty()) {
                ConstraintLayout_when_cart_is_not_empty.visibility = View.GONE
                LinearLayout_when_cart_is_empty.visibility = View.VISIBLE
            } else {
                LinearLayout_when_cart_is_empty.visibility = View.GONE
                ConstraintLayout_when_cart_is_not_empty.visibility = View.VISIBLE
                val cartItemList = it as List<CartItem>
                adapter.updateList(cartItemList)
                var totalCartAmount = 0
                for (cartItem in cartItemList) {
                    val itemTotalAmount = CartResultGenerator().generateResult(cartItem)
                    totalCartAmount += itemTotalAmount
                }

                textView_amount.text = totalCartAmount.toString()
            }

        })
    }

    override fun onItemAddedToCart(cartItem: CartItem) {
        cartItemViewModel.insertSingleItemToCart(cartItem)
    }

    override fun onItemRemovedFromCart(cartItem: CartItem) {
        cartItemViewModel.deleteSingleItemFromCart(cartItem)
    }

    override fun updateItemInCart(cartItem: CartItem) {
        cartItemViewModel.updateItemInCart(cartItem)
    }

    fun checkout() {
        cartItemViewModel.deleteAllItemFromCart()
        startActivity(Intent(this, ThankYouActivity::class.java))
        finish()
    }
}