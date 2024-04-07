package com.tutorial.tvvideoapp.models.cast

data class MovieCastDataModel(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)