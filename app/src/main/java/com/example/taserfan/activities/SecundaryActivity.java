package com.example.taserfan.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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

public class SecundaryActivity extends BaseActivity implements CallInterface {

    private EditText modelo, matricula, precioHora, descripcion, bateria, fecha, numplazas, numpuertas, cilindrada, velocidadMax, numRuedas, tamanyo;
    Moto moto;
    Tipo tipo;
    Coche coche;
    Bicicleta bici;
    Patinete patinete;
    String matri;
    Spinner spinnerCarnet, spinnerEstado, spinnerColor, spinnerTipoBici;
    ArrayList colores, carnets, estados, tiposBici;
    ArrayAdapter adapterColor, adapterCarnets, adapterEstado, adapterTipoBici;
    Button update;
    Result result;
    private String url = GestionPreferencias.getInstance().getIp(this) + ":" + GestionPreferencias.getInstance().getPuerto(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        tipo = (Tipo) getIntent().getExtras().get("tipo");
        matri = getIntent().getExtras().getString("matricula");

        modelo = findViewById(R.id.modelo);
        matricula = findViewById(R.id.matricula);
        precioHora = findViewById(R.id.precioHora);
        descripcion = findViewById(R.id.descripcion);
        bateria = findViewById(R.id.bateria);
        fecha = findViewById(R.id.fecha);


        numplazas = findViewById(R.id.numplazas);
        numpuertas = findViewById(R.id.numPuertas);

        cilindrada = findViewById(R.id.cilindrada);
        velocidadMax = findViewById(R.id.velocidadMax);

        numRuedas = findViewById(R.id.numRuedas);
        tamanyo = findViewById(R.id.tamanyo);

        spinnerCarnet = findViewById(R.id.spinnerCarnet);


        spinnerEstado = findViewById(R.id.spinnerEstado);
        spinnerColor = findViewById(R.id.spinnerColor);
        spinnerTipoBici = findViewById(R.id.spinnerTipoBici);

        update = findViewById(R.id.update);

    }

    @Override
    protected void onResume() {
        super.onResume();
        executeCall(this);
    }


    @Override
    public void doInBackground() {
        switch (tipo) {
            case MOTO:
                moto = Connector.getConector().get(Moto.class, url + API.Routes.MOTO + "?matricula=" + matri);
                break;
            case COCHE:
                coche = Connector.getConector().get(Coche.class, url + API.Routes.COCHE + "?matricula=" + matri);
                break;
            case BICICLETA:
                bici = Connector.getConector().get(Bicicleta.class, url + API.Routes.BICI + "?matricula=" + matri);
                break;
            case PATINETE:
                patinete = Connector.getConector().get(Patinete.class, url + API.Routes.PATINETE + "?matricula=" + matri);
                break;
        }
    }

    @Override
    public void doInUI() {
        switch (tipo) {
            case MOTO:
                matricula.setText("" + moto.getMatricula());
                modelo.setText("" + moto.getMarca());
                precioHora.setText("" + moto.getPrecioHora());
                descripcion.setText("" + moto.getDescripcion());
                fecha.setText("" + moto.getFechaAdq());
                bateria.setText("" + moto.getBateria());

                break;
            case COCHE:
                matricula.setText("" + coche.getMatricula());
                modelo.setText("" + coche.getMarca());
                precioHora.setText("" + coche.getPrecioHora());
                descripcion.setText("" + coche.getDescripcion());
                fecha.setText("" + coche.getFechaAdq());
                bateria.setText("" + coche.getBateria());

                break;
            case PATINETE:
                matricula.setText("" + patinete.getMatricula());
                modelo.setText("" + patinete.getMarca());
                precioHora.setText("" + patinete.getPrecioHora());
                descripcion.setText("" + patinete.getDescripcion());
                fecha.setText("" + patinete.getFechaAdq());
                bateria.setText("" + patinete.getBateria());

                break;
            case BICICLETA:
                matricula.setText("" + bici.getMatricula());
                modelo.setText("" + bici.getMarca());
                precioHora.setText("" + bici.getPrecioHora());
                descripcion.setText("" + bici.getDescripcion());
                fecha.setText("" + bici.getFechaAdq());
                bateria.setText("" + bici.getBateria());

                break;
        }
        switch (tipo) {
            case MOTO:
                cilindrada.setVisibility(View.VISIBLE);
                velocidadMax.setVisibility(View.VISIBLE);
                cilindrada.setEnabled(true);
                velocidadMax.setEnabled(true);

                cilindrada.setText("" + moto.getCilindrada());
                velocidadMax.setText("" + moto.getVelocidadMax());

                numplazas.setVisibility(View.GONE);
                numpuertas.setVisibility(View.GONE);
                numRuedas.setVisibility(View.GONE);
                tamanyo.setVisibility(View.GONE);
                numplazas.setEnabled(false);
                numpuertas.setEnabled(false);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);


                spinnerTipoBici.setVisibility(View.GONE);
                spinnerTipoBici.setEnabled(false);

                break;
            case COCHE:
                numplazas.setVisibility(View.VISIBLE);
                numpuertas.setVisibility(View.VISIBLE);
                numplazas.setEnabled(true);
                numpuertas.setEnabled(true);

                numplazas.setText("" + coche.getNumPlazas());
                numpuertas.setText("" + coche.getNumPuertas());

                numRuedas.setVisibility(View.GONE);
                tamanyo.setVisibility(View.GONE);
                numRuedas.setEnabled(false);
                tamanyo.setEnabled(false);
                cilindrada.setVisibility(View.GONE);
                velocidadMax.setVisibility(View.GONE);
                cilindrada.setEnabled(false);
                velocidadMax.setEnabled(false);

                spinnerTipoBici.setVisibility(View.GONE);
                spinnerTipoBici.setEnabled(false);

                break;
            case BICICLETA:
                spinnerTipoBici.setVisibility(View.VISIBLE);
                spinnerTipoBici.setEnabled(true);

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

                numRuedas.setText("" + patinete.getNumRuedas());
                tamanyo.setText("" + patinete.getTamanyo());
                numplazas.setVisibility(View.GONE);
                numpuertas.setVisibility(View.GONE);
                numplazas.setEnabled(false);
                numpuertas.setEnabled(false);
                spinnerTipoBici.setVisibility(View.GONE);
                spinnerTipoBici.setEnabled(false);

                cilindrada.setVisibility(View.GONE);
                velocidadMax.setVisibility(View.GONE);
                cilindrada.setEnabled(false);
                velocidadMax.setEnabled(false);

                break;

        }
        crearSpiners();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Carnet carnet = (Carnet) spinnerCarnet.getSelectedItem();
                Color color = (Color) spinnerColor.getSelectedItem();
                Estado estado = (Estado) spinnerEstado.getSelectedItem();

                String mod, fec;
                mod = modelo.getText().toString();
                fec = fecha.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);

                try {
                    Double.parseDouble(precioHora.getText().toString());
                }catch (NumberFormatException nfe){
                    builder.setMessage("El campo del precio no es un numero")
                            .setTitle("Falta informacion")
                            .setPositiveButton("Oki",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                if (mod.equals("")) {
                    builder.setMessage("El campo del modelo esta vacio")
                            .setTitle("Falta informacion")
                            .setPositiveButton("Oki",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (bateria.getText().toString().equals("")) {
                    builder.setMessage("El campo de la bateria esta vacio")
                            .setTitle("Falta informacion")
                            .setPositiveButton("Oki",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else if(!bateria.getText().toString().matches("[0-9]+")){
                    builder.setMessage("El campo de la bateria no es un numero entero")
                            .setTitle("Falta informacion")
                            .setPositiveButton("Oki",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else if (fec.equals("")) {
                    builder.setMessage("El campo de la fecha esta vacio")
                            .setTitle("Falta informacion")
                            .setPositiveButton("Oki",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (precioHora.getText().toString().equals("")) {
                    builder.setMessage("El campo del precio es incorrecto")
                            .setTitle("Falta informacion")
                            .setPositiveButton("Oki",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    switch (tipo) {
                        case MOTO:
                            if (velocidadMax.getText().toString().equals("")) {
                                builder.setMessage("El campo de velocidad Maxima esta vacio")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!velocidadMax.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo de velocidad Maxima no es un numero entero")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (cilindrada.getText().toString().equals("")) {
                                    builder.setMessage("El campo de cilindarda esta vacio")
                                            .setTitle("Falta informacion")
                                            .setPositiveButton("Oki",null);
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                            }else if(!cilindrada.getText().toString().matches("[0-9]+")){
                                builder.setMessage("El campo de cilindarda no es un numero entero")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }else {
                                executeCall(new CallInterface() {
                                    Moto auxM;
                                    @Override
                                    public void doInBackground() {
                                        auxM = new Moto(matri, mod, descripcion.getText().toString(), Integer.parseInt(bateria.getText().toString()), carnet.getStr(), color.getStr(), estado.getStr(), fec, Double.parseDouble(precioHora.getText().toString()), tipo, Integer.parseInt(velocidadMax.getText().toString()), Integer.parseInt(cilindrada.getText().toString()));
                                        result = Connector.getConector().put(Moto.class, auxM, url + API.Routes.MOTO);
                                    }

                                    @Override
                                    public void doInUI() {
                                        if (result instanceof Result.Success) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);
                                            builder.setMessage("Se ha actualizado correctamente: "+auxM)
                                                    .setTitle("Actualizado")
                                                    .setPositiveButton("Oki", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            finish();
                                                        }
                                                    });
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);
                                            builder.setMessage("Error code: "+error.getCode()+"\n"+"Error message: "+error.getError())
                                                    .setTitle("Error Update")
                                                    .setPositiveButton("Oki",null);
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
//                            Toast.makeText(getApplicationContext(), error.getError(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            break;
                        case COCHE:
                            if (numplazas.getText().toString().equals("")) {
                                builder.setMessage("El campo del numero de plazas esta vacio")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!numplazas.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo del numero de plazas no es un numero entero")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (numpuertas.getText().toString().equals("")) {
                                builder.setMessage("El campo del numero de puertas esta vacio")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }else if(!numpuertas.getText().toString().matches("[0-9]+")){
                                builder.setMessage("El campo de cilindarda no es un numero entero")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }else {
                                executeCall(new CallInterface() {
                                    Coche auxC;
                                    @Override
                                    public void doInBackground() {
                                        auxC = new Coche(matri, modelo.getText().toString(), descripcion.getText().toString(), Integer.parseInt(bateria.getText().toString()), carnet.getStr(), color.getStr(), estado.getStr(), fecha.getText().toString(), Double.parseDouble(precioHora.getText().toString()), tipo, Integer.valueOf(numplazas.getText().toString()), Integer.valueOf(numpuertas.getText().toString()));
                                        result = Connector.getConector().put(Coche.class, auxC, url + API.Routes.COCHE);
                                    }

                                    @Override
                                    public void doInUI() {
                                        if (result instanceof Result.Success) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);
                                            builder.setMessage("Se ha actualizado correctamente: "+auxC)
                                                    .setTitle("Actualizado")
                                                    .setPositiveButton("Oki", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            finish();
                                                        }
                                                    });
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);
                                            builder.setMessage("Error code: "+error.getCode()+"\n"+"Error message: "+error.getError())
                                                    .setTitle("Error Update")
                                                    .setPositiveButton("Oki",null);
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
                                    Bicicleta auxB;
                                    @Override
                                    public void doInBackground() {
                                        TipoBici tipoBici = (TipoBici) spinnerTipoBici.getSelectedItem();
                                        auxB = new Bicicleta(matri, modelo.getText().toString(), descripcion.getText().toString(), Integer.parseInt(bateria.getText().toString()), carnet.getStr(), color.getStr(), estado.getStr(), fecha.getText().toString(), Double.parseDouble(precioHora.getText().toString()), tipo, tipoBici.getStr());
                                        result = Connector.getConector().put(Bicicleta.class, auxB, url + API.Routes.BICI);
                                    }

                                    @Override
                                    public void doInUI() {
                                        if (result instanceof Result.Success) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);
                                            builder.setMessage("Se ha actualizado correctamente: "+auxB)
                                                    .setTitle("Actualizado")
                                                    .setPositiveButton("Oki", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            finish();
                                                        }
                                                    });
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);
                                            builder.setMessage("Error code: "+error.getCode()+"\n"+"Error message: "+error.getError())
                                                    .setTitle("Error Update")
                                                    .setPositiveButton("Oki",null);
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
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (!numRuedas.getText().toString().matches("[0-9]+")) {
                                builder.setMessage("El campo del numero de ruedas no es un numero entero")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else if (tamanyo.getText().toString().equals("")) {
                                builder.setMessage("El campo del tamaño esta vacio")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }else if(!tamanyo.getText().toString().matches("[0-9]+")){
                                builder.setMessage("El campo del tamaño no es un numero entero")
                                        .setTitle("Falta informacion")
                                        .setPositiveButton("Oki",null);
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }else {
                                executeCall(new CallInterface() {
                                    Patinete auxP;
                                    @Override
                                    public void doInBackground() {
                                        auxP = new Patinete(matri, modelo.getText().toString(), descripcion.getText().toString(), Integer.parseInt(bateria.getText().toString()), carnet.getStr(), color.getStr(), estado.getStr(), fecha.getText().toString(), Double.parseDouble(precioHora.getText().toString()), tipo, Integer.parseInt(numRuedas.getText().toString()), Integer.parseInt(tamanyo.getText().toString()));
                                        result = Connector.getConector().put(Patinete.class, auxP, url + API.Routes.PATINETE);
                                    }

                                    @Override
                                    public void doInUI() {
                                        if (result instanceof Result.Success) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);
                                            builder.setMessage("Se ha actualizado correctamente: "+auxP)
                                                    .setTitle("Actualizado")
                                                    .setPositiveButton("Oki", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            finish();
                                                        }
                                                    });
                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecundaryActivity.this);
                                            builder.setMessage("Error code: "+error.getCode()+"\n"+"Error message: "+error.getError())
                                                    .setTitle("Error Update")
                                                    .setPositiveButton("Oki",null);
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

    private void crearSpiners() {


        carnets = new ArrayList();
        adapterCarnets = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, carnets);
        spinnerCarnet.setAdapter(adapterCarnets);

        switch (tipo) {
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

        colores = new ArrayList();
        adapterColor = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, colores);
        spinnerColor.setAdapter(adapterColor);
        switch (tipo) {
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

        estados = new ArrayList();
        estados.add(Estado.PREPARADO);
        estados.add(Estado.ALQUILADO);
        estados.add(Estado.BAJA);
        estados.add(Estado.RESERVADO);
        adapterEstado = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, estados);
        spinnerEstado.setAdapter(adapterEstado);

        switch (tipo) {
            case BICICLETA:
                tiposBici = new ArrayList();
                tiposBici.add(TipoBici.PASEO);
                tiposBici.add(TipoBici.HIBRIDA);
                tiposBici.add(TipoBici.MOTANA);
                adapterTipoBici = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tiposBici);
                spinnerTipoBici.setAdapter(adapterTipoBici);
                break;
        }
    }
}