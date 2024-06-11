package com.example.hospital.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.Data.Models.BedDate
import com.example.hospital.databinding.ItemNurseBedBinding
import com.example.hospital.databinding.ItemNurseRoomBinding


class AdapterNurseRoomBed(val obj:(BedDate)->Unit) : RecyclerView.Adapter<AdapterNurseRoomBed.Holder>() {
    private var list: List<BedDate>? = null
    fun setList(list:  List<BedDate>?) {
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNurseRoomBed.Holder {
        val item=ItemNurseBedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(item)
    }

    override fun onBindViewHolder(holder:AdapterNurseRoomBed.Holder, position: Int) {
        list!!.let {
            holder.textName.text= it[position].name
        }
        holder.itemView.setOnClickListener {
            list?.get(position)!!.let {
            obj.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    inner class Holder(private val binding: ItemNurseBedBinding) : RecyclerView.ViewHolder(binding.root) {

        val textName=binding.textName


    }


}
