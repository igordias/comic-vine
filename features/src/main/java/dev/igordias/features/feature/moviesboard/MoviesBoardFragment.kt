package dev.igordias.features.feature.moviesboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import dev.igordias.features.base.arch.BaseFragment
import dev.igordias.features.base.extension.observeEvent
import dev.igordias.features.base.extension.unless
import dev.igordias.features.databinding.FragmentMoviesBoardBinding
import dev.igordias.features.feature.moviesboard.recycler.MovieCardsAdapter
import dev.igordias.features.inject.INJECT_COMPONENT_FEATURE_MOVIES_BOARD
import org.rewedigital.katana.Component
import org.rewedigital.katana.androidx.viewmodel.viewModel

class MoviesBoardFragment : BaseFragment() {

    override val component: Component = INJECT_COMPONENT_FEATURE_MOVIES_BOARD

    private lateinit var binding: FragmentMoviesBoardBinding

    private var movieCardsAdapter = MovieCardsAdapter()

    override val viewModel by viewModel<MoviesBoardViewModel, MoviesBoardFragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        unless(::binding.isInitialized) {
            binding = FragmentMoviesBoardBinding.inflate(inflater, container, false)
        }
        lifecycle.addObserver(viewModel)
        setupUi()
        subscribeUi()
        return binding.root
    }

    private fun setupUi() {
        with(binding.movieCards) {
            adapter = movieCardsAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.movies.observeEvent(this) {
            movieCardsAdapter.setMovies(it ?: emptyList())
        }

    }
}
