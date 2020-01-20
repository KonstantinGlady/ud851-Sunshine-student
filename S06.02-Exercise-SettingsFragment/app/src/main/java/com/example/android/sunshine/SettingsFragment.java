package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.example.android.sunshine.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    // TODO (4) Create SettingsFragment and extend PreferenceFragmentCompat

    // Do steps 5 - 11 within SettingsFragment
    // TODO (10) Implement OnSharedPreferenceChangeListener from SettingsFragment

    // TODO (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference

    public void setPreferenceSummary(Preference preference, Object value) {
        String valueString = value.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(valueString);
            if (index >= 0) {
                preference.setSummary(listPreference.getEntries()[index]);
            } else {
                preference.setSummary(valueString);
            }
        }

    }
    // TODO (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource

    // Do step 9 within onCreatePreference
    // TODO (9) Set the preference summary on each preference that isn't a CheckBoxPreference

    // TODO (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    // TODO (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart


    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_screen);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (!(preference instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }

        }
    }

    // TODO (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference preference = findPreference(s);
        if (preference != null) {
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, sharedPreferences.getString(s, ""));
            }
        }
    }


}



