package com.example.hospital.UI.nurse.Cases.CasesScreen;



import android.os.Bundle;
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
import com.example.hospital.Adapters.AdapterCasesNurse;
import com.example.hospital.Data.Models.ModelCasesNurse;
import com.example.hospital.databinding.FragmentCasesNurseBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CasesNurse extends Fragment {
    AdapterCasesNurse adapter;
    ItemTouchHelper.SimpleCallback simpleCallback ;
    private CasesNurseViewModel viewModel;

    FragmentCasesNurseBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCasesNurseBinding.inflate(inflater);
        // Inflate the layout for this fragment

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CasesNurseViewModel.class);
        adapter = new AdapterCasesNurse(requireContext());



        adapter.setOnItemClickListener(new AdapterCasesNurse.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                NavDirections action= CasesNurseDirections.Companion.actionCasesNurseToCaseDetials(id+"");
                NavHostFragment.findNavController(CasesNurse.this).navigate(action);
            }
        });
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<ModelCasesNurse>() {
            @Override
            public void onChanged(ModelCasesNurse model) {
                binding.txtCount.setText(model.getCount()+"");
                adapter.setModelPatientList(model.getData());
                binding.patientRecycler.setAdapter(adapter);
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