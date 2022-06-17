package com.teamonetech.freshdoko.domain.usecases

import com.teamonetech.freshdoko.domain.repository.AppRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(request: Int) = appRepository.getProducts()
    suspend  fun getToken(email:String,password:String) = appRepository.loginUser(email,password)

}