package dev.tomco.a26a_10208_l08

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.tomco.a26a_10208_l08.adapters.MovieAdapter
import dev.tomco.a26a_10208_l08.databinding.ActivityMainBinding
import dev.tomco.a26a_10208_l08.interfaces.MovieCallback
import dev.tomco.a26a_10208_l08.model.DataManager
import dev.tomco.a26a_10208_l08.model.MovieItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieAdapter = MovieAdapter(DataManager.generateMovieList())
        movieAdapter.movieCallback = object: MovieCallback{
            override fun favoriteButtonClicked(
                movie: MovieItem,
                position: Int
            ) {
                movie.toggleFavorite()
                movieAdapter.notifyItemChanged(position)
            }

        }

        binding.mainRVList.adapter = movieAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.mainRVList.layoutManager = linearLayoutManager
    }
}