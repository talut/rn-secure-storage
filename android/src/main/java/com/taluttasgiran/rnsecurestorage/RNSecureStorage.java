package com.taluttasgiran.rnsecurestorage;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.taluttasgiran.rnsecurestorage.cipher.CipherStorageFacebookConceal;

import java.io.IOException;

import static com.taluttasgiran.rnsecurestorage.Constants.FACE_SUPPORTED_NAME;
import static com.taluttasgiran.rnsecurestorage.Constants.FINGERPRINT_SUPPORTED_NAME;
import static com.taluttasgiran.rnsecurestorage.Constants.IRIS_SUPPORTED_NAME;
import static com.taluttasgiran.rnsecurestorage.RNSecureStorageModule.RN_SECURE_STORAGE;

public class RNSecureStorage {
    CipherStorageFacebookConceal cipherStorageFacebookConceal;
    ReactApplicationContext mContext;
    PrefsStorage prefsStorage;

    public RNSecureStorage(ReactApplicationContext context) {
        this.mContext = context;
        this.prefsStorage = new PrefsStorage(context);
        initialize();
    }


    private void initialize() {
        if (!shouldUseConceal()) {
            cipherStorageFacebookConceal = new CipherStorageFacebookConceal(this.mContext);
        } else {
            Log.d(RN_SECURE_STORAGE, "In development");
        }
    }

    private boolean shouldUseConceal() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

    public void setValueByKey(@NonNull String key, @NonNull String value) throws KeyChainException, CryptoInitializationException, IOException {
        if (!shouldUseConceal()) {
            this.prefsStorage.storeEncryptedEntry(key, this.cipherStorageFacebookConceal.encrypt(key, value));
        } else {
            Log.d(RN_SECURE_STORAGE, "In development");
        }
    }

    public String getValueByKey(@NonNull String key) {
        if (!shouldUseConceal()) {
            String encryptedEntry = this.prefsStorage.getEncryptedEntry(key);
            try {
                return this.cipherStorageFacebookConceal.decrypt(key, encryptedEntry);
            } catch (Exception e) {
                return null;
            }
        } else {
            Log.d(RN_SECURE_STORAGE, "In development");
            return null;
        }
    }

    public boolean removeValueByKey(@NonNull String key) {
        if (!shouldUseConceal()) {
            return this.prefsStorage.removeEntry(key);
        } else {
            Log.d(RN_SECURE_STORAGE, "In development");
            return false;
        }
    }

    public boolean existByKey(@NonNull String key) {
        if (!shouldUseConceal()) {
            return this.prefsStorage.exist(key);
        } else {
            Log.d(RN_SECURE_STORAGE, "In development");
            return false;
        }
    }

    public boolean clear() {
        if (!shouldUseConceal()) {
            return this.prefsStorage.clear();
        } else {
            Log.d(RN_SECURE_STORAGE, "In development");
            return false;
        }
    }

}
