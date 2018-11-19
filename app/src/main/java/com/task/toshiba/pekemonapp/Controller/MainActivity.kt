package com.task.toshiba.pekemonapp.Controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.LocalBroadcastManager
import android.view.MenuItem
import com.task.toshiba.multichoicesquizapp.Common.Common
import com.task.toshiba.pekemonapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeTitle("Pokemon List")

        //Register BroadCast
        LocalBroadcastManager.getInstance(this).registerReceiver(pokemonDetails, IntentFilter(Common.KEY_ENABLE))
        LocalBroadcastManager.getInstance(this).registerReceiver(showEvolution, IntentFilter(Common.KEY_EVOLUTION))
    }

    //create broadCast Handler
    private val pokemonDetails = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent!!.action!!.toString() == Common.KEY_ENABLE) {
                settingActionBar(true)

                val position = intent.getIntExtra("position", -1)
                var bundle = Bundle()
                bundle.putInt("position", position)
                val pokemonDetails = PokemonDetails.getInstance()
                pokemonDetails.arguments = bundle
                changeTitle(Common!!.pokemons!![position]!!.name)
                replaceFragment(pokemonDetails, PokemonDetails.tag)

            }


        }

    }
    private val showEvolution = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent!!.action!!.toString() == Common.KEY_EVOLUTION) {
                val num = intent.getStringExtra("num")
                var bundle = Bundle()
                bundle.putString("num", num)
                val pokemonDetails = PokemonDetails.getInstance()
                pokemonDetails.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.remove(pokemonDetails)
                fragmentTransaction.replace(R.id.list_pokemon_fragment, pokemonDetails)
                fragmentTransaction.addToBackStack(PokemonDetails.tag).commit()

                var pokemon = Common.findPokemonByNum(num)
                changeTitle(pokemon!!.name)

            }


        }

    }

    private fun changeTitle(name: String?) {
        toolBar.title = name
        setSupportActionBar(toolBar)
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.list_pokemon_fragment, fragment)
        fragmentTransaction.addToBackStack(tag).commit()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

            android.R.id.home -> {
                changeTitle("Pokemon List")
                popFragment()
            }

        }

        return true
    }

    private fun popFragment() {

        supportFragmentManager.popBackStack(PokemonDetails.tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        settingActionBar(false)
    }

    private fun settingActionBar(state: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(state)
        supportActionBar!!.setDisplayShowHomeEnabled(state)
    }
}
