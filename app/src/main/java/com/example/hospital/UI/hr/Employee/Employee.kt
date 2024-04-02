package com.example.hospital.UI.hr.Employee

import Data
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospital.Adapters.AdapterRecyclerEmployee
import com.example.hospital.Adapters.AdapterRecyclerTabes
import com.example.hospital.databinding.FragmentEmployeeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Employee : Fragment() {
private var _binding:FragmentEmployeeBinding ?=null
private val binding get() = _binding!!
private var  adapetr=AdapterRecyclerEmployee()
private lateinit var adapterRecyclerTabes :AdapterRecyclerTabes
private val tabList = ArrayList<String>()

   val employeeViewModel  : EmployeeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentEmployeeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterRecyclerTabes=AdapterRecyclerTabes{
            filter(it)
        }

observes()
        tabList.add("All")
        tabList.add("DOCTOR")
        tabList.add("NURSE")
        tabList.add("HR")
        tabList.add("MANAGER")
        adapterRecyclerTabes.list = tabList
      binding.recyclerTypesTaps.adapter=adapterRecyclerTabes


        employeeViewModel.getAllEmployee()
    }

    private fun filter(item: String) {
        employeeViewModel.liveData.observe(viewLifecycleOwner){
            val list =it.data.filter { it ->
                Log.e("TAG", "it:${ it.type} ", )
                Log.e("TAG", "item:${ item[0]} ", )
               it.type==item[0].toString()
           }

     if(item[0].toString()=="A"){
         adapetr.list= it.data as ArrayList<Data>
         binding.recyclerEmployee.adapter=adapetr
     }else{
         adapetr.list= list as ArrayList<Data>
         binding.recyclerEmployee.adapter=adapetr
     }
        }
    }

    private fun observes() {
        employeeViewModel.liveData.observe(viewLifecycleOwner){
            adapetr.list= it.data as ArrayList<Data>

            binding.recyclerEmployee.adapter=adapetr
        }
        employeeViewModel.errorBody.observe(viewLifecycleOwner){
            Log.e("TAG", "onViewCreated: $it", )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }
