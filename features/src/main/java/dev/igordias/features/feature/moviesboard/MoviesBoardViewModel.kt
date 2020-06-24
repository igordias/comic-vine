package dev.igordias.features.feature.moviesboard

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
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

    private var _testMessage: MutableLiveData<Event<String>> = MutableLiveData()
    val testMessage: LiveData<Event<String>> get() = _testMessage

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getAllMovies()
            _testMessage.postValue(Event(result.first().name))
        }.invokeOnCompletion { throwable ->
            throwable?.let {
                setDialog(errorHandler.handle(it) {})
            }
        }
    }
}
