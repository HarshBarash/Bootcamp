package github.bootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView
import github.bootcamp.utils.DISABLE
import github.bootcamp.utils.ENABLE
import github.bootcamp.utils.LOG_MAIN_ACTIVITY

class MainActivity : AppCompatActivity() {

    private lateinit var navController      : NavController
    private lateinit var navigationRailView : NavigationRailView
    private lateinit var searchRailView     : NavigationRailView

    override fun onCreate(savedInstanceState: Bundle?) {
        //themeSetter
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(LOG_MAIN_ACTIVITY, "onCreate called")

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        navigationRailView  = findViewById(R.id.navigation_rail)
        searchRailView      = findViewById(R.id.navigation_search)

        //Default fragment
        navigationRailView.selectedItemId = R.id.home

        navigationRailView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.settings -> {

                    navController.navigate(R.id.action_homeFragment_to_settingsFragment, null)
                    railController(DISABLE)

                    true
                }
                R.id.places -> {


                    true
                }
                R.id.ar -> {

                    true
                }
                R.id.sorting -> {


                    true
                }
                R.id.home -> {

                    true
                }
                else -> false
            }
        }
        //navigationRailView.setupWithNavController(navController)


    }

    override fun onStart() {
        super.onStart()
        Log.v(LOG_MAIN_ACTIVITY, "onStart called")

    }

    override fun onRestart() {
        super.onRestart()
        Log.v(LOG_MAIN_ACTIVITY, "onRestart called")

    }

    override fun onResume() {
        super.onResume()
        Log.v(LOG_MAIN_ACTIVITY, "onResume called")

    }

    override fun onPause() {
        super.onPause()
        Log.v(LOG_MAIN_ACTIVITY, "onPause called")

    }

    override fun onStop() {
        super.onStop()
        Log.v(LOG_MAIN_ACTIVITY, "onStop called")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(LOG_MAIN_ACTIVITY, "onDestroy called")

    }

    private fun railController(action: String) {
        when (action) {
            DISABLE -> {
                navigationRailView.visibility   = View.GONE
                searchRailView.visibility       = View.GONE
            }

            ENABLE -> {
                navigationRailView.visibility   = View.VISIBLE
                searchRailView.visibility       = View.VISIBLE
            }
        }
    }
}