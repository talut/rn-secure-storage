
import {Platform, NativeModules } from 'react-native';

const { RNRnSecureStorage } = NativeModules;
const isIOS = Platform.OS === "ios";

export const set = (key, value) => {
  if (isIOS) {
    return RNRnSecureStorage.set(key, value)
  } else {
    return RNRnSecureStorage.set(key, value);
  }
};

export const get = (key) => {
  if (isIOS) {
    return RNRnSecureStorage.get(key)
  } else {
    return RNRnSecureStorage.get(key);
  }
};

export const clear = (key) => {
  if (isIOS) {
    return RNRnSecureStorage.clearWithKey(key);
  } else {
    return RNRnSecureStorage.clearWithKey(key);
  }
};