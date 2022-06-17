package com.teamonetech.freshdoko.presentation.login.mobile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamonetech.freshdoko.data.commun.FreshDokoResult
import com.teamonetech.freshdoko.domain.models.ProductsModel
import com.teamonetech.freshdoko.domain.models.UserModel
import com.teamonetech.freshdoko.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class EmailLoginViewModel   @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _resultLogin = MutableLiveData<FreshDokoResult<UserModel>>()
    val resultLogin: LiveData<FreshDokoResult<UserModel>> = _resultLogin

    fun loginUser(email:String,password:String) {
        viewModelScope.launch {
        getCharactersUseCase.getToken(email,password).collect {
                _resultLogin.postValue(it)
            }
        }
    }
}