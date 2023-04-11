package com.example.wizelineproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizelineproject.data.model.MovieListResponse
import com.example.wizelineproject.databinding.FragmentMoviesListBinding
import com.example.wizelineproject.presentation.MoviesAdapter
import com.example.wizelineproject.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by activityViewModels()
    private var recyclerAdapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateMovies.observe(viewLifecycleOwner) {
            fillRecyclerMovies(it)
        }
    }

    private fun fillRecyclerMovies(model: MovieListResponse) {
        if(!model.result.isNullOrEmpty()){
            recyclerAdapter = MoviesAdapter(model.result, requireContext())
            binding.recyclerMovies.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerMovies.adapter = recyclerAdapter
        }
    }

    companion object {
        val tag = MoviesListFragment::class.java.canonicalName!!

        @JvmStatic
        fun newInstance() = MoviesListFragment()
    }
}