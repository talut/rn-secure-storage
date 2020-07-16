package com.taluttasgiran.rnsecurestorage;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.taluttasgiran.rnsecurestorage.cipher.CipherStorageFacebookConceal;
import com.taluttasgiran.rnsecurestorage.cipher.CipherStorageKeystore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static com.taluttasgiran.rnsecurestorage.RNSecureStorageModule.RN_SECURE_STORAGE;

public class RNSecureStorage {
    CipherStorageFacebookConceal cipherStorageFacebookConceal;
    CipherStorageKeystore cipherStorageKeystore;
    ReactApplicationContext mContext;
    PrefsStorage prefsStorage;

    public RNSecureStorage(ReactApplicationContext context) {
        this.mContext = context;
        this.prefsStorage = new PrefsStorage(context);
        initialize();
    }


    private void initialize() {
        if (shouldUseConceal()) {
            this.cipherStorageFacebookConceal = new CipherStorageFacebookConceal(this.mContext);
        } else {
            this.cipherStorageKeystore = new CipherStorageKeystore(RN_SECURE_STORAGE);
        }
    }

    private boolean shouldUseConceal() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

    public boolean setValueByKey(@NonNull String key, @NonNull String value) throws KeyChainException, CryptoInitializationException, IOException {
        if (shouldUseConceal()) {
            return this.prefsStorage.storeEncryptedEntry(key, this.cipherStorageFacebookConceal.encrypt(key, value));
        } else {
            try {
                String encryptValue = this.cipherStorageKeystore.encrypt(value);
                return this.prefsStorage.storeEncryptedEntry(key, encryptValue);
            } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | NoSuchProviderException | BadPaddingException | IllegalBlockSizeException e) {
                return false;
            }
        }
    }

    public String getValueByKey(@NonNull String key) throws KeyChainException, CryptoInitializationException, IOException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        String encryptedEntry = this.prefsStorage.getEncryptedEntry(key);
        if (encryptedEntry != null) {
            if (shouldUseConceal()) {
                return this.cipherStorageFacebookConceal.decrypt(key, encryptedEntry);
            } else {
                return this.cipherStorageKeystore.decrypt(encryptedEntry);
            }
        } else {
            return null;
        }
    }

    public boolean removeValueByKey(@NonNull String key) {
        return this.prefsStorage.removeEntry(key);
    }

    public boolean existByKey(@NonNull String key) {
        return this.prefsStorage.exist(key);
    }

    public JSONArray getAllKeys() {
        List<String> list = new ArrayList<>(this.prefsStorage.getAllStoredKeys().keySet());
        return new JSONArray(list);
    }

    public boolean clear() {
        return this.prefsStorage.clear();
    }

}
