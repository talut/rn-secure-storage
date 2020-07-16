package com.taluttasgiran.rnsecurestorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static com.taluttasgiran.rnsecurestorage.Constants.FACE_SUPPORTED_NAME;
import static com.taluttasgiran.rnsecurestorage.Constants.FINGERPRINT_SUPPORTED_NAME;
import static com.taluttasgiran.rnsecurestorage.Constants.IRIS_SUPPORTED_NAME;

public class RNSecureStorageModule extends ReactContextBaseJavaModule {
    public static final String RN_SECURE_STORAGE = "RNSecureStorage";
    RNSecureStorage rnSecureStorage;

    RNSecureStorageModule(ReactApplicationContext reactContext) {
        super(reactContext);
        rnSecureStorage = new RNSecureStorage(getReactApplicationContext());
    }

    @ReactMethod
    public void setItem(String key, String value, @Nullable ReadableMap options, Promise promise) {
        try {
            boolean status = this.rnSecureStorage.setValueByKey(key, value);
            if (status) {
                promise.resolve("Key stored");
            } else {
                promise.reject("", "");
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getItem(String key, Promise promise) {
        try {
            promise.resolve(this.rnSecureStorage.getValueByKey(key));
        } catch (Exception e) {
            promise.reject("Key not present", "");
        }
    }

    @ReactMethod
    public void exist(String key, Promise promise) {
        try {
            promise.resolve(this.rnSecureStorage.existByKey(key));
        } catch (Exception e) {
            promise.reject(e);
        }
    }


    @ReactMethod
    public void multiSet(ReadableArray keyValuePairs, @Nullable ReadableMap options, Promise promise) {
        ArrayList<String> unsettedPairs = new ArrayList<>();
        if (keyValuePairs.size() > 0) {
            final int size = keyValuePairs.size();
            for (int i = 0; i < size; i++) {
                ReadableArray keyValuePair = keyValuePairs.getArray(i);
                if (keyValuePair != null && keyValuePair.size() == 2) {
                    try {
                        String key = keyValuePair.getString(0);
                        String value = keyValuePair.getString(1);
                        if (key != null && value != null) {
                            this.rnSecureStorage.setValueByKey(key, value);
                        } else {
                            promise.reject("", "");
                        }
                    } catch (KeyChainException | CryptoInitializationException | IOException e) {
                        promise.reject("", "");
                    }
                } else {
                    if (keyValuePair.getString(0) != null) {
                        unsettedPairs.add(keyValuePair.getString(0));
                    }
                }
            }
            if (unsettedPairs.size() > 0) {
                promise.resolve(String.valueOf(new JSONArray(unsettedPairs)));
            } else {
                promise.resolve("All keys setted");
            }
        } else {
            promise.reject("", "");
        }
    }

    @ReactMethod
    public void multiGet(ReadableArray keys, Promise promise) {
        Map<String, String> keyValueList = new HashMap<>();
        if (keys.size() > 0) {
            final int size = keys.size();
            for (int i = 0; i < size; i++) {
                String key = keys.getString(i);
                if (key != null) {
                    String value = null;
                    try {
                        value = this.rnSecureStorage.getValueByKey(key);
                    } catch (IOException | CryptoInitializationException | KeyChainException | InvalidAlgorithmParameterException | BadPaddingException | NoSuchPaddingException | NoSuchProviderException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeyException ignored) {
                    }
                    if (value != null) {
                        keyValueList.put(key, value);
                    }
                }
            }
            if (keyValueList.size() > 0) {
                promise.resolve(String.valueOf(new JSONObject(keyValueList)));
            } else {
                promise.reject("", "");
            }
        } else {
            promise.reject("", "");
        }
    }

    @ReactMethod
    public void getAllKeys(Promise promise) {
        try {
            promise.resolve(String.valueOf(this.rnSecureStorage.getAllKeys()));
        } catch (Exception e) {
            promise.reject("", "");
        }
    }

    @ReactMethod
    public void removeItem(String key, Promise promise) {
        try {
            if (this.rnSecureStorage.removeValueByKey(key)) {
                promise.resolve("Removed successfully");
            } else {
                promise.reject("", "");
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }


    @ReactMethod
    public void clear(Promise promise) {
        try {
            if (this.rnSecureStorage.clear()) {
                promise.resolve("Removed successfully");
            } else {
                promise.reject("", "");
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }


    @ReactMethod
    public void multiRemove(ReadableArray keys, Promise promise) {
        ArrayList<String> unremovedKeys = new ArrayList<>();
        if (keys.size() > 0) {
            final int size = keys.size();
            for (int i = 0; i < size; i++) {
                String key = keys.getString(i);
                if (key != null) {
                    boolean status = this.rnSecureStorage.removeValueByKey(key);
                    if (!status) {
                        unremovedKeys.add(key);
                    }
                }
            }
            if (unremovedKeys.size() > 0) {
                promise.resolve(String.valueOf(new JSONArray(unremovedKeys)));
            } else {
                promise.resolve("All keys removed");
            }
        } else {
            promise.reject("", "");
        }
    }

    @ReactMethod
    public void getSupportedBiometryType(@NonNull Promise promise) {
        String reply = null;
        try {
            if (DeviceAvailability.isBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isFaceAuthAvailable(getReactApplicationContext())) {
                reply = FACE_SUPPORTED_NAME;
            } else if (DeviceAvailability.isBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isIrisAuthAvailable(getReactApplicationContext())) {
                reply = IRIS_SUPPORTED_NAME;
            } else if (DeviceAvailability.isBiometricAuthAvailable(getReactApplicationContext()) && DeviceAvailability.isFingerprintAuthAvailable(getReactApplicationContext())) {
                reply = FINGERPRINT_SUPPORTED_NAME;
            }
            promise.resolve(reply);
        } catch (Exception e) {
            promise.reject(Constants.Errors.E_SUPPORTED_BIOMETRY_ERROR, e);
        } catch (Throwable fail) {
            promise.reject(Constants.Errors.E_UNKNOWN_ERROR, fail);
        }
    }

    @Override
    public String getName() {
        return RN_SECURE_STORAGE;
    }
}
