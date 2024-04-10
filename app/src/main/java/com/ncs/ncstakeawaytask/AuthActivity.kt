package com.ncs.ncstakeawaytask

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class AuthActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val nav = intent.getStringExtra("nav")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navhost) as NavHostFragment
        val navController = navHostFragment.navController
        if (nav=="details"){
            navController.navigate(R.id.action_authchooserFragment_to_detailsEnterFragment)
        }


    }
}