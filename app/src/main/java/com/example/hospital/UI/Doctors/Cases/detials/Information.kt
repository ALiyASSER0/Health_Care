package com.example.hospital.UI.Doctors.Cases.detials

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hospital.R
import com.example.hospital.databinding.FragmentInformationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Information : Fragment() {
private var _binding: FragmentInformationBinding? =null
    val binding get() = _binding!!
    private val args:InformationArgs by navArgs()
  val infViewModel:InformationViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentInformationBinding.inflate(inflater)
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
        binding.btnComplete.setOnClickListener {
            poppBox2()
        }
        binding.btnMedical.setOnClickListener {
            showDialog()
        }
        binding.btnRequest.setOnClickListener {
            poppBox()
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
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        }


    }

    private fun poppBox2() {
        val listArray = arrayOf("End of consultation ", "Consultation request")
        var index = 0
        var selectedItem = listArray[index]

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Choose")
            .setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.background))
            .setSingleChoiceItems(listArray, index) { _, which ->
                index = which
                selectedItem = listArray[which]
            }
            .setPositiveButton("OK") { dialog, _ ->
                if(selectedItem=="End of consultation"){
                    Toast.makeText(requireContext(), "Hellow ali ", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), selectedItem, Toast.LENGTH_SHORT).show()

                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Handle cancel button click
                Toast.makeText(requireContext(), "No selection made", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_color))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
    }


    private fun poppBox() {
        val listArray = arrayOf("Medication ", "operation","Lab tests")
        var index = 0
        var selectedItem = listArray[index]

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Choose")
            .setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.background))
            .setSingleChoiceItems(listArray, index) { _, which ->
                index = which
                selectedItem = listArray[which]
            }
            .setPositiveButton("OK") { dialog, _ ->
             if(selectedItem=="Medication "){
                 Toast.makeText(requireContext(), "Hellow Medication ", Toast.LENGTH_SHORT).show()
             }else{
                 Toast.makeText(requireContext(), selectedItem, Toast.LENGTH_SHORT).show()

             }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Handle cancel button click
                Toast.makeText(requireContext(), "No selection made", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_color))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
    }

    private fun showDialog() {
        val dialog=Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheet)
        var upload:LinearLayout=dialog.findViewById(R.id.layoutUpload)
        var show:LinearLayout=dialog.findViewById(R.id.layoutShow)
        upload.setOnClickListener {
            Toast.makeText(requireContext(),"upload",Toast.LENGTH_SHORT).show()
        }
        show.setOnClickListener {
            Toast.makeText(requireContext(),"show",Toast.LENGTH_SHORT).show()
        }

    dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations=R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
   }
}