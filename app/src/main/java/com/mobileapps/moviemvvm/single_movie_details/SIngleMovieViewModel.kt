package com.mobileapps.moviemvvm.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobileapps.moviemvvm.data.repository.NetworkState
import com.mobileapps.moviemvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class SIngleMovieViewModel(private val movieDetailsRepository: MovieDetailsRepository,val movieId : Int) : ViewModel()
{
    private val compositeDisposable = CompositeDisposable()

    val movieDetails : LiveData<MovieDetails> by lazy {
        movieDetailsRepository.fetchingMovieDetails(compositeDisposable,movieId)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieDetailsRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}