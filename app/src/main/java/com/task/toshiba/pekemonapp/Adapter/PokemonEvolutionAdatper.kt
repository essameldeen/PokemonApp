package com.task.toshiba.pekemonapp.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertlevonyan.views.chip.Chip
import com.task.toshiba.multichoicesquizapp.Common.Common
import com.task.toshiba.multichoicesquizapp.Model.Evolution
import com.task.toshiba.multichoicesquizapp.Model.PokamnChild
import com.task.toshiba.pekemonapp.R

class PokemonEvolutionAdatper(var context: Context, var evulationList: List<Evolution>) : RecyclerView.Adapter<PokemonEvolutionAdatper.ViewHolder>() {
    lateinit var pokemon: PokamnChild

    init {
        if (evulationList == null)
            evulationList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return evulationList!!.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        pokemon = Common.findPokemonByNum(evulationList!![position]!!.num)!!
        holder.chip.chipText = evulationList!![position]!!.name
        holder.chip.changeBackgroundColor(Common.getColorByType(pokemon.type!![0]))

    }


    inner class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        lateinit var chip: Chip

        init {
            chip = viewItem.findViewById(R.id.chip) as Chip

            chip.setOnChipClickListener {
                LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(Common.KEY_EVOLUTION)
                        .putExtra("num", evulationList[adapterPosition].num))

            }

        }

    }
}