package com.devdays.bloodcare.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.devdays.bloodcare.R
import com.devdays.bloodcare.util.SharedPreferenceUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var mToolbarMain: Toolbar
    private lateinit var mBottomNavigationView: BottomNavigationView
    private lateinit var mNavControllerMain: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    private lateinit var mSharedPreferenceUtils: SharedPreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbarMain = findViewById(R.id.toolbarMain)

        setSupportActionBar(mToolbarMain)

        mBottomNavigationView = findViewById(R.id.bottomNavigationView)

        mSharedPreferenceUtils = SharedPreferenceUtils(this@MainActivity)

        mNavControllerMain = findNavController(R.id.fragmentNavHost)

        mAppBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.homeFragment,
                    R.id.findFragment,
                    R.id.profileFragment
                )
            ) { true }

        setupActionBarWithNavController(mNavControllerMain, mAppBarConfiguration)

        mBottomNavigationView.setupWithNavController(mNavControllerMain)

        mNavControllerMain.addOnDestinationChangedListener(this@MainActivity)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavControllerMain.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.homeFragment,
            R.id.findFragment,
            R.id.profileFragment -> {
                supportActionBar?.show()
                mToolbarMain.visibility = View.VISIBLE
                mBottomNavigationView.visibility = View.VISIBLE
            }
            else -> {
                supportActionBar?.hide()
                mToolbarMain.visibility = View.GONE
                mBottomNavigationView.visibility = View.GONE
            }
        }
    }
}