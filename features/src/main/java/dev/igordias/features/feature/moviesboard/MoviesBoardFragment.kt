package dev.igordias.features.feature.moviesboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.igordias.features.R
import dev.igordias.features.base.arch.BaseFragment
import dev.igordias.features.inject.INJECT_MODULE_FEATURE_MOVIES_BOARD
import org.rewedigital.katana.Component
import org.rewedigital.katana.androidx.viewmodel.viewModel

class MoviesBoardFragment : BaseFragment() {

    override val component = Component(listOf(INJECT_MODULE_FEATURE_MOVIES_BOARD))

    private val viewModel by viewModel<MoviesBoardViewModel, MoviesBoardFragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movies_board, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Implement
    }

}
