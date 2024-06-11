package com.example.hospital.UI.Doctors.Cases.Clinic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.example.hospital.Adapters.AdapterRecyclerClinc;
import com.example.hospital.Data.Models.ModelCases;
import com.example.hospital.databinding.FragmentClinicBinding;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;



@AndroidEntryPoint
public class ClinicFragment extends Fragment {
    AdapterRecyclerClinc adapterRecyclerClinc;
    ItemTouchHelper.SimpleCallback simpleCallback ;
    private ClinicFragmentViewModel viewModel;

  FragmentClinicBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClinicBinding.inflate(inflater);
        Log.e("log", "onCreateViewFragment");
        // Inflate the layout for this fragment

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ClinicFragmentViewModel.class);
        adapterRecyclerClinc = new AdapterRecyclerClinc(requireContext());



        adapterRecyclerClinc.setOnItemClickListener(new AdapterRecyclerClinc.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {

                    NavDirections action= ClinicFragmentDirections.Companion.actionClinicFragmentToInformation(id+"");
                    NavHostFragment.findNavController(ClinicFragment.this).navigate(action);


            }
        });
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<ModelCases>() {
            @Override
            public void onChanged(ModelCases model) {
                binding.txtCount.setText(model.getCount()+"");
                adapterRecyclerClinc.setModelPatientList(model.getData());
                binding.patientRecycler.setAdapter(adapterRecyclerClinc);
                binding.patientRecycler.startAnimation(AnimationUtils.loadAnimation(requireContext(), com.example.hospital.R.anim.recycler_anim));

            }
        });
        viewModel.getErrorBody().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getCases();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}