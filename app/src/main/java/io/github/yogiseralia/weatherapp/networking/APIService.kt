package io.github.yogiseralia.weatherapp.networking

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {

    private const val API_KEY = "72d2ee7ebb26555cf69dfc94fdc49fdd"
    private const val API_URL = "https://api.openweathermap.org/data/2.5/"


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

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getOpenWeatherMap(): OpenWeatherMapService {
        return getRetrofit().create(OpenWeatherMapService::class.java)
    }
}