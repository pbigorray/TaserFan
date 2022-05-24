package com.example.taserfan.activities;

import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.R;
import com.example.taserfan.activities.preferencias.GestionPreferencias;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;
import com.example.taserfan.objects.Bicicleta;
import com.example.taserfan.objects.Coche;
import com.example.taserfan.objects.Moto;
import com.example.taserfan.objects.Patinete;
import com.example.taserfan.objects.Tipo;
import com.example.taserfan.objects.Vehiculo;

public class SecundaryActivity extends BaseActivity implements CallInterface {

    private ImageView icon;
    private TextView modelo,matricula,precioHora,descripcion,bateria,estado,fecha,numplazas,numpuertas,cilindrada,velocidadMax,numRuedas,tamanyo,tipoBici;
    Moto moto;
    Tipo tipo;
    Coche coche;
    Bicicleta bici;
    Patinete patinete;
    String matri,color;
    private String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        tipo=(Tipo) getIntent().getExtras().get("tipo");
        color=getIntent().getExtras().getString("color");
        matri=getIntent().getExtras().getString("matricula");

        icon=findViewById(R.id.iconVe);
        modelo=findViewById(R.id.modelo);
        matricula=findViewById(R.id.matricula);
        precioHora=findViewById(R.id.precioHora);
        descripcion=findViewById(R.id.descripcion);
        bateria=findViewById(R.id.bateria);
        estado=findViewById(R.id.estado);
        fecha=findViewById(R.id.fecha);

        numplazas=findViewById(R.id.numplazas);
        numpuertas=findViewById(R.id.numpuertas);

        cilindrada=findViewById(R.id.cilindrada);
        velocidadMax=findViewById(R.id.velocidadMax);

        numRuedas=findViewById(R.id.numruedas);
        tamanyo=findViewById(R.id.tamaño);

        tipoBici=findViewById(R.id.tipoBici);


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
                matricula.setText("Descripcion: "+moto.getMatricula());
                modelo.setText(""+moto.getMarca());
                precioHora.setText("Precio/hora: "+moto.getPrecioHora());
                descripcion.setText(""+moto.getDescripcion());
                fecha.setText(""+moto.getFechaAdq());
                bateria.setText("Bareria: "+moto.getBateria());
                estado.setText(""+moto.getEstado());
                break;
            case COCHE:
                matricula.setText("Descripcion: "+coche.getMatricula());
                modelo.setText(""+coche.getMarca());
                precioHora.setText("Precio/hora: "+coche.getPrecioHora());
                descripcion.setText(""+coche.getDescripcion());
                fecha.setText(""+coche.getFechaAdq());
                bateria.setText("Bareria: "+coche.getBateria());
                estado.setText(""+coche.getEstado());
                break;
            case PATINETE:
                matricula.setText("Descripcion: "+patinete.getMatricula());
                modelo.setText(""+patinete.getMarca());
                precioHora.setText("Precio/hora: "+patinete.getPrecioHora());
                descripcion.setText(""+patinete.getDescripcion());
                fecha.setText(""+patinete.getFechaAdq());
                bateria.setText("Bareria: "+patinete.getBateria());
                estado.setText(""+patinete.getEstado());
                break;
            case BICICLETA:
                matricula.setText("Descripcion: "+bici.getMatricula());
                modelo.setText(""+bici.getMarca());
                precioHora.setText("Precio/hora: "+bici.getPrecioHora());
                descripcion.setText(""+bici.getDescripcion());
                fecha.setText(""+bici.getFechaAdq());
                bateria.setText("Bareria: "+bici.getBateria());
                estado.setText(""+bici.getEstado());
                break;
        }
        switch (tipo){
            case MOTO:
                icon.setImageResource(R.drawable.ic_moto);
                break;
            case COCHE:
                icon.setImageResource(R.drawable.ic_baseline_directions_car_24);
                break;
            case PATINETE:
                icon.setImageResource(R.drawable.ic_patinete);
                break;
            case BICICLETA:
                icon.setImageResource(R.drawable.ic_bici);
                break;
        }
        switch (color){
            case "verde":
                icon.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.verde));
                break;
            case "negro":
                icon.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.negro));
                break;
            case "blanco":
                icon.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.blanco));
                break;
            case "rojo":
                icon.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.rojo));
                break;
            case "azul":
                icon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.azul));
                break;
            case "amarillo":
                icon.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.amarillo));
                break;

        }
        switch (tipo){
            case MOTO:
                cilindrada.setVisibility(View.VISIBLE);
                velocidadMax.setVisibility(View.VISIBLE);

                cilindrada.setText("Cilindrada: "+moto.getCilindrada());
                velocidadMax.setText("Km/h Max"+moto.getVelocidadMax());

                break;
            case COCHE:
                numplazas.setVisibility(View.VISIBLE);
                numpuertas.setVisibility(View.VISIBLE);

                numplazas.setText("Plazas: "+coche.getNumPlazas());
                numpuertas.setText("Puertas: "+coche.getNumPuertas());
                break;
            case BICICLETA:
                tipoBici.setVisibility(View.VISIBLE);

                tipoBici.setText("Tipo: "+bici.getTipoBici());
                break;
            case PATINETE:
                numRuedas.setVisibility(View.VISIBLE);
                tamanyo.setVisibility(View.VISIBLE);

                numRuedas.setText("Ruedas: "+patinete.getNumRuedas());
                tamanyo.setText("Tamaño: "+patinete.getTamanyo());

                break;
        }


    }
}