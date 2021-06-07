package com.haithamghanem.extremesolutiontask.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haithamghanem.extremesolutiontask.data.model.Item
import com.haithamghanem.extremesolutiontask.data.model.Result
import com.haithamghanem.extremesolutiontask.databinding.ComicsListItemBinding
import com.haithamghanem.extremesolutiontask.databinding.FragmentHeroesInfoBinding

class ComicsAdapter: RecyclerView.Adapter<ComicsAdapter.ComicsAdapterViewHolder>(){

    private val callback  = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.resourceURI == newItem.resourceURI
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsAdapterViewHolder {
        val binding = com.haithamghanem.extremesolutiontask.databinding.ComicsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsAdapterViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bindCharacter(item, position)
    }

    override fun getItemCount(): Int {
      return  differ.currentList.size
    }




    inner class ComicsAdapterViewHolder(val binding: ComicsListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindCharacter(item: Item, position: Int) {
            // Log.d("sayedss", "${result.thumbnail.path +"."+ result.thumbnail.extension}")
            binding.comicName.text = item.name
            //https://ichef.bbci.co.uk/news/976/cpsprodpb/12A9B/production/_111434467_gettyimages-1143489763.jpg
//            val fullPath: String = item.resourceURI
//            Log.d("tamer", "bindCharacter: $fullPath")
//            Glide.with(binding.comicImg.context).load(fullPath).into(binding.comicImg)
        }
    }

}