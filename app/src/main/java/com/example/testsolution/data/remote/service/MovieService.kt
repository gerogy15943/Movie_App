package com.example.testsolution.data.remote.service

import com.example.testsolution.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("./all.json")
    suspend fun getAllMovies(@Query("offset") offset: Int): Response<MovieResponse>
}