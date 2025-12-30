package dev.tomco.a26a_10208_l08.adapters

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.tomco.a26a_10208_l08.R
import dev.tomco.a26a_10208_l08.databinding.MovieItemBinding
import dev.tomco.a26a_10208_l08.interfaces.MovieCallback
import dev.tomco.a26a_10208_l08.model.MovieItem
import dev.tomco.a26a_10208_l08.utilities.Constants
import dev.tomco.a26a_10208_l08.utilities.ImageLoader
import dev.tomco.a26a_10208_l08.utilities.TimeFormatter
import java.time.format.DateTimeFormatter
import kotlin.math.max

class MovieAdapter(private val movies: List<MovieItem>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieCallback: MovieCallback? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = MovieItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        with(holder) {
            with(getItem(position)) {
                binding.movieLBLTitle.text = name
                binding.movieLBLReleaseDate.text = releaseDate.format(
                    DateTimeFormatter.ofPattern("dd-MMM-yyyy")
                )
                binding.movieLBLDuration.text = TimeFormatter.formatTime(length)
                binding.movieLBLGenres.text = genre.joinToString(", ")
                binding.movieLBLActors.text = actors.joinToString(", ")
                binding.movieLBLOverview.text = overview
                binding.movieRBRating.rating = rating / 2
                ImageLoader.getInstance().loadImage(
                    poster,
                    binding.movieIMGPoster
                )
                if (isFavorite) binding.movieIMGFavorite.setImageResource(R.drawable.heart)
                else binding.movieIMGFavorite.setImageResource(R.drawable.empty_heart)
                binding.movieCVData.setOnClickListener {
                    val animatorSet = ArrayList<ObjectAnimator>()
                    if (isCollapsed) {
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLGenres,
                                        "maxLines",
                                        binding.movieLBLGenres.lineCount
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLGenres.lineCount - Constants.TextLengths.GENRES_MIN_LINES).toLong(),
                                            0L
                                        ) * 50L)
                                    )
                            )
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLActors,
                                        "maxLines",
                                        binding.movieLBLActors.lineCount
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLActors.lineCount - Constants.TextLengths.ACTORS_MIN_LINES).toLong(),
                                            0L
                                        ) * 50L)
                                    )
                            )
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLOverview,
                                        "maxLines",
                                        binding.movieLBLOverview.lineCount
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLOverview.lineCount - Constants.TextLengths.OVERVIEW_MIN_LINES).toLong(),
                                            0L
                                        ) * 50L)
                                    )
                            )
                    } else {
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLGenres,
                                        "maxLines",
                                        Constants.TextLengths.GENRES_MIN_LINES
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLGenres.lineCount - Constants.TextLengths.GENRES_MIN_LINES).toLong(),
                                            0L
                                        ) * 50L)
                                    )
                            )
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLActors,
                                        "maxLines",
                                        Constants.TextLengths.ACTORS_MIN_LINES
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLActors.lineCount - Constants.TextLengths.ACTORS_MIN_LINES).toLong(),
                                            0L
                                        ) * 50L)
                                    )
                            )
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLOverview,
                                        "maxLines",
                                        Constants.TextLengths.OVERVIEW_MIN_LINES
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLOverview.lineCount - Constants.TextLengths.OVERVIEW_MIN_LINES).toLong(),
                                            0L
                                        ) * 50L)
                                    )
                            )
                    }
                    toggleCollapsed()
                    animatorSet
                        .forEach { animator -> animator.start() }
                }


            }
        }
    }

    override fun getItemCount(): Int = movies.size
    fun getItem(position: Int): MovieItem = movies[position]


    inner class MovieViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.movieIMGFavorite.setOnClickListener {
                movieCallback?.favoriteButtonClicked(
                    getItem(absoluteAdapterPosition),
                    absoluteAdapterPosition
                )
            }
        }
    }
}