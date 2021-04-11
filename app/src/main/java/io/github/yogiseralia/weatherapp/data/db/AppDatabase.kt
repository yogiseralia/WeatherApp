package io.github.yogiseralia.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    WeatherItemConverter::class,
    CurrentConverter::class,
    TempConverter::class,
    FeelsLikeConverter::class,
    DailyItemConverter::class,
    HourlyItemConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}