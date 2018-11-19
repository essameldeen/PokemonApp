package com.task.toshiba.multichoicesquizapp.Controllers


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mancj.materialsearchbar.MaterialSearchBar
import com.task.toshiba.multichoicesquizapp.Common.Common
import com.task.toshiba.multichoicesquizapp.Common.ItemOffestDecoration
import com.task.toshiba.multichoicesquizapp.Model.PokamnChild
import com.task.toshiba.multichoicesquizapp.Services.Interface.IPokemon
import com.task.toshiba.multichoicesquizapp.Services.Interface.RetrofitClient
import com.task.toshiba.pekemonapp.Adapter.PokemonAdapter
import com.task.toshiba.pekemonapp.Interface.OnItemClick
import com.task.toshiba.pekemonapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import java.util.ArrayList


class FragmentPokemonList : Fragment(), OnItemClick {

    internal var compositeDisposable = CompositeDisposable()
    lateinit var pokemonAdapter: PokemonAdapter
    lateinit var pokemonSearchAdapter: PokemonAdapter
    internal lateinit var recyclerView: RecyclerView
    internal var last_suggest: MutableList<String> = ArrayList()
    internal var iPokemon: IPokemon

    init {
        val retrofit = RetrofitClient.Instance
        iPokemon = retrofit.create(IPokemon::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemonRecycle) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?
        var itemDecoration = ItemOffestDecoration(activity!!, R.dimen.spacing)
        recyclerView.addItemDecoration(itemDecoration)

        // searchBar setup
        search_bar.setHint("Enter Pokemon Name")
        search_bar.setCardViewElevation(10)
        search_bar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val suggest = ArrayList<String>()
                for (search in last_suggest) {
                    if (search.toLowerCase().contains(search_bar.text.toLowerCase())) {
                        suggest.add(search)
                    }
                }
                search_bar.lastSuggestions = suggest
            }
        })
        search_bar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {

            }

            override fun onSearchStateChanged(enabled: Boolean) {
                if (!enabled) {
                    pokemonRecycle.adapter = pokemonAdapter
                }

            }

            override fun onSearchConfirmed(text: CharSequence?) {
                startSearch(text.toString())
            }
        })



        fetchData()


    }

    private fun startSearch(search: String?) {

        if (Common.pokemons!!.size > 0) {
            val result = ArrayList<PokamnChild>()
            for (pokemon in Common.pokemons!!) {
                if (pokemon.name!!.toLowerCase().contains(search!!.toLowerCase())) {
                    result.add(pokemon)
                }
            }
            pokemonSearchAdapter = PokemonAdapter(context!!, result!!, this)
            recyclerView.adapter = pokemonSearchAdapter
        }

    }

    private fun fetchData() {
        showProgress()
        compositeDisposable.add(iPokemon.listPokemon
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { pokman ->
                    Common.pokemons = pokman.pokemon!!
                    setUpAdapter()

                    showSearchBar()
                });
    }

    private fun showSearchBar() {
        last_suggest.clear()
        for (pokemon in Common.pokemons!!) {
            last_suggest.add(pokemon.name!!)
        }
        search_bar.visibility = View.VISIBLE
        search_bar.lastSuggestions = last_suggest
    }

    private fun setUpAdapter() {
        hideProgress()
        if (Common.pokemons != null) {
            pokemonAdapter = PokemonAdapter(activity!!, Common.pokemons!!, this)
            recyclerView.adapter = pokemonAdapter
        } else {
            Toast.makeText(activity, "No Data ", Toast.LENGTH_LONG).show()
        }

    }

    private fun hideProgress() {
        progress.visibility = View.INVISIBLE
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun onClickItem(view: View, position: Int, num: String?) {
        LocalBroadcastManager.getInstance(context!!).sendBroadcast(Intent(Common.KEY_ENABLE).putExtra("num", num))

    }

}
