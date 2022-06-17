package com.teamonetech.freshdoko.data.repository

import com.teamonetech.freshdoko.data.commun.FreshDokoResult
import com.teamonetech.freshdoko.data.datasource.local.AppDao
import com.teamonetech.freshdoko.data.datasource.remote.RemoteDataSource
import com.teamonetech.freshdoko.data.mappers.mapToDomainModel
import com.teamonetech.freshdoko.domain.models.ProductsModel
import com.teamonetech.freshdoko.domain.models.UserModel

import com.teamonetech.freshdoko.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val appDao: AppDao
) :
    AppRepository {
    override suspend fun getProducts(): Flow<FreshDokoResult<List<ProductsModel>>> =
        flow {
            when (val result = remoteDataSource.getProducts()) {
                is FreshDokoResult.Success -> {
                    result.data?.let {
                        it.mapToDomainModel().apply {
//                            appDao.saveProducts(this)
                            emit(FreshDokoResult.Success(this))
                        }

                    }
                }
                is FreshDokoResult.Error -> {
//                    val productsModel = appDao.getListProducts()

//                    if (productsModel.isNotEmpty()) {
//                        emit(
//                            FreshDokoResult.Success(
//                                data = productsModel
//                            )
//                        )
//                    } else {
                        emit(FreshDokoResult.Error(result.exception))
//                    }
                }
                else -> {}
            }
        }.onStart { emit(FreshDokoResult.Loading) }

    override suspend fun loginUser(email:String,password:String): Flow<FreshDokoResult<UserModel>> = flow {
         when (val result = remoteDataSource.loginUser(email,password)) {
             is FreshDokoResult.Success -> {
                 result.data?.let {
                     it.mapToDomainModel().apply {
//                            appDao.saveProducts(this)
                         emit(FreshDokoResult.Success(this))
                     }

                 }
             }
             is FreshDokoResult.Error -> {
//
                 emit(FreshDokoResult.Error(result.exception))
//                    }
             }
             else -> {}
         }
     }.onStart { emit(FreshDokoResult.Loading) }
}
