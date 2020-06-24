package dev.igordias.features.base.error

import android.util.Log
import dev.igordias.comicvineapp.core.domain.util.RequestException
import dev.igordias.features.base.dialog.DialogData

class ErrorHandler {
    private val HANDLED_ERROR_TAG = "HANDLED_ERROR"

    fun handle(throwable: Throwable, action: (() -> Unit)? = null): DialogData {
        Log.w(HANDLED_ERROR_TAG, throwable)
        return when (throwable) {
            is RequestException -> DialogData.Error(throwable.message, buildRetryButton(action))
            else -> DialogData.Information("ERROR", "Unexpected error", action)
        }
    }

    private fun buildRetryButton(action: (() -> Unit)?) =
        DialogData.Companion.DialogButton("Retry") { action?.invoke() }

}