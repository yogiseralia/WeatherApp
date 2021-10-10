package io.github.yogiseralia.weatherapp.utils

import android.os.Build
import java.time.Instant
import java.time.ZoneId
import java.util.*

object TimeUtils {
    fun isNight(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant.now().atZone(ZoneId.systemDefault()).hour > 18
        } else {
            Calendar.getInstance(TimeZone.getDefault()).get(Calendar.HOUR_OF_DAY) > 18
        }
    }

    fun getFormattedTime(onlyTime: Int, addNoOfDays: Int): String {
        val calendarCurrent = Calendar.getInstance(Locale.getDefault())
        val calendarToDate = Calendar.getInstance(Locale.getDefault())
        calendarToDate.time = Date(onlyTime.toLong())
        calendarToDate[Calendar.DAY_OF_MONTH] = calendarCurrent[Calendar.DAY_OF_MONTH]
        calendarToDate[Calendar.MONTH] = calendarCurrent[Calendar.MONTH]
        calendarToDate[Calendar.YEAR] = calendarCurrent[Calendar.YEAR]
        calendarToDate.add(Calendar.DAY_OF_YEAR, addNoOfDays)

        val dateFormat = java.text.SimpleDateFormat("EEE MMM dd yyyy", Locale.getDefault())
        return dateFormat.format(calendarToDate.time)
    }

    fun getFormattedDate(addNoOfDays: Int): String {
        val calendarCurrent = Calendar.getInstance(Locale.getDefault())
        calendarCurrent.add(Calendar.DAY_OF_YEAR, addNoOfDays)
        val dateFormat = java.text.SimpleDateFormat("dd/MM", Locale.getDefault())
        return dateFormat.format(calendarCurrent.time)
    }
}
