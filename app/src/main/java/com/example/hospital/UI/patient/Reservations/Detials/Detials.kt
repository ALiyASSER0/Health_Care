package com.example.hospital.UI.patient.Reservations.Detials

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hospital.Adapters.AdapterDetials
import com.example.hospital.R
import com.example.hospital.databinding.FragmentDetialsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Detials : Fragment() {
    private var _binding:FragmentDetialsBinding?=null
    private val binding get() = _binding!!
    private lateinit var  adapetr:AdapterDetials
    private val detialsViewModel:DetialsViewModel by viewModels()
    private val args:DetialsArgs by navArgs()
   var workingHour:Int?=null
    var id:Int?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentDetialsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         id=args.id
        binding.progress.setVisibility(View.GONE)
        binding.progress2.setVisibility(View.GONE)
        binding.nameCliniclValue.text=args.name
        binding.priceWalletValue.text=args.price
        adapetr= AdapterDetials{
            binding.progress2.setVisibility(View.VISIBLE)
            detialsViewModel.getNumberInQueue(id!!,it)
            workingHour=it
        }
        detialsViewModel.getWorkingHour(id!!)

        observation()
        onClicks()
        checks()

    }

    private fun checks() {
        binding.check.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.check1.setChecked(false)
            }
        }

        binding.check1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.check.setChecked(false)
            }
        }
    }

    private fun onClicks() {
        binding.fawry.setOnClickListener{
            binding.fawry.text=""
            binding.fawry.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.progress.setVisibility(View.VISIBLE)
          validation()

        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun validation() {
        val select=binding.numberTimeValue.text
        if(select.isEmpty()){
            binding.progress.setVisibility(View.GONE)
            binding.fawry.text="Reserve"
            binding.fawry.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))
            Toast.makeText(context,"please select date",Toast.LENGTH_SHORT).show()
        }else{
            workingHour?.let {
                    it1 -> detialsViewModel.reserveClinic(id!!, it1)
            }
        }
    }


    private fun observation() {
        detialsViewModel.liveData.observe(viewLifecycleOwner){
            adapetr.setList(it)
            binding.recyclerDetials.adapter=adapetr
        }
        detialsViewModel.errorBody.observe(viewLifecycleOwner){
            Log.e("TAG", "detialsViewModel: $it")
        }
        detialsViewModel.liveDataNumberReserve.observe(viewLifecycleOwner){
            binding.progress2.setVisibility(View.GONE)
            binding.numberTimeValue.text=it.numberInQueue.toString()
        }
        detialsViewModel.errorBodyNumberReserve.observe(viewLifecycleOwner){
            binding.progress2.setVisibility(View.GONE)
            Log.e("TAG", "detialsViewModel: $it")
        }
        detialsViewModel.liveDataReserveClinic.observe(viewLifecycleOwner){
            binding.progress.setVisibility(View.GONE)
            val action=DetialsDirections.actionDetialsToHomePatient2(null)
            findNavController().navigate(action)
            Toast.makeText(context,"Check History",Toast.LENGTH_SHORT).show()
        }
        detialsViewModel.errorBodyReserveClinic.observe(viewLifecycleOwner){
            binding.progress.setVisibility(View.GONE)
            binding.fawry.text="Reserve"
            binding.fawry.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))

            Log.e("TAG", "detialsViewModel: $it")
        }
    }
}
