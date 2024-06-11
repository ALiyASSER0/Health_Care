package com.example.hospital.UI.patient.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hospital.Data.Models.Data
import com.example.hospital.UI.MySharedPreferences
import com.example.hospital.databinding.FragmentHome2Binding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

var _binding:FragmentHome2Binding?=null

val binding get() = _binding!!


    val args : HomeArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHome2Binding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      val gson= Gson()
        val jsonString=args.dataOfUser
        val model=gson.fromJson(jsonString,Data::class.java)


        val name:String?=model?.firstName

        if(name.isNullOrEmpty()){
            binding.textUserName.text=MySharedPreferences.getUserName()
        }else{
            model?.let {
                it.firstName?.let { it1 -> MySharedPreferences.setUserName(it1) } }
            binding.textUserName.text=name
        }


binding.cardReservations.setOnClickListener {
    val action=HomeDirections.actionHomePatientToReservations()
    findNavController().navigate(action)
}
        binding.cardUpLoad.setOnClickListener {
            val action=HomeDirections.actionHomePatientToMainActivity2()
            findNavController().navigate(action)

        }
        binding.chat.setOnClickListener {
            val action=HomeDirections.actionHomePatientToChaty()
            findNavController().navigate(action)
        }

        binding.cardHistory.setOnClickListener {
            val action=HomeDirections.actionHomePatientToHistory(MySharedPreferences.getUserName()!!)

            findNavController().navigate(action)

        }

binding.cardTasks.setOnClickListener {

    val ac=HomeDirections.actionHomePatientToProfile("")
    findNavController().navigate(ac)

}

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }

