package com.taluttasgiran.rnsecurestorage.secureStorage;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.security.KeyStore;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class SecureStorage {

    private static final String KEY_ALIAS = "RNSecureStorage";
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12; // Bytes
    private static final int GCM_TAG_LENGTH = 16; // Bytes

    private KeyStore keyStore;

    public SecureStorage() {
        initKeyStore();
    }

    private void initKeyStore() throws RuntimeException {
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
            keyStore.load(null);

            if (!keyStore.containsAlias(KEY_ALIAS)) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE);
                keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                        .setRandomizedEncryptionRequired(false)
                        .build());
                keyGenerator.generateKey();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String encrypt(String input) {
        try {
            SecretKey key = (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] iv = new byte[GCM_IV_LENGTH];
            new SecureRandom().nextBytes(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(8 * GCM_TAG_LENGTH, iv));
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            String encryptedBase64 = Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
            String ivBase64 = Base64.encodeToString(iv, Base64.DEFAULT);
            return ivBase64 + ":" + encryptedBase64;
        } catch (Exception e) {
            // Handle exceptions
            return null;
        }
    }

    public String decrypt(String encrypted) {
        try {
            String[] parts = encrypted.split(":");
            byte[] iv = Base64.decode(parts[0], Base64.DEFAULT);
            byte[] encryptedBytes = Base64.decode(parts[1], Base64.DEFAULT);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(8 * GCM_TAG_LENGTH, iv));
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            // Handle exceptions
            return null;
        }
    }
}
