# RNSecureStorage

Secure Storage for React Native (Android & iOS) 

Note: This package is more improved version of [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store), with RNSecureStorage now under api 23 is supporting too.

### IOS

RNSecureStorage is using Keychain for secure storaging.

### Android

Under API 23 RNSecureStorage is using [secure-preferences](https://github.com/scottyab/secure-preferences/) by [@scottyab](https://github.com/scottyab)

Above API 23 RNSecureStorage is using [Android Keystore](https://developer.android.com/training/articles/keystore)

## Getting Started

**With NPM**
```
npm install --save rn-secure-storage
```

**With YARN**
```
yarn add rn-secure-storage
```

**Automatic linking**

```
react-native link rn-secure-storage
```

**Manual Linking**

**[Manual Installation](/docs/manual-installation.md)** (If something went wrong with react-native link)


## Usage


```javascript

import RNSecureStorage, { ACCESSIBLE } from 'rn-secure-storage'

```

**SET**
```javascript
// {accessible: ACCESSIBLE.WHEN_UNLOCKED} -> This for IOS
RNSecureStorage.set("key1", "this is a value", {accessible: ACCESSIBLE.WHEN_UNLOCKED})
.then((res) => {
console.log(res);
}, (err) => {
console.log(err);
});
```

*Example Success Response for Set*
```javascript
{"message":"Key stored successfuly", "status":1} You can use this response with JSON.parse()
```

**GET**
```javascript
RNSecureStorage.get("key1").then((value) => {
console.log(value) // Will return direct value
}).catch((err) => {
console.log(err)
})
```

**REMOVE**
```javascript
RNSecureStorage.remove("key1").then((val) => {
console.log(val)
}).catch((err) => {
console.log(err)
});
```

## Options

| Key | Platform | Description | Default |
|---|---|---|---|
|**`accessible`**|iOS only|This dictates when a keychain item is accessible, see possible values in `Keychain.ACCESSIBLE`. |*`Keychain.ACCESSIBLE.WHEN_UNLOCKED`*|

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

## Thanks

- **[Thanks to you @pradeep1991singh for react-native-secure-key-store](https://github.com/pradeep1991singh/)**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details




