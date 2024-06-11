package com.example.hospital.UI.Doctors.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.hospital.Data.Models.Data;

import com.example.hospital.databinding.FragmentHomeDoctorBinding;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorHomeFragment extends Fragment implements View.OnClickListener {
FragmentHomeDoctorBinding binding;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoctorHomeFragment() {
        // Required empty public constructor

    }


    public static DoctorHomeFragment newInstance(String param1, String param2) {
        DoctorHomeFragment fragment = new DoctorHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeDoctorBinding.inflate(inflater);
        Log.w("onCreateView", "in onCreateView");
        getViewLifecycleOwnerLiveData();
        return binding.getRoot();
     }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.cardTasks.setOnClickListener(this);
        binding.cardEmploye.setOnClickListener(this);
    }
    int  x=0;
    @Override
    public void onClick(View v) {
    if (v.getId()==binding.cardEmploye.getId()){

        NavDirections action= DoctorHomeFragmentDirections.Companion.actionDoctorHomeFragmentToClinicFragment();
NavHostFragment.findNavController(this).navigate(action);
//        NavDirections action=DoctorHomeFragmentDirections.Companion.actionDoctorHomeFragmentToInformation();
//        NavHostFragment.findNavController(this).navigate(action);

    }
    if (v.getId()==binding.cardTasks.getId()){
        DoctorHomeFragmentArgs object=DoctorHomeFragmentArgs.fromBundle(getArguments());
        NavDirections action=DoctorHomeFragmentDirections.Companion.actionDoctorHomeFragmentToProfile(String.valueOf(object));
        NavHostFragment.findNavController(this).navigate(action);
    }


    }
}