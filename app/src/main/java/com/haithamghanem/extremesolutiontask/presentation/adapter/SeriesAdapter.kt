package com.haithamghanem.extremesolutiontask.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haithamghanem.extremesolutiontask.data.model.Item
import com.haithamghanem.extremesolutiontask.data.model.ItemXX
import com.haithamghanem.extremesolutiontask.databinding.SeriesListItemBinding
import com.haithamghanem.extremesolutiontask.databinding.StoriesListItemBinding

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.SeriesAdapterViewHolder>() {

    private val callback  = object : DiffUtil.ItemCallback<ItemXX>(){
        override fun areItemsTheSame(oldItem: ItemXX, newItem: ItemXX): Boolean {
            return oldItem.resourceURI == newItem.resourceURI
        }

        override fun areContentsTheSame(oldItem: ItemXX, newItem: ItemXX): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesAdapterViewHolder {
        val binding = SeriesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesAdapterViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bindCharacter(item, position)
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }




    inner class SeriesAdapterViewHolder(val binding: SeriesListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindCharacter(item: ItemXX, position: Int) {
            // Log.d("sayedss", "${result.thumbnail.path +"."+ result.thumbnail.extension}")
            binding.seriesName.text = item.name
            //https://ichef.bbci.co.uk/news/976/cpsprodpb/12A9B/production/_111434467_gettyimages-1143489763.jpg
//            val fullPath: String = item.resourceURI
//            Log.d("tamer", "bindCharacter: $fullPath")
//            Glide.with(binding.seriesImg.context).load(fullPath).into(binding.seriesImg)
        }
    }
}