package com.example.hospital.UI.hr.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.Data.Models.ModelRegisterHr

import com.example.hospital.R
import com.example.hospital.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class Register : Fragment() {

    private var _binding: FragmentRegisterBinding?=null
    private val binding get() = _binding!!
  private val viewmodel:  RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding=FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClicks()
         menui()
        observes()





        }

    private fun validation() {
       val fName=binding.editFname
       val lName=binding.editLname
       val gender=binding.dropdownGender.text
       val specialist=binding.dropdownSpecialist.text
       val birthDay=binding.editBirthday
       val status=binding.dropdownStatus.text
       val role=binding.dropdownRole.text
       val blood=binding.dropdownType.text
       val nationalID=binding.editNationalID
       val insurance=binding.editInsuranceNumber
       val phone=binding.editPhone
       val address=binding.editAddress
       val email=binding.editEmail
       if(fName.text.toString().isEmpty()){
           fName.error="Require"
       }else if(lName.text.toString().isEmpty()){
           lName.error="Require"
       } else if(gender.isEmpty()|| gender.toString().contains("Specialist")){
           binding.spinnerGender.error="Require"
       }else if(specialist.isEmpty()|| specialist.toString().contains("Specialist")){
           binding.spinnerSpecialist.error="Require"
       }else if(birthDay.text.toString().isEmpty()){
           birthDay.error="Require"
       }else if(status.isEmpty()|| status.toString().contains("Status") ){
           binding.spinnerStatus.error="Require"
       }else if(role.isEmpty() || role.toString().contains("Role")){
           binding.spinnerRole.error="Require"
       }else if(blood.isEmpty() || blood.toString().contains("Blood")){
           binding.spinnerType.error="Require"
       }else if(nationalID.text.toString().isEmpty()){
           nationalID.error="Require"
       }else if(insurance.text.toString().isEmpty()){
           insurance.error="Require"
       }else if(phone.text.toString().isEmpty()){
           phone.error="Require"
       }else if(address.text.toString().isEmpty()){
           address.error="Require"
       }else if(email.text.toString().isEmpty()){
           email.error="Require"
       }else{

           if(nationalID.text.length<14){
               nationalID.error="should at least 14 character"
           }else if(insurance.text.length<9){
               insurance.error="should at least 9 character"
           }else if(phone.text.length<11){
               phone.error="should at least 11 character"
           }else if(!(email.text.toString().contains("@"))){
                   email.error="should contain @"
           }else{
               binding.btnCreate.text=""
               binding.btnCreate.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.btn))
               binding.progress.visibility=View.VISIBLE

               viewmodel.register(
                   ModelRegisterHr(
                       binding.editAddress.text.toString(),
                       "Cardiology",
                       binding.dropdownType.text.toString(),
                       "2023-12-21",
                       binding.editEmail.text.toString(),
                       binding.editFname.text.toString(),
                       binding.dropdownGender.text[0].toString(),
                       binding.editInsuranceNumber.text.toString(),
                       binding.editLname.text.toString(),
                       binding.editPhone.text.toString(),
                       binding.editNationalID.text.toString(),
                       binding.dropdownStatus.text[0].toString(),
                       binding.dropdownSpecialist.text[0].toString(),
                   )
               )
           }
       }





    }

    private fun observes() {
        viewmodel.liveData.observe(viewLifecycleOwner){
            binding.progress.visibility=View.INVISIBLE
            if(it){
                binding.btnCreate.text="Create User"
                binding.btnCreate.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))
            Toast.makeText(context,"done!",Toast.LENGTH_LONG).show()
            }
        }
    viewmodel.errorBody.observe(viewLifecycleOwner){
        binding.progress.visibility=View.INVISIBLE
        binding.btnCreate.text="Create User"
        binding.btnCreate.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_color))
        Toast.makeText(context,it.string(),Toast.LENGTH_SHORT).show()
    }

    }


    fun onClicks(){
        binding.btnCreate.setOnClickListener{
            binding.spinnerGender.error=null
            binding.spinnerSpecialist.error=null
            binding.spinnerStatus.error=null
            binding.spinnerRole.error=null
            binding.spinnerType.error=null
            validation()
        }

    }

  fun menui(){
      val itemGender= listOf("Male","Female")
      val adapterGender=ArrayAdapter(requireContext()!!,R.layout.list_item,itemGender)
      val itemSpecialist= listOf("doctor","nurse","receptionist","manger","laboratory Physician","hr")
      val adapterSpecialist=ArrayAdapter(requireContext()!!,R.layout.list_item,itemSpecialist)
      val itemRole= listOf("Admin","User")
      val adapterRole=ArrayAdapter(requireContext()!!,R.layout.list_item,itemRole)
      val itemStatus= listOf("Single","Married","divorced")
      val adapterStatus=ArrayAdapter(requireContext()!!,R.layout.list_item,itemStatus)
      val itemBlood= listOf("O+","O-","A+","A-","B+","B-","AB+","AB-")
      val adapterBlood=ArrayAdapter(requireContext(),R.layout.list_item,itemBlood)
      binding.dropdownGender.setAdapter(adapterGender)
      binding.dropdownRole.setAdapter(adapterRole)
      binding.dropdownSpecialist.setAdapter(adapterSpecialist)
      binding.dropdownStatus.setAdapter(adapterStatus)
      binding.dropdownType.setAdapter(adapterBlood)
  }
    }
