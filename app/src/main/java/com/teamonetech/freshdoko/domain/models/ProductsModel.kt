package com.teamonetech.freshdoko.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "products")
@Parcelize
data class ProductsModel(
    @PrimaryKey
    val id:String,
    val name: String,
    val description:String,
    val url:String,
    val price:Double,
    val currency:String,
    val attribute:String,
    val minValue:Int,
    var quantity:Int =0
) : Parcelable


data class CartItem(var product: ProductsModel, var quantity: Int = 0)
