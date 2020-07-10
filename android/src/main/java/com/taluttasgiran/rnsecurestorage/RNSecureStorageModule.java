package com.taluttasgiran.rnsecurestorage;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import javax.annotation.Nullable;

public class RNSecureStorageModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    RNSecureStorageModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }


    @ReactMethod
    public void setItem(String key, String value, @Nullable ReadableMap options, Promise promise) {
        Log.d("RNSecureStorage: Key", key);
        Log.d("RNSecureStorage: Value", value);
    }

    @ReactMethod
    public void getItem(String key, Promise promise) {
        Log.d("RNSecureStorage: Key", key);
    }

    @ReactMethod
    public void exist(String key, Promise promise) {
        Log.d("RNSecureStorage: Key", key);
    }

    @ReactMethod
    public void getAllKeys(Promise promise) {
        Log.d("RNSecureStorage:", "getAllKeys");
    }

    @ReactMethod
    public void multiSet(ReadableArray keyValuePair, Promise promise) {
        Log.d("RNSecureStorage", String.valueOf(keyValuePair.getArray(0).getString(0)));
    }

    @ReactMethod
    public void multiGet(ReadableArray keys, Promise promise) {
        Log.d("RNSecureStorage", String.valueOf(keys));
    }


    @ReactMethod
    public void removeItem(String key, Promise promise) {
        Log.d("RNSecureStorage: Key", key);
    }

    @ReactMethod
    public void multiRemove(ReadableArray keys, Promise promise) {
        Log.d("RNSecureStorage", String.valueOf(keys));
    }

    @ReactMethod
    public void clear(Promise promise) {
        Log.d("RNSecureStorage", "clear");
    }

    @NonNull
    @Override
    public String getName() {
        return "RNSecureStorage";
    }
}
