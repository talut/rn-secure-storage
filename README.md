# RNSecureStorage

Secure Storage for React Native (Android & iOS) - Keychain & Keystore

## Getting Started

**With NPM**
```
npm install --save rn-secure-storage
```

**With YARN**
```
yarn add rn-secure-storage
```

### Version 3.0.0
- Android part rewritten.
- IOS part rewrote with Swift and new API's.
- All API's names changed new API's added.
- Some of api return type changed.
- `removeAll` added.
- `getAllKeys` added.
- `multiSet` added.
- `multiGet` added.
- `multiRemove` added.
- `getSupportedBiometryType` added.

##### [Version 2.0.5](https://github.com/talut/rn-secure-storage/tree/2.0.5)
**You can still use v2.0.5**

### Usage

```javascript

 // {accessible: ACCESSIBLE.WHEN_UNLOCKED} -> This is only for IOS
 import React from "react"
 import { Button, SafeAreaView, ScrollView, StatusBar } from "react-native"
 import RNSecureStorage, { ACCESSIBLE } from "rn-secure-storage"
 const App = () => {
	 /**
		* Set a value from secure storage.
		*/
	 const setItem = () => {
		 RNSecureStorage.setItem("token", "^W((nXWi~M`$Gtu<s+;$`M1SotPG^~", { accessible: ACCESSIBLE.WHEN_UNLOCKED })
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Get a value from secure storage.
		*/
	 const getItem = () => {
		 RNSecureStorage.getItem("token")
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Remove a value from secure storage.
		*/
	 const removeItem = () => {
		 RNSecureStorage.removeItem("token")
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Removes whole RNSecureStorage data (It'll return unremoved keys)
		*/
	 const removeAll = () => {
		 RNSecureStorage.clear()
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Checks if a key has been set it'll return tru/false
		*/
	 const itemExist = () => {
		 RNSecureStorage.exist("@refreshToken")
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Get all setted keys from secure storage.
		*/
	 const getAllKeys = () => {
		 RNSecureStorage.getAllKeys()
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Multiple key pair set for secure storage. Will return unsetted keys.
		*/
	 const multiSet = () => {
		 const items = {
			 "@idToken": "id_token_value",
			 "@refreshToken": "refresh_token_value"
		 };
		 RNSecureStorage.multiSet(items, { accessible: ACCESSIBLE.WHEN_UNLOCKED })
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Get multiple values from secure storage.
		*/
	 const multiGet = () => {
		 RNSecureStorage.multiGet(["@idToken", "@accessToken", "@refreshToken"])
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Remove values from secure storage (On error will return unremoved keys)
		*/
	 const multiRemove = () => {
		 RNSecureStorage.multiRemove(["@refreshToken", "token"])
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };
	 /**
		* Get supported biometry type (Will return FaceID, TouchID or undefined)
		*/
	 const getSupportedBiometryType = () => {
		 RNSecureStorage.getSupportedBiometryType()
			 .then(res => {
				 console.log(res);
			 })
			 .catch(err => {
				 console.log(err);
			 });
	 };

	 return (
      <SafeAreaView>
        <ScrollView>
          <Button title="Store Item" onPress={setItem} />
          <Button title="Get Item" onPress={getItem} />
          <Button title="Remove Item" onPress={removeItem} />
          <Button title="Remove All" onPress={removeAll} />
          <Button title="Is Item Exist?" onPress={itemExist} />
          <Button title="Get All Keys" onPress={getAllKeys} />
          <Button title="Multiple Set" onPress={multiSet} />
          <Button title="Multiple Get" onPress={multiGet} />
          <Button title="Multiple Remove" onPress={multiRemove} />
          <Button title="Get Supported Biometry Type" onPress={getSupportedBiometryType} />
        </ScrollView>
      </SafeAreaView>
  )
}

export default App

```


## Options

| Key | Platform | Description | Default |
|---|---|---|---|
|**`accessible`**|iOS only|This indicates when a keychain item is accessible, see possible values in `Keychain.ACCESSIBLE`. |*`Keychain.ACCESSIBLE.WHEN_UNLOCKED`*|

### `Keychain.ACCESSIBLE` enum

| Key | Description |
|-----|-------------|
|**`WHEN_UNLOCKED`**|The data in the keychain item can be accessed only while the device is unlocked by the user.|
|**`AFTER_FIRST_UNLOCK`**|The data in the keychain item cannot be accessed after a restart until the device has been unlocked once by the user.|
|**`ALWAYS`**|The data in the keychain item can always be accessed regardless of whether the device is locked.|
|**`WHEN_PASSCODE_SET_THIS_DEVICE_ONLY`**|The data in the keychain can only be accessed when the device is unlocked. Only available if a passcode is set on the device. Items with this attribute never migrate to a new device.|
|**`WHEN_UNLOCKED_THIS_DEVICE_ONLY`**|The data in the keychain item can be accessed only while the device is unlocked by the user. Items with this attribute do not migrate to a new device.|
|**`AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY`**|The data in the keychain item cannot be accessed after a restart until the device has been unlocked once by the user. Items with this attribute never migrate to a new device.|
|**`ALWAYS_THIS_DEVICE_ONLY`**|The data in the keychain item can always be accessed regardless of whether the device is locked. Items with this attribute never migrate to a new device.|

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details




