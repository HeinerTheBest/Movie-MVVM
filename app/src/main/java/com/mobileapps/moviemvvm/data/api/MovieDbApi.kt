package com.mobileapps.moviemvvm.data.api

import com.mobileapps.moviemvvm.data.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbApi
{

    //https://api.themoviedb.org/3/movie/429203?api_key=4f9c18edc7a03e1e4444fae0a16350a1

    @GET("movie/{movieId}")
    fun getMovieDetail(@Path("movieId")id : Int) : Single<MovieDetails>

}