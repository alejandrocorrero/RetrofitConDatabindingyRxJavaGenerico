package com.example.a2dam.retrofit;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a2dam.retrofit.api.SwapiClient;
import com.example.a2dam.retrofit.api.SwapiService;
import com.example.a2dam.retrofit.api.responses.PeopleResponse;
import com.example.a2dam.retrofit.api.responses.Planet;
import com.example.a2dam.retrofit.api.responses.Planets;
import com.example.a2dam.retrofit.api.responses.Result;
import com.example.a2dam.retrofit.databinding.ActivityMainBinding;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private SwapiService swapiService;
    private ActivityMainBinding mBinding;
    private MainActivityAdapter adapter;
    private MainActivityViewModel mviewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        mviewmodel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        adapter= new MainActivityAdapter(mviewmodel,this);
        RecyclerView recyclerView = mBinding.list;
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        swapiService = SwapiClient.getInstance(this).getService();
        obtenerPersona();
    }


    private void obtenerPersona() {
        mviewmodel.getPeople().observe(this,this::setPeople);

    }



    private void setPeople(List<Result> result) {
        adapter.setList(result);

    }



}
