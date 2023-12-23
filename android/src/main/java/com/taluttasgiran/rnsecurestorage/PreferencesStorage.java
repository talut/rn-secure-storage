package com.taluttasgiran.rnsecurestorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONArray;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Shared preferences storage.
 * <p>
 * This class is used to store encrypted values in shared preferences.
 * It is used to store the encryption key and the initialization vector.
 * The key and the initialization vector are used to encrypt and decrypt the values.
 * </p>
 */
public class PreferencesStorage {
    public static final String RN_SECURE_STORAGE = "RN_SECURE_STORAGE";

    @NonNull
    private final SharedPreferences prefs;

    public PreferencesStorage(@NonNull final ReactApplicationContext reactContext) {
        String prefsName = reactContext.getPackageName() + "." + RN_SECURE_STORAGE;
        String prefNameBase64 = Base64.encodeToString(prefsName.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        String fileName = prefNameBase64.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
        this.prefs = reactContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }


    @Nullable
    public String getEncryptedEntry(@NonNull final String key) {
        return this.prefs.getString(key, null);
    }

    public boolean removeEntry(@NonNull final String key) {
        if (this.prefs.getString(key, null) != null) {
            prefs.edit().remove(key).apply();
            return true;
        } else {
            return false;
        }
    }

    public boolean clear() {
        return this.prefs.edit().clear().commit();
    }

    public boolean exist(@NonNull final String key) {
        return this.prefs.contains(key);
    }

    public void storeEncryptedEntry(@NonNull final String key, @NonNull final String encryptedValue) {
        prefs.edit().putString(key, encryptedValue).apply();
    }


    public JSONArray getAllStoredKeys() {
        List<String> list = new ArrayList<>(this.prefs.getAll().keySet());
        return new JSONArray(list);
    }


}
