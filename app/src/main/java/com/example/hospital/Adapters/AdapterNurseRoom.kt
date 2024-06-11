package com.example.hospital.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.Data.Models.NurseRoomData
import com.example.hospital.databinding.ItemNurseRoomBinding


class AdapterNurseRoom(val obj:(NurseRoomData)->Unit) : RecyclerView.Adapter<AdapterNurseRoom.Holder>() {
    private var list: List<NurseRoomData>? = null
    fun setList(list:  List<NurseRoomData>?) {
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNurseRoom.Holder {
        val item=ItemNurseRoomBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(item)
    }

    override fun onBindViewHolder(holder:AdapterNurseRoom.Holder, position: Int) {
        list!!.let {
            holder.textName.text= it[position].name
            holder.textType.text=it[position].type
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

    inner class Holder(private val binding: ItemNurseRoomBinding) : RecyclerView.ViewHolder(binding.root) {

        val textType=binding.textType
        val textName=binding.textName


    }


}
