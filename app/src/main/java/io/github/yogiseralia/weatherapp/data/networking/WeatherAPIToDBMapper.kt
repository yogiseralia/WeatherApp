package io.github.yogiseralia.weatherapp.data.networking

import io.github.yogiseralia.weatherapp.data.db.*
import io.github.yogiseralia.weatherapp.utils.mapper.ListMapperImpl
import io.github.yogiseralia.weatherapp.utils.mapper.Mapper

open class WeatherAPIToDBMapper {
    private fun NWCurrent?.toCurrentRecord(): DBCurrent = DBCurrent(
        this?.sunrise ?: 0,
        this?.temp ?: 0.0,
        this?.visibility ?: 0,
        this?.uvi ?: 0.0,
        this?.pressure ?: 0,
        this?.clouds ?: 0,
        this?.feelsLike ?: 0.0,
        this?.windGust ?: 0.0,
        this?.dt ?: 0,
        this?.windDeg ?: 0,
        this?.dewPoint ?: 0.0,
        this?.sunset ?: 0,
        weatherItemListMapper.map(this?.NWWeather.orEmpty()),
        this?.humidity ?: 0,
        this?.windSpeed ?: 0.0
    )

    fun mapWeatherToDB(input: OneCallAPIResponse): WeatherEntity {
        return WeatherEntity(
            0,
            input.NWCurrent.toCurrentRecord(),
            input.timezone ?: "",
            input.timezoneOffset ?: 0,
            input.NWDaily.orEmpty().toDailyItemRecord(),
            input.lon ?: 0.0,
            input.NWHourly.orEmpty().toHourlyItemRecord(),
            input.lat ?: 0.0
        )
    }

    private fun List<NWDailyItem?>.toDailyItemRecord(): List<DBDailyItem> {
        return dailyListListMapper.map(this)
    }

    private fun List<NWHourlyItem?>.toHourlyItemRecord(): List<DBHourlyItem> {
        return hourlyListListMapper.map(this)
    }

    private val hourlyListListMapper: ListMapperImpl<NWHourlyItem?, DBHourlyItem> =
        ListMapperImpl(object :
            Mapper<NWHourlyItem?, DBHourlyItem> {
            override fun map(input: NWHourlyItem?): DBHourlyItem {
                return DBHourlyItem(
                    input?.temp ?: 0.0,
                    input?.visibility ?: 0,
                    input?.uvi ?: 0.0,
                    input?.pressure ?: 0,
                    input?.clouds ?: 0,
                    input?.feelsLike ?: 0.0,
                    input?.windGust ?: 0.0,
                    input?.dt ?: 0,
                    input?.pop ?: 0.0,
                    input?.windDeg ?: 0,
                    input?.dewPoint ?: 0.0,
                    weatherItemListMapper.map(input?.NWWeather.orEmpty()),
                    input?.humidity ?: 0,
                    input?.windSpeed ?: 0.0
                )
            }
        })

    private val dailyListListMapper: ListMapperImpl<NWDailyItem?, DBDailyItem> =
        ListMapperImpl(object :
            Mapper<NWDailyItem?, DBDailyItem> {
            override fun map(input: NWDailyItem?): DBDailyItem {
                return DBDailyItem(
                    input?.sunrise ?: 0,
                    input?.NWTemp.toTempRecord(),
                    input?.uvi ?: 0.0,
                    input?.pressure ?: 0,
                    input?.clouds ?: 0,
                    input?.NWFeelsLike.toFeelsLikeRecord(),
                    input?.dt ?: 0,
                    input?.pop ?: 0.0,
                    input?.windDeg ?: 0,
                    input?.dewPoint ?: 0.0,
                    input?.sunset ?: 0,
                    weatherItemListMapper.map(input?.NWWeather.orEmpty()),
                    input?.humidity ?: 0,
                    input?.windSpeed ?: 0.0
                )
            }
        })

    val weatherItemListMapper: ListMapperImpl<NWWeatherItem?, DBWeatherItem> =
        ListMapperImpl(object :
            Mapper<NWWeatherItem?, DBWeatherItem> {
            override fun map(input: NWWeatherItem?): DBWeatherItem {
                return DBWeatherItem(
                    input?.icon ?: "",
                    input?.description ?: "",
                    input?.main ?: "",
                    input?.id ?: 0
                )
            }
        })

    private fun NWFeelsLike?.toFeelsLikeRecord(): DBFeelsLike {
        return DBFeelsLike(
            this?.eve ?: 0.0,
            this?.night ?: 0.0,
            this?.day ?: 0.0,
            this?.morn ?: 0.0
        )
    }

    private fun NWTemp?.toTempRecord(): DBTemp {
        return DBTemp(
            this?.min ?: 0.0,
            this?.max ?: 0.0,
            this?.eve ?: 0.0,
            this?.night ?: 0.0,
            this?.day ?: 0.0,
            this?.morn ?: 0.0
        )
    }
}