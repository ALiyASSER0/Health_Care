package com.example.hospital.UI.nurse.Room.RoonNurse
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.Adapters.AdapterNurseRoom
import com.example.hospital.databinding.FragmentNurseRoomBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NurseRoom : Fragment() {

    private var _binding: FragmentNurseRoomBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapetr: AdapterNurseRoom
    private val reservationsViewModel: NurseRoomViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNurseRoomBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation()
        reservationsViewModel.getAllRooms()
        observations()
    }

    private fun observations() {
        reservationsViewModel.liveData.observe(viewLifecycleOwner){
            binding.name.text= it.count.toString()
            adapetr.setList(it.data)
            binding.recyclerClinic.adapter=adapetr
        }
        reservationsViewModel.errorBody.observe(viewLifecycleOwner){
            Log.e("TAG", "reservationsViewModel: $it", )
        }
    }

    private fun navigation() {
        adapetr= AdapterNurseRoom{ obj->
            val gson = Gson()
            val jsonString = gson.toJson(obj)
            val action = NurseRoomDirections.actionNurseCallsToCallsDetials(jsonString)
            findNavController().navigate(action)
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}