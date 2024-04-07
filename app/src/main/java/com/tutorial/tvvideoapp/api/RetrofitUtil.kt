package com.tutorial.tvvideoapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val API_KEY = "6a8cbba4c46d34ce6233ec6b1cb6cef6"

    val moviesApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IMovieService::class.java)

    fun getApiInstance(): IMovieService {
        return moviesApi
    }
}