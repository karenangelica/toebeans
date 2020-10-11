package com.example.toebeanscatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toebeanscatapp.roomdb.Cats
import com.squareup.picasso.Picasso

class CatAdapter(private val catList: List<Cats>, private val picasso: Picasso) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder { // Called by the RecyclerView when its time to create view holder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cat_item,
        parent, false) // layout file into a view object
        return CatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) { // the recyled one
        val currentItem = catList[position]

        picasso.load(currentItem.cat_url).into(holder.imageView)
        holder.textView.text = currentItem.cat_name

    }

    override fun getItemCount() = catList.size


    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivCat)
        val textView: TextView = itemView.findViewById(R.id.name)
    }
}