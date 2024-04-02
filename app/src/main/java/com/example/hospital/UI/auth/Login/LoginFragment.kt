package com.example.hospital.UI.auth.Login
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.databinding.FragmentLoginBinding
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
    }

    private fun validation() {
      val email=binding.editEmail.text.toString()
      val password=binding.editTextPassword.text.toString()
        if(email.isEmpty()){
            binding.editEmail.error = "Require"
        }else if(password.isEmpty()){
            binding.editTextPassword.error="Require"
        }else{
            binding.progressBar.visibility=View.VISIBLE
            viewModel.login(email,password)
        }
    }

    private fun observes() {
        var action1=LoginFragmentDirections.actionLoginFragmentToHome2()
        viewModel.liveData.observe(viewLifecycleOwner){
            binding.progressBar.visibility=View.INVISIBLE
            if(it.toString().isNotEmpty()){
                findNavController().navigate(action1)
            }
        }
        viewModel.errorCode.observe(viewLifecycleOwner){
            binding.progressBar.visibility=View.INVISIBLE
            Toast.makeText(context,"Wrong Password",Toast.LENGTH_LONG).show()

        }
    }

    private fun onClicks() {
        var action2=LoginFragmentDirections.actionLoginFragmentToPatientRegister()
        binding.btnLogin.setOnClickListener{
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
