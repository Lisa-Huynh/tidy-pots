package com.example.tidypots

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlin.properties.Delegates

//import com.example.tidypots.databinding.ActivityMainBinding

var hasCamera by Delegates.notNull<Boolean>()

class MainActivity : AppCompatActivity() {

    //lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Check whether the app is running on a device that has a front-facing camera
        hasCamera = applicationContext.packageManager.hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FRONT)

        //    setupNavigation()
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    //    private fun setupNavigation() {
    //        val navController = findNavController(R.id.nav_host_fragment)
    //    }

}