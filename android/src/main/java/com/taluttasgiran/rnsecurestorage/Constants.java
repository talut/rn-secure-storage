package com.taluttasgiran.rnsecurestorage;

import android.security.keystore.KeyProperties;

class Constants {
    public static final String RN_SECURE_STORAGE = "RN_SECURE_STORAGE";
    public static final String FINGERPRINT_SUPPORTED_NAME = "Fingerprint";
    public static final String FACE_SUPPORTED_NAME = "Face";
    public static final String IRIS_SUPPORTED_NAME = "Iris";

    public static final String ALGORITHM_AES = KeyProperties.KEY_ALGORITHM_AES;
    public static final String BLOCK_MODE_CBC = KeyProperties.BLOCK_MODE_CBC;
    public static final String PADDING_PKCS7 = KeyProperties.ENCRYPTION_PADDING_PKCS7;
    public static final String ENCRYPTION_TRANSFORMATION =
            ALGORITHM_AES + "/" + BLOCK_MODE_CBC + "/" + PADDING_PKCS7;
    public static final int ENCRYPTION_KEY_SIZE = 256;
    public static final String DEFAULT_SERVICE = "RN_SECURE_STORAGE_DEFAULT_ALIAS";


    @interface Errors {
        String E_EMPTY_PARAMETERS = "E_EMPTY_PARAMETERS";
        String E_CRYPTO_FAILED = "E_CRYPTO_FAILED";
        String E_KEYSTORE_ACCESS_ERROR = "E_KEYSTORE_ACCESS_ERROR";
        String E_SUPPORTED_BIOMETRY_ERROR = "E_SUPPORTED_BIOMETRY_ERROR";
        /**
         * Raised for unexpected errors.
         */
        String E_UNKNOWN_ERROR = "E_UNKNOWN_ERROR";
    }
}
