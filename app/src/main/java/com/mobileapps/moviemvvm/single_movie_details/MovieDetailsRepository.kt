package com.mobileapps.moviemvvm.single_movie_details

import androidx.lifecycle.LiveData
import com.mobileapps.moviemvvm.data.api.MovieDbApi
import com.mobileapps.moviemvvm.data.repository.MovieDetailsNetworkSource
import com.mobileapps.moviemvvm.data.repository.NetworkState
import com.mobileapps.moviemvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable

class MovieDetailsRepository (private val apiService : MovieDbApi)
{
    lateinit var movieDetailsNetworkSource: MovieDetailsNetworkSource

    fun fetchingMovieDetails (compositeDisposable: CompositeDisposable, movieId : Int) : LiveData<MovieDetails>
    {
        movieDetailsNetworkSource = MovieDetailsNetworkSource(apiService,compositeDisposable)
        movieDetailsNetworkSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkSource.dowloadedMovieDetails
    }


    fun getMovieDetailsNetworkState() : LiveData<NetworkState>
    {
        return movieDetailsNetworkSource.networkState
    }

}