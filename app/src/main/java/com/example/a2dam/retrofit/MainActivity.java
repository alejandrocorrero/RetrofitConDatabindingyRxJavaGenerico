package com.example.a2dam.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.a2dam.retrofit.api.SwapiClient;
import com.example.a2dam.retrofit.api.SwapiService;
import com.example.a2dam.retrofit.api.responses.PeopleResponse;
import com.example.a2dam.retrofit.api.responses.Result;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SwapiService swapiService;
    private Button btnPersona;
    private TextView lblPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        swapiService = SwapiClient.getInstance().getService();
        lblPersona = findViewById(R.id.lblPersona);
        btnPersona = findViewById(R.id.btnPersona);
        btnPersona.setOnClickListener(v -> obtenerPersona());
    }

    private void obtenerPersona() {
        Observable<PeopleResponse> llamada = swapiService.getPeoples();
        (Observable<PeopleResponse>)swapiService.getPeoples(),PeopleResponse.class, true, true);
        llamada.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::setPeople);
    }

    private void setPeople(PeopleResponse results) {
        lblPersona.setText(results.getResults().get(2).getName());
    }


}
