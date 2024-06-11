package com.example.hospital.UI.patient.Reservations.Reservation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.Adapters.AdapterClinic
import com.example.hospital.Data.Models.ModelClinic
import com.example.hospital.Data.Models.ModelClinicItem
import com.example.hospital.databinding.FragmentReservationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Reservations : Fragment() {

private var _binding:FragmentReservationsBinding?=null
    private val binding get() = _binding!!
    private lateinit var  adapetr:AdapterClinic
    private val reservationsViewModel : ReservationsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentReservationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Handle action here
              val textSearch=binding.editSearch.text
                    reservationsViewModel.liveData.observe(viewLifecycleOwner){ it ->
                        val newList=it.filter {
                            it.name.contains(textSearch) || it.doctor.firstName.contains(textSearch) ||it.doctor.lastName.contains(textSearch)
                        }

                        val modelClinic = ModelClinic().apply {
                            addAll(newList)
                        }
                        adapetr.setList(modelClinic)
                        binding.recyclerClinic.adapter=adapetr
                    }
                true
            } else {
                false
            }
        }
        navigation()
        reservationsViewModel.getAllClinic()
  observations()
    }

    private fun navigation() {

        adapetr=AdapterClinic{id,name,price->
            val action =ReservationsDirections.actionReservationsToDetials(name,id,price)
            findNavController().navigate(action)
        }

    binding.btnBack.setOnClickListener {
        findNavController().popBackStack()
    }

    }

    private fun observations() {
        reservationsViewModel.liveData.observe(viewLifecycleOwner){
            adapetr.setList(it)
            binding.recyclerClinic.adapter=adapetr
        }
        reservationsViewModel.errorBody.observe(viewLifecycleOwner){
            Log.e("TAG", "reservationsViewModel: $it", )
        }


    }

}