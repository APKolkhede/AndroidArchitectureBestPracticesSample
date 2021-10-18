package com.techtest.core.responses

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("page") val page: Long,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_results") val totalResults: Long,
    @SerializedName("total_pages") val totalPages: Long
)
