package github.bootcamp.settingsscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import github.bootcamp.MainActivity
import github.bootcamp.R
import github.bootcamp.utils.*
import java.util.*

class SettingsFragment : Fragment(), View.OnClickListener {

    private val itemLanguages = arrayOf("English", "Russian", "German")

    private lateinit var etName: TextInputEditText
    private lateinit var etUsername: TextInputEditText
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var spinLanguages: Spinner

    private lateinit var fltSave: FloatingActionButton

    private lateinit var clOtherColors: ConstraintLayout
    private lateinit var vBasicColorLightGreen: View
    private lateinit var vBasicColorLightBlue: View
    private lateinit var vBasicColorBeige: View

    //Возможно новые данные пользователя
    private var newTheme: String? = null
    private var newLanguage: String? = null
    private var newName: String? = null
    private var newUsername: String? = null
    private var newAppIcon: String? = null

    //Текущие данные пользователя
    private var sName = "<Name>"
    private var sUsername = "<Username>"
    private var sTheme = "<Theme>"
    private var sAppIcon = "<AppIcon>"
    private var sLanguage:String? = null

    private var needToRestartActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(LOG_SETTINGS_FRAGMENT, "onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        Log.v(LOG_SETTINGS_FRAGMENT, "onCreateView called")

        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        vBasicColorLightGreen = rootView.findViewById(R.id.vBasicColorLightGreen)
        vBasicColorBeige = rootView.findViewById(R.id.vBasicColorBeige)
        vBasicColorLightBlue = rootView.findViewById(R.id.vBasicColorLightBlue)
        clOtherColors = rootView.findViewById(R.id.clOtherColors)

        topAppBar = rootView.findViewById(R.id.topAppBar)
        etName = rootView.findViewById(R.id.etName)
        etUsername = rootView.findViewById(R.id.etUsername)
        spinLanguages = rootView.findViewById(R.id.spinLanguages)

        fltSave = rootView.findViewById(R.id.fltSave)

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, itemLanguages)
        spinLanguages.adapter = adapter

        listenerSetter()

        setUserSettings()

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v(LOG_SETTINGS_FRAGMENT, "onDestroyView called")

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.vBasicColorLightGreen -> {
                if (sTheme == LIGHT_GREEN_THEME) {
                    Toast.makeText(requireContext(), "Light Green theme is already selected!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Light Green theme selected!", Toast.LENGTH_SHORT).show()
                    newTheme = LIGHT_GREEN_THEME
                }
            }
            R.id.vBasicColorLightBlue -> {
                if (sTheme == LIGHT_BLUE_THEME) {
                    Toast.makeText(requireContext(), "Light Blue theme is already selected!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Light Blue theme selected!", Toast.LENGTH_SHORT).show()
                    newTheme = LIGHT_BLUE_THEME
                }
            }
            R.id.vBasicColorBeige -> {
                if (sTheme == BEIGE_THEME) {
                    Toast.makeText(requireContext(), "Beige theme is already selected!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Beige theme selected!", Toast.LENGTH_SHORT).show()
                    newTheme = BEIGE_THEME
                }
            }
            R.id.fltSave -> {
                saveUserSettings()
            }
        }
    }

    private fun saveUserSettings() {

        val spConfig = activity?.getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE) ?: return
        with (spConfig.edit()) {

            if (newTheme != null)
                putString(SETTINGS_THEME, newTheme)

            if (newAppIcon != null)
                putString(SETTINGS_APP_ICON, newAppIcon)

            if (newLanguage != null)
                putString(SETTINGS_LANGUAGE, newLanguage)

            apply()
        }


        if (newTheme != null) {
            Log.v(LOG_SETTINGS_FRAGMENT, "Theme has been changed")
            needToRestartActivity = true
        }

        if (newLanguage != null) {
            Log.v(LOG_SETTINGS_FRAGMENT, "Language has been changed")
            needToRestartActivity = true
        }


        Toast.makeText(requireContext(),"What a save!", Toast.LENGTH_SHORT).show()
    }

    private fun setUserSettings() {
        //аналог updateUI без блэкджека
        Log.v(LOG_SETTINGS_FRAGMENT, "System language: " + Locale.getDefault().displayLanguage.toString())

        val spConfig = activity?.getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE)

        sLanguage = spConfig?.getString(SETTINGS_LANGUAGE, Locale.getDefault().displayLanguage.toString())
        sTheme = spConfig?.getString(SETTINGS_THEME, LIGHT_BLUE_THEME).toString()
        sAppIcon = spConfig?.getString(SETTINGS_APP_ICON, "icon").toString()

        //Все настройки пользователя для комфортного деббагинга в будущем
        Log.v(LOG_SETTINGS_FRAGMENT, "<-- User Settings -->\n" +
                                                "Current language: $sLanguage" + "\n" +
                                                "Current name: $sName" + "\n" +
                                                "Current username: $sUsername" + "\n" +
                                                "Current theme: $sTheme" + "\n" +
                                                "Current app icon: $sAppIcon")

        when (sLanguage) {
            ENGLISH -> {
                spinLanguages.setSelection(0)
            }
            RUSSIAN -> {
                spinLanguages.setSelection(1)
            }
            GERMAN  -> {
                spinLanguages.setSelection(2)
            }
        }

        when(sTheme) {
            LIGHT_BLUE_THEME -> {

            }
            LIGHT_GREEN_THEME -> {

            }
            BEIGE_THEME -> {

            }
        }
        etName.setText(sName)
        etUsername.setText(sUsername)
    }

    private fun listenerSetter() {
        vBasicColorLightGreen.setOnClickListener(this)
        vBasicColorLightBlue.setOnClickListener(this)
        vBasicColorBeige.setOnClickListener(this)
        clOtherColors.setOnClickListener(this)
        fltSave.setOnClickListener(this)

        topAppBar.setNavigationOnClickListener {
            if (needToRestartActivity) {
                //(activity as MainActivity?)?.recreate()
                (activity as MainActivity?)?.finish()
                startActivity(Intent(requireContext(),MainActivity::class.java))
            } else {
                findNavController().popBackStack()
                (activity as MainActivity?)?.railController(ENABLE)
            }
        }

        spinLanguages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.v(LOG_SETTINGS_FRAGMENT, "Spinner: " + itemLanguages[position])
                if (itemLanguages[position] == sLanguage)
                    return
                else
                    newLanguage = itemLanguages[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

}