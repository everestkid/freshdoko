package com.teamonetech.freshdoko.utils

import android.content.Context
import android.widget.Toast
import com.teamonetech.freshdoko.domain.models.CartItem
import io.paperdb.Paper

class ShoppingCart {

    companion object {
        fun addItem(cartItem: CartItem) {
            val cart = ShoppingCart.getCart()

            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem == null) {
                cartItem.quantity = cartItem.quantity + cartItem.product.minValue
                cart.add(cartItem)
            } else {
                targetItem.quantity = targetItem.quantity + targetItem.product.minValue
            }
            ShoppingCart.saveCart(cart)

        }

        fun removeItem(cartItem: CartItem) {
            val cart = ShoppingCart.getCart()

            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem != null) {
                if (targetItem.quantity > 0) {
                    targetItem.quantity = targetItem.quantity - targetItem.product.minValue
                } else {
                    cart.remove(targetItem)
                }

                if(targetItem.quantity ==0 ) cart.remove(targetItem)
            }

            ShoppingCart.saveCart(cart)
        }

        fun saveCart(cart: MutableList<CartItem>) {
            Paper.book().write("cart", cart)
        }

        fun getCart(): MutableList<CartItem>  {
            return Paper.book().read("cart", mutableListOf()) ?: emptyList<CartItem>().toMutableList()
        }

        fun getShoppingCartSize(): Int {
            var cartSize = 0
            ShoppingCart.getCart().forEach {
                cartSize += it.quantity;
            }

            return cartSize
        }
    }

}