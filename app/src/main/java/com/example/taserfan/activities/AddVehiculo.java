package com.example.taserfan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.R;
import com.example.taserfan.activities.preferencias.GestionPreferencias;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;
import com.example.taserfan.objects.Bicicleta;
import com.example.taserfan.objects.Coche;
import com.example.taserfan.objects.Moto;
import com.example.taserfan.objects.Patinete;
import com.example.taserfan.objects.Tipo;

import java.util.ArrayList;

public class AddVehiculo extends BaseActivity implements AdapterView.OnItemSelectedListener {

    EditText matricula, modelo, descripcion, bateria, carnet, color, estado,
            fecha, precioHora, numPlazas, numPuertas, cilindrada,
            velocidadMax, tipoBici, numRuedas, tamanyo;
    Spinner spinner;
    ArrayList tipos;
    ArrayAdapter arrayAdapter;
    Button add;
    Result result;
    private String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehiculo);
        matricula = findViewById(R.id.matricula);
        modelo = findViewById(R.id.modelo);
        descripcion = findViewById(R.id.descripcion);
        bateria = findViewById(R.id.bateria);
        carnet = findViewById(R.id.carnet);
        color = findViewById(R.id.color);
        estado = findViewById(R.id.estado);
        fecha = findViewById(R.id.fecha);
        precioHora = findViewById(R.id.precioHora);
        numPlazas = findViewById(R.id.numplazas);
        numPuertas = findViewById(R.id.numPuertas);
        cilindrada = findViewById(R.id.cilindrada);
        velocidadMax = findViewById(R.id.velocidadMax);
        tipoBici = findViewById(R.id.tipoBici);
        numRuedas = findViewById(R.id.numRuedas);
        tamanyo = findViewById(R.id.tamanyo);

        add=findViewById(R.id.add);


        spinner = findViewById(R.id.spinnerTipo);
        tipos = new ArrayList();
        tipos.add(Tipo.MOTO);
        tipos.add(Tipo.COCHE);
        tipos.add(Tipo.BICICLETA);
        tipos.add(Tipo.PATINETE);

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tipos);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeCall(new CallInterface() {
                    @Override
                    public void doInBackground() {
                        Tipo aux=(Tipo) spinner.getSelectedItem();
                        switch (aux){
                            case MOTO:
                                Moto auxM=new Moto(matricula.getText().toString(),modelo.getText().toString(),descripcion.getText().toString(),Integer.valueOf(bateria.getText().toString()),carnet.getText().toString(),color.getText().toString(),estado.getText().toString(),fecha.getText().toString(),Double.valueOf(precioHora.getText().toString()),aux,Integer.valueOf(velocidadMax.getText().toString()),Integer.valueOf(cilindrada.getText().toString()));
                                result= Connector.getConector().post(Moto.class,auxM, url+ API.Routes.MOTO);
                                break;
                            case COCHE:
                                Coche auxC=new Coche(matricula.getText().toString(),modelo.getText().toString(),descripcion.getText().toString(),Integer.valueOf(bateria.getText().toString()),carnet.getText().toString(),color.getText().toString(),estado.getText().toString(),fecha.getText().toString(),Double.valueOf(precioHora.getText().toString()),aux,Integer.valueOf(numPlazas.getText().toString()),Integer.valueOf(numPuertas.getText().toString()));
                                result= Connector.getConector().post(Coche.class,auxC, url+API.Routes.COCHE);
                                break;
                            case BICICLETA:
                                Bicicleta auxB=new Bicicleta(matricula.getText().toString(),modelo.getText().toString(),descripcion.getText().toString(),Integer.valueOf(bateria.getText().toString()),carnet.getText().toString(),color.getText().toString(),estado.getText().toString(),fecha.getText().toString(),Double.valueOf(precioHora.getText().toString()),aux,tipoBici.getText().toString());
                                result= Connector.getConector().post(Bicicleta.class,auxB, url+API.Routes.BICI);
                                break;
                            case PATINETE:
                                Patinete auxP=new Patinete(matricula.getText().toString(),modelo.getText().toString(),descripcion.getText().toString(),Integer.valueOf(bateria.getText().toString()),carnet.getText().toString(),color.getText().toString(),estado.getText().toString(),fecha.getText().toString(),Double.valueOf(precioHora.getText().toString()),aux,Integer.valueOf(numRuedas.getText().toString()),Integer.valueOf(tamanyo.getText().toString()));
                                result= Connector.getConector().post(Patinete.class,auxP, url+API.Routes.PATINETE);
                                break;
                        }
                    }

                    @Override
                    public void doInUI() {
                        if (result instanceof Result.Success){
                            Toast.makeText(getApplicationContext(), "Insertado correctamente correctamente", Toast.LENGTH_SHORT).show();
                        }else{
                            Result.Error error =(Result.Error)result;
                            Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Tipo aux = (Tipo) adapterView.getSelectedItem();
        switch (aux) {
            case MOTO:
                cilindrada.setVisibility(View.VISIBLE);
                velocidadMax.setVisibility(View.VISIBLE);
                cilindrada.setEnabled(true);
                velocidadMax.setEnabled(true);

                numPlazas.setVisibility(View.GONE);
                numPuertas.setVisibility(View.GONE);
                numRuedas.setVisibility(View.GONE);
                tamanyo.setVisibility(View.GONE);
                tipoBici.setVisibility(View.GONE);
                numPlazas.setEnabled(false);
                numPuertas.setEnabled(false);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);
                tipoBici.setEnabled(false);

                break;
            case COCHE:
                numPlazas.setVisibility(View.VISIBLE);
                numPuertas.setVisibility(View.VISIBLE);
                numPlazas.setEnabled(true);
                numPuertas.setEnabled(true);

                numRuedas.setVisibility(View.GONE);
                tamanyo.setVisibility(View.GONE);
                tipoBici.setVisibility(View.GONE);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);
                tipoBici.setEnabled(false);

                cilindrada.setVisibility(View.GONE);
                velocidadMax.setVisibility(View.GONE);
                cilindrada.setEnabled(false);
                velocidadMax.setEnabled(false);
                break;
            case PATINETE:
                numRuedas.setVisibility(View.VISIBLE);
                tamanyo.setVisibility(View.VISIBLE);
                numRuedas.setEnabled(true);
                tamanyo.setEnabled(true);

                numPlazas.setVisibility(View.GONE);
                numPuertas.setVisibility(View.GONE);
                tipoBici.setVisibility(View.GONE);
                numPlazas.setEnabled(false);
                numPuertas.setEnabled(false);
                tipoBici.setEnabled(false);

                cilindrada.setVisibility(View.GONE);
                velocidadMax.setVisibility(View.GONE);
                cilindrada.setEnabled(false);
                velocidadMax.setEnabled(false);
                break;
            case BICICLETA:
                tipoBici.setVisibility(View.VISIBLE);
                tipoBici.setEnabled(true);

                numPlazas.setVisibility(View.GONE);
                numPuertas.setVisibility(View.GONE);
                numRuedas.setVisibility(View.GONE);
                tamanyo.setVisibility(View.GONE);
                numPlazas.setEnabled(false);
                numPuertas.setEnabled(false);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);
                cilindrada.setVisibility(View.GONE);
                velocidadMax.setVisibility(View.GONE);
                cilindrada.setEnabled(false);
                velocidadMax.setEnabled(false);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        spinner.setSelection(0);
    }
}