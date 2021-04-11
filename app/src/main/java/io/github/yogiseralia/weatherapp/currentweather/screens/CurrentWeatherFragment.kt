package io.github.yogiseralia.weatherapp.currentweather.screens

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.WeatherApp
import io.github.yogiseralia.weatherapp.currentweather.CurrentWeather
import io.github.yogiseralia.weatherapp.data.networking.APIService
import io.github.yogiseralia.weatherapp.data.networking.WeatherAPIToDBMapper
import io.github.yogiseralia.weatherapp.data.networking.WeatherRepository
import io.github.yogiseralia.weatherapp.main.CurrentWeatherScreenViewModelFactory
import io.github.yogiseralia.weatherapp.utils.Status
import java.util.*

class CurrentWeatherFragment : Fragment() {
    private val TAG = "CurrentWeatherFragment"
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel
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
        val layout_view: View =
            inflater.inflate(R.layout.fragment_current_weather, container, false)
        txtvw_weather_type = layout_view.findViewById(R.id.txtvw_weather_type)
        txtvw_temperature = layout_view.findViewById(R.id.txtvw_temperature)
        txtvw_humidity = layout_view.findViewById(R.id.txtvw_humidity)
        txtvw_city_name = layout_view.findViewById(R.id.txtvw_city_name)
        txtvw_state_name = layout_view.findViewById(R.id.txtvw_state_name)
        imgvw_weather = layout_view.findViewById(R.id.imgvw_weather)
        imgvw_degree = layout_view.findViewById(R.id.imgvw_degree)
        lottie_loading = layout_view.findViewById(R.id.lottie_loading)

        setupViewModel()
        getWeatherData()
        return layout_view
    }

    private fun getWeatherData() {
        val weather = currentWeatherViewModel.getWeather(27.5530, 76.6346)
        this.activity?.let {
            weather.observe(it, Observer {
                currentWeather = it.data
                when (it.status) {
                    is Status.SUCCESS -> {
                        Log.d(TAG, "success ${currentWeather?.temperature.toString()} K")
                        setDataOnUI(
                            currentWeather,
                            txtvw_temperature,
                            txtvw_humidity,
                            txtvw_weather_type,
                            txtvw_city_name,
                            txtvw_state_name,
                            imgvw_weather
                        )
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
            })
        }
    }

    private fun setDataOnUI(
        currentWeather: CurrentWeather?,
        txtvw_temperature: TextView,
        txtvw_humidity: TextView,
        txtvw_weather_type: TextView,
        txtvw_city_name: TextView,
        txtvw_state_name: TextView,
        imgvw_weather: ImageView
    ) {
        txtvw_temperature.text = currentWeather?.temperature?.minus(273)?.toInt().toString()
        txtvw_humidity.text = "HUM ${currentWeather?.humidity.toString()}%"
        txtvw_weather_type.text =
            currentWeather?.status?.toUpperCase(Locale.getDefault()).toString()
        imgvw_weather.alpha = 0.4f
        imgvw_weather.setIcon(currentWeather?.iconId, currentWeather?.iconName?.contains("n"))
        try {
            val fromLocation: MutableList<Address> =
                Geocoder(activity).getFromLocation(27.5530, 76.6346, 1)
            txtvw_city_name.text = fromLocation.get(0).locality
            txtvw_state_name.text = fromLocation.get(0).adminArea
        } catch (e: Exception) {
            Log.d(TAG, "geocoding failed")
        }
    }

    private fun setupViewModel() {
        currentWeatherViewModel = ViewModelProvider(
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
}

private fun ImageView.setIcon(icon: Int?, isNight: Boolean?) {

    when (icon) {
        800 ->
            if (isNight == false) {
                this.setImageResource(R.drawable.ic_wi_day_clear)
            } else {
                this.setImageResource(R.drawable.ic_wi_night_clear)
            }

        801, 802, 803, 804 -> if (isNight == false) {
            this.setImageResource(R.drawable.ic_wi_day_cloudy)
        } else this.setImageResource(R.drawable.ic_wi_night_cloudy)

        741 -> if (isNight == false) {
            this.setImageResource(R.drawable.ic_wi_day_fog)
        } else this.setImageResource(R.drawable.ic_wi_night_fog)
    }

}
