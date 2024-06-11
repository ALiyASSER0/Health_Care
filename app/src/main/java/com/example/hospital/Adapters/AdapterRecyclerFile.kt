package com.example.hospital.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.Data.Models.ModelRetrieveFile
import com.example.hospital.R
import com.example.hospital.databinding.ItemMaterialBinding
import com.example.hospital.databinding.ItemTabBinding

class AdapterRecyclerFile(val id:(String)->Unit) : RecyclerView.Adapter<AdapterRecyclerFile.Holder>() {


    var list : ModelRetrieveFile ?= null

    fun setMyList(list: ModelRetrieveFile?) {
        this.list = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val itemTabBinding=ItemMaterialBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(itemTabBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
         holder.textType.text="${list?.get(position)?.type}eport"


    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }



    inner class Holder (private val binding : ItemMaterialBinding) : RecyclerView.ViewHolder(binding.root){

        val textType = binding.textName
        init {

            itemView.setOnClickListener {
                list?.get(layoutPosition)?.let {
                        it1 -> id.invoke(it1.file)
                }
            }
        }

    }


}