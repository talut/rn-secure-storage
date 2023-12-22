import { NativeModules, Platform } from "react-native";

export const ACCESSIBLE = {
  WHEN_UNLOCKED: "AccessibleWhenUnlocked",
  AFTER_FIRST_UNLOCK: "AccessibleAfterFirstUnlock",
  ALWAYS: "AccessibleAlways",
  WHEN_PASSCODE_SET_THIS_DEVICE_ONLY: "AccessibleWhenPasscodeSetThisDeviceOnly",
  WHEN_UNLOCKED_THIS_DEVICE_ONLY: "AccessibleWhenUnlockedThisDeviceOnly",
  AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY: "AccessibleAfterFirstUnlockThisDeviceOnly",
  ALWAYS_THIS_DEVICE_ONLY: "AccessibleAlwaysThisDeviceOnly",
};
const { RNSecureStorage } = NativeModules;

const supportedBiometryType = RNSecureStorage.getSupportedBiometryType;
RNSecureStorage.getSupportedBiometryType = () => {
  if (Platform.OS === "android") {
    return Promise.reject("Not supported on Android");
  }
  return supportedBiometryType;
};

const set = RNSecureStorage.setItem;
RNSecureStorage.setItem = (key, value, options = {}) => {
  if (Platform.OS === "android") {
    return set(key, value);
  }
  return set(key, value, options);
};

const multiSet = RNSecureStorage.multiSet;
RNSecureStorage.multiSet = (keyValuePairs, options = {}) => {
  if (Platform.OS === "android") {
    return multiSet(keyValuePairs);
  }
  return multiSet(keyValuePairs, options);
};

export default RNSecureStorage;
