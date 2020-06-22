package dev.igordias.features.base.arch

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.igordias.features.base.dialog.DialogData

class BaseViewModel: ViewModel(), LifecycleObserver {
    private val _dialog = MutableLiveData<Event<DialogData>>()
    val dialog: LiveData<Event<DialogData>> get() = _dialog
}