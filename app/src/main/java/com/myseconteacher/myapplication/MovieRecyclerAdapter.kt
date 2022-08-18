package com.myseconteacher.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieRecyclerAdapter(val items: List<MovieModel>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.tvMovieTile.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivMovieImage = view.findViewById<ImageView>(R.id.ivMovieImage)
    val tvMovieTile = view.findViewById<TextView>(R.id.tvMoveTitle)
}


data class MovieModel(
    var name: String? = null,
    var image: Int? = null
)