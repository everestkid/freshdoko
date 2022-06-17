package com.teamonetech.freshdoko.presentation.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.teamonetech.freshdoko.databinding.ItemCharacterBinding
import com.teamonetech.freshdoko.domain.models.CartItem
import com.teamonetech.freshdoko.domain.models.ProductsModel
import com.teamonetech.freshdoko.utils.*
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe


class ProductsAdapter(
    private val list: List<ProductsModel>,
    private val clickAction: (ProductsModel) -> Unit,
    private val cartAction: (List<CartItem>) -> Unit
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

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

        fun bindTo(productsModel: ProductsModel, position: Int) {
            with(view) {
                ltQuantity.visibility = View.INVISIBLE
                btnAddToCart.visibility = View.VISIBLE
                ltTotal.visibility = View.GONE

                productsModel.apply {

                    val cartItems = ShoppingCart.getCart()

                    for (item in cartItems){
                        if(item.product.id == this.id){
                            ltQuantity.visibility = View.VISIBLE
                            btnAddToCart.visibility = View.INVISIBLE
                            ltTotal.visibility = View.VISIBLE
                            tvTotalPrice.text = getItemTotal(item,view.root.context)
                            tvTotalQuantity.text = getQuantityString(item,view.root.context)
                            tvQuantityValue.text = item.quantity.toString()
                        }

                    }
                    Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {observable ->
                        btnQuantityAdd.setOnClickListener {
                            ShoppingCart.addItem(CartItem(product = this))
                            for (item in ShoppingCart.getCart()) {
                                if (item.product.id == this.id) {
                                    tvQuantityValue.text = item.quantity.toString()
                                    tvTotalQuantity.text = getQuantityString(item,view.root.context)
                                    tvTotalPrice.text = getItemTotal(item,view.root.context)
                                }

                            }
                            observable.onNext(ShoppingCart.getCart())

                        }

                        btnQuantitySubstract.setOnClickListener {
                            ShoppingCart.removeItem(CartItem(product = this))
                            for (item in ShoppingCart.getCart()) {
                                if (item.product.id == this.id) {
                                    tvQuantityValue.text = item.quantity.toString()
                                    tvTotalQuantity.text = getQuantityString(item,view.root.context)
                                    tvTotalPrice.text = getItemTotal(item,view.root.context)
                                }

                            }

                           if(!ShoppingCart.getCart().any { it.product.id ==this.id }) notifyItemChanged(position)

                            observable.onNext(ShoppingCart.getCart())

                        }



                        btnAddToCart.setOnClickListener {
                            val cartItem = CartItem(product = this)
                            ShoppingCart.addItem(cartItem)
                            notifyItemChanged(position)
//                            btnAddToCart.visibility = View.GONE
//                            ltTotal.visibility = View.VISIBLE
//                            ltQuantity.visibility = View.VISIBLE
//
//                            for (item in ShoppingCart.getCart()) {
//                                if (item.product.id == this.id) {
//                                    tvQuantityValue.text = item.quantity.toString()
//                                    tvTotalQuantity.text =
//                                        getQuantityString(item, view.root.context)
//                                    tvTotalPrice.text = getItemTotal(item, view.root.context)
//                                }
//                            }
                            clickAction(this)


                            observable.onNext(ShoppingCart.getCart())



                        }

                    }).subscribe { cart ->
                       cartAction(cart.toList())
                    }



                    tvName.text = name
                    val priceString = getPriceString(this,view.root.context)
                    tvPrice.text = priceString
                    tvMinOrder.text = getMinOrder(this,view.root.context)
                    imgCharacter.load(url){
                        crossfade(true)
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