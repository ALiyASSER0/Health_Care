package com.example.hospital.UI.auth.Login
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.hospital.CustomAnimated
import com.example.hospital.Data.Models.Data

import com.example.hospital.UI.MySharedPreferences
import com.example.hospital.databinding.FragmentLoginBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding :FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observes()
        onClicks()
        errorMessageRemove()
    }
    private fun errorMessageRemove(){
        binding.editTextPassword1.doAfterTextChanged {
            binding.editTextPassword.error = null
        }

        binding.editEmail1.doAfterTextChanged{
            binding.editEmail.error = null
        }

    }
    override fun onStart() {
        super.onStart()
        animated()
    }
    private fun animated() {
        val con: Context? =this.context
        val profileAnim : CustomAnimated = CustomAnimated(con)
        val stb= profileAnim.stb
        stb.duration=1750
        stb.startOffset=75
        profileAnim.stb=stb
        //didn't work from here (fragment) work in xml anim file
//     stb.repeatCount=Animation.INFINITE
//stb.repeatMode=Animation.INFINITE
//        binding.imageView2.startAnimation(stb)
        binding.textView.startAnimation(profileAnim.ttb1)
        profileAnim.AnimatedScreen(
            false,binding.textView2,binding.editEmail,
            binding.editTextPassword,binding.btnLogin,binding.account,binding.goToReg, binding.imageView2)
//        binding.textView2.startAnimation(profileAnim.ttb2)
//        binding.editEmail.startAnimation(profileAnim.ttb3)
//        binding.editTextPassword.startAnimation(profileAnim.ttb4)
//        binding.btnLogin.startAnimation(profileAnim.ttb5)
//        binding.account.startAnimation(profileAnim.ttb6)
//        binding.goToReg.startAnimation(profileAnim.ttb7)

    }
    private fun validation() {
        var email=binding.editEmail1.text.toString()
        val password=binding.editTextPassword1.text.toString()

        if(email.isEmpty()){
            binding.editEmail.error = "Require"
        }else if(password.isEmpty()){
            binding.editTextPassword.error="Require"
        }else{
            binding.progressBar.visibility=View.VISIBLE
//            email=if(email.first().equals('0'))email else "0$email"
            email=if(email.first().equals('0'))email else "$email"

            viewModel.login(email,password)
        }
    }

    private fun observes() {

        viewModel.accessTokenLiveData.observe(viewLifecycleOwner){
            binding.progressBar.visibility=View.INVISIBLE
            MySharedPreferences.setUserTOKEN(it.access)
            viewModel.getCurrentUser()
        }
        viewModel.currentUserLiveData.observe(viewLifecycleOwner){
            binding.progressBar.visibility=View.INVISIBLE
            navigation(it)
        }
        viewModel.accessTokenErrorBody.observe(viewLifecycleOwner){
            binding.progressBar.visibility=View.INVISIBLE
            Toast.makeText(context,"Wrong Password",Toast.LENGTH_LONG).show()
        }
        viewModel.currentUserErrorBody.observe(viewLifecycleOwner){
            binding.progressBar.visibility=View.INVISIBLE
            Toast.makeText(context,"Wrong Password",Toast.LENGTH_LONG).show()

        }

    }

    private fun navigation(data: Data) {

        val gson=Gson()
        val josnString=gson.toJson(data)
        var action1 :NavDirections=LoginFragmentDirections.actionLoginFragmentToHome2(josnString)
        var action2=LoginFragmentDirections.actionLoginFragmentToHomePatient(josnString)
        var action3=LoginFragmentDirections.actionLoginFragmentToDoctorHomeFragment(josnString)
        var action4= LoginFragmentDirections.actionLoginFragmentToHomeNurse(josnString)
        if(data.employee?.type=="H"){
            findNavController().navigate(action1)
        }else if(data.employee==null){

            findNavController().navigate(action2)
        }else if(data.employee.type=="Doctor"){
            findNavController().navigate(action3)

        }else if(data.employee.type=="Nurse"){
            findNavController().navigate(action4)

        }


    }

    private fun onClicks() {
        var action2=LoginFragmentDirections.actionLoginFragmentToPatientRegister()
        binding.btnLogin.setOnClickListener{
            binding.editTextPassword1
                .onEditorAction(EditorInfo.IME_ACTION_DONE)
            binding.editEmail1
                .onEditorAction(EditorInfo.IME_ACTION_DONE)
            validation()

        }
        binding.goToReg.setOnClickListener {
            findNavController().navigate(action2)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
