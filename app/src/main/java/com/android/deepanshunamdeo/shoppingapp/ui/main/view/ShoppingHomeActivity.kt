package com.android.deepanshunamdeo.shoppingapp.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.deepanshunamdeo.shoppingapp.R
import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem
import com.android.deepanshunamdeo.shoppingapp.data.model.ShoppingItem
import com.android.deepanshunamdeo.shoppingapp.databinding.ActivityShoppingHomeBinding
import com.android.deepanshunamdeo.shoppingapp.ui.main.adapter.ShoppingItemAdapter
import com.android.deepanshunamdeo.shoppingapp.ui.main.adapter.ShoppingItemAdapter.ItemAddRemoveOnClickListener
import com.android.deepanshunamdeo.shoppingapp.ui.main.viewmodel.CartItemViewModel
import com.android.deepanshunamdeo.shoppingapp.ui.main.viewmodel.ShoppingItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.min


@AndroidEntryPoint
class ShoppingHomeActivity : AppCompatActivity(), ItemAddRemoveOnClickListener {

    private lateinit var textCartItemCount: TextView
    private lateinit var adapter: ShoppingItemAdapter
    private val shoppingItemViewModel: ShoppingItemViewModel by viewModels()
    private val cartItemViewModel: CartItemViewModel by viewModels()

    private lateinit var binding: ActivityShoppingHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingHomeBinding.inflate(layoutInflater)
        val view = binding.root

        setupUI()
        setContentView(view)
        /*  shoppingItemViewModel.getAllShoppingItem.observe(this, Observer {
              adapter.updateList(it as List<ShoppingItem>)
          })*/

    }

    override fun onResume() {
        super.onResume()
        shoppingItemViewModel.getAllShoppingItem.observe(this, {
            adapter.updateList(it as List<ShoppingItem>)
        })
    }


    private fun setupUI() {

        binding.recyclerViewShoppingItem.layoutManager = LinearLayoutManager(this)
        adapter = ShoppingItemAdapter(arrayListOf(), arrayListOf<CartItem>(), this)
        binding.recyclerViewShoppingItem.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewShoppingItem.context,
                (binding.recyclerViewShoppingItem.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerViewShoppingItem.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        val menuItem: MenuItem = menu!!.findItem(R.id.action_cart)
        val actionView: View = menuItem.actionView
        textCartItemCount = actionView.findViewById<View>(R.id.cart_badge) as TextView

        actionView.setOnClickListener {
            onOptionsItemSelected(menuItem)
        }

        cartItemViewModel.getAllCartItem.observe(this, {
            adapter.updateCart(it as List<CartItem>)
        })

        cartItemViewModel.cartQuantity.observe(this, {
            if (it != null) {
                setupBadgeCount(it)
            } else {
                setupBadgeCount(0)
            }
        })

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_cart) {
            startActivity(Intent(this, CartActivity::class.java))
        }
        return super.onOptionsItemSelected(item)

    }

    private fun setupBadgeCount(cartItemCount: Int) {

        if (textCartItemCount.text != null) {
            if (cartItemCount == 0) {
                if (textCartItemCount.visibility != View.GONE) {
                    textCartItemCount.visibility = View.GONE
                }
            } else {
                textCartItemCount.text = min(cartItemCount, 99).toString()
                if (textCartItemCount.visibility != View.VISIBLE) {
                    textCartItemCount.visibility = View.VISIBLE
                }
            }
        }
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

}