package dev.igordias.features.base.dialog

sealed class DialogData private constructor(
    open val title: String,
    open val message: String,
    open val onDismissAction: (() -> Unit)? = null
) {
    val isDismissible = onDismissAction != null

    data class Confirmation(
        override val title: String,
        override val message: String,
        val confirmButton: DialogButton
    ) : DialogData(title, message)

    data class DismissibleConfirmation(
        override val title: String,
        override val message: String,
        val confirmButton: DialogButton,
        override val onDismissAction: (() -> Unit)
    ) : DialogData(title, message, onDismissAction)

    //TODO: Get error title from Strings
    data class Error(
        override val message: String,
        val retryButton: DialogButton
    ) : DialogData(title = "ERROR", message = message)

    data class YesOrNo(
        override val title: String,
        override val message: String,
        val onYesAction: (() -> Unit),
        val onNoAction: (() -> Unit)
    ) : DialogData(title, message) {
        val yesButton = DialogButton("Yes", onYesAction)
        val noButton = DialogButton("No", onNoAction)
    }

    data class Information(
        override val title: String,
        override val message: String,
        override val onDismissAction: (() -> Unit)?
    ) : DialogData(title, message, onDismissAction)

    companion object {
        data class DialogButton(val text: String, val onClickAction: (() -> Unit))
    }

}