package com.taluttasgiran.rnsecurestorage;

import androidx.annotation.NonNull;

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
import com.taluttasgiran.rnsecurestorage.secureStorage.SecureStorage;

public class RNSecureStorageModule extends ReactContextBaseJavaModule {
    public static final String RN_SECURE_STORAGE = "RNSecureStorage";

    /**
     * Known error codes.
     */
    @interface Errors {
        String KEY_REQUIRED = "KEY_REQUIRED";
        String NOT_FOUND = "NOT_FOUND";
        String VALUE_REQUIRED = "VALUE_REQUIRED";
        String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    }

    /**
     * Name-to-instance lookup  map.
     */
    private final SecureStorage secureStorage;
    /**
     * Shared preferences storage.
     */
    private final PreferencesStorage prefsStorage;

    /**
     * Default constructor.
     */
    public RNSecureStorageModule(@NonNull final ReactApplicationContext reactContext) {
        super(reactContext);
        prefsStorage = new PreferencesStorage(reactContext);
        try {
            secureStorage = new SecureStorage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public String getName() {
        return RN_SECURE_STORAGE;
    }

    /**
     * Set a value.
     */
    @ReactMethod
    protected void setItem(@NonNull final String key, @NonNull final String value, @NonNull final Promise promise) {
        try {
            String encryptedValue = secureStorage.encrypt(value);
            prefsStorage.storeEncryptedEntry(key, encryptedValue);
            promise.resolve("Stored successfully");
        } catch (Exception e) {
            promise.reject("RNSecureStorage: An error occurred during key store", e);
        }
    }

    /**
     * Get a value from secure storage.
     */
    @ReactMethod
    protected void getItem(@NonNull final String key, @NonNull final Promise promise) {
        try {
            String encryptedValue = prefsStorage.getEncryptedEntry(key);
            if (encryptedValue != null) {
                promise.resolve(secureStorage.decrypt(encryptedValue));
            } else {
                promise.reject(Errors.NOT_FOUND, "RNSecureStorage: Value for " + key + " does not exist.");
            }
        } catch (Exception e) {
            promise.reject(e);
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
    public void multiSet(ReadableMap keyValuePairs, Promise promise) {
        if (keyValuePairs.toHashMap().size() > 0) {
            ReadableMapKeySetIterator iterator = keyValuePairs.keySetIterator();
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                String value = keyValuePairs.getString(key);
                if (value == null) {
                    promise.reject(Errors.VALUE_REQUIRED, "RNSecureStorage: You must provide a value to store this key: " + key);
                    return;
                }
                setItem(key, value, promise);
            }
            promise.resolve("Stored successfully");
        } else {
            promise.reject(Errors.VALUE_REQUIRED, "RNSecureStorage: You must provide at least one key/value pair to store");
        }
    }

    /**
     * Get multiple values from secure storage.
     */
    @ReactMethod
    public void multiGet(ReadableArray keys, Promise promise) {
        WritableMap resultMap = new WritableNativeMap();
        if (keys.size() > 0) {
            final int size = keys.size();
            for (int i = 0; i < size; i++) {
                String key = keys.getString(i);
                String encryptedValue = prefsStorage.getEncryptedEntry(key);
                if (encryptedValue != null) {
                    resultMap.putString(key, secureStorage.decrypt(encryptedValue));
                } else {
                    resultMap.putString(key, null);
                }
            }
            promise.resolve(resultMap);
        } else {
            promise.reject(Errors.KEY_REQUIRED, "RNSecureStorage: You must provide at least one key to get");
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
            promise.reject(Errors.NOT_FOUND, "RNSecureStorage: There are no stored keys.");
        }
    }

    /**
     * Remove a value from secure storage.
     */
    @ReactMethod
    public void removeItem(String key, Promise promise) {
        try {
            if (this.prefsStorage.removeEntry(key)) {
                promise.resolve("Removed successfully");
            } else {
                promise.reject(Errors.NOT_FOUND, "RNSecureStorage: Value for key " + key + " does not exist.");
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    /**
     * Remove values from secure storage (On error will return unremoved keys)
     */
    @ReactMethod
    public void multiRemove(ReadableArray keys, Promise promise) {
        WritableArray unremovedKeys = new WritableNativeArray();
        if (keys.size() > 0) {
            final int size = keys.size();
            for (int i = 0; i < size; i++) {
                String key = keys.getString(i);
                if (!this.prefsStorage.removeEntry(key)) {
                    unremovedKeys.pushString(key);
                }
            }
            if (unremovedKeys.size() > 0) {
                promise.reject(Errors.NOT_FOUND, "RNSecureStorage: Value for keys " + unremovedKeys + " does not exist.");
            } else {
                promise.resolve("Removed successfully");
            }
        } else {
            promise.reject(Errors.KEY_REQUIRED, "RNSecureStorage: You must provide at least one key to remove");
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
                    promise.resolve(true);
                } else {
                    promise.resolve(String.valueOf(this.prefsStorage.getAllStoredKeys()));
                }
            } else {
                promise.reject(Errors.UNKNOWN_ERROR, "RNSecureStorage: An error occurred during key remove");
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

}
