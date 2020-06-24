package dev.igordias.features.base.extension

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import dev.igordias.features.base.dialog.DialogData
import dev.igordias.features.base.dialog.DialogData.*

fun Context.showDialog(dialogData: DialogData): Dialog = AlertDialog.Builder(this).run {
    setTitle(dialogData.title)
    setMessage(dialogData.message)
    resolveActions(dialogData)
    show()
}

fun AlertDialog.Builder.resolveActions(dialogData: DialogData): AlertDialog.Builder =
    dialogData.run {
        when (this) {
            is Confirmation -> setPositiveButton(confirmButton.text, confirmButton.onClickAction)
            is Error -> setNegativeButton(retryButton.text, retryButton.onClickAction)
            is Information -> setOnCancelListener { onDismissAction?.invoke() }
            is DismissibleConfirmation -> {
                setPositiveButton(confirmButton.text, confirmButton.onClickAction)
                setOnCancelListener { onDismissAction() }
            }
            is YesOrNo -> {
                setPositiveButton(yesButton.text, yesButton.onClickAction)
                setNegativeButton(noButton.text, noButton.onClickAction)
            }
        }
    }

fun AlertDialog.Builder.setPositiveButton(
    text: String,
    action: (() -> Unit)?
): AlertDialog.Builder =
    setPositiveButton(text) { _, _ -> action?.invoke() }

fun AlertDialog.Builder.setNegativeButton(
    text: String,
    action: (() -> Unit)?
): AlertDialog.Builder =
    setNegativeButton(text) { _, _ -> action?.invoke() }
