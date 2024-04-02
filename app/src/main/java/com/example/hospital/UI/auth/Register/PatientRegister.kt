package com.example.hospital.UI.auth.Register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.Data.Models.ModelRegisterPatient
import com.example.hospital.databinding.FragmentPatientRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject


@AndroidEntryPoint
class PatientRegister : Fragment() {

private var _binding: FragmentPatientRegisterBinding?=null
private val binding get() = _binding!!
val viewModel:PatientRegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentPatientRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        onClicks()

        observes()

    }

    private fun observes() {
        var action=PatientRegisterDirections.actionPatientRegisterToLoginFragment()
        viewModel.liveData.observe(viewLifecycleOwner){flag->
            binding.progress.visibility=View.INVISIBLE
            if(flag){
                findNavController().navigate(action)
            }
        }
        viewModel.errorBody.observe(viewLifecycleOwner){
            binding.progress.visibility=View.INVISIBLE
            Toast.makeText(context,it.string(),Toast.LENGTH_LONG).show()
        }

    }
    private fun validation(){
        val fName=  binding.editFname
        val lName=  binding.editLname
        val email=  binding.editEmail
        val password=binding.editPassword
        val configPassword=  binding.editPasswordConfirm
        val phone= binding.editPhone
        if(fName.text.isEmpty()){
            fName.error="Require"
        }else if(lName.text.isEmpty()){
            lName.error="Require"
        }else if(phone.text.isEmpty()){
            phone.error="Require"
        }else if(email.text.isEmpty()){
            email.error="Require"
        }else if(password.text.isEmpty()){
            password.error="Require"
        }else if(configPassword.text.isEmpty()){
            configPassword.error="Require"
        }else{
            if(!(email.text.contains("@"))){
                email.error="email should contain @"
            }else if(password.text.length<9){
                password.error="password should at least 9"
            }else if(phone.text.length<9){
                phone.error="phone should be 11 character"
            }else{
                Log.e("TAG", "validation:${password.text.toString() == configPassword.text.toString()} ", )
                if(password.text.toString() == configPassword.text.toString()){

                    binding.progress.visibility=View.VISIBLE
                    viewModel.register(ModelRegisterPatient(
                        fName.text.toString(),
                        lName.text.toString(),
                        email.text.toString(),
                        password.text.toString(),
                        configPassword.text.toString(),
                        phone.text.toString()
                    ))
                }else{
                    Toast.makeText(context,"Password and Confirm Password does not match",Toast.LENGTH_LONG).show()
                }
            }



        }



    }
    private fun onClicks() {
        binding.btnCreate.setOnClickListener{

            validation()
        }
    }


}
