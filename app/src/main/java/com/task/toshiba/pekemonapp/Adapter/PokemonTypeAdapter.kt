package com.task.toshiba.pekemonapp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.task.toshiba.multichoicesquizapp.Common.Common
import com.task.toshiba.pekemonapp.Interface.OnItemClick
import com.task.toshiba.pekemonapp.R


class PokemonTypeAdapter(var context: Context, var typeList: List<String>, var itemClik: OnItemClick) : RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return typeList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.chip.chipText = typeList[position]
        holder.chip.changeBackgroundColor(Common.getColorByType(typeList[position]))
    }


    inner class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        lateinit var chip: com.robertlevonyan.views.chip.Chip

        init {
            chip = viewItem.findViewById(R.id.chip) as com.robertlevonyan.views.chip.Chip
            chip.setOnClickListener {
                itemClik.onClickItem(viewItem, adapterPosition, "")
            }
        }


    }
}