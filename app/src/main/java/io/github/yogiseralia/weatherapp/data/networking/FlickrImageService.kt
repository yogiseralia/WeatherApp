package io.github.yogiseralia.weatherapp.data.networking

import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrImageService {
    @GET("")
    suspend fun imageTextSearch(
        @Query("method") method: String = "flickr.photos.search",
        @Query("text") cityWithCountry: String,
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("per_page") per_page: Int = 1,
        @Query("format") format: String = "json",
        @Query("api_key") apiKey: String
    ): FlickImageSearchResponse
}

