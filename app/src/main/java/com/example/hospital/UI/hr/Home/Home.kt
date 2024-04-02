package com.example.hospital.UI.hr.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentHomeBinding


class Home : Fragment() {
 private var _binding :FragmentHomeBinding?=null
 private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding=FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    binding.cardNewEmployee.setOnClickListener {
        val action =HomeDirections.actionHome2ToRegister()
       findNavController().navigate(action)

    }
binding.cardEmploye.setOnClickListener {
    val action=HomeDirections.actionHome2ToEmployee()
    findNavController().navigate(action)
}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }
