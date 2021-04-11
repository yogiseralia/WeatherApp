package io.github.yogiseralia.weatherapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "weather")
data class WeatherEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val _id: Int,

    @ColumnInfo(name = "current")
    val DBCurrent: DBCurrent,

    @ColumnInfo(name = "timezone")
    val timezone: String,

    @ColumnInfo(name = "timezone_offset")
    val timezoneOffset: Int,

    @ColumnInfo(name = "daily")
    val DBDaily: List<DBDailyItem>,

    @ColumnInfo(name = "lon")
    val lon: Double,

    @ColumnInfo(name = "hourly")
    val DBHourly: List<DBHourlyItem>,

    @ColumnInfo(name = "lat")
    val lat: Double
)

data class DBDailyItem(

    @ColumnInfo(name = "sunrise")
    val sunrise: Int,

    @ColumnInfo(name = "temp")
    val DBTemp: DBTemp,

    @ColumnInfo(name = "uvi")
    val uvi: Double,

    @ColumnInfo(name = "pressure")
    val pressure: Int,

    @ColumnInfo(name = "clouds")
    val clouds: Int,

    @ColumnInfo(name = "feels_like")
    val DBFeelsLike: DBFeelsLike,

    @ColumnInfo(name = "dt")
    val dt: Int,

    @ColumnInfo(name = "pop")
    val pop: Double,

    @ColumnInfo(name = "wind_deg")
    val windDeg: Int,

    @ColumnInfo(name = "dew_point")
    val dewPoint: Double,

    @ColumnInfo(name = "sunset")
    val sunset: Int,

    @ColumnInfo(name = "weather")
    val DBWeather: List<DBWeatherItem>,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double
)

data class DBCurrent(

    @ColumnInfo(name = "sunrise")
    val sunrise: Int,

    @ColumnInfo(name = "temp")
    val temp: Double,

    @ColumnInfo(name = "visibility")
    val visibility: Int,

    @ColumnInfo(name = "uvi")
    val uvi: Double,

    @ColumnInfo(name = "pressure")
    val pressure: Int,

    @ColumnInfo(name = "clouds")
    val clouds: Int,

    @ColumnInfo(name = "feels_like")
    val feelsLike: Double,

    @ColumnInfo(name = "wind_gust")
    val windGust: Double,

    @ColumnInfo(name = "dt")
    val dt: Int,

    @ColumnInfo(name = "wind_deg")
    val windDeg: Int,

    @ColumnInfo(name = "dew_point")
    val dewPoint: Double,

    @ColumnInfo(name = "sunset")
    val sunset: Int,

    @ColumnInfo(name = "weather")
    val DBWeather: List<DBWeatherItem>,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double
)

data class DBFeelsLike(

    @ColumnInfo(name = "eve")
    val eve: Double,

    @ColumnInfo(name = "night")
    val night: Double,

    @ColumnInfo(name = "day")
    val day: Double,

    @ColumnInfo(name = "morn")
    val morn: Double
)

data class DBHourlyItem(

    @ColumnInfo(name = "temp")
    val temp: Double = 0.0,

    @ColumnInfo(name = "visibility")
    val visibility: Int = 0,

    @ColumnInfo(name = "uvi")
    val uvi: Double = 0.0,

    @ColumnInfo(name = "pressure")
    val pressure: Int = 0,

    @ColumnInfo(name = "clouds")
    val clouds: Int = 0,

    @ColumnInfo(name = "feels_like")
    val feelsLike: Double = 0.0,

    @ColumnInfo(name = "wind_gust")
    val windGust: Double = 0.0,

    @ColumnInfo(name = "dt")
    val dt: Int = 0,

    @ColumnInfo(name = "pop")
    val pop: Double = 0.0,

    @ColumnInfo(name = "wind_deg")
    val windDeg: Int = 0,

    @ColumnInfo(name = "dew_point")
    val dewPoint: Double = 0.0,

    @ColumnInfo(name = "weather")
    val DBWeather: List<DBWeatherItem>,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double
)

data class DBTemp(

    @ColumnInfo(name = "min")
    val min: Double,

    @ColumnInfo(name = "max")
    val max: Double,

    @ColumnInfo(name = "eve")
    val eve: Double,

    @ColumnInfo(name = "night")
    val night: Double,

    @ColumnInfo(name = "day")
    val day: Double,

    @ColumnInfo(name = "morn")
    val morn: Double
)

data class DBWeatherItem(

    @ColumnInfo(name = "icon")
    val icon: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "main")
    val main: String,

    @ColumnInfo(name = "id")
    val id: Int
)

// below are type converters //

class CurrentConverter {

    @TypeConverter
    fun fromCurrent(value: DBCurrent): String {
        val gson = Gson()
        val type = object : TypeToken<DBCurrent>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCurrent(value: String): DBCurrent {
        val gson = Gson()
        val type = object : TypeToken<DBCurrent>() {}.type
        return gson.fromJson(value, type)
    }
}

class TempConverter {

    @TypeConverter
    fun fromTemp(value: DBTemp): String {
        val gson = Gson()
        val type = object : TypeToken<DBTemp>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTemp(value: String): DBTemp {
        val gson = Gson()
        val type = object : TypeToken<DBTemp>() {}.type
        return gson.fromJson(value, type)
    }
}

class FeelsLikeConverter {

    @TypeConverter
    fun fromFeelsLike(value: DBFeelsLike): String {
        val gson = Gson()
        val type = object : TypeToken<DBFeelsLike>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toFeelsLike(value: String): DBFeelsLike {
        val gson = Gson()
        val type = object : TypeToken<DBFeelsLike>() {}.type
        return gson.fromJson(value, type)
    }
}

class DailyItemConverter {

    @TypeConverter
    fun fromCurrent(value: List<DBDailyItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<DBDailyItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCurrent(value: String): List<DBDailyItem> {
        val gson = Gson()
        val type = object : TypeToken<List<DBDailyItem>>() {}.type
        return gson.fromJson(value, type)
    }
}

class HourlyItemConverter {

    @TypeConverter
    fun fromHourlyItemList(value: List<DBHourlyItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<DBHourlyItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toHourlyItemList(value: String): List<DBHourlyItem> {
        val gson = Gson()
        val type = object : TypeToken<List<DBHourlyItem>>() {}.type
        return gson.fromJson(value, type)
    }
}

class WeatherItemConverter {

    @TypeConverter
    fun fromWeatherItemList(value: List<DBWeatherItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<DBWeatherItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWeatherItemList(value: String): List<DBWeatherItem> {
        val gson = Gson()
        val type = object : TypeToken<List<DBWeatherItem>>() {}.type
        return gson.fromJson(value, type)
    }
}