package com.example.wizelineproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.local.entities.Movie
import com.example.wizelineproject.R
import com.example.wizelineproject.databinding.FragmentMoviesListBinding
import com.example.wizelineproject.presentation.MoviesAdapter
import com.example.wizelineproject.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesListFragment : Fragment(), MoviesAdapter.OnItemClickListener {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by activityViewModels()
    private var recyclerAdapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        recyclerAdapter = MoviesAdapter(this)
        binding.recyclerMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerMovies.adapter = recyclerAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        viewModel.getNowPlayingMovies()
        observeNowPlayingMovies()
    }

    private fun observeNowPlayingMovies() {
        lifecycleScope.launch {
            viewModel.uiState.collect { result ->
                fillRecyclerMovies(result.moviesList)
            }
        }
    }

    private fun fillRecyclerMovies(moviesList: List<Movie>) {
        if (moviesList.isNotEmpty()) {
            recyclerAdapter?.submitList(moviesList)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MoviesListFragment()
    }

    override fun onItemClick(item: Movie?) {
        //TODO revisar el tema de pasar valores con navigation
        /*val fragment = DetailMovieFragment.newInstance(item?.id!!)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host, fragment, tag)
            .commit()*/

        val bundle = bundleOf("selectedMovie" to item)
        findNavController().navigate(R.id.action_moviesListFragment_to_detailMovieFragment, bundle)
    }
}