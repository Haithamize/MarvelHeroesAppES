package com.haithamghanem.extremesolutiontask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.haithamghanem.extremesolutiontask.data.model.Result
import com.haithamghanem.extremesolutiontask.databinding.FragmentHeroesInfoBinding
import com.haithamghanem.extremesolutiontask.presentation.adapter.ComicsAdapter
import com.haithamghanem.extremesolutiontask.presentation.adapter.EventsAdapter
import com.haithamghanem.extremesolutiontask.presentation.adapter.SeriesAdapter
import com.haithamghanem.extremesolutiontask.presentation.adapter.StoriesAdapter
import kotlinx.android.synthetic.main.fragment_heroes_info.*


class HeroesInfoFragment : Fragment() {

    private lateinit var heroesInfoBinding: FragmentHeroesInfoBinding
    private lateinit var result: Result
    private lateinit var comicsAdapter: ComicsAdapter
    private lateinit var seriesAdapter: SeriesAdapter
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var storiesAdapter: StoriesAdapter




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bundle = this.arguments
        if (bundle != null) {
            result = bundle.getSerializable("HeroInfo") as Result

        }
        Log.d("bundle", "onCreateView: $result")
        return inflater.inflate(R.layout.fragment_heroes_info, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroesInfoBinding = FragmentHeroesInfoBinding.bind(view)
        val fullPath: String = result.thumbnail.path +"."+ result.thumbnail.extension
        Glide.with(view.context).load(fullPath).into(heroDetailImg)
        heroesInfoBinding.heroDescriptionTxt.text = result.description
        heroesInfoBinding.heroName.text = result.name

        initComicsRecyclerView()
        initSeriesRecyclerView()
        initEventsRecyclerView()
        initStoriesRecyclerView()

    }

    private fun initStoriesRecyclerView() {
        storiesAdapter = StoriesAdapter()
        storiesAdapter.differ.submitList(result.stories.items)
        heroesInfoBinding.rvStories.apply {
            adapter = storiesAdapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun initEventsRecyclerView() {
        eventsAdapter = EventsAdapter()
        eventsAdapter.differ.submitList(result.events.items)
        heroesInfoBinding.rvEvents.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun initSeriesRecyclerView() {
        seriesAdapter = SeriesAdapter()
        seriesAdapter.differ.submitList(result.series.items)
        heroesInfoBinding.rvSeries.apply {
            adapter = seriesAdapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun initComicsRecyclerView() {
        comicsAdapter = ComicsAdapter()
        comicsAdapter.differ.submitList(result.comics.items)
        heroesInfoBinding.rvComics.apply {
            adapter = comicsAdapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        }
    }


}