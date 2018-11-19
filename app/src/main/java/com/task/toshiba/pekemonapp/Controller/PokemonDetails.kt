package com.task.toshiba.pekemonapp.Controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.task.toshiba.pekemonapp.R

class PokemonDetails : Fragment() {


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
