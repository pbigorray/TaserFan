package com.example.taserfan.activities.preferencias;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;


import com.example.taserfan.R;

public class PreferenciasFracment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferencias,rootKey);

        //Tema
        ListPreference themePreference = getPreferenceManager().findPreference(getString(R.string.settings_theme_key));
        if (themePreference.getValue() == null) {
            themePreference.setValue(ThemeSetup.Mode.DEFAULT.name());
        }
        themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            ThemeSetup.applyTheme(ThemeSetup.Mode.valueOf((String) newValue));
            return true;
        });
        // ip
        final EditTextPreference ip = getPreferenceManager().findPreference("ip");
        ip.setSummary("Actualmente: " + GestionPreferencias.getInstance().getIp(getContext()));
        ip.setOnPreferenceChangeListener((preference, newValue) -> {
            ip.setSummary("Actualmente: " + newValue);
            return true;
        });

        // puerto
        final EditTextPreference url = findPreference("puerto");
        url.setSummary("Actualmente: " + GestionPreferencias.getInstance().getPuerto(getContext()));
        url.setOnPreferenceChangeListener((preference, newValue) -> {
            url.setSummary("Actualmente: " + newValue);
            return true;
        });
    }
}