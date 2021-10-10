package io.github.yogiseralia.weatherapp.currentweather.screens

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.db.williamchart.view.LineChartView
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.WeatherApp
import io.github.yogiseralia.weatherapp.currentweather.CurrentWeather
import io.github.yogiseralia.weatherapp.currentweather.WeekdayWeather
import io.github.yogiseralia.weatherapp.data.networking.APIService
import io.github.yogiseralia.weatherapp.data.networking.WeatherAPIToDBMapper
import io.github.yogiseralia.weatherapp.data.networking.WeatherRepository
import io.github.yogiseralia.weatherapp.preferences.SettingsPrefs
import io.github.yogiseralia.weatherapp.utils.Outcome
import io.github.yogiseralia.weatherapp.utils.Status
import io.github.yogiseralia.weatherapp.utils.TimeUtils


class CurrentWeatherFragmentNew : Fragment() {
    private val TAG = "CurrentWeatherFragment"
    private val currentWeatherViewModel: CurrentWeatherViewModel by lazy {
        ViewModelProvider(
            this,
            CurrentWeatherScreenViewModelFactory(
                WeatherRepository(
                    APIService.getOpenWeatherAPI(),
                    (activity?.application as WeatherApp).appDatabase.weatherDao(),
                    WeatherAPIToDBMapper()
                )
            )
        ).get(CurrentWeatherViewModel::class.java)
    }

    private lateinit var txtvw_weather_condition: TextView
    private lateinit var lbl_temperature_chart: TextView
    private lateinit var imgvw_weather_condition: ImageView
    private lateinit var weekly_forecast_list: RecyclerView
    private lateinit var weekday_temperature_chart: LineChartView
    private lateinit var lottie_loading: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout_view: View =
            inflater.inflate(R.layout.fragment_current_weather_new, container, false)
        setupUI(layout_view)
        getWeatherData()
        return layout_view
    }

    private fun setupUI(layout_view: View) {
        lottie_loading = layout_view.findViewById(R.id.lottie_loading)
        txtvw_weather_condition = layout_view.findViewById(R.id.txtvw_weather_condition)
        imgvw_weather_condition = layout_view.findViewById(R.id.imgvw_weather_condition)
        weekly_forecast_list = layout_view.findViewById(R.id.weekday_forecast_list)
        weekday_temperature_chart = layout_view.findViewById(R.id.weekday_temperature_chart)
        lbl_temperature_chart = layout_view.findViewById(R.id.lbl_temperature_chart)
    }

    private fun getWeatherData() {
        currentWeatherViewModel.getWeather(27.5530, 76.6346)
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    is Status.SUCCESS -> setDataOnUI(it.data)
                    is Status.LOADING -> showProgress()
                    is Status.ERROR -> showError(it)
                }
            })
    }

    private fun setDataOnUI(currentWeather: CurrentWeather?) {
        currentWeather?.let {
            txtvw_weather_condition.text = currentWeather.status
            imgvw_weather_condition.setIcon(currentWeather.iconId, TimeUtils.isNight())
            weekly_forecast_list.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            weekly_forecast_list.adapter = WeeklyForecastAdapter(currentWeather.weeklyWeather)
            setChart(currentWeather.weeklyWeather)
        }
        lottie_loading.visibility = GONE
    }

    private fun setChart(weeklyWeather: List<WeekdayWeather>) {
        lbl_temperature_chart.visibility = VISIBLE
        weekday_temperature_chart.visibility = VISIBLE
        weekday_temperature_chart.gradientFillColors =
            intArrayOf(
                ResourcesCompat.getColor(context?.resources!!, R.color.colorAccent, context?.theme),
                Color.TRANSPARENT
            )
        weekday_temperature_chart.animation.duration = 1000
//        weekday_temperature_chart.tooltip =
//            SliderTooltip().also {
//                it.color = Color.WHITE
//            }
//        weekday_temperature_chart.onDataPointTouchListener = { index, _, _ ->
            /*lineChartValue.text =
                lineSet.toList()[index]
                    .second
                    .toString()*/
//        }
        val minTemps = ArrayList<Pair<String, Float>>()
        weeklyWeather.map {
            minTemps.add(
                Pair(
                    it.formattedDate,
                    SettingsPrefs.kelvinToCelsius(it.minTemp).toInt().toFloat()
                )
            )
        }

        val maxTemps = ArrayList<Pair<String, Float>>()
        weeklyWeather.map {
            maxTemps.add(
                Pair(
                    it.formattedDate,
                    SettingsPrefs.kelvinToCelsius(it.maxTemp).toInt().toFloat()
                )
            )
        }
//        weekday_temperature_chart.animate(minTemps)
        weekday_temperature_chart.animate(maxTemps)
    }

    private fun showProgress() {
        lottie_loading.visibility = VISIBLE
    }

    private fun showError(it: Outcome<CurrentWeather?>) {
        lottie_loading.visibility = GONE
        Log.d(TAG, "error ${it.message}")
    }

//    private fun getGeoCodedAddress(): MutableList<Address>? {
//        var fromLocation: MutableList<Address>? = null
//        try {
//            fromLocation =
//                Geocoder(activity).getFromLocation(27.5530, 76.6346, 1)
//        } catch (e: Exception) {
//            Log.d(TAG, "geocoding failed")
//        }
//        return fromLocation
//    }
}