package com.example.taserfan.activities;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.R;
import com.example.taserfan.activities.preferencias.GestionPreferencias;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;
import com.example.taserfan.objects.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class VehiculoView extends BaseActivity implements CallInterface, View.OnClickListener {
    RecyclerView recyclerView;
    List<Vehiculo> vehiculos;
    MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo_view);
        recyclerView= findViewById(R.id.recicle);

        vehiculos=new ArrayList<Vehiculo>();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, vehiculos);
        myRecyclerViewAdapter.setListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);

        LinearLayoutManager myLinearLayaoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayaoutManager);
        recyclerView.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this)+ API.Routes.VEHICULOS;
        //vehiculos.add(new Vehiculo("1212ABC","AWA","rojo","preparado"));
        vehiculos = Connector.getConector().getAsList(Vehiculo.class,url);

    }

    @Override
    public void doInUI() {
        if (vehiculos !=null) {
            myRecyclerViewAdapter.setNewData(vehiculos);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent(getApplicationContext(),SecundaryActivity.class);
        int position=recyclerView.getChildAdapterPosition(view);
        intent.putExtra("position",position);

        startActivity(intent);
    }
}