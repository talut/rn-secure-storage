# RNSecureStorage

[![npm version](https://badge.fury.io/js/rn-secure-storage.svg)](https://badge.fury.io/js/rn-secure-storage)
[![npm downloads](https://img.shields.io/npm/dm/rn-secure-storage.svg?maxAge=2592000)](https://img.shields.io/npm/dm/rn-secure-storage.svg?maxAge=2592000)
[![npm](https://img.shields.io/npm/dt/rn-secure-storage.svg?maxAge=2592000)](https://img.shields.io/npm/dt/rn-secure-storage.svg?maxAge=2592000)
![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)
Secure Storage for React Native (Android & iOS) - Keychain & Keystore

### Thanks for using this library

Please read my disclaimer about maintaining this library [here](#disclaimer)

#### If you like this library, please consider supporting my work by buying me a coffee

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://buymeacoff.ee/talut)

## Getting Started

**With NPM**

```
npm install --save rn-secure-storage
```

**With YARN**

```
yarn add rn-secure-storage
```

### What's changed in v3.0.0

- Rewritten Android module with enhanced security features. [I need your reviews](#ineedyourreviews)
- iOS module redeveloped using Swift and updated APIs.
- Comprehensive renaming and expansion of APIs.
- Modifications to the return types of some APIs.
- Added `clear` for comprehensive data clearance.
- Introduced `getAllKeys` for retrieving all stored keys.
- Implemented `multiSet` for setting multiple values simultaneously.
- New `multiGet` feature for fetching multiple values at once.
- `multiRemove` added for bulk deletion of items.
- `getSupportedBiometryType` introduced for iOS (supports biometric authentication types).

### API

- [setItem](#setitem)
- [getItem](#getitem)
- [removeItem](#removeitem)
- [exists](#exists)
- [getAllKeys](#getallkeys)
- [clear](#clear)
- [multiSet](#multiset)
- [multiGet](#multiget)
- [multiRemove](#multiremove)
- [getSupportedBiometryType](#getsupportedbiometrytype)

### setItem

```js
import RNSecureStorage, {ACCESSIBLE} from 'rn-secure-storage';

RNSecureStorage.setItem("key", "value", {accessible: ACCESSIBLE.WHEN_UNLOCKED})
```

### getItem

```js
RNSecureStorage.getItem("key")
```

### removeItem

```js
RNSecureStorage.removeItem("key")
```

### exists

```js
RNSecureStorage.exist("key")
```

### getAllKeys

```js
RNSecureStorage.getAllKeys()
```

### clear

```js
RNSecureStorage.clear()
```

### multiSet

```js
import RNSecureStorage, {ACCESSIBLE} from 'rn-secure-storage';

const items = {"key_1": "multi key 1", "key_2": "multi key 2"};
RNSecureStorage.multiSet(items, {accessible: ACCESSIBLE.WHEN_UNLOCKED})
```

### multiGet

```js
RNSecureStorage.multiGet(["key_1", "key_2"])
```

### multiRemove

```js
RNSecureStorage.multiRemove(["key_1", "key_2"])
```

### getSupportedBiometryType

```js
RNSecureStorage.getSupportedBiometryType()
```

## Options

| Key              | Platform | Description                                                                                      | Default                               |
|------------------|----------|--------------------------------------------------------------------------------------------------|---------------------------------------|
| **`accessible`** | iOS only | This indicates when a keychain item is accessible, see possible values in `Keychain.ACCESSIBLE`. | *`Keychain.ACCESSIBLE.WHEN_UNLOCKED`* |

### `Keychain.ACCESSIBLE` enum

| Key                                       | Description                                                                                                                                                                            |
|-------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **`WHEN_UNLOCKED`**                       | The data in the keychain item can be accessed only while the device is unlocked by the user.                                                                                           |
| **`AFTER_FIRST_UNLOCK`**                  | The data in the keychain item cannot be accessed after a restart until the device has been unlocked once by the user.                                                                  |
| **`ALWAYS`**                              | The data in the keychain item can always be accessed regardless of whether the device is locked.                                                                                       |
| **`WHEN_PASSCODE_SET_THIS_DEVICE_ONLY`**  | The data in the keychain can only be accessed when the device is unlocked. Only available if a passcode is set on the device. Items with this attribute never migrate to a new device. |
| **`WHEN_UNLOCKED_THIS_DEVICE_ONLY`**      | The data in the keychain item can be accessed only while the device is unlocked by the user. Items with this attribute do not migrate to a new device.                                 |
| **`AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY`** | The data in the keychain item cannot be accessed after a restart until the device has been unlocked once by the user. Items with this attribute never migrate to a new device.         |
| **`ALWAYS_THIS_DEVICE_ONLY`**             | The data in the keychain item can always be accessed regardless of whether the device is locked. Items with this attribute never migrate to a new device.                              |

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

### I need your reviews

I have rewritten the Android module with enhanced security features. I need your reviews. Please test the new version and let me know your
thoughts. I will be happy to hear your suggestions and comments. I'm planning to release the new version to handle biometric authentication
on Android.

### Connect with me
<p align="left">
<a href="https://linkedin.com/in/taluttasgiran" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="taluttasgiran" height="30" width="40" /></a>
<a href="https://twitter.com/taluttasgiran" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/twitter.svg" alt="taluttasgiran" height="30" width="40" /></a>
<a href="https://dev.to/talut" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/devto.svg" alt="talut" height="30" width="40" /></a>
<a href="https://stackoverflow.com/users/3366361" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/stack-overflow.svg" alt="3366361" height="30" width="40" /></a>
<a href="https://www.youtube.com/c/@taluttasgiran" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/youtube.svg" alt="@taluttasgiran" height="30" width="40" /></a>
</p>

### Disclaimer

As an open-source enthusiast and developer, I originally created this library for professional use. However, with recent changes in my
career, my focus has shifted away from React Native, limiting my involvement to personal projects. Consequently, my time to address issues
and review pull requests for this library has become restricted. I remain committed to maintaining and improving this library, but my
response times might be longer. I greatly appreciate your patience and understanding.

The beauty of open-source is in collaboration. If you find this library useful and would like to contribute, whether through code,
addressing issues, or even buying a coffee to show support, it would be immensely appreciated. Together, we can ensure the continued
development and enhancement of this library.

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/talut)
