package com.taluttasgiran.rnsecurestorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.taluttasgiran.rnsecurestorage.RNSecureStorageModule.KnownCiphers;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorage;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorage.EncryptionResult;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class PrefsStorage {
    public static final String RN_SECURE_STORAGE = "RN_SECURE_STORAGE";

    static public class ResultSet extends CipherStorage.CipherResult<byte[]> {
        @KnownCiphers
        public final String cipherStorageName;

        public ResultSet(@KnownCiphers final String cipherStorageName, final byte[] valueBytes) {
            super(valueBytes);

            this.cipherStorageName = cipherStorageName;
        }
    }

    @NonNull
    private final SharedPreferences prefs;

    public PrefsStorage(@NonNull final ReactApplicationContext reactContext) {
        this.prefs = reactContext.getSharedPreferences(RN_SECURE_STORAGE, Context.MODE_PRIVATE);
    }


    @Nullable
    public ResultSet getEncryptedEntry(@NonNull final String key) {
        // in case of wrong value or username
        byte[] bytesForValue = getBytesForValue(key);
        String cipherStorageName = getCipherStorageName(key);

        if (cipherStorageName == null) {
            // If the CipherStorage name is not found, we assume it is because the entry was written by an older
            // version of this library. The older version used Facebook Conceal, so we default to that.
            cipherStorageName = KnownCiphers.FB;
        }

        return new ResultSet(cipherStorageName, bytesForValue);

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

    public boolean clear() {
        return this.prefs.edit().clear().commit();
    }

    public boolean exist(@NonNull final String key) {
        return this.prefs.contains(key);
    }

    public void storeEncryptedEntry(@NonNull final String key, @NonNull final EncryptionResult encryptionResult) {
        prefs.edit()
                .putString(key, Base64.encodeToString(encryptionResult.value, Base64.DEFAULT))
                .apply();
    }

    @Nullable
    private byte[] getBytesForValue(@NonNull final String key) {
        return getBytes(key);
    }

    @Nullable
    private String getCipherStorageName(@NonNull final String service) {
        String key = getKeyForCipherStorage(service);

        return this.prefs.getString(key, null);
    }


    public JSONArray getAllStoredKeys() {
        List<String> list = new ArrayList<>(this.prefs.getAll().keySet());
        return new JSONArray(list);
    }

    @NonNull
    public static String getKeyForCipherStorage(@NonNull final String service) {
        return service + ":" + "c";
    }

    @Nullable
    private byte[] getBytes(@NonNull final String key) {
        String value = this.prefs.getString(key, null);

        if (value != null) {
            return Base64.decode(value, Base64.DEFAULT);
        }

        return null;
    }
}
