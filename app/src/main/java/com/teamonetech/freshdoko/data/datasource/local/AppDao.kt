package com.teamonetech.freshdoko.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.teamonetech.freshdoko.domain.models.ProductsModel

@Dao
interface AppDao {

    @Query("SELECT * FROM products")
     fun getListProducts(): List<ProductsModel>

    @Insert(onConflict = REPLACE)
     fun saveProducts(listProducts: List<ProductsModel>)
}