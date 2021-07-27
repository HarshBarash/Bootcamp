package github.bootcamp.settingsscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import github.bootcamp.MainActivity
import github.bootcamp.R
import github.bootcamp.utils.ENABLE
import github.bootcamp.utils.LOG_SETTINGS_FRAGMENT

class SettingsFragment : Fragment(), View.OnClickListener {

    private val items                           = listOf("English", "Russian", "German")

    private lateinit var etName                 : TextInputEditText
    private lateinit var etUsername             : TextInputEditText
    private lateinit var topAppBar              : MaterialToolbar
    private lateinit var tfLanguage             : TextInputLayout

    private lateinit var clOtherColors          : ConstraintLayout
    private lateinit var vBasicColorLightGreen  : View
    private lateinit var vBasicColorLightBlue   : View
    private lateinit var vBasicColorBeige       : View

    /* Last Change: HellGuy39 - 26.07.21
     * Comment: Хорошо подумай перед тем как лезть в моё болото
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.v(LOG_SETTINGS_FRAGMENT, "onCreateView called")

        val rootView            = inflater.inflate(R.layout.fragment_settings, container, false)

        //Навигация дальше
//        view.findViewById<Button>(R.id.signup_btn).setOnClickListener {
//            findNavController().navigate(R.id.action_register_to_registered)
//        }

        vBasicColorLightGreen   = rootView.findViewById(R.id.vBasicColorLightGreen)
        vBasicColorBeige        = rootView.findViewById(R.id.vBasicColorBeige)
        vBasicColorLightBlue    = rootView.findViewById(R.id.vBasicColorLightBlue)
        clOtherColors           = rootView.findViewById(R.id.clOtherColors)

        topAppBar               = rootView.findViewById(R.id.topAppBar)
        etName                  = rootView.findViewById(R.id.etName)
        etUsername              = rootView.findViewById(R.id.etUsername)
        tfLanguage              = rootView.findViewById(R.id.tfLanguage)

        listenerSetter()

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (tfLanguage.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v(LOG_SETTINGS_FRAGMENT, "onDestroyView called")

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.vBasicColorLightGreen  -> {}
            R.id.vBasicColorLightBlue   -> {}
            R.id.vBasicColorBeige       -> {}
        }
    }

    private fun listenerSetter() {
        vBasicColorLightGreen.setOnClickListener(this)
        vBasicColorLightBlue.setOnClickListener (this)
        vBasicColorBeige.setOnClickListener     (this)
        clOtherColors.setOnClickListener        (this)
        topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
            (activity as MainActivity?)?.railController(ENABLE)
        }
    }
}