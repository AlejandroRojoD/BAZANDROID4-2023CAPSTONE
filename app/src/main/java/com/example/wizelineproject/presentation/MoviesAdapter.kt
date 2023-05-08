package com.example.wizelineproject.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizelineproject.BadAplication
import com.example.wizelineproject.R
import com.example.local.entities.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(
    private val callback: OnItemClickListener
) :
    ListAdapter<com.example.local.entities.Movie, MoviesAdapter.MoviesViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item = item, listener = callback)
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: com.example.local.entities.Movie, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(item) }
            itemView.title.text = item.title
            itemView.overView.text = item.overview
            itemView.rate.text = item.rating.toString()
            Glide.with(BadAplication.appContext)
                .load(item.posterUrl)
                .into(itemView.imageContainer)
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<com.example.local.entities.Movie>() {
        override fun areItemsTheSame(oldItem: com.example.local.entities.Movie, newItem: com.example.local.entities.Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.example.local.entities.Movie, newItem: com.example.local.entities.Movie): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: com.example.local.entities.Movie?)
    }
}