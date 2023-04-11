package com.example.wizelineproject.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizelineproject.R
import com.example.wizelineproject.config.Constants
import com.example.wizelineproject.data.model.MovieResponse

class MoviesAdapter(private val mList: List<MovieResponse>, private val context: Context) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {


    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage = itemView.findViewById<ImageView>(R.id.imageContainer)
        val movieTitle = itemView.findViewById<TextView>(R.id.title)
        val movieOverview = itemView.findViewById<TextView>(R.id.overView)
        val movieRate = itemView.findViewById<TextView>(R.id.rate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item, parent, false
            )
        )

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = mList[position]

        holder.movieTitle.text = movie.title
        holder.movieOverview.text = movie.overview
        holder.movieRate.text = movie.voteAverage.toString()
        Glide.with(context)
            .load(Constants.IMAGE_PREFIX + movie.posterPath)
            .into(holder.movieImage)
    }

}