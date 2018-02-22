package com.example.a2dam.retrofit;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.a2dam.retrofit.api.responses.Planet;
import com.example.a2dam.retrofit.api.responses.Result;
import com.example.a2dam.retrofit.databinding.ActivityMainItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivityAdapter extends ListAdapter<Result, MainActivityAdapter.ViewHolder> {

    private final LifecycleOwner ctr;
    private List<Result> people = new ArrayList<>();
    private MainActivityViewModel mviewmodel;

    MainActivityAdapter(MainActivityViewModel viewModel, LifecycleOwner context) {
        super(new DiffCallback<Result>() {
            @Override
            public boolean areItemsTheSame(@NonNull Result oldStudent, @NonNull Result newStudent) {
                return Objects.equals(oldStudent.getName(), newStudent.getName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Result oldStudent,
                                              @NonNull Result newStudent) {
                return oldStudent.equals(newStudent);
            }
        });
        this.mviewmodel = viewModel;
        this.ctr = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityMainItemBinding binding = ActivityMainItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(MainActivityAdapter.ViewHolder holder, int position) {
        String cosa = people.get(position).getHomeworld().replaceAll("\\D+", "");
        mviewmodel.getPlanet(Integer.parseInt(cosa), position).observe(ctr, p -> holder.bind(people.get(position), p));


    }


    @Override
    public void setList(List<Result> list) {
        this.people = list;
        super.setList(list);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final ActivityMainItemBinding binding;


        ViewHolder(ActivityMainItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(Result result, Planet planet) {
            binding.setResult(result);
            binding.setPlanet(planet);
            binding.executePendingBindings();
        }
    }
}