package com.example.hospital.Adapters

import Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.hospital.R


class AdapterRecyclerEmployee : RecyclerView.Adapter<AdapterRecyclerEmployee.Holder>() {


    var list  =ArrayList<Data>()
    var onUserClick : OnUserClick ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       val view = LayoutInflater.from(parent.context)
               .inflate(R.layout.holder_recycle_employee, parent , false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list?.get(position)


        holder.apply {

            holder.textName.text = data?.firstName
            holder.textType.text = "Spechial- ${data?.type}"
        }

    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class Holder (view : View) : RecyclerView.ViewHolder(view){

        val textType = view.findViewById<TextView>(R.id.text_type);
        val textName = view.findViewById<TextView>(R.id.text_user_name);
        val btnCall = view.findViewById<ImageView>(R.id.image_call);

        init {
            itemView.setOnClickListener {
                onUserClick?.onClick(list?.get(layoutPosition)?.id!!)
            }

            btnCall.setOnClickListener {
                onUserClick?.onCall(list?.get(layoutPosition)?.id!!)
            }
        }

    }

    interface OnUserClick {
        fun onClick (id : Int)

        fun onCall (id : Int)
    }
}