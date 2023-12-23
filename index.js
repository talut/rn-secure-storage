import {NativeModules, Platform} from "react-native";

export const ACCESSIBLE = {
	WHEN_UNLOCKED: "AccessibleWhenUnlocked",
	AFTER_FIRST_UNLOCK: "AccessibleAfterFirstUnlock",
	ALWAYS: "AccessibleAlways",
	WHEN_PASSCODE_SET_THIS_DEVICE_ONLY: "AccessibleWhenPasscodeSetThisDeviceOnly",
	WHEN_UNLOCKED_THIS_DEVICE_ONLY: "AccessibleWhenUnlockedThisDeviceOnly",
	AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY: "AccessibleAfterFirstUnlockThisDeviceOnly",
	ALWAYS_THIS_DEVICE_ONLY: "AccessibleAlwaysThisDeviceOnly",
};
const {RNSecureStorage} = NativeModules;

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

const getAllKeys = RNSecureStorage.getAllKeys;
RNSecureStorage.getAllKeys = () => {
	if (Platform.OS === "android") {
		return new Promise((resolve, reject) => {
			getAllKeys()
				.then((keys) => keys ? resolve(JSON.parse(keys)) : resolve(null))
				.catch((error) => reject(error));
		});
	}
	return getAllKeys();
};

export default RNSecureStorage;
