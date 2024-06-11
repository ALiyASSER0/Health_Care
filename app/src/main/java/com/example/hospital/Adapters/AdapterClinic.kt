package com.example.hospital.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.Data.Models.ModelClinic
import com.example.hospital.databinding.ItemClinicBinding


class AdapterClinic(val obj:(Int,String,String)->Unit) : RecyclerView.Adapter<AdapterClinic.Holder>() {
    private var list: ModelClinic? = null
    fun setList(list: ModelClinic?) {
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterClinic.Holder {
        val item=ItemClinicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(item)
    }

    override fun onBindViewHolder(holder:AdapterClinic.Holder, position: Int) {
        list!!.let {
            holder.textTitle.text= it[position].name
            holder.textName.text= "Dr.${it[position].doctor.firstName}"
            holder.textPrice.text=it[position].price.toString()
        }
        holder.itemView.setOnClickListener {
            list?.get(position)!!.let {
                it.id?.let { it1 -> obj.invoke(it1,it.name,it.price.toString()) }

            }
        }
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    inner class Holder(private val binding: ItemClinicBinding) : RecyclerView.ViewHolder(binding.root) {

        val textTitle=binding.textTilte
        val textName=binding.textName
        val textPrice=binding.textFeesPrice

    }


}
