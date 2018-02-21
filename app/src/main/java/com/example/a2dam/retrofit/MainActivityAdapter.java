package com.example.a2dam.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.a2dam.retrofit.api.SwapiClient;
import com.example.a2dam.retrofit.api.SwapiService;
import com.example.a2dam.retrofit.api.responses.Planet;
import com.example.a2dam.retrofit.api.responses.Result;
import com.example.a2dam.retrofit.databinding.ActivityMainItemBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private List<Result> people = new ArrayList<>();
    private List<Planet> planets = new ArrayList<>();
    private SwapiService swapiService;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityMainItemBinding binding = ActivityMainItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        swapiService = SwapiClient.getInstance(parent.getContext()).getService();
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(MainActivityAdapter.ViewHolder holder, int position) {
        String cosa = people.get(position).getHomeworld().replaceAll("\\D+", "");
        Observable<Planet> llamada = swapiService.getPlanet(Integer.parseInt(cosa));

        llamada.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(planet ->  holder.bind(people.get(position),planet));


    }

    void setPlanet(List<Planet> planet) {
        this.planets = planet;
        notifyDataSetChanged();
    }

    void setList(List<Result> list) {
        this.people = list;
        notifyDataSetChanged();
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