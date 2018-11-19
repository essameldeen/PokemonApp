package com.task.toshiba.pekemonapp.Controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.task.toshiba.multichoicesquizapp.Common.Common
import com.task.toshiba.multichoicesquizapp.Model.PokamnChild
import com.task.toshiba.pekemonapp.Adapter.PokemonEvolutionAdatper
import com.task.toshiba.pekemonapp.Adapter.PokemonTypeAdapter
import com.task.toshiba.pekemonapp.Interface.OnItemClick

import com.task.toshiba.pekemonapp.R
import com.task.toshiba.pekemonapp.R.id.*
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import kotlinx.android.synthetic.main.single_pokemon_item.*

class PokemonDetails : Fragment(), OnItemClick {

    lateinit var pokemon: PokamnChild
    lateinit var name: TextView
    lateinit var height: TextView
    lateinit var weight: TextView
    lateinit var image: ImageView


    companion object {
        internal var instance: PokemonDetails? = null
        internal var tag: String = "POKEMON_DETAILS"
        fun getInstance(): PokemonDetails {
            if (instance == null)
                instance = PokemonDetails()
            return instance!!
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

        val num = arguments!!.getString("num")
        if(num==null){
            val position = arguments!!.getInt("position")
            pokemon = Common!!.pokemons!![position]
        }else{
            pokemon = Common.findPokemonByNum(num)!!
        }

        updateUi()


    }

    private fun initView(view: View) {
        name = view.findViewById(R.id.name) as TextView
        height = view.findViewById(R.id.height) as TextView
        weight = view.findViewById(R.id.weight) as TextView
        image = view.findViewById(R.id.image) as ImageView

        recycle_type.setHasFixedSize(true)
        recycle_type.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycle_weakness.setHasFixedSize(true)
        recycle_weakness.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycle_prevEvolution.setHasFixedSize(true)
        recycle_prevEvolution.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycle_nextEvolution.setHasFixedSize(true)
        recycle_nextEvolution.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


    }

    private fun updateUi() {

            Glide.with(context!!).load(pokemon!!.img).into(image)
            name.text = pokemon!!.name
            height.text = "Height: " + pokemon!!.height
            weight.text = "Weight: " + pokemon!!.weight

            setUpAdapter()
        }


    private fun setUpAdapter() {
        val adapterType = PokemonTypeAdapter(activity!!, pokemon.type!!, this)
        recycle_type.adapter = adapterType

        val adapterWeakness = PokemonTypeAdapter(activity!!, pokemon.weaknesses!!, this)
        recycle_weakness.adapter = adapterWeakness

        if (pokemon.next_evolution != null) {
            val adapterNextEvolution = PokemonEvolutionAdatper(activity!!, pokemon.next_evolution!!)
            recycle_prevEvolution.adapter = adapterNextEvolution
        }

        if (pokemon.prev_evolution != null) {
            val adapterPreEvolution = PokemonEvolutionAdatper(activity!!, pokemon.prev_evolution!!)
            recycle_prevEvolution.adapter = adapterPreEvolution
        }

    }

    override fun onClickItem(view: View, position: Int) {
        Toast.makeText(context!!, "Position " + position, Toast.LENGTH_LONG).show()
    }

}
