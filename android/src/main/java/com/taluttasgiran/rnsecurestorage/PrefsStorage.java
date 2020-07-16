package com.taluttasgiran.rnsecurestorage;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;

@SuppressWarnings({"unused", "WeakerAccess"})
public class PrefsStorage {
    public static final String RN_SECURE_STORAGE = "RN_SECURE_STORAGE";

    @NonNull
    private final SharedPreferences prefs;

    public PrefsStorage(@NonNull final ReactApplicationContext reactContext) {
        this.prefs = reactContext.getSharedPreferences(RN_SECURE_STORAGE, Context.MODE_PRIVATE);
    }

    public boolean removeEntry(@NonNull final String key) {
        if (this.prefs.getString(key, null) != null) {
            prefs.edit()
                    .remove(key)
                    .apply();
            return true;
        } else {
            return false;
        }

    }

    public void storeEncryptedEntry(@NonNull final String key, @NonNull final String value) {
        prefs.edit()
                .putString(key, value)
                .apply();
    }

    public String getEncryptedEntry(@NonNull final String key) {
        return this.prefs.getString(key, null);
    }

    public boolean exist(@NonNull final String key) {
        return this.prefs.contains(key);
    }

    public boolean clear() {
        return this.prefs.edit().clear().commit();
    }
}
