package com.example.taserfan.activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
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
    Button todos,coche,moto,bici,patinete,añadir;
    EditText busqueda;
    Tipo tipo;
    Result result;
    private String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo_view);
        recyclerView= findViewById(R.id.recicle);
        añadir=findViewById(R.id.add);
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
        busqueda=findViewById(R.id.busqueda);

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),AddVehiculo.class);

                startActivity(intent);
            }
        });


        ItemTouchHelper.SimpleCallback sck = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Vehiculo v=  auxList.get(viewHolder.getAdapterPosition());
                String auxUrl="";
                switch (v.getTipo()) {
                    case MOTO:
                        auxUrl=url+API.Routes.MOTO;
                        break;
                    case COCHE:
                        auxUrl=url+API.Routes.COCHE;
                        break;
                    case PATINETE:
                        auxUrl=url+API.Routes.PATINETE;
                        break;
                    case BICICLETA:
                        auxUrl=url+API.Routes.BICI;
                        break;

                }
                String finalAuxUrl = auxUrl;
                executeCall(new CallInterface() {
                    @Override
                    public void doInBackground() {
                            result=Connector.getConector().deleteVehiculo(Vehiculo.class,(finalAuxUrl +"?matricula="+v.getMatricula()));
                    }

                    @Override
                    public void doInUI() {
                        if (result instanceof Result.Success){
                            Toast.makeText(getApplicationContext(), "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                        }else {
                            Result.Error error=(Result.Error)result;
                            AlertDialog.Builder builder = new AlertDialog.Builder(VehiculoView.this);
                            builder.setMessage("Error code: "+error.getCode()+"\n"+"Error message: "+error.getError())
                                    .setTitle("Error Delete")
                                    .setPositiveButton("Oki",null);
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                        vehiculos.remove(viewHolder.getAdapterPosition());
                        myRecyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                });

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(sck);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        executeCall(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doInBackground() {
      //String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this)+ API.Routes.VEHICULOS;
        //vehiculos.add(new Vehiculo("1212ABC","AWA","rojo","preparado"));

       // vehiculos = Connector.getConector().getAsList(Vehiculo.class,url);

        vehiculos = Connector.getConector().getAsList(Vehiculo.class, url+ API.Routes.VEHICULOS);
        auxList=vehiculos;
    }

    @Override
    public void doInUI() {
        myRecyclerViewAdapter.setNewData(auxList);
    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent(getApplicationContext(),SecundaryActivity.class);
        int position=recyclerView.getChildAdapterPosition(view);
        intent.putExtra("position",position);
        intent.putExtra("tipo",auxList.get(position).getTipo());
        intent.putExtra("matricula",auxList.get(recyclerView.getChildAdapterPosition(view)).getMatricula());

        startActivity(intent);
    }



    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.vehiculos:
                tipo=null;
                buscar();
                break;
            case R.id.moto:
                tipo=Tipo.MOTO;
                buscar();

                 break;
            case R.id.coche:
                tipo=Tipo.COCHE;
                buscar();

                break;
            case R.id.bici:
                tipo=Tipo.BICICLETA;
                buscar();

                break;
            case R.id.patinete:
                tipo=Tipo.PATINETE;
                buscar();
                break;
        }
    }

    public void buscar(){
        if (tipo==null){
            auxList=vehiculos;
        }else {
            auxList = vehiculos.stream().filter(vehiculo -> vehiculo.getTipo() == tipo).collect(Collectors.toList());
        }

        busqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (tipo==null){
                    auxList=vehiculos;
                }else{
                    auxList=vehiculos.stream().filter(vehiculo -> vehiculo.getTipo()==tipo).collect(Collectors.toList());
                }
                auxList=auxList.stream().filter(vehiculo -> vehiculo.getMatricula().contains(editable.toString())).collect(Collectors.toList());
                myRecyclerViewAdapter.setNewData(auxList);
            }
        });
        myRecyclerViewAdapter.setNewData(auxList);
    }
}