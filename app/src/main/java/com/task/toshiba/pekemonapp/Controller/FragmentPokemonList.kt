package com.task.toshiba.multichoicesquizapp.Controllers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.task.toshiba.multichoicesquizapp.Common.Common
import com.task.toshiba.multichoicesquizapp.Common.ItemOffestDecoration
import com.task.toshiba.multichoicesquizapp.Services.Interface.IPokemon
import com.task.toshiba.multichoicesquizapp.Services.Interface.RetrofitClient
import com.task.toshiba.pekemonapp.Adapter.PokemonAdapter
import com.task.toshiba.pekemonapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_list.*


class FragmentPokemonList : Fragment() {
    internal var compositeDisposable = CompositeDisposable()
    internal lateinit var recyclerView: RecyclerView
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
        fetchData()
    }

    private fun fetchData() {
        showProgress()
        compositeDisposable.add(iPokemon.listPokemon
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { pokman ->
                    Common.pokemons = pokman.pokemon!!
                    setUpAdapter()
                });
    }

    private fun setUpAdapter() {
        hideProgress()
        if (Common.pokemons != null) {
            var pokemonAdapter = PokemonAdapter(activity!!, Common.pokemons!!)
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
}
