package com.example.taserfan.activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.R;
import com.example.taserfan.activities.preferencias.GestionPreferencias;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;
import com.example.taserfan.objects.Tipo;
import com.example.taserfan.objects.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehiculoView extends BaseActivity implements CallInterface, View.OnClickListener {
    RecyclerView recyclerView;
    List<Vehiculo> vehiculos,auxList;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    Button todos,coche,moto,bici,patinete;
    Tipo tipo;

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


        todos=findViewById(R.id.vehiculos);
        coche=findViewById(R.id.coche);
        moto=findViewById(R.id.moto);
        bici=findViewById(R.id.bici);
        patinete=findViewById(R.id.patinete);


        ItemTouchHelper.SimpleCallback sck = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                vehiculos.remove(viewHolder.getAdapterPosition());
                myRecyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(sck);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
      //String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this)+ API.Routes.VEHICULOS;
        //vehiculos.add(new Vehiculo("1212ABC","AWA","rojo","preparado"));

       // vehiculos = Connector.getConector().getAsList(Vehiculo.class,url);

        String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this);

        vehiculos = Connector.getConector().getAsList(Vehiculo.class, url+ API.Routes.VEHICULOS);

        if (tipo==null){

        }else if(tipo==Tipo.MOTO){
            auxList=vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==Tipo.MOTO).collect(Collectors.toList());
        }else if(tipo==Tipo.COCHE){
            auxList=vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==Tipo.COCHE).collect(Collectors.toList());

        }else if (tipo==Tipo.BICICLETA){
            auxList=vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==Tipo.BICICLETA).collect(Collectors.toList());

        }else if (tipo==Tipo.PATINETE){
            auxList=vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==Tipo.PATINETE).collect(Collectors.toList());

        }


    }

    @Override
    public void doInUI() {
        if (tipo ==null) {
            myRecyclerViewAdapter.setNewData(vehiculos);
        }else {
            myRecyclerViewAdapter.setNewData(auxList);
        }
    }

    @Override
    public void onClick(View view) {
//        Intent intent =new Intent(getApplicationContext(),SecundaryActivity.class);
//        int position=recyclerView.getChildAdapterPosition(view);
//        intent.putExtra("position",position);
//
//        startActivity(intent);
    }



    public void onRadioButtonClicked(View view) {

        switch (view.getId()) {
            case R.id.vehiculos:
                tipo=null;
                executeCall(this);
                break;
            case R.id.moto:
                tipo=Tipo.MOTO;
                executeCall(this);

                 break;
            case R.id.coche:
                tipo=Tipo.COCHE;
                executeCall(this);

                break;
            case R.id.bici:
                tipo=Tipo.BICICLETA;
                executeCall(this);

                break;
            case R.id.patinete:
                tipo=Tipo.PATINETE;
                executeCall(this);

                break;
        }
    }
}