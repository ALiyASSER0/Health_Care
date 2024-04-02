package com.example.hospital.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.R
import com.example.hospital.databinding.ItemTabBinding

class AdapterRecyclerTabes(val id:(String)->Unit) : RecyclerView.Adapter<AdapterRecyclerTabes.Holder>() {


    var list : ArrayList<String> ?= null
    var rowIndex  = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val itemTabBinding=ItemTabBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(itemTabBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list?.get(position)

        holder.apply {

            if (rowIndex == position){

                textType.background =ContextCompat.getDrawable(myContext!!
                                         , R.drawable.background_rounded_selected)
                textType.setTextColor(ContextCompat.getColor(myContext!!, R.color.white))

                textType.text = data
            }else{
                textType.background =ContextCompat.getDrawable(myContext!!
                    , R.drawable.rounded_gray_strock)
                textType.setTextColor(ContextCompat.getColor(myContext!!, R.color.black))

                textType.text = data

            }


        }

    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }



    inner class Holder (private val binding : ItemTabBinding) : RecyclerView.ViewHolder(binding.root){

        val textType = binding.textType
        var myContext :Context ?= null
        init {
            myContext = binding.root.context
            itemView.setOnClickListener {

                rowIndex = layoutPosition
              id.invoke(list?.get(layoutPosition)!!)

                notifyDataSetChanged()
            }
        }

    }


}