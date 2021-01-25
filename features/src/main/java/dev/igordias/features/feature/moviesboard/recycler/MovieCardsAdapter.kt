package dev.igordias.features.feature.moviesboard.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.igordias.comicvineapp.core.domain.entity.Movie

class MovieCardsAdapter : RecyclerView.Adapter<MovieCardViewHolder>() {

    private var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        return MovieCardViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    internal fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}