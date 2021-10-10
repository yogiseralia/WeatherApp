package io.github.yogiseralia.weatherapp.currentweather.screens

import android.widget.ImageView
import io.github.yogiseralia.weatherapp.R

internal fun ImageView.setIcon(icon: Int, isNight: Boolean = true) {

    when (icon) {
        800 ->
            if (!isNight) {
                this.setImageResource(R.drawable.ic_wi_day_clear)
            } else {
                this.setImageResource(R.drawable.ic_wi_night_clear)
            }

        801, 802, 803, 804 -> if (isNight == false) {
            this.setImageResource(R.drawable.ic_wi_day_cloudy)
        } else this.setImageResource(R.drawable.ic_wi_night_cloudy)

        741 -> if (!isNight) {
            this.setImageResource(R.drawable.ic_wi_day_fog)
        } else this.setImageResource(R.drawable.ic_wi_night_fog)

    }

}
