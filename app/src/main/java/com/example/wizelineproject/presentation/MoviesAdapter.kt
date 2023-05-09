package com.example.wizelineproject.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.local.entities.Movie
import com.example.wizelineproject.BadAplication
import com.example.wizelineproject.R
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(
    private val callback: OnItemClickListener
) :
    ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item = item, listener = callback)
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(item) }
            itemView.title.text = item.title
            itemView.overView.text = item.overview
            itemView.rate.text = item.rating.toString()
            Glide.with(BadAplication.appContext)
                .load(item.posterUrl)
                .into(itemView.imageContainer)
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Movie?)
    }
}