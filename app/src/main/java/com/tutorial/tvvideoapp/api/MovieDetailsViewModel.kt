package com.tutorial.tvvideoapp.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tutorial.tvvideoapp.models.moviedetails.MovieDetailsDataModel

class MovieDetailsViewModel(val repo: MovieDetailsRepo): ViewModel() {
    val movieDetailsLV= MutableLiveData<MovieDetailsDataModel>()


    suspend fun getMovieDetailsById(id: Int){
        val k = repo.getMovieDetails(id)
        if(k.isSuccessful){
            movieDetailsLV.value = k.body()
        }
    }
}