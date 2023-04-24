package com.example.wizelineproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizelineproject.R
import com.example.wizelineproject.databinding.FragmentTopRatedBinding
import com.example.wizelineproject.domain.entities.Movie
import com.example.wizelineproject.presentation.MoviesAdapter
import com.example.wizelineproject.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class TopRatedFragment : Fragment(), MoviesAdapter.OnItemClickListener {
    private var _binding: FragmentTopRatedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by activityViewModels()
    private var recyclerAdapter: MoviesAdapter? = null
    private var uiJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getNowPlayingMovies()
        viewModel.getTopRatedMovies()
        observeTopRatedMovies()
    }

    private fun observeTopRatedMovies() {
        uiJob = lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { result ->
                fillRecyclerMovies(result.moviesList)
            }
        }
    }

    private fun fillRecyclerMovies(moviesList: List<Movie>) {
        if (moviesList.isNotEmpty()) {
            recyclerAdapter = MoviesAdapter(moviesList, requireContext(), this)
            binding.recyclerMovies.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerMovies.adapter = recyclerAdapter
        }
    }

    companion object {
        val tag = TopRatedFragment::class.java.canonicalName!!

        @JvmStatic
        fun newInstance() = TopRatedFragment()
    }

    override fun onItemClick(item: Movie?) {
        val bundle = bundleOf("selectedMovie" to item)
        findNavController().navigate(R.id.action_topRatedFragment_to_detailMovieFragment, bundle)
    }
}