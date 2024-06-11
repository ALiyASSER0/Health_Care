package com.example.hospital.UI.SharedScreen;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hospital.CustomAnimated;
import com.example.hospital.Data.Models.Data;
import com.example.hospital.R;
import com.example.hospital.databinding.FragmentProfileBinding;
import com.google.gson.Gson;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 *
 */

public class Profile extends Fragment implements View.OnClickListener {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentProfileBinding binding;
    CustomAnimated profileAnim;
    SafeVarargs varar;
    private DatePickerDialog datePickerDialog;
    // TODO: Rename and change types o f   parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Nullable
//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        Log.w("onCreateAnimation", "in onCreateAnimation");
//        return super.onCreateAnimation(transit, enter, nextAnim);
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        Log.w("onAttach", "in onAttach");

        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.w("onStart", "in onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.w("onResume", "in onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.w("onResume", "in onpause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.w("onResume", "in onStop");

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("onResume", "in onDestroy ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.w("onResume", "in onDetach");
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater);
        Log.w("onCreateView", "in onCreateView");
        getViewLifecycleOwnerLiveData();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        int number = ProfileArgs.fromBundle(getArguments()).getNum();
        animated();
        Log.d("Safe args value", "is " + "number" + " .");
        binding.shape4.setOnClickListener(this);
        binding.CancelButton.setOnClickListener(this);
        binding.SaveButton.setOnClickListener(this);
        binding.e4.setOnClickListener(this);
        binding.img2.setOnClickListener(this);
        Log.w("onViewCreated", "in onViewCreated");
        Gson gson=new Gson();
        String jsonString= ProfileArgs.fromBundle(getArguments()).getDataOfUser();
        Data obj=gson.fromJson(jsonString,Data.class);
        Log.e("w",obj.toString());
        Log.e("w2",obj.getFirstName());
        String e1=obj.getFirstName()+" "+obj.getLastName();
        binding.e1.setText(e1);


        if (obj==null){
binding.e2.setVisibility(View.VISIBLE);
binding.img1.setVisibility(View.VISIBLE);
            binding.e2.setText(obj.getAddress());
        }
    Log.e("w4",binding.e5.getText().toString());

        Log.e("w7",obj.getAddress());
//      if (obj.getAddress().length()>0){
//          Log.e("w8",obj.getAddress());
//        binding.e5.setText(obj.getAddress());
//      }

            binding.e6.setText(obj.getEmail());


        binding.e7.setText(obj.getUsername());


    }

    private void animated() {

        profileAnim = new CustomAnimated(this.requireContext());

        profileAnim.setContext(requireContext());
        Animation a = profileAnim.getTtb1();
//        a.setDuration(700);
//        a.setStartOffset(200);
        a.setBackgroundColor(Color.BLUE);
        a.setRepeatCount(Animation.ABSOLUTE);
        binding.e1.startAnimation(a);
      profileAnim.AnimatedScreen
              (true,binding.e2, binding.img1,binding.e3,binding.img2,
              binding.e4,binding.img3,binding.e5,binding.img4,
              binding.e6,binding.img5,binding.e7,binding.img6,binding.shape2);
//      ******* profileAnim.AnimatedScreen******* reduce line of code
//        binding.e2.startAnimation(profileAnim.getTtb2());
//        binding.img1.startAnimation(profileAnim.getTtb2());
//        binding.e3.startAnimation(profileAnim.getTtb3());
//        binding.img2.startAnimation(profileAnim.getTtb3());
//        binding.e4.startAnimation(profileAnim.getTtb4());
//        binding.img3.startAnimation(profileAnim.getTtb4());
//        binding.e5.startAnimation(profileAnim.getTtb5());
//        binding.img4.startAnimation(profileAnim.getTtb5());
//        binding.e6.startAnimation(profileAnim.getTtb6());
//        binding.img5.startAnimation(profileAnim.getTtb6());
//        binding.e7.startAnimation(profileAnim.getTtb7());
//        binding.img6.startAnimation(profileAnim.getTtb7());
//        binding.shape2.startAnimation(profileAnim.getStb());

    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.shape4) {
            binding.shape4.animate().setDuration(350)
                    .rotationYBy(360f).start();
            binding.pencil1.animate().setDuration(350)
                    .rotationYBy(360f).start();
            binding.e1.setEnabled(true);
            binding.e2.setEnabled(true);
            binding.e3.setEnabled(true);
            binding.e4.setEnabled(true);
            binding.e5.setEnabled(true);
            binding.e6.setEnabled(true);
            binding.e7.setEnabled(true);
            binding.SaveButton.setVisibility(View.VISIBLE);
            binding.CancelButton.setVisibility(View.VISIBLE);
            binding.SaveButton.setEnabled(true);
            binding.CancelButton.setEnabled(true);

        }


        if (view.getId() == R.id.SaveButton) {
            Toast.makeText(requireContext(), "hi ", Toast.LENGTH_LONG).show();


//           requireActivity().onBackPressed();
//            var action = ProfileDirections.actionProfileToHome2();
//            NavHostFragment.findNavController(this).navigate(action);

            //Make copy of data to check if data Changed to send request or not
            //Send request To post New Data .

        }
        if (view.getId() == R.id.CancelButton) {

            //Reset Data  and close editing.
            binding.e1.setEnabled(false);
            binding.e2.setEnabled(false);
            binding.e3.setEnabled(false);
            binding.e4.setEnabled(false);
            binding.e5.setEnabled(false);
            binding.e6.setEnabled(false);
            binding.e7.setEnabled(false);

            binding.SaveButton.setVisibility(View.INVISIBLE);
            binding.CancelButton.setVisibility(View.INVISIBLE);
            binding.SaveButton.setEnabled(false);
            binding.CancelButton.setEnabled(false);
            Toast.makeText(requireContext(), "Editing finshed", Toast.LENGTH_LONG).show();
        }
        if (view.getId() == R.id.img3 || view.getId() == R.id.e4) {

            // binding.e4.setText();
            Animation imgStop = profileAnim.getStb();
            imgStop.setRepeatCount(Animation.ABSOLUTE);
            binding.shape2.startAnimation(imgStop);

            // on below line we are getting
            // the instance of our calendar.
            final Calendar c = Calendar.getInstance();

            // on below line we are getting
            // our day, month and year.
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
//DatePickerDialog datePickerDialog1=new DatePickerDialog(this);

//            int style = AlertDialog.THEME_HOLO_LIGHT;
            // on below line we are creating a variable for date picker dialog.
            datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    requireContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // on below line we are setting date to our text view.
                            binding.e4.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year - 21, month, day);
            Log.e("date", System.currentTimeMillis() + "");
            Calendar c1 = Calendar.getInstance();

            c1.set(year - 20, 0, 1);
            datePickerDialog.getDatePicker().setMaxDate((c1.getTimeInMillis()));
            c1.set(year - 75, 0, 1);
            datePickerDialog.getDatePicker().setMinDate((c1.getTimeInMillis()));

            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        }
        if (view.getId() == R.id.img2) {
            //to stop   (Animation)  android:repeatCount="infinite"
            binding.shape2.clearAnimation();

//            for now it useless ...😒
//            profileAnim.getStb().cancel();
//            profileAnim.getStb().reset();

        }

    }


}