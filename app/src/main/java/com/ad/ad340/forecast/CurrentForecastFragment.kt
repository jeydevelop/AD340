package com.ad.ad340.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ad.ad340.*
import com.ad.ad340.details.ForecastDetailsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * A simple [Fragment] subclass.
 */
class CurrentForecastFragment : Fragment() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepository = ForecastRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)

        val zipcode = arguments?.getString(KEY_ZIPCODE) ?: ""


        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())



        val locationEntryButton: FloatingActionButton = view.findViewById(R.id.locationEntryButton)
        locationEntryButton.setOnClickListener {
            appNavigator.navigateToLocationEntry()
        }

        val forecastList: RecyclerView = view.findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(requireContext())
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) {forecast ->
            showForecastDetails(forecast)
        }
        forecastList.adapter = dailyForecastAdapter


        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            //update our list adapter
            dailyForecastAdapter.submitList(forecastItems)

        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)

        forecastRepository.loadForecast(zipcode)

        return view
    }

    private fun showForecastDetails(forecast: DailyForecast) {
      appNavigator.navigateToForecastDetails(forecast)
    }

    companion object {
        const val KEY_ZIPCODE = "key_zipcode"

        fun newInstance(zipcode: String): CurrentForecastFragment {
            val fragment = CurrentForecastFragment()

            val args = Bundle()
            args.putString(KEY_ZIPCODE, zipcode)
            fragment.arguments = args

            return fragment
        }
    }

}

