package com.example.taserfan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import com.example.taserfan.objects.Carnet;
import com.example.taserfan.objects.Coche;
import com.example.taserfan.objects.Color;
import com.example.taserfan.objects.Estado;
import com.example.taserfan.objects.Moto;
import com.example.taserfan.objects.Patinete;
import com.example.taserfan.objects.Tipo;
import com.example.taserfan.objects.TipoBici;

import java.util.ArrayList;

public class AddVehiculo extends BaseActivity implements AdapterView.OnItemSelectedListener {

    EditText matricula, modelo, descripcion, bateria,
            fecha, precioHora, numPlazas, numPuertas, cilindrada,
            velocidadMax, numRuedas, tamanyo;
    Spinner spinner,spinnerCarnet,spinnerEstado,spinnerColor,spinnerTipoBici;
    ArrayList tipos,colores,carnets,estados,tiposBici;
    ArrayAdapter arrayAdapter,adapterColor,adapterCarnets,adapterEstado,adapterTipoBici;
    Button add;
    Result result;
    private final String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehiculo);
        matricula = findViewById(R.id.matricula);
        modelo = findViewById(R.id.modelo);
        descripcion = findViewById(R.id.descripcion);
        bateria = findViewById(R.id.bateria);

        fecha = findViewById(R.id.fecha);
        precioHora = findViewById(R.id.precioHora);
        numPlazas = findViewById(R.id.numplazas);
        numPuertas = findViewById(R.id.numPuertas);
        cilindrada = findViewById(R.id.cilindrada);
        velocidadMax = findViewById(R.id.velocidadMax);

        numRuedas = findViewById(R.id.numRuedas);
        tamanyo = findViewById(R.id.tamanyo);

        add=findViewById(R.id.add);
        spinnerCarnet=findViewById(R.id.spinnerCarnet);


        spinner = findViewById(R.id.spinnerTipo);
        spinnerEstado=findViewById(R.id.spinnerEstado);
        spinnerColor=findViewById(R.id.spinnerColor);
        spinnerTipoBici=findViewById(R.id.spinnerTipoBici);

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
                Tipo aux = (Tipo) spinner.getSelectedItem();
                Carnet carnet = (Carnet) spinnerCarnet.getSelectedItem();
                Color color = (Color) spinnerColor.getSelectedItem();
                Estado estado = (Estado) spinnerEstado.getSelectedItem();

                String mod, fec;
                mod = modelo.getText().toString();
                fec = fecha.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(AddVehiculo.this);

                try {
                    Double.parseDouble(precioHora.getText().toString());
                } catch (NumberFormatException nfe) {
                    builder.setMessage("El campo del precio no es un numero")
                            .setTitle("Falta informacion");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                if (mod.equals("")) {
                    builder.setMessage("El campo del modelo esta vacio")
                            .setTitle("Falta informacion");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (bateria.getText().toString().equals("")) {
                    builder.setMessage("El campo de la bateria es incorrecto")
                            .setTitle("Falta informacion");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (!bateria.getText().toString().matches("[0-9]+")) {
                    builder.setMessage("El campo de la bateria no es un numero entero")
                            .setTitle("Falta informacion");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (fec.equals("")) {
                    builder.setMessage("El campo de la fecha esta vacio")
                            .setTitle("Falta informacion");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (precioHora.getText().toString().equals("")) {
                    builder.setMessage("El campo del precio es incorrecto")
                            .setTitle("Falta informacion");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    switch (aux) {
                        case MOTO:
                            if (velocidadMax.getText().toString().equals("")) {
                                builder.setMessage("El campo de velocidad Maxima esta vacio")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!velocidadMax.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo de velocidad Maxima no es un numero entero")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (cilindrada.getText().toString().equals("")) {
                                builder.setMessage("El campo de cilindarda esta vacio")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!cilindrada.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo de cilindarda no es un numero entero")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else {
                                executeCall(new CallInterface() {
                                    @Override
                                    public void doInBackground() {
                                        Moto auxM = new Moto(matricula.getText().toString(), modelo.getText().toString(), descripcion.getText().toString(), Integer.parseInt(bateria.getText().toString()), carnet.getStr(), color.getStr(), estado.getStr(), fecha.getText().toString(), Double.parseDouble(precioHora.getText().toString()), aux, Integer.parseInt(velocidadMax.getText().toString()), Integer.parseInt(cilindrada.getText().toString()));
                                        result = Connector.getConector().post(Moto.class, auxM, url + API.Routes.MOTO);
                                    }

                                    @Override
                                    public void doInUI() {
                                        if (result instanceof Result.Success) {
                                            Toast.makeText(getApplicationContext(), "Añadido correctamente", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(AddVehiculo.this);
                                            builder.setMessage(error.getError())
                                                    .setTitle("Error Add");
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
//                            Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            break;
                        case COCHE:
                            if (numPlazas.getText().toString().equals("")) {
                                builder.setMessage("El campo del numero de plazas esta vacio")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!numPlazas.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo del numero de plazas no es un numero entero")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (numPuertas.getText().toString().equals("")) {
                                builder.setMessage("El campo del numero de puertas esta vacio")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!numPuertas.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo de cilindarda no es un numero entero")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else {
                                executeCall(new CallInterface() {
                                    @Override
                                    public void doInBackground() {
                                        Coche auxC = new Coche(matricula.getText().toString(), modelo.getText().toString(), descripcion.getText().toString(), Integer.parseInt(bateria.getText().toString()), carnet.getStr(), color.getStr(), estado.getStr(), fecha.getText().toString(), Double.parseDouble(precioHora.getText().toString()), aux, Integer.parseInt(numPlazas.getText().toString()), Integer.parseInt(numPuertas.getText().toString()));
                                        result = Connector.getConector().post(Coche.class, auxC, url + API.Routes.COCHE);
                                    }

                                    @Override
                                    public void doInUI() {
                                        if (result instanceof Result.Success) {
                                            Toast.makeText(getApplicationContext(), "Añadido correctamente", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(AddVehiculo.this);
                                            builder.setMessage(error.getError())
                                                    .setTitle("Error add");
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
//                            Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            break;
                        case BICICLETA:
                            executeCall(new CallInterface() {
                                @Override
                                public void doInBackground() {
                                    TipoBici tipoBici = (TipoBici) spinnerTipoBici.getSelectedItem();
                                    Bicicleta auxB = new Bicicleta(matricula.getText().toString(), modelo.getText().toString(), descripcion.getText().toString(), Integer.parseInt(bateria.getText().toString()), carnet.getStr(), color.getStr(), estado.getStr(), fecha.getText().toString(), Double.parseDouble(precioHora.getText().toString()), aux, tipoBici.getStr());
                                    result = Connector.getConector().post(Bicicleta.class, auxB, url + API.Routes.BICI);
                                }

                                @Override
                                public void doInUI() {
                                    if (result instanceof Result.Success) {
                                        Toast.makeText(getApplicationContext(), "Añadido correctamente", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Result.Error error = (Result.Error) result;
                                        AlertDialog.Builder builder = new AlertDialog.Builder(AddVehiculo.this);
                                        builder.setMessage(error.getError())
                                                .setTitle("Error Add");
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
//                            Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            break;
                        case PATINETE:

                            if (numRuedas.getText().toString().equals("")) {
                                builder.setMessage("El campo del numero de ruedas esta vacio")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!numRuedas.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo del numero de ruedas no es un numero entero")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (tamanyo.getText().toString().equals("")) {
                                builder.setMessage("El campo del tamaño esta vacio")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!tamanyo.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo del tamaño no es un numero entero")
                                        .setTitle("Falta informacion");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else {
                                executeCall(new CallInterface() {
                                    @Override
                                    public void doInBackground() {
                                        Patinete auxP = new Patinete(matricula.getText().toString(), modelo.getText().toString(), descripcion.getText().toString(), Integer.parseInt(bateria.getText().toString()), carnet.getStr(), color.getStr(), estado.getStr(), fecha.getText().toString(), Double.parseDouble(precioHora.getText().toString()), aux, Integer.parseInt(numRuedas.getText().toString()), Integer.parseInt(tamanyo.getText().toString()));
                                        result = Connector.getConector().post(Patinete.class, auxP, url + API.Routes.PATINETE);
                                    }

                                    @Override
                                    public void doInUI() {
                                        if (result instanceof Result.Success) {
                                            Toast.makeText(getApplicationContext(), "Añadido correctamente", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(AddVehiculo.this);
                                            builder.setMessage(error.getError())
                                                    .setTitle("Error Add");
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
//                            Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                            break;
                    }
                }
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
                spinnerTipoBici.setVisibility(View.GONE);
                numPlazas.setEnabled(false);
                numPuertas.setEnabled(false);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);
                spinnerTipoBici.setEnabled(false);



                break;
            case COCHE:
                numPlazas.setVisibility(View.VISIBLE);
                numPuertas.setVisibility(View.VISIBLE);
                numPlazas.setEnabled(true);
                numPuertas.setEnabled(true);

                numRuedas.setVisibility(View.GONE);
                tamanyo.setVisibility(View.GONE);
                spinnerTipoBici.setVisibility(View.GONE);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);
                spinnerTipoBici.setEnabled(false);

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
                spinnerTipoBici.setVisibility(View.GONE);
                numPlazas.setEnabled(false);
                numPuertas.setEnabled(false);
                spinnerTipoBici.setEnabled(false);

                cilindrada.setVisibility(View.GONE);
                velocidadMax.setVisibility(View.GONE);
                cilindrada.setEnabled(false);
                velocidadMax.setEnabled(false);
                break;
            case BICICLETA:
                spinnerTipoBici.setVisibility(View.VISIBLE);
                spinnerTipoBici.setEnabled(true);

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
        crearSpiners();
    }

    private void crearSpiners() {
        Tipo aux=(Tipo) spinner.getSelectedItem();

        carnets=new ArrayList();
        adapterCarnets = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, carnets);
        spinnerCarnet.setAdapter(adapterCarnets);

        switch (aux){
            case MOTO:
                carnets.add(Carnet.AM);
                carnets.add(Carnet.A);
                break;
            case COCHE:
                carnets.add(Carnet.B);
                break;
            default:
                carnets.add(Carnet.NO);
                break;
        }
        adapterCarnets.notifyDataSetChanged();

        colores=new ArrayList();
        adapterColor = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, colores);
        spinnerColor.setAdapter(adapterColor);
        switch (aux){
            case MOTO:
                colores.add(Color.VERDE);
                colores.add(Color.AZUL);
                colores.add(Color.BLANCO);
                colores.add(Color.NEGRO);
                break;
            case COCHE:
                colores.add(Color.VERDE);
                colores.add(Color.ROJO);
                colores.add(Color.AMARILLO);
                colores.add(Color.AZUL);
                colores.add(Color.BLANCO);
                break;
            default:
                colores.add(Color.VERDE);
                colores.add(Color.ROJO);
                colores.add(Color.AMARILLO);
                colores.add(Color.AZUL);
                colores.add(Color.BLANCO);
                colores.add(Color.NEGRO);
                break;

        }
        adapterColor.notifyDataSetChanged();

        estados=new ArrayList();
        estados.add(Estado.PREPARADO);
        estados.add(Estado.ALQUILADO);
        estados.add(Estado.BAJA);
        estados.add(Estado.RESERVADO);
        adapterEstado=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, estados);
        spinnerEstado.setAdapter(adapterEstado);

        switch (aux) {
            case BICICLETA:
                tiposBici=new ArrayList();
                tiposBici.add(TipoBici.PASEO);
                tiposBici.add(TipoBici.HIBRIDA);
                tiposBici.add(TipoBici.MOTANA);
                adapterTipoBici=new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tiposBici);
                spinnerTipoBici.setAdapter(adapterTipoBici);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        spinner.setSelection(0);
    }
}