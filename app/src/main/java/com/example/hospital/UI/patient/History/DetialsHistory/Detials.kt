package com.example.hospital.UI.patient.History.DetialsHistory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hospital.databinding.FragmentDetials2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detials : Fragment() {
    private var _binding: FragmentDetials2Binding?=null
    private val binding get() = _binding!!
    private val args:DetialsArgs by navArgs()

    private val detialsViewModel2: DetialsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? 
    ): View? {
        _binding= FragmentDetials2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        defination()
        observation()
        onClicks()
    }

    private fun defination() {
        binding.nameClinicValue.text=args.userInf.clinic.name
        binding.nameDateValue.text=args.userInf.working_hour.day
        binding.namePaidValue.text=args.userInf.working_hour.day_name
        binding.namePaidValueTime.text=args.userInf.reserved_at
        binding.nameReservationOrderValue.text=args.userInf.number_in_qeue.toString()
        binding.nameValue.text=args.name
    }

    private fun onClicks() {
        binding.btn.setOnClickListener{
            detialsViewModel2.deleteReserve(args.userInf.id)

        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observation() {

        detialsViewModel2.liveData.observe(viewLifecycleOwner) {
          if(it){
              Toast.makeText(context,"deleted",Toast.LENGTH_SHORT).show()
              val action=DetialsDirections.actionDetials2ToHistory()
              findNavController().navigate(action)
          }else{
              Toast.makeText(context,"Not deleted",Toast.LENGTH_SHORT).show()
          }

        }


    }

    }
