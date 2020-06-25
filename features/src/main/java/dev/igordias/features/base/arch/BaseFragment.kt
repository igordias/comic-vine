package dev.igordias.features.base.arch

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import dev.igordias.features.base.dialog.DialogData
import dev.igordias.features.base.extension.observeEvent
import dev.igordias.features.base.extension.showDialog
import org.rewedigital.katana.KatanaTrait
import org.rewedigital.katana.android.fragment.KatanaFragment

abstract class BaseFragment : KatanaFragment(), KatanaTrait {
    abstract val viewModel: BaseViewModel

    private var activity: Activity? = null
    private var dialog: Dialog? = null

    private fun onNextDialog(dialogData: DialogData?) {
        dialogData?.apply {
            dialog?.dismiss()
            dialog = activity?.showDialog(this)
        }
    }

    override fun onInject(activity: Activity, savedInstanceState: Bundle?) {
        this.activity = activity
        lifecycle.addObserver(viewModel)
    }

    open fun subscribeUi() {
        viewModel.dialog.observeEvent(this, ::onNextDialog)
    }

}