package com.task.toshiba.multichoicesquizapp.Common

import android.graphics.Color
import com.task.toshiba.multichoicesquizapp.Model.PokamnChild
import com.task.toshiba.multichoicesquizapp.Model.Pokman
import java.util.ArrayList

object Common {
    var pokemons: List<PokamnChild>? = ArrayList()
    val KEY_ENABLE: String = "position"
    val KEY_EVOLUTION = "evolution"

    fun findPokemonByNum(num: String?): PokamnChild? {
        for (pokemon in Common.pokemons!!)
            if (pokemon.num.equals(num))
                return pokemon
        return null
    }

    fun getColorByType(type: String): Int {
        when (type) {
            "Normal" -> return Color.parseColor("#A4A27A")
            "Dragon" -> return Color.parseColor("#743BFB")
            "Psychic" -> return Color.parseColor("#F15B85")
            "Electric" -> return Color.parseColor("#E9CA3C")
            "Ground" -> return Color.parseColor("#D9BF6C")
            "Grass" -> return Color.parseColor("#81C85B")
            "Poison" -> return Color.parseColor("#A441A3")
            "Steel" -> return Color.parseColor("#BAB7D2")
            "Fairy" -> return Color.parseColor("#DDA2DF")
            "Fire" -> return Color.parseColor("#F48130")
            "Fight" -> return Color.parseColor("#BE3027")
            "Bug" -> return Color.parseColor("#A8B822")
            "Ghost" -> return Color.parseColor("#705693")
            "Dark" -> return Color.parseColor("#745945")
            "Ice" -> return Color.parseColor("#9BD8D8")
            "Water" -> return Color.parseColor("#658FF1")
            else -> return Color.parseColor("#658FA0")
        }
    }


}