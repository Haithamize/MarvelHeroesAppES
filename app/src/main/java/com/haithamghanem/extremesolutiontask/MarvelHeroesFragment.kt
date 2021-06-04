package com.haithamghanem.extremesolutiontask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.haithamghanem.extremesolutiontask.data.model.Result
import com.haithamghanem.extremesolutiontask.data.util.Resource
import com.haithamghanem.extremesolutiontask.databinding.FragmentMarvelHeroesBinding
import com.haithamghanem.extremesolutiontask.presentation.adapter.MarvelHeroesAdapter
import com.haithamghanem.extremesolutiontask.presentation.viewmodel.HeroCharactersViewModel

class MarvelHeroesFragment : Fragment() {

    private lateinit var viewModel: HeroCharactersViewModel
    private lateinit var fragmentMarvelHeroesBinding: FragmentMarvelHeroesBinding
    private lateinit var marvelHeroesAdapter: MarvelHeroesAdapter
    private var limit: Int = 10
    private var list = ArrayList<Result>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marvel_heroes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMarvelHeroesBinding = FragmentMarvelHeroesBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        initRecyclerView()
        viewHeroesList()


    }

    private fun viewHeroesList() {
        viewModel.getMarvelHeroCharacters(limit)
        viewModel.marvelHeroCharacters.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> {
                    Log.d("TAG", "viewHeroesList: ${response.data}")
                    Log.d("TAG", "viewHeroesList: ${response.data?.data?.results?.size}")
                    hideProgressBar()
                    response.data?.let {
                        marvelHeroesAdapter.differ.submitList(it.data.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun initRecyclerView() {
        marvelHeroesAdapter = MarvelHeroesAdapter()
        fragmentMarvelHeroesBinding.rvHeroCharacters.apply {
            adapter = marvelHeroesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar(){
        fragmentMarvelHeroesBinding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        fragmentMarvelHeroesBinding.progressBar.visibility = View.GONE
    }

}