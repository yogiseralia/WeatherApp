package io.github.yogiseralia.weatherapp.data.networking

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {

    private const val API_KEY = "72d2ee7ebb26555cf69dfc94fdc49fdd"
    private const val OPEN_WEATHER_MAP_API_URL = "https://api.openweathermap.org/data/2.5/"
    private const val FLICKR_IMAGES_API_URL = "https://api.flickr.com/services/rest/"


    private val _APIKeyInserter: (Interceptor.Chain) -> Response = {
        var request: Request = it.request()
        val url: HttpUrl = request.url().newBuilder()
            .addQueryParameter("appid", API_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        it.proceed(request)
    }

    private fun getClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(_APIKeyInserter).build()
    }

    private fun getRetrofit(baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getOpenWeatherAPI(): OpenWeatherAPIService {
        return getRetrofit(OPEN_WEATHER_MAP_API_URL).create(OpenWeatherAPIService::class.java)
    }

    fun getFlickrImage(): FlickrImageService {
        return getRetrofit(OPEN_WEATHER_MAP_API_URL).create(FlickrImageService::class.java)
    }
}