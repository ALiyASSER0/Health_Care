package com.example.hospital.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.Data.Models.Cases;
import com.example.hospital.Data.Models.DataNurse;
import com.example.hospital.R;
import com.example.hospital.databinding.NurseItemBinding;
import com.example.hospital.databinding.PatitentItemBinding;

import java.util.List;

public class AdapterCasesNurse extends RecyclerView.Adapter<AdapterCasesNurse.Holder> {
    Context context;
    NurseItemBinding binding;
    private List<DataNurse> modelPatientList;

    private OnItemClickListener onItemClick;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClick = listener;
    }
    public List<DataNurse> getModelPatientList() {

        return modelPatientList;
    }

    public void setModelPatientList(List<DataNurse> modelPatientList) {
        this.modelPatientList = modelPatientList;
    }

    public AdapterCasesNurse(@NonNull Context context) {
        super();
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = NurseItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
//      binding.CardView.startAnimation(AnimationUtils.loadAnimation(context,R.anim.recycler_anim));

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(modelPatientList.get(position));
        var anim= AnimationUtils.loadAnimation(context, R.anim.recycler_anim);
holder.binding.getRoot().startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return modelPatientList.isEmpty() ? 0 : modelPatientList.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        NurseItemBinding binding;

        public Holder(@NonNull NurseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


            binding.btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClick != null) {
                        onItemClick.onItemClick(modelPatientList.get(position).getPatient().getId());
                    }
                }
            });
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

int visibilityState=binding.btnDetails.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE;
binding.btnDetails.setVisibility(visibilityState);
    }
});

        }

        void bind(DataNurse modelPatient) {
        //    String name=modelPatient.getName().length()>=5? modelPatient.getName().substring(0,5): modelPatient.getName();
            binding.textUserName.setText(modelPatient.getPatient().getFirst_name()+" "+modelPatient.getPatient().getFirst_name());
            binding.textStatus.setText(modelPatient.getType());
            binding.txtNum.setText(modelPatient.getNumber_in_qeue()+"");
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
