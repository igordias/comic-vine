package dev.igordias.features.inject

import dev.igordias.comicvineapp.core.data.inject.INJECT_COMPONENT_REPOSITORY
import dev.igordias.features.feature.moviesboard.MoviesBoardViewModel
import org.rewedigital.katana.Component
import org.rewedigital.katana.Module
import org.rewedigital.katana.androidx.viewmodel.viewModel
import org.rewedigital.katana.dsl.get


private val INJECT_MODULE_FEATURE_MOVIES_BOARD = Module {
    viewModel { MoviesBoardViewModel(get(), get()) }
}

val INJECT_COMPONENT_FEATURE_MOVIES_BOARD = baseFeatureComponent(INJECT_MODULE_FEATURE_MOVIES_BOARD)

private fun baseFeatureComponent(featureModule: Module): Component =
    Component(
        modules = listOf(featureModule),
        dependsOn = listOf(INJECT_COMPONENT_ERROR_HANDLING, INJECT_COMPONENT_REPOSITORY)
    )