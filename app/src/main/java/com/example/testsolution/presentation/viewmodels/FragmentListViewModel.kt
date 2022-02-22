package com.example.testsolution.presentation.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testsolution.domain.model.MovieDomain
import com.example.testsolution.domain.usecases.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentListViewModel @Inject constructor(private val useCase: GetMoviesUseCase): ViewModel() {

    val moviesLiveData = MutableLiveData<PagingData<MovieDomain>>()


    init {viewModelScope.launch(Dispatchers.IO) {
        getAllMovies().collectLatest {
            moviesLiveData.postValue(it)
        }
    }
    }

    suspend fun getAllMovies(): Flow<PagingData<MovieDomain>>{
        return useCase.invoke().map {
            it }.cachedIn(viewModelScope)
    }
}