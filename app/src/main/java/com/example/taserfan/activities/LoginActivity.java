package com.example.taserfan.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.activities.preferencias.GestionPreferencias;
import com.example.taserfan.activities.preferencias.Preferencias;
import com.example.taserfan.activities.preferencias.ThemeSetup;
import com.example.taserfan.objects.AuthenticationData;
import com.example.taserfan.objects.Empleado;
import com.example.taserfan.R;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;
import com.example.taserfan.base.LoggedInUserRepository;
import com.example.taserfan.objects.Vehiculo;

public class LoginActivity extends BaseActivity implements CallInterface {
    private EditText user;
    private EditText pass;
    private Button login;
    private Result result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ThemeSetup.applyPreferenceTheme(getApplicationContext());

        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        login=findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeCall(LoginActivity.this);

            }
        });
    }

    @Override
    public void doInBackground() {
        AuthenticationData auth=new AuthenticationData(user.getText().toString(),pass.getText().toString());
        String url= GestionPreferencias.getInstance().getIp(this)+":"+GestionPreferencias.getInstance().getPuerto(this)+ API.Routes.AUTHENTICATE;
        result= Connector.getConector().post(Empleado.class,auth,url);
//        result= Connector.getConector().post(Empleado.class,new AuthenticationData("pepa@mordor.es","1111"),url);

    }

    @Override
    public void doInUI() {
        if (result instanceof Result.Success){
            LoggedInUserRepository.getInstance().login(((Result.Success<Empleado>)result).getData());
            Intent intent = new Intent(getApplicationContext(),VehiculoView.class);
            startActivity(intent);
        }else{
            Result.Error error =(Result.Error)result;
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Error code: "+error.getCode()+"\n"+"Error message: "+error.getError())
                    .setTitle("Error login")
                    .setPositiveButton("Oki",null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preferencias,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.configuracion:
                Intent intent = new Intent(this, Preferencias.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}