package com.teamonetech.freshdoko.data.commun

sealed class FreshDokoResult<out T> {
    data class Success<out T>(val data: T) : FreshDokoResult<T>()
    data class Error(val exception: DataSourceException) : FreshDokoResult<Nothing>()
    object Loading : FreshDokoResult<Nothing>()
}

inline fun <T : Any> FreshDokoResult<T>.onSuccess(action: (T) -> Unit): FreshDokoResult<T> {
    if (this is FreshDokoResult.Success) action(data)
    return this
}

inline fun <T : Any> FreshDokoResult<T>.onError(action: (DataSourceException) -> Unit): FreshDokoResult<T> {
    if (this is FreshDokoResult.Error) action(exception)
    return this
}

inline fun <T : Any> FreshDokoResult<T>.onLoading(action: () -> Unit): FreshDokoResult<T> {
    if (this is FreshDokoResult.Loading) action()
    return this
}