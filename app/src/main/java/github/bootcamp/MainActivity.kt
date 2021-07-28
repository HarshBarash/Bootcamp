package github.bootcamp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigationrail.NavigationRailView
import github.bootcamp.utils.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController      : NavController
    private lateinit var navigationRailView : NavigationRailView
    private lateinit var searchRailView     : NavigationRailView

    override fun onCreate(savedInstanceState: Bundle?) {
        themeController() //Эта херь определяет и ставит тему пользователя
        super.onCreate(savedInstanceState)
        setLanguage() // Эта херь ставит язык
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
                R.id.stats -> {
                    navController.navigate(R.id.action_homeFragment_to_statsFragment, null)
                    railController(DISABLE)
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

    fun railController(action: String) {
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

    private fun setLanguage() {
        val sp = getSharedPreferences(SETTINGS_FILE, 0)
        val defLang = Locale.getDefault().displayLanguage

        var language: String = sp.getString(SETTINGS_LANGUAGE, null) ?: return

        Log.v(LOG_MAIN_ACTIVITY, "SharedPrefs lang: $language")

        if (defLang == language)
            return
        else if (language == RUSSIAN)
            language = "ru"
        else if (language == ENGLISH)
            language = "en"
        else if (language == GERMAN)
            language = "de"

        val locale = Locale(language)
        Locale.setDefault(locale)
        // Create a new configuration object
        val config = Configuration()
        // Set the locale of the new configuration
        config.locale = locale
        // Update the configuration of the Accplication context
        resources.updateConfiguration(
            config,
            resources.displayMetrics
        )
    }

    private fun themeController() {
        //В процессе разработки
        val spConfig = getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE)
        val theme = spConfig.getString(SETTINGS_THEME, LIGHT_BLUE_THEME)

        Log.v(LOG_MAIN_ACTIVITY, "Theme: $theme")


        if (theme == LIGHT_GREEN_THEME)
            setTheme(R.style.AppLightGreenTheme)

        if (theme == LIGHT_BLUE_THEME)
            setTheme(R.style.AppLightBlueTheme)

        if (theme == BEIGE_THEME)
            setTheme(R.style.AppBeigeTheme)

        //val language = spConfig.getString(SETTINGS_THEME, Locale.getDefault().displayLanguage.toString())


        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {

            }
            Configuration.UI_MODE_NIGHT_NO -> {

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.v(LOG_MAIN_ACTIVITY, "onBackPressed called")
        TODO("Переопределить эту херь для фрагментов")
    }


}