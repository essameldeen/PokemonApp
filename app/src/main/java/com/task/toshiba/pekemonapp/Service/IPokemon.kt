package com.task.toshiba.multichoicesquizapp.Services.Interface

import com.task.toshiba.multichoicesquizapp.Model.Pokman
import io.reactivex.Observable
import retrofit2.http.GET

interface IPokemon {
    @get:GET("pokedex.json")
    val listPokemon: Observable<Pokman>
}