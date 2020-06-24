package dev.igordias.features.feature.moviesboard

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import dev.igordias.comicvineapp.core.domain.entity.Movie
import dev.igordias.comicvineapp.core.domain.repository.MovieRepository
import dev.igordias.features.base.arch.BaseViewModel
import dev.igordias.features.base.arch.Event
import dev.igordias.features.base.error.ErrorHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesBoardViewModel constructor(
    private val repository: MovieRepository,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    private var _movies: MutableLiveData<Event<List<Movie>>> = MutableLiveData()
    val movies: LiveData<Event<List<Movie>>> get() = _movies

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        fetchMovies()
    }

    private fun fetchMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAllMovies().let {
                _movies.postValue(Event(it))
            }
        }.invokeOnCompletion {
            it?.let { onError(it, ::fetchMovies) }
        }
    }

}
