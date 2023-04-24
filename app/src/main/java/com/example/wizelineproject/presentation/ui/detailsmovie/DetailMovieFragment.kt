package com.example.wizelineproject.presentation.ui.detailsmovie

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.wizelineproject.databinding.FragmentDetailMovieBinding
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.presentation.ui.detailsmovie.compose.DetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentMovie: Movie

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
                        DetailScreen(selectedMovie = currentMovie)
                    }
                }
            }
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable("selectedMovie", Movie::class.java)?.let { model ->
                    currentMovie = model
                }
            } else {
                it.getParcelable<Movie>("selectedMovie")?.let { model ->
                    currentMovie = model
                }
            }
        }
    }

    companion object {
        val tag = DetailMovieFragment::class.java.canonicalName!!

        @JvmStatic
        fun newInstance() = DetailMovieFragment()
    }
}