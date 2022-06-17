package com.teamonetech.freshdoko.data.datasource.remote


import com.teamonetech.freshdoko.GetTokenMutation
import com.teamonetech.freshdoko.ProductCollectionQuery
import com.teamonetech.freshdoko.data.commun.FreshDokoResult

interface RemoteDataSource {
    suspend fun getProducts(): FreshDokoResult<ProductCollectionQuery.Products?>
    suspend fun loginUser(email:String,password:String):FreshDokoResult<GetTokenMutation.TokenCreate?>
}