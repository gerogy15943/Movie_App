package com.example.testsolution.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testsolution.data.mapper.MovieResponseMapper
import com.example.testsolution.data.paging.MoviePagingDataSource
import com.example.testsolution.data.remote.service.MovieService
import com.example.testsolution.domain.model.MovieDomain
import com.example.testsolution.domain.repository.MovieDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(private val movieService: MovieService,
                                              private val mapper: MovieResponseMapper): MovieDataSource {
    override fun getMovies(): Flow<PagingData<MovieDomain>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {MoviePagingDataSource(movieService, mapper)}
        ).flow
    }
}