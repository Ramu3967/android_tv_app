package com.tutorial.tvvideoapp.api

import com.tutorial.tvvideoapp.models.cast.MovieCastDataModel
import com.tutorial.tvvideoapp.models.moviedetails.MovieDetailsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieService {

    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int, @Query("api_key") apiKey: String): Response<MovieDetailsDataModel>

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditDetails(
        @Path("movie_id") id: Int, @Query("api_key") apiKey: String): Response<MovieCastDataModel>


}