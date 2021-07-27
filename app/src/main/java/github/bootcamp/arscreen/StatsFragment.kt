package github.bootcamp.arscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import github.bootcamp.MainActivity
import github.bootcamp.R
import github.bootcamp.utils.ENABLE


class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        val topAppBar: MaterialToolbar = view.findViewById(R.id.topAppBarStats)
        topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
            (activity as MainActivity?)?.railController(ENABLE)
        }

        var person_button: ImageView = view.findViewById(R.id.acc)
        person_button.setOnClickListener {
            Toast.makeText(context?.applicationContext, "Person Statistic", Toast.LENGTH_SHORT)
                .show()
            //insertDataToDatabase()
        }


        return view
    }
    /* выдаёт NullPointerException . Почему-то не может получить application

    private fun insertDataToDatabase() {

        //----- сейчас в xml стоит текст вью, если начнёшь редачить то поменяй там на едит текст чтобы проверить робит ли это ------//

        var countCollected: EditText? = null
        var rating: EditText? = null
        var countSavedTrees: EditText? = null
        var countVisitedPlaces: EditText? = null

        countCollected = view?.findViewById(R.id.count_collected)
        rating = view?.findViewById(R.id.user_rating)
        countSavedTrees = view?.findViewById(R.id.count_saved_trees)
        countVisitedPlaces = view?.findViewById(R.id.count_visited_places)


        if (inputCheck(countCollected!!.text, rating!!.text, countVisitedPlaces!!.text)){
            val stats = Stats_Room(Integer.parseInt(countCollected.text.toString()), Integer.parseInt(countVisitedPlaces.text.toString()), Integer.parseInt(rating.text.toString()))

            // ------ проблема здесь ------//
            val mStatsViewModel: StatsViewModel = StatsViewModel(MyApplication().getApplication())
            try  {
                mStatsViewModel.addStats(stats)
                Toast.makeText(context?.applicationContext, "Successfully added!", Toast.LENGTH_SHORT).show()
            } catch (exception: Exception){
                Toast.makeText(context?.applicationContext, "App context cannot be null", Toast.LENGTH_SHORT).show()
            }
            //------- проблема здесь --------//
        }else{
            Toast.makeText(requireContext(), "Fill all", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(
        countCollected: Editable,
        rating: Editable,
        countVisitedPlaces: Editable,
    ): Boolean {
        return !(countCollected.isEmpty() && rating.isEmpty() && countVisitedPlaces.isEmpty())
    }

     */

}