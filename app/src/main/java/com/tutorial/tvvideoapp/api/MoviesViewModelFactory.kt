package com.tutorial.tvvideoapp.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoviesViewModelFactory(private val repo: MovieDetailsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) return MovieDetailsViewModel(repo) as T
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}