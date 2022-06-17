package com.teamonetech.freshdoko.domain.models

data class UserModel(val token:String, val refreshToken:String,val csrfToken:String,val email:String)