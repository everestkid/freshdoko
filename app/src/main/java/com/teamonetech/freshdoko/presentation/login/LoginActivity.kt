package com.teamonetech.freshdoko.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teamonetech.freshdoko.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}