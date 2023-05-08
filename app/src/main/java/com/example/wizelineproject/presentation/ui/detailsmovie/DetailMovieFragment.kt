package com.example.wizelineproject.presentation.ui.detailsmovie

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.local.entities.Movie
import com.example.wizelineproject.databinding.FragmentDetailMovieBinding
import com.example.wizelineproject.presentation.MoviesViewModel
import com.example.wizelineproject.presentation.ui.detailsmovie.compose.DetailScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by activityViewModels()
    private lateinit var currentMovie: com.example.local.entities.Movie
    private var localGenreList: List<String> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        DetailScreen(selectedMovie = currentMovie, genresList = localGenreList)
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable("selectedMovie", com.example.local.entities.Movie::class.java)?.let { model ->
                    currentMovie = model
                }
            } else {
                it.getParcelable<com.example.local.entities.Movie>("selectedMovie")?.let { model ->
                    currentMovie = model
                }
            }
        }
//        currentMovie = arguments?.getParcelable("selectedMovie", Movie::class.java)!!
        viewModel.getMovieWithGenres(currentMovieId = currentMovie.id)
        observeMovieWithGenre()
    }

    private fun fillGenresMovie(genreList: List<String>) {
        localGenreList = genreList
    }

    private fun observeMovieWithGenre() {
        lifecycleScope.launch {
            viewModel.uiState.collect { result ->
                fillGenresMovie(result.genreList)
            }
        }
    }

    companion object {
        val tag = DetailMovieFragment::class.java.canonicalName!!

        @JvmStatic
        fun newInstance(selectedMovie: com.example.local.entities.Movie): DetailMovieFragment {
            val fragment = DetailMovieFragment()
//            val args = Bundle()
//            args.putParcelable("selectedMovie", selectedMovie)
//            fragment.arguments = args
//            currentMovie = selectedMovie
            return fragment
        }
    }
}