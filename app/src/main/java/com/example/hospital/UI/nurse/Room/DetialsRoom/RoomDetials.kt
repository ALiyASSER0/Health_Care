package com.example.hospital.UI.nurse.Room.DetialsRoom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.Adapters.AdapterNurseRoomBed
import com.example.hospital.Data.Models.NurseRoomData
import com.example.hospital.R

import com.example.hospital.databinding.FragmentRoomsDetialsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RoomDetials : Fragment() {

    private var _binding: FragmentRoomsDetialsBinding? = null
    private val binding get() = _binding!!
    private val args: RoomDetialsArgs by navArgs()
    private lateinit var adapetr: AdapterNurseRoomBed
    private val viewModel: RoomDetialsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentRoomsDetialsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson = Gson()
        val obj = args.jsonString
        val model = gson.fromJson(obj, NurseRoomData::class.java)
    binding.nameValue.text="${model.incharge?.first_name} ${model.incharge?.last_name}"
    binding.nameHospitalValue.text=model.name
    binding.nameClinicValue.text=model.type
    binding.nameDateValue.text=model.status
    binding.namePaymentValue.text=model.number_in_room.toString()
    binding.nameUnconfirmedValue.text=model.maxNumber_inRoom.toString()
        adapetr=AdapterNurseRoomBed{
            Toast.makeText(context,it.id.toString(),Toast.LENGTH_SHORT).show()
        }
   binding.bed.setOnClickListener {
       Log.e("id", model.id.toString() )
       showDialog()

   }

    viewModel.getAllBeds(model.id.toString())

    }


    private fun showDialog() {
        val dialog= Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheet2)
        val recyclerBed = dialog.findViewById<RecyclerView>(R.id.recyclerBeds)

        recyclerBed.adapter = adapetr


        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations= R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
        viewModel.liveData.observe(viewLifecycleOwner) {
            val recyclerBed = dialog.findViewById<RecyclerView>(R.id.recyclerBeds)

            recyclerBed.adapter = adapetr
            adapetr.setList(it.BedDate)

            dialog.show()
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations= R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.BOTTOM)

        }
        viewModel.errorBody.observe(viewLifecycleOwner){
            Log.e("TAG", "reservationsViewModel: $it", )
        }



    }



}