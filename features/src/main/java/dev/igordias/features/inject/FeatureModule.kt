package dev.igordias.features.inject

import dev.igordias.features.feature.moviesboard.MoviesBoardFragment
import dev.igordias.features.feature.moviesboard.MoviesBoardViewModel
import org.rewedigital.katana.Module
import org.rewedigital.katana.androidx.fragment.KatanaFragmentFactory
import org.rewedigital.katana.androidx.viewmodel.viewModel
import org.rewedigital.katana.dsl.component
import org.rewedigital.katana.dsl.get
import org.rewedigital.katana.dsl.singleton

val INJECT_MODULE_FEATURE_MOVIES_BOARD = Module {
    singleton {
        KatanaFragmentFactory(component)
            .handlesFragment<MoviesBoardFragment>()
    }
    viewModel { MoviesBoardViewModel(get()) }
}