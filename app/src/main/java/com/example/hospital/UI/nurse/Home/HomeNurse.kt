package com.example.hospital.UI.nurse.Home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hospital.Data.Models.Data
import com.example.hospital.Data.Models.ModelCurrentUser
import com.example.hospital.R
import com.example.hospital.UI.MySharedPreferences

import com.example.hospital.databinding.FragmentHomeNurseBinding
import com.google.gson.Gson


class HomeNurse : Fragment() {
    private var _binding: FragmentHomeNurseBinding? =null
    val binding get() = _binding!!
    val args: HomeNurseArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeNurseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson= Gson()
        val jsonString=args.dataOfUser
        val model=gson.fromJson(jsonString, Data::class.java)


        val name:String?=model?.firstName

        if(name.isNullOrEmpty()){
            binding.textUserName.text= MySharedPreferences.getUserName()
        }else{
            model?.let { it.firstName?.let { it1 -> MySharedPreferences.setUserName(it1) } }
            binding.textUserName.text=name
        }
        binding.cardEmploye.setOnClickListener {
            val action= HomeNurseDirections.actionHomeNurseToCasesNurse()

            findNavController().navigate(action)
        }
        binding.cardNewEmployee.setOnClickListener {
            val action= HomeNurseDirections.actionHomeNurseToNurseCalls()

            findNavController().navigate(action)
        }
        binding.cardAttendance.setOnClickListener {

        }

      binding.cardCalls.setOnClickListener {
          showDialog()
      }


    }
    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheet2)

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }
    //        var upload: LinearLayout =dialog.findViewById(R.id.surgery)
//        var show: LinearLayout =dialog.findViewById(R.id.patient)
//        upload.setOnClickListener {
//            Toast.makeText(requireContext(),"surgery", Toast.LENGTH_SHORT).show()
//        }
//        show.setOnClickListener {
//            Toast.makeText(requireContext(),"patient", Toast.LENGTH_SHORT).show()
//        }

}