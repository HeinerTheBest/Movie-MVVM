package com.mobileapps.moviemvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobileapps.moviemvvm.data.api.MovieDbApi
import com.mobileapps.moviemvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsNetworkSource(private val apiServie : MovieDbApi, private val compositeDisposable: CompositeDisposable)
{

    val TAG = "MovieDetNetworkSource"
    private val _netorkState = MutableLiveData<NetworkState>()
    val networkState : LiveData<NetworkState>
        get() = _netorkState

    private val _dowloadedMovieDetailResponse = MutableLiveData<MovieDetails>()
    val dowloadedMovieDetails : LiveData<MovieDetails>
        get() = _dowloadedMovieDetailResponse

    fun fetchMovieDetails(movieId : Int)
    {
        _netorkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiServie.getMovieDetail(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _netorkState.postValue(NetworkState.LOADED)
                            _dowloadedMovieDetailResponse.postValue(it)
                        },
                        {
                            _netorkState.postValue(NetworkState.ERROR)
                            Log.e(TAG,it.message)
                        }
                    )
            )
        }catch (e : Exception)
        {
            Log.e(TAG,e.message)
        }


    }
}