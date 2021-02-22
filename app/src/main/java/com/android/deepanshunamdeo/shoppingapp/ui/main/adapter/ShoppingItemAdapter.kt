package com.android.deepanshunamdeo.shoppingapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.deepanshunamdeo.shoppingapp.R
import com.android.deepanshunamdeo.shoppingapp.data.model.CartItem
import com.android.deepanshunamdeo.shoppingapp.data.model.ShoppingItem
import com.bumptech.glide.Glide

class ShoppingItemAdapter(
    private val shoppingItemList: ArrayList<ShoppingItem>,
    private val cartItemList: ArrayList<CartItem>,
    val listener: ItemAddRemoveOnClickListener
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {


    class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView_item_name: TextView = itemView.findViewById(R.id.textView_item_name)
        val textView_item_description: TextView =
            itemView.findViewById(R.id.textView_item_description)
        val textView_item_price: TextView = itemView.findViewById(R.id.textView_item_price)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView_item_quantity: TextView = itemView.findViewById(R.id.textView_item_quantity)
        val imageButton_add_Item_to_cart: ImageButton =
            itemView.findViewById(R.id.imageButton_add_Item_to_cart)
        val imageButton_remove_Item_from_cart: ImageButton =
            itemView.findViewById(R.id.imageButton_remove_Item_from_cart)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val shoppingItemViewHolder = ShoppingItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_item_layout, parent, false)
        )
        //handel on click here
        return shoppingItemViewHolder
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val currentShoppingItem = shoppingItemList[position]
        holder.textView_item_name.text = currentShoppingItem.name
        holder.textView_item_description.text = currentShoppingItem.description
        holder.textView_item_price.text = currentShoppingItem.price.toString()
        holder.textView_item_quantity.text = "0"

        Glide.with(holder.imageView.context).load(currentShoppingItem.image_url)
            .placeholder(R.drawable.ic_baseline_fastfood_24).into(holder.imageView);


        if (cartItemList.isNotEmpty()) {
            for (cart in cartItemList) {
                if (currentShoppingItem.name == cart.cartItemName) {
                    holder.textView_item_quantity.text = cart.cartItemQuantity.toString()
                }
            }
        }

        holder.imageButton_add_Item_to_cart.setOnClickListener {
            if (cartItemList.isEmpty()) {
                listener.onItemAddedToCart(
                    CartItem(
                        currentShoppingItem.name,
                        1,
                        currentShoppingItem.price
                    )
                )
            } else {
                for (cartItem in cartItemList) {
                    if (currentShoppingItem.name == cartItem.cartItemName) {
                        if (cartItem.cartItemQuantity == 0) {
                            listener.onItemAddedToCart(
                                CartItem(
                                    currentShoppingItem.name,
                                    cartItem.cartItemQuantity + 1,
                                    currentShoppingItem.price
                                )
                            )
                            holder.textView_item_quantity.text =
                                (cartItem.cartItemQuantity + 1).toString()

                        } else {
                            listener.onItemRemovedFromCart(cartItem)
                            listener.onItemAddedToCart(
                                CartItem(
                                    currentShoppingItem.name,
                                    cartItem.cartItemQuantity + 1,
                                    currentShoppingItem.price
                                )
                            )
                            holder.textView_item_quantity.text =
                                (cartItem.cartItemQuantity + 1).toString()

                        }
                    } else {
                        listener.onItemAddedToCart(
                            CartItem(
                                currentShoppingItem.name,
                                1,
                                currentShoppingItem.price
                            )
                        )
                        holder.textView_item_quantity.text =
                            (cartItem.cartItemQuantity + 1).toString()

                    }
                }
            }
        }
        holder.imageButton_remove_Item_from_cart.setOnClickListener {

            if (cartItemList.isNotEmpty()) {
                for (cartItem in cartItemList) {
                    if (currentShoppingItem.name == cartItem.cartItemName) {
                        if (cartItem.cartItemQuantity == 0) {
                            listener.onItemRemovedFromCart(
                                CartItem(
                                    currentShoppingItem.name,
                                    cartItem.cartItemQuantity,
                                    currentShoppingItem.price
                                )
                            )
                            holder.textView_item_quantity.text =
                                (cartItem.cartItemQuantity - 1).toString()
                        }
                        if (cartItem.cartItemQuantity > 1) {
                            listener.onItemRemovedFromCart(
                                CartItem(
                                    currentShoppingItem.name,
                                    cartItem.cartItemQuantity,
                                    currentShoppingItem.price
                                )
                            )
                            listener.onItemAddedToCart(
                                CartItem(
                                    currentShoppingItem.name,
                                    cartItem.cartItemQuantity - 1,
                                    currentShoppingItem.price
                                )
                            )
                            holder.textView_item_quantity.text =
                                (cartItem.cartItemQuantity - 1).toString()
                        } else {
                            listener.onItemRemovedFromCart(
                                CartItem(
                                    currentShoppingItem.name,
                                    cartItem.cartItemQuantity,
                                    currentShoppingItem.price
                                )
                            )
                            holder.textView_item_quantity.text =
                                (cartItem.cartItemQuantity - 1).toString()
                        }
                    } else {
                        listener.onItemRemovedFromCart(
                            CartItem(
                                currentShoppingItem.name,
                                cartItem.cartItemQuantity,
                                currentShoppingItem.price
                            )
                        )
                        holder.textView_item_quantity.text =
                            (cartItem.cartItemQuantity - 1).toString()

                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingItemList.size
    }

    fun updateList(shoppingItem: List<ShoppingItem>) {
        this.shoppingItemList.clear()
        this.shoppingItemList.addAll(shoppingItem)

        notifyDataSetChanged()
    }

    fun updateCart(cartItem: List<CartItem>) {
        this.cartItemList.clear()
        this.cartItemList.addAll(cartItem)

        notifyDataSetChanged()
    }

    interface ItemAddRemoveOnClickListener {

        fun onItemAddedToCart(cartItem: CartItem)
        fun onItemRemovedFromCart(cartItem: CartItem)

    }
}