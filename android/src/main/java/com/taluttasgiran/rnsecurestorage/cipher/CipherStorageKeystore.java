package com.taluttasgiran.rnsecurestorage.cipher;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings({"unused", "WeakerAccess"})
public class CipherStorageKeystore {

    private SecretKeySpec secretKey;
    private byte[] iv;
    private String decryptedString;
    private String encryptedString;

    public CipherStorageKeystore(String myKey) {
        MessageDigest sha = null;
        byte[] key = new byte[0];
        try {
            key = myKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(key.length);
        try {
            sha = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // use only first 128 bit
        this.secretKey = new SecretKeySpec(key, "AES");
        this.iv = new byte[]{11, 53, 63, 87, 11, 69, 63, 28, 0, 9, 18, 99, 95, 23, 45, 8};
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String encrypt(String strToEncrypt) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, this.secretKey, new IvParameterSpec(this.iv));
        return Base64.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)), Base64.DEFAULT);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String decrypt(String strToDecrypt) throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING", "BC");
        cipher.init(Cipher.DECRYPT_MODE, this.secretKey, new IvParameterSpec(iv));
        return new String(cipher.doFinal(Base64.decode(strToDecrypt, Base64.DEFAULT)), StandardCharsets.UTF_8);
    }

}
