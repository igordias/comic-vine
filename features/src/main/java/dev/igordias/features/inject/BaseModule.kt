package dev.igordias.features.inject

import dev.igordias.features.base.error.ErrorHandler
import org.rewedigital.katana.Component
import org.rewedigital.katana.Module
import org.rewedigital.katana.dsl.singleton

private val INJECT_MODULE_ERROR_HANDLING = Module {
    singleton {
        ErrorHandler()
    }
}

val INJECT_COMPONENT_ERROR_HANDLING = Component(INJECT_MODULE_ERROR_HANDLING)