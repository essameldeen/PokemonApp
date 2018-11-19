package com.task.toshiba.pekemonapp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.task.toshiba.multichoicesquizapp.Model.PokamnChild
import com.task.toshiba.pekemonapp.Interface.OnItemClick
import com.task.toshiba.pekemonapp.R

class PokemonAdapter(var context: Context, var pokemons: List<PokamnChild>, var onItemClick: OnItemClick) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.single_pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(pokemons[position].img).into(holder.image)
        holder.name.text = pokemons[position].name
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var image: ImageView
        internal var name: TextView

        init {
            image = itemView.findViewById(R.id.image) as ImageView
            name = itemView.findViewById(R.id.name) as TextView
            itemView.setOnClickListener {
                var num = pokemons[adapterPosition].num
                onItemClick.onClickItem(itemView,adapterPosition ,num)
            }
        }

    }
}