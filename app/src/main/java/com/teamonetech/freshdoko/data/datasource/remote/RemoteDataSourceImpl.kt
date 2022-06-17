package com.teamonetech.freshdoko.data.datasource.remote


import GraphQlApolloClient
import com.apollographql.apollo3.api.Error as ApolloError
import com.teamonetech.freshdoko.GetTokenMutation
import com.teamonetech.freshdoko.ProductCollectionQuery
import com.teamonetech.freshdoko.R
import com.teamonetech.freshdoko.data.commun.DataSourceException
import com.teamonetech.freshdoko.data.commun.FreshDokoResult

class RemoteDataSourceImpl : RemoteDataSource {

    override suspend fun getProducts(): FreshDokoResult<ProductCollectionQuery.Products?> {
        return try {
            val result = GraphQlApolloClient.getProducts().execute()
            if (result.hasErrors()) {
                FreshDokoResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                FreshDokoResult.Success(result.data?.products)
            }
        } catch (e: Exception) {
            FreshDokoResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): FreshDokoResult<GetTokenMutation.TokenCreate?> {
        return try {
            val result = GraphQlApolloClient.getToken(email,password).execute()
            if (result.hasErrors()) {
                FreshDokoResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                if (!result.data?.tokenCreate?.errors.isNullOrEmpty()){
                    FreshDokoResult.Error(DataSourceException.Server(ApolloError(result.data?.tokenCreate?.errors?.first()?.message ?:"Login Error",null,null,null,null)))
                }
                else {
                    FreshDokoResult.Success(result.data?.tokenCreate)
                }
            }
        } catch (e: Exception) {
            FreshDokoResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }
}