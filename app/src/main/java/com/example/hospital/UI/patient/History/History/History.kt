package com.example.hospital.UI.patient.History.History

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hospital.Adapters.AdapterHistory
import com.example.hospital.R
import com.example.hospital.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class History : Fragment() {
    private var _binding:FragmentHistoryBinding ?=null
    private val binding get() = _binding!!
    lateinit var  adapetr: AdapterHistory
    private val historyViewModel : HistoryViewModel by viewModels()
    val args:HistoryArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHistoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapetr=AdapterHistory{
          val action=HistoryDirections.actionHistoryToDetials2(it,args.name)
            findNavController().navigate(action)
        }

        historyViewModel.getAllHistory()
        observations()
        onClicks()

    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observations() {

        historyViewModel.liveData.observe(viewLifecycleOwner){
            adapetr.setMyList(it.data)
            binding.recyclerClinic.adapter=adapetr
        }
        historyViewModel.errorBody.observe(viewLifecycleOwner){
            Log.e("TAG", "reservationsViewModel: $it", )
        }
    }



}
