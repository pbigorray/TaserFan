package com.example.taserfan.activities.preferencias;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.taserfan.API.API;
import com.example.taserfan.R;

public class GestionPreferencias {
    private  SharedPreferences preferences;
    private static GestionPreferencias intance;

    public String getTheme(Context context) {
        inicializa(context);
        return preferences.getString(context.getString(R.string.settings_theme_key),ThemeSetup.Mode.DEFAULT.name());
    }

    private  void inicializa(Context context) {
        if (preferences==null){
            preferences= PreferenceManager.getDefaultSharedPreferences(context);
        }
    }
    public static GestionPreferencias getInstance(){
        if (intance==null){
            intance=new GestionPreferencias();
        }
        return intance;
    }
    public   String getIp(Context context){
        inicializa(context);
        return preferences.getString("ip", API.Routes.IP);
    }
    public  String getPuerto(Context context){
        inicializa(context);
        return preferences.getString("puerto",API.Routes.PUERTO);
    }

}