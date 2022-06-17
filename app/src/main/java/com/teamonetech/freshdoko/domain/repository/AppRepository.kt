package com.teamonetech.freshdoko.domain.repository

import com.teamonetech.freshdoko.data.commun.FreshDokoResult
import com.teamonetech.freshdoko.domain.models.ProductsModel
import com.teamonetech.freshdoko.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getProducts(): Flow<FreshDokoResult<List<ProductsModel>>>
    suspend fun loginUser(email:String,password:String):Flow<FreshDokoResult<UserModel>>
}