package com.example.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavAdapter(

    private val Favor: List<Task>

) : RecyclerView.Adapter<FavAdapter.FavorViewHolder>() {

    class FavorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorViewHolder {
        return FavorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_favorite,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavorViewHolder, position: Int) {
        val curFavor = Favor[position]
        holder.itemView.tvFavorTitle.text = curFavor.taskName

    }

    override fun getItemCount(): Int {
        return Favor.size
    }
}