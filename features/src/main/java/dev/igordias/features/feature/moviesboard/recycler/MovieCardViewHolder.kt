package dev.igordias.features.feature.moviesboard.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.igordias.comicvineapp.core.domain.entity.Movie
import dev.igordias.features.databinding.MovieCardBinding

class MovieCardViewHolder private constructor(private val binding: MovieCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie

        Glide.with(binding.root)
            .load(movie.image.medium)
            .centerInside()
            .into(binding.movieImage)

    }

    companion object {
        fun inflate(parent: ViewGroup): MovieCardViewHolder {
            return MovieCardViewHolder(
                MovieCardBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

}