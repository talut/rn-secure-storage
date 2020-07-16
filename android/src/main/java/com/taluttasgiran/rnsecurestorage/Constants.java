package com.taluttasgiran.rnsecurestorage;

class Constants {
    public static final String RN_SECURE_STORAGE = "RN_SECURE_STORAGE";
    public static final String FINGERPRINT_SUPPORTED_NAME = "Fingerprint";
    public static final String FACE_SUPPORTED_NAME = "Face";
    public static final String IRIS_SUPPORTED_NAME = "Iris";

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
