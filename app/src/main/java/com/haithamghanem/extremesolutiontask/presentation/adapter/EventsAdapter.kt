package com.haithamghanem.extremesolutiontask.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haithamghanem.extremesolutiontask.data.model.Item
import com.haithamghanem.extremesolutiontask.data.model.ItemX
import com.haithamghanem.extremesolutiontask.databinding.ComicsListItemBinding
import com.haithamghanem.extremesolutiontask.databinding.EventListItemBinding

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventsAdapterViewHolder>() {

    private val callback  = object : DiffUtil.ItemCallback<ItemX>(){
        override fun areItemsTheSame(oldItem: ItemX, newItem: ItemX): Boolean {
            return oldItem.resourceURI == newItem.resourceURI
        }

        override fun areContentsTheSame(oldItem: ItemX, newItem: ItemX): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapterViewHolder {
        val binding = EventListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsAdapterViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bindCharacter(item, position)
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }




    inner class EventsAdapterViewHolder(val binding: EventListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindCharacter(item: ItemX, position: Int) {
            // Log.d("sayedss", "${result.thumbnail.path +"."+ result.thumbnail.extension}")
            Log.d("eventname", "bindCharacter: ${item.name}")
            Log.d("eventlist", "bindCharacter: ${differ.currentList.size}")
            binding.eventName.text = item.name
            //https://ichef.bbci.co.uk/news/976/cpsprodpb/12A9B/production/_111434467_gettyimages-1143489763.jpg
//            val fullPath: String = item.resourceURI
//            Log.d("tamer", "bindCharacter: $fullPath")
//            Glide.with(binding.eventImg.context).load(fullPath).into(binding.eventImg)
        }
    }
}