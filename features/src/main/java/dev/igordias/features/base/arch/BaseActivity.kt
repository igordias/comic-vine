package dev.igordias.features.base.arch

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import dev.igordias.features.base.dialog.DialogData
import dev.igordias.features.base.extension.observeEvent
import dev.igordias.features.base.extension.showDialog
import org.rewedigital.katana.KatanaTrait

abstract class BaseActivity : AppCompatActivity(), KatanaTrait {
    abstract val viewModel: BaseViewModel

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        subscribeUi()
    }

    open fun subscribeUi() {
        viewModel.dialog.observeEvent(this, ::onNextDialog)
    }

    private fun onNextDialog(dialogData: DialogData?) {
        dialog?.dismiss()
        dialog = dialogData?.let { showDialog(it) }
    }


}