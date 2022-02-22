package com.example.testsolution.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testsolution.data.mapper.MovieResponseMapper
import com.example.testsolution.data.remote.service.MovieService
import com.example.testsolution.domain.model.MovieDomain

class MoviePagingDataSource(private val service: MovieService,
                            private val mapper: MovieResponseMapper): PagingSource<Int, MovieDomain>() {

    override fun getRefreshKey(state: PagingState<Int, MovieDomain>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomain> {
        val offset = params.key ?: 0
        return try{
            val response = service.getAllMovies(offset)
            val pagedResponse = response.body()
            val data = mapper.toMovieDomain(pagedResponse!!.resultList)

            var nextPage: Int? = null
            var prevPage: Int? = null

            if(data!!.isEmpty()) {
                nextPage = null
            }
            else {
                nextPage = offset + 20
            }
             LoadResult.Page(
                data = data.orEmpty(),
                prevKey = prevPage,
                nextKey = nextPage
            )

        } catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}