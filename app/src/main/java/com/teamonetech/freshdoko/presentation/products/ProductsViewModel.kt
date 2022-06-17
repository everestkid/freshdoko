package com.teamonetech.freshdoko.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamonetech.freshdoko.data.commun.FreshDokoResult
import com.teamonetech.freshdoko.domain.models.ProductsModel
import com.teamonetech.freshdoko.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel     @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _resultListCharacters = MutableLiveData<FreshDokoResult<List<ProductsModel>>>()
    val resultListCharacters: LiveData<FreshDokoResult<List<ProductsModel>>> = _resultListCharacters

    fun getProductsList(page: Int) {
        viewModelScope.launch {
            getCharactersUseCase(page).collect {
                _resultListCharacters.postValue(it)
            }
        }
    }
}