package com.haithamghanem.extremesolutiontask

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haithamghanem.extremesolutiontask.data.model.Result
import com.haithamghanem.extremesolutiontask.data.util.Resource
import com.haithamghanem.extremesolutiontask.databinding.FragmentMarvelHeroesBinding
import com.haithamghanem.extremesolutiontask.presentation.adapter.MarvelHeroesAdapter
import com.haithamghanem.extremesolutiontask.presentation.viewmodel.HeroCharactersViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MarvelHeroesFragment : Fragment(), MarvelHeroesAdapter.RecyclerViewOnItemClickListener {

    private lateinit var viewModel: HeroCharactersViewModel
    private lateinit var fragmentMarvelHeroesBinding: FragmentMarvelHeroesBinding
    private lateinit var marvelHeroesAdapter: MarvelHeroesAdapter
    private var limit: Int = 20
    private var isScrolling = false
    private var isLoading = false






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_marvel_heroes, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMarvelHeroesBinding = FragmentMarvelHeroesBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        initRecyclerView()

        viewHeroesList()
        fragmentMarvelHeroesBinding.searchIcon.setOnClickListener {
            fragmentMarvelHeroesBinding.marvelLogoImg.visibility = View.GONE
            fragmentMarvelHeroesBinding.searchView.visibility = View.VISIBLE
            fragmentMarvelHeroesBinding.searchView.setQuery("",false)
            fragmentMarvelHeroesBinding.searchView.isFocusedByDefault
            setSearchView()
        }
    }

    private fun viewHeroesList() {
        viewModel.getMarvelHeroCharacters(limit)
        viewModel.marvelHeroCharacters.observe(viewLifecycleOwner, { response ->
            if(response.equals(null)){
                return@observe
            }

            when (response) {
                is Resource.Success -> {
                    Log.d("TAG", "viewHeroesList: ${response.data}")
                    Log.d("TAG", "viewHeroesList: ${response.data?.data?.results?.size}")
                    Log.d("number of items", "${response.data?.data?.count}")
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
        marvelHeroesAdapter = MarvelHeroesAdapter(this)
        fragmentMarvelHeroesBinding.rvHeroCharacters.apply {
            adapter = marvelHeroesAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@MarvelHeroesFragment.onScrollListener)
        }
    }

    private fun showProgressBar(){
        isLoading = true
        fragmentMarvelHeroesBinding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        isLoading = false
        fragmentMarvelHeroesBinding.progressBar.visibility = View.GONE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentMarvelHeroesBinding.rvHeroCharacters.layoutManager as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItems >= sizeOfCurrentList

            val shouldPaginate = !isLoading && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                limit += 20
                viewModel.getMarvelHeroCharacters(limit)
                isScrolling = false
            }

        }
    }

    override fun onItemClickListener(position: Int, view: View, result: Result) {
        Log.d("position", "onItemClickListener: $position")
        val bundle = Bundle()
        bundle.putSerializable("HeroInfo",result)
        findNavController().navigate(R.id.action_marvelHeroesFragment_to_heroesInfoFragment,bundle)

    }



    //Searching Functionality
    private fun viewSearchedHero(){


        if (view!=null) {
            viewModel.searchedHeroCharacters.observe(viewLifecycleOwner, { response ->
                if (response.equals(null)) {
                    return@observe
                }

                when (response) {
                    is Resource.Success -> {

                        Log.d("searchedherosuccess", "${response.data?.data?.count}")
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
    }

    private fun setSearchView(){
        fragmentMarvelHeroesBinding.searchView.setOnQueryTextListener( object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getSearchedHeroCharacters(query.toString(),limit)
                viewSearchedHero()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //here i'm doing a delay to give the user sometime to write a meaningful search query w hst5dm el kotlin coroutines delay function and delay about 2 secs
                //hst5dm el MainScope w da coroutine launcher specially created for ui components
                MainScope().launch {
                    delay(2000)
                    viewModel.getSearchedHeroCharacters(newText.toString(),limit)
                    viewSearchedHero()
                }
                return false
            }

        })

        fragmentMarvelHeroesBinding.searchView.setOnCloseListener {
            initRecyclerView()
            viewHeroesList()
            false
        }
    }

}