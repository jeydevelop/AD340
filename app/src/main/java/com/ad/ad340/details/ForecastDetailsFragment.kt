package com.ad.ad340.details

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.ad.ad340.*
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("MM-dd-yyyy")

class ForecastDetailsFragment : Fragment() {

    private val args: ForecastDetailsFragmentArgs by navArgs()

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_forecast_details, container, false)

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val tempText = layout.findViewById<TextView>(R.id.tempText3)
        val descriptionText = layout.findViewById<TextView>(R.id.descriptionText3)
        val dateText = layout.findViewById<TextView>(R.id.dateText)
        val forecastIcon = layout.findViewById<ImageView>(R.id.forecastIcon)

        tempText.text = formatTempForDisplay(args.temp, tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text = args.description

        dateText.text = DATE_FORMAT.format(Date(args.date * 1000))
        forecastIcon.load("http://openweathermap.org/img/wn/${args.icon}@2x.png")



        return layout
    }
}