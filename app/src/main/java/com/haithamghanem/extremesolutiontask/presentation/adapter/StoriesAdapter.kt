package com.haithamghanem.extremesolutiontask.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haithamghanem.extremesolutiontask.data.model.Item
import com.haithamghanem.extremesolutiontask.data.model.ItemXXX
import com.haithamghanem.extremesolutiontask.databinding.ComicsListItemBinding
import com.haithamghanem.extremesolutiontask.databinding.StoriesListItemBinding

class StoriesAdapter : RecyclerView.Adapter<StoriesAdapter.StoriesAdapterViewHolder>(){

    private val callback  = object : DiffUtil.ItemCallback<ItemXXX>(){
        override fun areItemsTheSame(oldItem: ItemXXX, newItem: ItemXXX): Boolean {
            return oldItem.resourceURI == newItem.resourceURI
        }

        override fun areContentsTheSame(oldItem: ItemXXX, newItem: ItemXXX): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesAdapterViewHolder {
        val binding = StoriesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoriesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesAdapterViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bindCharacter(item, position)
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }




    inner class StoriesAdapterViewHolder(val binding: StoriesListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindCharacter(item: ItemXXX, position: Int) {
            // Log.d("sayedss", "${result.thumbnail.path +"."+ result.thumbnail.extension}")
            binding.storyName.text = item.name
            //https://ichef.bbci.co.uk/news/976/cpsprodpb/12A9B/production/_111434467_gettyimages-1143489763.jpg
            val fullPath: String = item.resourceURI
            Log.d("tamer", "bindCharacter: $fullPath")
            Glide.with(binding.storyImg.context).load(fullPath).into(binding.storyImg)
        }
    }
}