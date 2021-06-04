package com.haithamghanem.extremesolutiontask.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haithamghanem.extremesolutiontask.databinding.HeroesListItemBinding
import com.haithamghanem.extremesolutiontask.data.model.Result

class MarvelHeroesAdapter: RecyclerView.Adapter<MarvelHeroesAdapter.MarvelHeroesViewHolder>() {

    private val callback  = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelHeroesViewHolder {
        val binding = HeroesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelHeroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelHeroesViewHolder, position: Int) {
            val result = differ.currentList[position]
            holder.bindCharacter(result)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class MarvelHeroesViewHolder(val binding: HeroesListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindCharacter(result: Result){
            Log.d("sayedss", "${result.thumbnail.path +"."+ result.thumbnail.extension}")
            binding.heroNameTxt.text = result.name
            //https://ichef.bbci.co.uk/news/976/cpsprodpb/12A9B/production/_111434467_gettyimages-1143489763.jpg
            val fullPath: String = result.thumbnail.path +"."+ result.thumbnail.extension
            Log.d("tamer", "bindCharacter: $fullPath")
            Glide.with(binding.heroImg.context).load(fullPath).into(binding.heroImg)

        }
    }


}