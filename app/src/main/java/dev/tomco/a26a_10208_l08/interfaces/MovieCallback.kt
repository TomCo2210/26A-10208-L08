package dev.tomco.a26a_10208_l08.interfaces

import dev.tomco.a26a_10208_l08.model.MovieItem

interface MovieCallback {
    fun favoriteButtonClicked(movie: MovieItem, position: Int)
}