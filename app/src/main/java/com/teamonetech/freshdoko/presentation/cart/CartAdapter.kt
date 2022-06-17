package com.teamonetech.freshdoko.presentation.cart


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.teamonetech.freshdoko.databinding.ItemCharacterBinding
import com.teamonetech.freshdoko.domain.models.CartItem
import com.teamonetech.freshdoko.utils.ShoppingCart
import com.teamonetech.freshdoko.utils.getMinOrder
import com.teamonetech.freshdoko.utils.getPriceString
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe


class CartAdapter(
    private val list: List<CartItem>,
    private val clickAction: (CartItem) -> Unit,
    private val cartAction: (List<CartItem>) -> Unit
) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(list[position],position)
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val view: ItemCharacterBinding) :
        RecyclerView.ViewHolder(view.root) {
        init {
            cartAction(ShoppingCart.getCart())
//            view.root.setOnClickListener { clickAction(list[adapterPosition]) }
        }

        fun bindTo(productsModel: CartItem, position: Int) {
            with(view) {

                productsModel.apply {

                    val cartItems = ShoppingCart.getCart()

                    for (item in cartItems){
                        if(item.product.id == this.product.id){
                            ltQuantity.visibility = View.VISIBLE
                            btnAddToCart.visibility = View.INVISIBLE
                            tvQuantityValue.text = item.quantity.toString()
                        }
                    }
                    Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {observable ->
                        btnQuantityAdd.setOnClickListener {
                            ShoppingCart.addItem(CartItem(product = this.product))
                            for (item in ShoppingCart.getCart()) {
                                if (item.product.id == this.product.id) {
                                    tvQuantityValue.text = item.quantity.toString()
                                }

                            }
                            observable.onNext(ShoppingCart.getCart())

                        }

                        btnQuantitySubstract.setOnClickListener {
                            ShoppingCart.removeItem(CartItem(product = this.product))
                            for (item in ShoppingCart.getCart()) {
                                if (item.product.id == this.product.id) {
                                    tvQuantityValue.text = item.quantity.toString()
                                }
                            }
                            observable.onNext(ShoppingCart.getCart())

                        }



                        btnAddToCart.setOnClickListener {
                            val item = CartItem(product = this.product)
                            ShoppingCart.addItem(item)
                            notifyItemChanged(position)
                            clickAction(this)
                            observable.onNext(ShoppingCart.getCart())



                        }

                    }).subscribe { cart ->
                        cartAction(cart.toList())
                    }



                    tvName.text = product.name
                    val priceString = getPriceString(this.product,view.root.context)
                    tvPrice.text = priceString
                    tvMinOrder.text = getMinOrder(this.product,view.root.context)
                    imgCharacter.load(product.url){
                        crossfade(false)
                    }
//                    imgCharacter.load(image) {
//                        crossfade(true)
//
//                    }
                }
            }
        }
    }
}