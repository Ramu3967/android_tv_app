package com.tutorial.tvvideoapp.api

import com.tutorial.tvvideoapp.api.RetrofitUtil.API_KEY

class MovieDetailsRepo(val api: IMovieService) {
    suspend fun getMovieDetails(id: Int) = api.getMovieDetails(id, API_KEY)
    suspend fun getMovieCredits(id: Int) = api.getCreditDetails(id, API_KEY)
}