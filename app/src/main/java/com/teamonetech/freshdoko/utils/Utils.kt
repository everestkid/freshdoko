package com.teamonetech.freshdoko.utils

import android.content.Context
import com.teamonetech.freshdoko.R
import com.teamonetech.freshdoko.domain.models.CartItem
import com.teamonetech.freshdoko.domain.models.ProductsModel


fun getCurrencyName(currency: String, context: Context) = if (currency.equals("NPR"))
    context.getString(R.string.nepali_currency) else currency


fun getMinOrder(productsModel: ProductsModel,context: Context):String{
    with(productsModel) {
        return "$minValue $attribute"
    }
}

fun getPriceString(productsModel: ProductsModel,context: Context):String {
    with(productsModel) {
        val convertedCurrency =
            if (currency == "NPR") context.getString(R.string.nepali_currency) else currency
            return "$convertedCurrency ${price.toInt()}/$attribute "
    }
}

fun getQuantityString(cartItem: CartItem,context: Context):String {
    with(cartItem) {

        return "$quantity ${this.product.attribute} "
    }

}


fun getItemTotal(cartItem: CartItem,context: Context):String {
    with(cartItem) {
        val convertedCurrency =
            if (product.currency == "NPR") context.getString(R.string.nepali_currency) else product.currency
        return "$convertedCurrency ${product.price * quantity}"
    }

}


fun getCartSummary (cartItems:List<CartItem>): Pair<Int, Double> {
    var quantity = 0
    var price = 0.0
    cartItems.forEach{
        val itemQuantity = it.quantity
        val itemPrice = itemQuantity * it.product.price

        quantity += itemQuantity
        price += itemPrice
    }

    return Pair(quantity,price)
}
