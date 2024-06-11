package com.example.hospital.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.Data.Models.DataHistory
import com.example.hospital.Data.Models.ModelClinic
import com.example.hospital.databinding.ItemHistoryBinding
import okhttp3.internal.notify


class AdapterHistory(val obj:(DataHistory)->Unit) : RecyclerView.Adapter<AdapterHistory.Holder>() {
     var list: List<DataHistory>? = null
    fun setMyList(list: List<DataHistory>?) {
        this.list = list

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHistory.Holder {
      val item=ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(item)
    }

    override fun onBindViewHolder(holder:AdapterHistory.Holder, position: Int) {
       list!!.let {
           holder.textTilte.text= it[position].clinic.name
           holder.textRate.text= it[position].working_hour.day
       }
   holder.btn.setOnClickListener {
       list?.get(position)!!.let {
          obj.invoke(it)

       }
   }
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    inner class Holder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

         val textRate=binding.textRate
         val textTilte=binding.textTilte
         val btn=binding.btn

    }


}
