package io.github.yogiseralia.weatherapp.currentweather.screens

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.WeatherApp
import io.github.yogiseralia.weatherapp.currentweather.CurrentWeather
import io.github.yogiseralia.weatherapp.data.networking.APIService
import io.github.yogiseralia.weatherapp.data.networking.WeatherAPIToDBMapper
import io.github.yogiseralia.weatherapp.data.networking.WeatherRepository
import io.github.yogiseralia.weatherapp.utils.Status
import java.util.*


class CurrentWeatherFragment : Fragment() {
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
        )[CurrentWeatherViewModel::class.java]
    }
    private lateinit var txtvw_weather_type: TextView
    private lateinit var txtvw_temperature: TextView
    private lateinit var txtvw_humidity: TextView
    private lateinit var txtvw_city_name: TextView
    private lateinit var txtvw_state_name: TextView
    private lateinit var imgvw_weather: ImageView
    private lateinit var imgvw_degree: ImageView
    private lateinit var lottie_loading: LottieAnimationView
    private var currentWeather: CurrentWeather? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutView: View =
            inflater.inflate(R.layout.fragment_current_weather, container, false)
        setupUI(layoutView)
        getWeatherData()
        return layoutView
    }

    private fun setupUI(layoutView: View) {
        txtvw_weather_type = layoutView.findViewById(R.id.txtvw_weather_type)
        txtvw_temperature = layoutView.findViewById(R.id.txtvw_temperature)
        txtvw_humidity = layoutView.findViewById(R.id.txtvw_humidity)
        txtvw_city_name = layoutView.findViewById(R.id.txtvw_city_name)
        txtvw_state_name = layoutView.findViewById(R.id.txtvw_state_name)
        imgvw_weather = layoutView.findViewById(R.id.imgvw_weather)
        imgvw_degree = layoutView.findViewById(R.id.imgvw_degree)
        lottie_loading = layoutView.findViewById(R.id.lottie_loading)
    }

    private fun getWeatherData() {
        val weather = currentWeatherViewModel.getWeather(27.5530, 76.6346)
        this.activity?.let {
            weather.observe(it) {
                currentWeather = it.data
                when (it.status) {
                    is Status.SUCCESS -> {
                        Log.d(TAG, "success ${currentWeather?.temperature.toString()} K")
                        setDataOnUI()
                        imgvw_degree.visibility = VISIBLE
                        lottie_loading.visibility = GONE
                    }

                    is Status.LOADING -> {
                        imgvw_degree.visibility = GONE
                        lottie_loading.visibility = VISIBLE
                        Log.d(TAG, "loading...")
                    }

                    is Status.ERROR -> {
                        imgvw_degree.visibility = GONE
                        lottie_loading.visibility = GONE
                        Log.d(TAG, "error ${it.message}")
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDataOnUI() {
        currentWeather?.let {
            txtvw_temperature.text = it.temperature.minus(273).toInt().toString()
            txtvw_humidity.text = "HUM ${it.humidity}%"
            txtvw_weather_type.text =
                it.status.toUpperCase(Locale.getDefault())
            imgvw_weather.alpha = 0.4f
            imgvw_weather.setIcon(it.iconId, it.iconName.contains("n"))
        }

        getGeoCodedAddress()?.let {
            txtvw_city_name.text = it[0].locality
            txtvw_state_name.text = it[0].adminArea
        }
    }

    private fun getGeoCodedAddress(): MutableList<Address>? {
        var fromLocation: MutableList<Address>? = null
        try {
            fromLocation = context?.let { Geocoder(it).getFromLocation(27.5530, 76.6346, 1) }
        } catch (e: Exception) {
            Log.d(TAG, "geocoding failed")
        }
        return fromLocation
    }
}