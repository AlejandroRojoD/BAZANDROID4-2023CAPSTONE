package com.example.wizelineproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.wizelineproject.databinding.FragmentLatestMovieBinding
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class LatestMovieFragment : Fragment() {
    private var _binding: FragmentLatestMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by activityViewModels()
    private var uiJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLatestMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLatestMovie()
        observeLatestMovie()
    }

    private fun observeLatestMovie() {
        uiJob = lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { result ->
                fillMovieData(result.latestMovie)
            }
        }
    }

    private fun fillMovieData(latestMovie: Movie?) {
        if (!latestMovie?.posterUrl.isNullOrEmpty()) {
            Glide.with(requireContext())
                .load(latestMovie?.posterUrl)
                .into(binding.imageContainer)
        }
        binding.movieTitle.text = latestMovie?.title
        binding.movieDescription.text = latestMovie?.overview
    }

    companion object {
        val tag = LatestMovieFragment::class.java.canonicalName!!

        @JvmStatic
        fun newInstance() = LatestMovieFragment()
    }
}