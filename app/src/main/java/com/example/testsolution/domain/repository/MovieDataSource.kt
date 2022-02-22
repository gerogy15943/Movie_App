package com.example.testsolution.domain.repository


import androidx.paging.PagingData
import com.example.testsolution.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    fun getMovies(): Flow<PagingData<MovieDomain>>
}