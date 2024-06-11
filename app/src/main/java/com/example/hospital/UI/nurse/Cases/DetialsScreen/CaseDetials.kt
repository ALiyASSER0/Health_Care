package com.example.hospital.UI.nurse.Cases.DetialsScreen

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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hospital.R
import com.example.hospital.databinding.FragmentCaseDetialsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CaseDetials : Fragment() {
    private var _binding: FragmentCaseDetialsBinding? =null

    val binding get() = _binding!!
    private val args: CaseDetialsArgs by navArgs()
    val infViewModel: CasesDetialsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentCaseDetialsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        args.id?.let {
            infViewModel.getCasesDetials(it)
        }





        observes()


        clicks()




    }

    private fun clicks() {

        binding.btnMedical.setOnClickListener {
            showDialog()
        }

    }

    private fun observes() {
        infViewModel.liveData.observe(viewLifecycleOwner){
            binding.textName.text="${it.first_name} ${it.last_name}"
            binding.textBlood.text=it.blood
            binding.textGander.text=it.gender
            binding.textStatus.text=it.status
        }
        infViewModel.errorBody.observe(viewLifecycleOwner){
            Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
        }


    }




    private fun showDialog() {
        val dialog= Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheet)
        var upload: LinearLayout =dialog.findViewById(R.id.layoutUpload)
        var show: LinearLayout =dialog.findViewById(R.id.layoutShow)
        upload.setOnClickListener {
            Toast.makeText(requireContext(),"upload", Toast.LENGTH_SHORT).show()
        }
        show.setOnClickListener {
            Toast.makeText(requireContext(),"show", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations=R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }


}