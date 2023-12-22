package com.taluttasgiran.rnsecurestorage;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.taluttasgiran.rnsecurestorage.PrefsStorage.ResultSet;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorage;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorage.DecryptionResult;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorage.DecryptionResultHandler;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorageFacebookConceal;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorageKeystoreAesCbc;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorageKeystoreRsaEcb;
import com.taluttasgiran.rnsecurestorage.cipherStorage.CipherStorageKeystoreRsaEcb.NonInteractiveHandler;
import com.taluttasgiran.rnsecurestorage.exceptions.CryptoFailedException;
import com.taluttasgiran.rnsecurestorage.exceptions.EmptyParameterException;
import com.taluttasgiran.rnsecurestorage.exceptions.KeyStoreAccessException;

import java.util.HashMap;
import java.util.Map;

public class RNSecureStorageModule extends ReactContextBaseJavaModule {
    //region Constants
    public static final String RN_SECURE_STORAGE = "RNSecureStorage";
    public static final String RN_SECURE_STORAGE_ALIAS = "RNSecureStorageAlias";
    public static final String FINGERPRINT_SUPPORTED_NAME = "Fingerprint";
    public static final String FACE_SUPPORTED_NAME = "Face";
    public static final String IRIS_SUPPORTED_NAME = "Iris";

    /**
     * Options mapping keys.
     */
    @interface Maps {
        String SECURITY_LEVEL = "securityLevel";
        String STORAGE = "storage";
    }

    /**
     * Known error codes.
     */
    @interface Errors {
        String E_EMPTY_PARAMETERS = "E_EMPTY_PARAMETERS";
        String E_CRYPTO_FAILED = "E_CRYPTO_FAILED";
        String E_SUPPORTED_BIOMETRY_ERROR = "E_SUPPORTED_BIOMETRY_ERROR";
        /**
         * Raised for unexpected errors.
         */
        String E_UNKNOWN_ERROR = "E_UNKNOWN_ERROR";
    }

    /**
     * Supported ciphers.
     */
    @StringDef({KnownCiphers.FB, KnownCiphers.AES, KnownCiphers.RSA})
    public @interface KnownCiphers {
        /**
         * Facebook conceal compatibility lib in use.
         */
        String FB = "FacebookConceal";
        /**
         * AES encryption.
         */
        String AES = "KeystoreAESCBC";
        /**
         * Biometric + RSA.
         */
        String RSA = "KeystoreRSAECB";
    }

    //region Members
    /**
     * Name-to-instance lookup  map.
     */
    private final Map<String, CipherStorage> cipherStorageMap = new HashMap<>();
    /**
     * Shared preferences storage.
     */
    private final PrefsStorage prefsStorage;
    //endregion

    //region Initialization

    /**
     * Default constructor.
     */
    public RNSecureStorageModule(@NonNull final ReactApplicationContext reactContext) {
        super(reactContext);
        prefsStorage = new PrefsStorage(reactContext);
        addCipherStorageToMap(new CipherStorageFacebookConceal(reactContext));
        addCipherStorageToMap(new CipherStorageKeystoreAesCbc());
        addCipherStorageToMap(new CipherStorageKeystoreRsaEcb());
    }

    //region Overrides

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public String getName() {
        return RN_SECURE_STORAGE;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();

        constants.put(SecurityLevel.ANY.jsName(), SecurityLevel.ANY.name());
        constants.put(SecurityLevel.SECURE_SOFTWARE.jsName(), SecurityLevel.SECURE_SOFTWARE.name());
        constants.put(SecurityLevel.SECURE_HARDWARE.jsName(), SecurityLevel.SECURE_HARDWARE.name());

        return constants;
    }
    //endregion

    //region React Methods

    /**
     * Set a value.
     */
    @ReactMethod
    protected void setItem(@NonNull final String key, @NonNull final String value, @NonNull final ReadableMap options, @NonNull final Promise promise) {
        try {
            setValueWithKey(key, value, options);
            promise.resolve("Key stored successfully");
        } catch (EmptyParameterException e) {
            promise.reject(Errors.E_EMPTY_PARAMETERS, e);
        } catch (CryptoFailedException e) {
            promise.reject(Errors.E_CRYPTO_FAILED, e);
        } catch (Throwable fail) {
            promise.reject(Errors.E_UNKNOWN_ERROR, fail);
        }
    }

    /**
     * Get a value from secure storage.
     */
    @ReactMethod
    protected void getItem(@NonNull final String key, @NonNull ReadableMap options, @NonNull final Promise promise) {
        try {
            promise.resolve(getValue(key, options));
        } catch (CryptoFailedException e) {
            promise.reject(Errors.E_CRYPTO_FAILED, e);
        } catch (Throwable fail) {
            promise.reject(Errors.E_UNKNOWN_ERROR, fail);
        }
    }

    /**
     * Checks if a key has been set.
     */
    @ReactMethod
    public void exist(String key, Promise promise) {
        try {
            promise.resolve(this.prefsStorage.exist(key));
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /**
     * Multiple key pair set for secure storage
     */
    @ReactMethod
    public void multiSet(ReadableMap keyValuePairs, @NonNull ReadableMap options, Promise promise) {
        WritableArray unsettedPairs = new WritableNativeArray();
        if (keyValuePairs != null && keyValuePairs.keySetIterator().hasNextKey()) {
            ReadableMapKeySetIterator iterator = keyValuePairs.keySetIterator();
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                String value = keyValuePairs.getString(key);

                if (value != null) {
                    try {
                        this.setValueWithKey(key, value, options);
                    } catch (CryptoFailedException | EmptyParameterException e) {
                        promise.reject("error", e.getMessage());
                    }
                } else {
                    unsettedPairs.pushString(key);
                }
            }

            if (unsettedPairs.size() > 0) {
                promise.resolve(unsettedPairs);
            } else {
                promise.resolve("All keys setted");
            }
        } else {
            promise.reject(Errors.E_UNKNOWN_ERROR, "RNSecureStorage: An error occurred during key / value pair set.");
        }
    }

    /**
     * Get multiple values from secure storage.
     */
    @ReactMethod
    public void multiGet(ReadableArray keys, @NonNull ReadableMap options, Promise promise) {
        WritableMap keyValueList = new WritableNativeMap();
        if (keys.size() > 0) {
            final int size = keys.size();
            for (int i = 0; i < size; i++) {
                String key = keys.getString(i);
                String value = null;
                try {
                    value = this.getValue(key, options);
                } catch (CryptoFailedException ignored) {
                }
                if (value != null) {
                    keyValueList.putString(key, value);
                }
            }
            promise.resolve(keyValueList);
        } else {
            promise.reject(Errors.E_UNKNOWN_ERROR, "RNSecureStorage: An error occurred during key/value get");
        }
    }

    /**
     * Get all setted keys from secure storage.
     */
    @ReactMethod
    public void getAllKeys(Promise promise) {
        try {
            promise.resolve(String.valueOf(this.prefsStorage.getAllStoredKeys()));
        } catch (Exception e) {
            promise.reject("thereAreNoKeys", "RNSecureStorage: There are no stored keys.");
        }
    }

    /**
     * Remove a value from secure storage.
     */
    @ReactMethod
    public void removeItem(String key, @NonNull ReadableMap options, Promise promise) {
        try {
            boolean status = this.prefsStorage.removeEntry(key);
            removeValueWithKey(key, options);
            if (status) {
                promise.resolve("Removed successfully");
            } else {
                promise.reject(Errors.E_UNKNOWN_ERROR, "RNSecureStorage: An error occurred during key remove");
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /**
     * Remove values from secure storage (On error will return unremoved keys)
     */
    @ReactMethod
    public void multiRemove(ReadableArray keys, @NonNull ReadableMap options, Promise promise) {
        WritableArray unremovedKeys = new WritableNativeArray();
        if (keys.size() > 0) {
            final int size = keys.size();
            for (int i = 0; i < size; i++) {
                String key = keys.getString(i);
                boolean status = this.prefsStorage.removeEntry(key);
                removeItem(key, options, promise);
                if (!status) {
                    unremovedKeys.pushString(key);
                }
            }
            if (unremovedKeys.size() > 0) {
                promise.resolve(unremovedKeys);
            } else {
                promise.resolve("All keys removed");
            }
        } else {
            promise.reject("keyNotStored", "RNSecureStorage: An error occurred during key remove");
        }
    }

    /**
     * Removes whole RNSecureStorage data (On error will return unremoved keys)
     */
    @ReactMethod
    public void clear(Promise promise) {
        try {
            if (this.prefsStorage.clear()) {
                if (this.prefsStorage.getAllStoredKeys().isNull(0)) {
                    promise.resolve("All keys/values removed successfully");
                } else {
                    promise.resolve(String.valueOf(this.prefsStorage.getAllStoredKeys()));
                }
            } else {
                promise.reject(Errors.E_UNKNOWN_ERROR, "RNSecureStorage: An error occurred during key remove");
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /**
     * Get supported biometry type (Will return FaceID, TouchID, Fingerprint, Iris, Face or undefined/null)
     */
    @ReactMethod
    public void getSupportedBiometryType(@NonNull final Promise promise) {
        try {
            String reply = null;
            if (isFaceAuthAvailable()) {
                reply = FACE_SUPPORTED_NAME;
            } else if (isIrisAuthAvailable()) {
                reply = IRIS_SUPPORTED_NAME;
            } else if (isFingerprintAuthAvailable()) {
                reply = FINGERPRINT_SUPPORTED_NAME;
            }

            promise.resolve(reply);
        } catch (Exception e) {
            promise.reject(Errors.E_SUPPORTED_BIOMETRY_ERROR, e);
        } catch (Throwable fail) {
            promise.reject(Errors.E_UNKNOWN_ERROR, fail);
        }
    }

    //endregion

    protected String getValue(@NonNull final String key, @NonNull final ReadableMap options) throws CryptoFailedException {
        final ResultSet resultSet = prefsStorage.getEncryptedEntry(key);

        if (resultSet == null) {
            return null;
        }
        final CipherStorage current = getSelectedStorage(options);
        DecryptionResult decryptionResult = decryptToResult(RN_SECURE_STORAGE_ALIAS, current, resultSet);
        return decryptionResult.value;
    }

    protected void setValueWithKey(@NonNull final String key, @NonNull final String value, @NonNull final ReadableMap options) throws CryptoFailedException, EmptyParameterException {
        throwIfEmptyParams(key, value);
        final SecurityLevel level = getSecurityLevelOrDefault(options);
        final CipherStorage storage = getSelectedStorage(options);

        throwIfInsufficientLevel(storage, level);

        final CipherStorage.EncryptionResult result = storage.encrypt(RN_SECURE_STORAGE_ALIAS, value, level);
        prefsStorage.storeEncryptedEntry(key, result);
    }

    protected void removeValueWithKey(@NonNull final String key, @NonNull final ReadableMap options) throws CryptoFailedException, EmptyParameterException {
        if (TextUtils.isEmpty(key)) {
            throw new EmptyParameterException("you passed empty or null key");
        }
        final SecurityLevel level = getSecurityLevelOrDefault(options);
        final CipherStorage storage = getSelectedStorage(options);
        throwIfInsufficientLevel(storage, level);
        try {
            storage.removeKey(key);
        } catch (KeyStoreAccessException e) {
            throw new CryptoFailedException("KeyStoreAccessException", e);
        }
    }

    @NonNull
    private CipherStorage getSelectedStorage(@Nullable final ReadableMap options)
            throws CryptoFailedException {
        final String cipherName = getSpecificStorageOrDefault(options);

        CipherStorage result = null;

        if (null != cipherName) {
            result = getCipherStorageByName(cipherName);
        }

        // attempt to access none existing storage will force fallback logic.
        if (null == result) {
            result = getCipherStorageForCurrentAPILevel();
        }

        return result;
    }

    /**
     * Extract user specified storage from options.
     */
    @KnownCiphers
    @Nullable
    private static String getSpecificStorageOrDefault(@Nullable final ReadableMap options) {
        String storageName = null;

        if (null != options && options.hasKey(Maps.STORAGE)) {
            storageName = options.getString(Maps.STORAGE);
        }

        return storageName;
    }

    /**
     * Get security level from options or fallback {@link SecurityLevel#ANY} value.
     */
    @NonNull
    private static SecurityLevel getSecurityLevelOrDefault(@Nullable final ReadableMap options) {
        return getSecurityLevelOrDefault(options, SecurityLevel.ANY.name());
    }

    /**
     * Get security level from options or fallback to default value.
     */
    @NonNull
    private static SecurityLevel getSecurityLevelOrDefault(@Nullable final ReadableMap options,
                                                           @NonNull final String fallback) {
        String minimalSecurityLevel = null;

        if (null != options && options.hasKey(Maps.SECURITY_LEVEL)) {
            minimalSecurityLevel = options.getString(Maps.SECURITY_LEVEL);
        }

        if (null == minimalSecurityLevel) minimalSecurityLevel = fallback;

        return SecurityLevel.valueOf(minimalSecurityLevel);
    }
    //endregion

    //region Implementation

    private void addCipherStorageToMap(@NonNull final CipherStorage cipherStorage) {
        cipherStorageMap.put(cipherStorage.getCipherStorageName(), cipherStorage);
    }

    @NonNull
    private DecryptionResult decryptToResult(@NonNull final String alias,
                                             @NonNull final CipherStorage storage,
                                             @NonNull final ResultSet resultSet)
            throws CryptoFailedException {
        final DecryptionResultHandler handler = new NonInteractiveHandler();
        storage.decrypt(handler, alias, resultSet.value, SecurityLevel.ANY);

        CryptoFailedException.reThrowOnError(handler.getError());

        if (null == handler.getResult()) {
            throw new CryptoFailedException("No decryption results and no error. Something deeply wrong!");
        }

        return handler.getResult();
    }

    @NonNull
    CipherStorage getCipherStorageForCurrentAPILevel() throws CryptoFailedException {
        final int currentApiLevel = Build.VERSION.SDK_INT;
        CipherStorage foundCipher = null;

        for (CipherStorage variant : cipherStorageMap.values()) {
            Log.d(RN_SECURE_STORAGE, "Probe cipher storage: " + variant.getClass().getSimpleName());
            final int minApiLevel = variant.getMinSupportedApiLevel();
            final int capabilityLevel = variant.getCapabilityLevel();
            final boolean isSupportedApi = (minApiLevel <= currentApiLevel);
            if (!isSupportedApi) continue;
            if (foundCipher != null && capabilityLevel < foundCipher.getCapabilityLevel()) continue;
            foundCipher = variant;
        }

        if (foundCipher == null) {
            throw new CryptoFailedException("Unsupported Android SDK " + Build.VERSION.SDK_INT);
        }

        Log.d(RN_SECURE_STORAGE, "Selected storage: " + foundCipher.getClass().getSimpleName());

        return foundCipher;
    }

    public static void throwIfEmptyParams(@Nullable final String key,
                                          @Nullable final String value)
            throws EmptyParameterException {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            throw new EmptyParameterException("you passed empty or null key/value");
        }
    }

    public static void throwIfInsufficientLevel(@NonNull final CipherStorage storage,
                                                @NonNull final SecurityLevel level)
            throws CryptoFailedException {
        if (storage.securityLevel().satisfiesSafetyThreshold(level)) {
            return;
        }

        throw new CryptoFailedException(
                String.format(
                        "Cipher Storage is too weak. Required security level is: %s, but only %s is provided",
                        level.name(),
                        storage.securityLevel().name()));
    }

    @Nullable
    CipherStorage getCipherStorageByName(@KnownCiphers @NonNull final String knownName) {
        return cipherStorageMap.get(knownName);
    }

    boolean isFingerprintAuthAvailable() {
        return DeviceAvailability.isBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isFingerprintAuthAvailable(getReactApplicationContext());
    }

    boolean isFaceAuthAvailable() {
        return DeviceAvailability.isBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isFaceAuthAvailable(getReactApplicationContext());
    }

    boolean isIrisAuthAvailable() {
        return DeviceAvailability.isBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isIrisAuthAvailable(getReactApplicationContext());
    }

    //endregion
    //region Nested declarations
}
