package dev.igordias.features.feature.moviesboard

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.igordias.features.R
import dev.igordias.features.base.arch.BaseFragment
import dev.igordias.features.base.extension.observeEvent
import dev.igordias.features.databinding.FragmentMoviesBoardBinding
import dev.igordias.features.inject.INJECT_COMPONENT_FEATURE_MOVIES_BOARD
import org.rewedigital.katana.Component
import org.rewedigital.katana.androidx.viewmodel.viewModel

class MoviesBoardFragment : BaseFragment() {

    override val component: Component = INJECT_COMPONENT_FEATURE_MOVIES_BOARD

    private lateinit var binding: FragmentMoviesBoardBinding

    override val viewModel by viewModel<MoviesBoardViewModel, MoviesBoardFragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_board, container, false)
        subscribeUi()
        return binding.root
    }
}
