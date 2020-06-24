package dev.igordias.features.base.arch

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.igordias.features.base.dialog.DialogData
import dev.igordias.features.base.error.ErrorHandler
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel constructor(
    val errorHandler: ErrorHandler
) : ViewModel(), LifecycleObserver {

    private val _dialog = MutableLiveData<Event<DialogData>>()
    val dialog: LiveData<Event<DialogData>> get() = _dialog

    fun setDialog(dialogData: DialogData) {
        _dialog.postValue(Event(dialogData))
    }

}