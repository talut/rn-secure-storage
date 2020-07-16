package com.taluttasgiran.rnsecurestorage.cipher;

import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.crypto.keychain.KeyChain;
import com.facebook.react.bridge.ReactApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings({"unused", "WeakerAccess"})
public class CipherStorageFacebookConceal implements CipherBase {
    public static final String RN_SECURE_STORAGE = "RN_SECURE_STORAGE";

    private final Crypto crypto;

    public CipherStorageFacebookConceal(@NonNull final ReactApplicationContext reactContext) {
        KeyChain keyChain = new SharedPrefsBackedKeyChain(reactContext, CryptoConfig.KEY_256);

        this.crypto = AndroidConceal.get().createDefaultCrypto(keyChain);
    }

    @Override
    public String encrypt(@NonNull String key, @NonNull String value) throws KeyChainException, CryptoInitializationException, IOException {
        if (!crypto.isAvailable()) {
            throw new Error();
        }
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        OutputStream cryptoStream = crypto.getCipherOutputStream(bout, Entity.create(key));
        cryptoStream.write(value.getBytes("UTF-8"));
        cryptoStream.close();
        String result = Base64.encodeToString(bout.toByteArray(), Base64.DEFAULT);
        bout.close();
        return result;
    }

    @Override
    public String decrypt(@NonNull String key, @NonNull String value) throws KeyChainException, CryptoInitializationException, IOException {
        if (!crypto.isAvailable()) {
            throw new Error();
        }
        ByteArrayInputStream bin = new ByteArrayInputStream(Base64.decode(value, Base64.DEFAULT));
        InputStream cryptoStream = crypto.getCipherInputStream(bin, Entity.create(key));
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read = cryptoStream.read(buffer)) != -1) {
            bout.write(buffer, 0, read);
        }
        cryptoStream.close();
        String result = new String(bout.toByteArray(), "UTF-8");
        bin.close();
        bout.close();
        return result;
    }
}
