package com.example.testsolution.domain.usecases

import androidx.paging.PagingData
import com.example.testsolution.domain.model.MovieDomain
import com.example.testsolution.domain.repository.MovieDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(val dataSource: MovieDataSource) {
    suspend fun invoke(): Flow<PagingData<MovieDomain>> {
        return dataSource.getMovies()
    }
}