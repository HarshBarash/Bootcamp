package github.bootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView
import github.bootcamp.utils.LOG_MAIN_ACTIVITY

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        //themeSetter
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(LOG_MAIN_ACTIVITY, "onCreate called")

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Установка navigation view
        val navigationRailView = findViewById<NavigationRailView>(R.id.navigation_rail)
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
}