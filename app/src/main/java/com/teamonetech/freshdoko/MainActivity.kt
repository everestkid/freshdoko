package com.teamonetech.freshdoko;

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.teamonetech.freshdoko.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity:AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        setSupportActionBar(binding.tbMain)
        val navController = (supportFragmentManager
            .findFragmentById(binding.navHostFragment.id) as NavHostFragment)
            .navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
       binding.tbMain.setupWithNavController(navController, appBarConfiguration)
    }



}