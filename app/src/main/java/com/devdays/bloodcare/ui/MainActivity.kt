package com.devdays.bloodcare.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.devdays.bloodcare.R
import com.devdays.bloodcare.util.SharedPreferenceUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var mToolbarMain: Toolbar
    private lateinit var mBottomNavigationView: BottomNavigationView
    private lateinit var mNavControllerMain: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    private lateinit var mSharedPreferenceUtils: SharedPreferenceUtils
    private var mLoginToken: Boolean = false
    private var mDestination: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbarMain = findViewById(R.id.toolbarMain)

        setSupportActionBar(mToolbarMain)

        mBottomNavigationView = findViewById(R.id.bottomNavigationView)

        mSharedPreferenceUtils = SharedPreferenceUtils(this@MainActivity)
        mLoginToken = mSharedPreferenceUtils.mLoginToken

        mDestination = when {
            mLoginToken -> {
                R.id.homeFragment
            }
            else -> {
                R.id.signInFragment
            }
        }

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

        val mNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment
        val mGraphInflater = mNavHostFragment.navController.navInflater
        val mNavGraph = mGraphInflater.inflate(R.navigation.nav_main)
        val mNavController = mNavHostFragment.navController

        mNavGraph.startDestination = mDestination
        mNavController.graph = mNavGraph
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