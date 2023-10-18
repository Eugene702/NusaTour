package com.eugenefeilianputrarangga.dicoding.nusatour.activity.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eugenefeilianputrarangga.dicoding.nusatour.databinding.ComponentTouristCardBinding
import com.eugenefeilianputrarangga.dicoding.nusatour.model.Tour

class RecyclerAdapter(
    private val data: ArrayList<Tour>
): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback{
        fun onItemClick(data: Tour)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(val binding: ComponentTouristCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ComponentTouristCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(data[position]){
                Glide
                    .with(holder.itemView.context)
                    .load(photo)
                    .into(binding.photo)

                binding.title.text = title
                binding.description.text = description
                binding.layout.setOnClickListener { onItemClickCallback.onItemClick(data[position]) }
            }
        }
    }
}