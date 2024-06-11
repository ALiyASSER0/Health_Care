package com.example.hospital.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.Data.Models.ModelWorkingHour
import com.example.hospital.R
import com.example.hospital.databinding.ItemDetialsBinding


class AdapterDetials(val id:(Int)->Unit) : RecyclerView.Adapter<AdapterDetials.Holder>() {
    private var list: ModelWorkingHour? = null
    var rowIndex  = -1
    fun setList(list: ModelWorkingHour?) {
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDetials.Holder {
        val item=ItemDetialsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(item)
    }

    override fun onBindViewHolder(holder:AdapterDetials.Holder, position: Int) {
        list!!.let {
            holder.textDay.text= it[position].day
        }
        holder.apply {

            if (rowIndex == position){
                card.setCardBackgroundColor(getColor(myContext!!,R.color.gray))
                startTime.setTextColor(ContextCompat.getColor(myContext!!, R.color.black))
                to.setTextColor(ContextCompat.getColor(myContext!!, R.color.black))
                endTime.setTextColor(ContextCompat.getColor(myContext!!, R.color.black))

            }else{
                holder.card.setCardBackgroundColor(Color.WHITE)
                startTime.setTextColor(ContextCompat.getColor(myContext!!, R.color.arrow))
                to.setTextColor(ContextCompat.getColor(myContext!!, R.color.arrow))
                endTime.setTextColor(ContextCompat.getColor(myContext!!, R.color.arrow))

            }


        }
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    inner class Holder(private val binding: ItemDetialsBinding) : RecyclerView.ViewHolder(binding.root) {

        val textDay=binding.textNumDay
        val startTime=binding.startTime
        val to=binding.to
        val endTime=binding.endTime
        val card=binding.cardView
        var myContext : Context?= null
        init {
            myContext = binding.root.context
            itemView.setOnClickListener {

                rowIndex = layoutPosition
                list?.get(layoutPosition)?.id?.let { it1 -> id.invoke(it1) }

                notifyDataSetChanged()
            }
        }




    }


}
