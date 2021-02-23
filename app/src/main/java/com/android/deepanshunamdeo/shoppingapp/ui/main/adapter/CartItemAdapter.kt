package com.android.deepanshunamdeo.shoppingapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.deepanshunamdeo.shoppingapp.R
import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem

class CartItemAdapter(
    private val cartItemList: ArrayList<CartItem>,
    val listener: ShoppingItemAdapter.ItemAddRemoveOnClickListener
) : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>(),
    ShoppingItemAdapter.ItemAddRemoveOnClickListener {

    class CartItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val textView_item_name: TextView = itemView.findViewById(R.id.textView_item_name_cart)
        val textView_item_price: TextView = itemView.findViewById(R.id.textView_item_price_cart)
        val textView_item_quantity: TextView = itemView.findViewById(R.id.textView_item_quantity)
        val imageButton_add_Item_to_cart: ImageButton =
            itemView.findViewById(R.id.imageButton_add_Item_to_cart)
        val imageButton_remove_Item_from_cart: ImageButton =
            itemView.findViewById(R.id.imageButton_remove_Item_from_cart)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartItemViewHolder {

        return CartItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cart_list_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val currentItem = cartItemList[position]
        holder.textView_item_name.text = currentItem.cartItemName
        holder.textView_item_price.text = currentItem.cartItemPrice.toString()
        holder.textView_item_quantity.text = currentItem.cartItemQuantity.toString()


        holder.imageButton_add_Item_to_cart.setOnClickListener {
            listener.updateItemInCart(CartItem(
                    currentItem.cartItemName,
                    currentItem.cartItemQuantity + 1,
                    currentItem.cartItemPrice))
            holder.textView_item_quantity.text = (currentItem.cartItemQuantity + 1).toString()
        }
        holder.imageButton_remove_Item_from_cart.setOnClickListener {
            if (currentItem.cartItemQuantity > 1) {
                listener.updateItemInCart(
                    CartItem(
                        currentItem.cartItemName,
                        currentItem.cartItemQuantity - 1,
                        currentItem.cartItemPrice
                    )
                )
                holder.textView_item_quantity.text = (currentItem.cartItemQuantity - 1).toString()
            }else{
                listener.onItemRemovedFromCart(currentItem)
            }
        }
    }

    fun updateList(cartItem: List<CartItem>) {
        this.cartItemList.clear()
        this.cartItemList.addAll(cartItem)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cartItemList.size

    override fun onItemAddedToCart(cartItem: CartItem) {
    }

    override fun onItemRemovedFromCart(cartItem: CartItem) {
    }

    override fun updateItemInCart(cartItem: CartItem) {

    }
}