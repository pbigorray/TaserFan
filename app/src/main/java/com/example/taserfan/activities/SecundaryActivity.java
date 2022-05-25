package com.example.taserfan.activities;

import android.os.Bundle;
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
import com.example.taserfan.objects.Bicicleta;
import com.example.taserfan.objects.Coche;
import com.example.taserfan.objects.Moto;
import com.example.taserfan.objects.Patinete;
import com.example.taserfan.objects.Tipo;

public class SecundaryActivity extends BaseActivity implements CallInterface {

    private EditText modelo,matricula,precioHora,descripcion,carnet,color,bateria,estado,fecha,numplazas,numpuertas,cilindrada,velocidadMax,numRuedas,tamanyo,tipoBici;
    Moto moto;
    Tipo tipo;
    Coche coche;
    Bicicleta bici;
    Patinete patinete;
    String matri;
    Button update;
    Result result;
    private String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        tipo=(Tipo) getIntent().getExtras().get("tipo");
        matri=getIntent().getExtras().getString("matricula");

        modelo=findViewById(R.id.modelo);
        matricula=findViewById(R.id.matricula);
        precioHora=findViewById(R.id.precioHora);
        descripcion=findViewById(R.id.descripcion);
        bateria=findViewById(R.id.bateria);
        estado=findViewById(R.id.estado);
        fecha=findViewById(R.id.fecha);
        carnet=findViewById(R.id.carnet);
        color=findViewById(R.id.color);

        numplazas=findViewById(R.id.numplazas);
        numpuertas=findViewById(R.id.numPuertas);

        cilindrada=findViewById(R.id.cilindrada);
        velocidadMax=findViewById(R.id.velocidadMax);

        numRuedas=findViewById(R.id.numRuedas);
        tamanyo=findViewById(R.id.tamanyo);

        tipoBici=findViewById(R.id.tipoBici);

        update =findViewById(R.id.update);

    }

    @Override
    protected void onResume() {
        super.onResume();
        executeCall(this);
    }


    @Override
    public void doInBackground() {
        switch (tipo){
            case MOTO:
                moto=Connector.getConector().get(Moto.class, url+API.Routes.MOTO +"?matricula="+ matri);
                break;
            case COCHE:
                coche=Connector.getConector().get(Coche.class, url+API.Routes.COCHE +"?matricula="+ matri);
                break;
            case BICICLETA:
                bici=Connector.getConector().get(Bicicleta.class, url+API.Routes.BICI +"?matricula="+ matri);
                break;
            case PATINETE:
                patinete=Connector.getConector().get(Patinete.class, url+API.Routes.PATINETE+"?matricula="+ matri);
                break;
        }
    }

    @Override
    public void doInUI() {
        switch (tipo){
            case MOTO:
                matricula.setText(""+moto.getMatricula());
                modelo.setText(""+moto.getMarca());
                precioHora.setText(""+moto.getPrecioHora());
                descripcion.setText(""+moto.getDescripcion());
                fecha.setText(""+moto.getFechaAdq());
                bateria.setText(""+moto.getBateria());
                estado.setText(""+moto.getEstado());
                carnet.setText(""+moto.getTipoCarnet());
                color.setText(""+moto.getColor());
                break;
            case COCHE:
                matricula.setText(""+coche.getMatricula());
                modelo.setText(""+coche.getMarca());
                precioHora.setText(""+coche.getPrecioHora());
                descripcion.setText(""+coche.getDescripcion());
                fecha.setText(""+coche.getFechaAdq());
                bateria.setText(""+coche.getBateria());
                estado.setText(""+coche.getEstado());
                carnet.setText(""+coche.getTipoCarnet());
                color.setText(""+coche.getColor());
                break;
            case PATINETE:
                matricula.setText(""+patinete.getMatricula());
                modelo.setText(""+patinete.getMarca());
                precioHora.setText(""+patinete.getPrecioHora());
                descripcion.setText(""+patinete.getDescripcion());
                fecha.setText(""+patinete.getFechaAdq());
                bateria.setText(""+patinete.getBateria());
                estado.setText(""+patinete.getEstado());
                carnet.setText(""+patinete.getTipoCarnet());
                color.setText(""+patinete.getColor());
                break;
            case BICICLETA:
                matricula.setText(""+bici.getMatricula());
                modelo.setText(""+bici.getMarca());
                precioHora.setText(""+bici.getPrecioHora());
                descripcion.setText(""+bici.getDescripcion());
                fecha.setText(""+bici.getFechaAdq());
                bateria.setText(""+bici.getBateria());
                estado.setText(""+bici.getEstado());
                carnet.setText(""+bici.getTipoCarnet());
                color.setText(""+bici.getColor());
                break;
        }
        switch (tipo){
            case MOTO:
                cilindrada.setVisibility(View.VISIBLE);
                velocidadMax.setVisibility(View.VISIBLE);
                cilindrada.setEnabled(true);
                velocidadMax.setEnabled(true);

                cilindrada.setText(""+moto.getCilindrada());
                velocidadMax.setText(""+moto.getVelocidadMax());

                numplazas.setVisibility(View.GONE);
                numpuertas.setVisibility(View.GONE);
                numRuedas.setVisibility(View.GONE);
                tamanyo.setVisibility(View.GONE);
                tipoBici.setVisibility(View.GONE);
                numplazas.setEnabled(false);
                numpuertas.setEnabled(false);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);
                tipoBici.setEnabled(false);

                break;
            case COCHE:
                numplazas.setVisibility(View.VISIBLE);
                numpuertas.setVisibility(View.VISIBLE);
                numplazas.setEnabled(true);
                numpuertas.setEnabled(true);

                numplazas.setText(""+coche.getNumPlazas());
                numpuertas.setText(""+coche.getNumPuertas());

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
            case BICICLETA:
                tipoBici.setVisibility(View.VISIBLE);
                tipoBici.setEnabled(true);

                tipoBici.setText(""+bici.getTipoBici());

                numplazas.setVisibility(View.GONE);
                numpuertas.setVisibility(View.GONE);
                numRuedas.setVisibility(View.GONE);
                tamanyo.setVisibility(View.GONE);
                numplazas.setEnabled(false);
                numpuertas.setEnabled(false);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);
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

                numRuedas.setText(""+patinete.getNumRuedas());
                tamanyo.setText(""+patinete.getTamanyo());
                numplazas.setVisibility(View.GONE);
                numpuertas.setVisibility(View.GONE);
                tipoBici.setVisibility(View.GONE);
                numplazas.setEnabled(false);
                numpuertas.setEnabled(false);
                tipoBici.setEnabled(false);

                cilindrada.setVisibility(View.GONE);
                velocidadMax.setVisibility(View.GONE);
                cilindrada.setEnabled(false);
                velocidadMax.setEnabled(false);

                break;
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeCall(new CallInterface() {
                    @Override
                    public void doInBackground() {
                        switch (tipo){
                            case MOTO:
                                Moto auxM=new Moto(matri,modelo.getText().toString(),descripcion.getText().toString(),Integer.valueOf(bateria.getText().toString()),carnet.getText().toString(),color.getText().toString(),estado.getText().toString(),fecha.getText().toString(),Double.valueOf(precioHora.getText().toString()),tipo,Integer.valueOf(velocidadMax.getText().toString()),Integer.valueOf(cilindrada.getText().toString()));
                                result= Connector.getConector().put(Moto.class,auxM, url+API.Routes.MOTO);
                                break;
                            case COCHE:
                                Coche auxC=new Coche(matri,modelo.getText().toString(),descripcion.getText().toString(),Integer.valueOf(bateria.getText().toString()),carnet.getText().toString(),color.getText().toString(),estado.getText().toString(),fecha.getText().toString(),Double.valueOf(precioHora.getText().toString()),tipo,Integer.valueOf(numplazas.getText().toString()),Integer.valueOf(numpuertas.getText().toString()));
                                result= Connector.getConector().put(Coche.class,auxC, url+API.Routes.COCHE);
                                break;
                            case BICICLETA:
                                Bicicleta auxB=new Bicicleta(matri,modelo.getText().toString(),descripcion.getText().toString(),Integer.valueOf(bateria.getText().toString()),carnet.getText().toString(),color.getText().toString(),estado.getText().toString(),fecha.getText().toString(),Double.valueOf(precioHora.getText().toString()),tipo,tipoBici.getText().toString());
                                result= Connector.getConector().put(Bicicleta.class,auxB, url+API.Routes.BICI);
                                break;
                            case PATINETE:
                                Patinete auxP=new Patinete(matri,modelo.getText().toString(),descripcion.getText().toString(),Integer.valueOf(bateria.getText().toString()),carnet.getText().toString(),color.getText().toString(),estado.getText().toString(),fecha.getText().toString(),Double.valueOf(precioHora.getText().toString()),tipo,Integer.valueOf(numRuedas.getText().toString()),Integer.valueOf(tamanyo.getText().toString()));
                                result= Connector.getConector().put(Patinete.class,auxP, url+API.Routes.PATINETE);
                                break;
                        }
                    }

                    @Override
                    public void doInUI() {
                        if (result instanceof Result.Success){
                            Toast.makeText(getApplicationContext(), "Actualizado correctamente", Toast.LENGTH_SHORT).show();
                        }else{
                            Result.Error error =(Result.Error)result;
                            Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}