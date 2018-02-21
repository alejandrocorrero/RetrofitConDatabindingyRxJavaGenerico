package com.example.a2dam.retrofit;

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
import com.example.a2dam.retrofit.databinding.ActivityMainBinding;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private SwapiService swapiService;
    private ActivityMainBinding mBinding;
    private MainActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);
         adapter= new MainActivityAdapter();
        RecyclerView recyclerView = mBinding.list;
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        swapiService = SwapiClient.getInstance(this).getService();
        obtenerPersona();
    }

    private void obtenerPersona() {
        Observable<PeopleResponse> llamada = swapiService.getPeoples();
        llamada.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::setPeople);
    }



    private void setPeople(PeopleResponse peopleResponse) {
        adapter.setList(peopleResponse.getResults());

    }


}
