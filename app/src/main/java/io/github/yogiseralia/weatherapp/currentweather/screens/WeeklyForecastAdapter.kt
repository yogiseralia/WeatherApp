package io.github.yogiseralia.weatherapp.currentweather.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.yogiseralia.weatherapp.R
import io.github.yogiseralia.weatherapp.currentweather.WeekdayWeather
import io.github.yogiseralia.weatherapp.preferences.SettingsPrefs

class WeeklyForecastAdapter(private val weeklyWeather: List<WeekdayWeather>) :
    RecyclerView.Adapter<WeeklyForecastAdapter.WeekDayViewHolder>() {

    class WeekDayViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
        private val forecastIcon: ImageView =
            layout.findViewById(R.id.list_item_imgvw_weekday_forecast)
        private val forecastMaxTemp: TextView =
            layout.findViewById(R.id.list_item_txtvw_max_temp)
        private val forecastMinTemp: TextView =
            layout.findViewById(R.id.list_item_txtvw_min_temp)
        private val forecastDate: TextView =
            layout.findViewById(R.id.list_item_txtvw_weekday_date)
        private val forecastText: TextView =
            layout.findViewById(R.id.list_item_txtvw_weekday_forecast)
        private val forecastRainPercent: TextView =
            layout.findViewById(R.id.list_item_txtvw_weekday_rain_percent)
        private val forecastWindSpeed: TextView =
            layout.findViewById(R.id.list_item_txtvw_weekday_wind)
        private val forecastUVPercent: TextView =
            layout.findViewById(R.id.list_item_txtvw_weekday_uv)

        fun bind(weekdayWeather: WeekdayWeather) {
            forecastIcon.setIcon(weekdayWeather.iconId, false)
            forecastMaxTemp.text = SettingsPrefs.getInSelectedTempUnit(weekdayWeather.maxTemp)
            forecastMinTemp.text = SettingsPrefs.getInSelectedTempUnit(weekdayWeather.minTemp)
            forecastDate.text = weekdayWeather.formattedTime
            forecastText.text = weekdayWeather.descriptiveCondition
            forecastText.text = weekdayWeather.descriptiveCondition
            forecastRainPercent.text = "${weekdayWeather.precipitation_percent.toInt()}%"
            forecastWindSpeed.text = "${weekdayWeather.windSpeed.toInt()}m/s"
            forecastUVPercent.text = "${weekdayWeather.uvi.toInt()}%"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekDayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_weekday_weather_forecart, parent, false)
        return WeekDayViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeekDayViewHolder, position: Int) {
        holder.bind(weeklyWeather[position])
    }

    override fun getItemCount(): Int = weeklyWeather.size
}