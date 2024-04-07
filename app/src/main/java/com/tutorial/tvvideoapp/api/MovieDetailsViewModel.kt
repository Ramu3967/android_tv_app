package com.tutorial.tvvideoapp.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tutorial.tvvideoapp.models.cast.MovieCastDataModel
import com.tutorial.tvvideoapp.models.moviedetails.MovieDetailsDataModel

class MovieDetailsViewModel(val repo: MovieDetailsRepo): ViewModel() {
    val movieDetailsLV= MutableLiveData<MovieDetailsDataModel>()
    val movieCastDetailsLV = MutableLiveData<MovieCastDataModel>()

    suspend fun getMovieDetailsById(id: Int){
        val movieResponse = repo.getMovieDetails(id)
        if(movieResponse.isSuccessful){
            movieDetailsLV.postValue(movieResponse.body())
        }
    }

    suspend fun getMovieCastDetailsById(id: Int){
        val creditResponse = repo.getMovieCredits(id)
        if(creditResponse.isSuccessful){
            movieCastDetailsLV.postValue(creditResponse.body())
        }
    }
}