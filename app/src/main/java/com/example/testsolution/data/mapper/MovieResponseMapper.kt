package com.example.testsolution.data.mapper

import com.example.testsolution.data.model.Movie
import com.example.testsolution.domain.model.MovieDomain

class MovieResponseMapper {
    fun toMovieDomain(list: List<Movie>): List<MovieDomain>{
        val temp = arrayListOf<MovieDomain>()
         list.forEach {
             temp.add(MovieDomain(title = it.title, description = it.description, image_url = it.multimedia.src))
         }
            return temp
        }
    }