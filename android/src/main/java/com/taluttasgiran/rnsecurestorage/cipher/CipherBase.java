package com.taluttasgiran.rnsecurestorage.cipher;

import androidx.annotation.NonNull;

import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;

import java.io.IOException;

interface CipherBase {
    String encrypt(@NonNull String key, @NonNull String value) throws KeyChainException, CryptoInitializationException, IOException;
    String decrypt(@NonNull String key, @NonNull String value) throws KeyChainException, CryptoInitializationException, IOException;
}
