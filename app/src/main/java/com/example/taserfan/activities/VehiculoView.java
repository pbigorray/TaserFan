package com.example.taserfan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.taserfan.R;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;
import com.example.taserfan.objects.Vehiculo;

public class VehiculoView extends BaseActivity implements CallInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo_view);
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void doInUI() {

    }
}