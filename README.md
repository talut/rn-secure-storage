# RNSecureStorage

[![npm version](https://badge.fury.io/js/rn-secure-storage.svg)](https://badge.fury.io/js/rn-secure-storage)
[![npm downloads](https://img.shields.io/npm/dm/rn-secure-storage.svg?maxAge=2592000)](https://img.shields.io/npm/dm/rn-secure-storage.svg?maxAge=2592000)
![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)

Secure Storage for React Native (Android & iOS) - Keychain & Keystore

#### If you like this library, please consider supporting my work by buying me a coffee

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://buymeacoff.ee/talut)

### Thanks for using this library

Please read my disclaimer about maintaining this library [here](#disclaimer)

For Android [I need your reviews](#i-need-your-reviews)

## Getting Started

**With NPM**

```
npm install --save rn-secure-storage
```

**With YARN**

```
yarn add rn-secure-storage
```

### What's changed in v3.0.1
- Return type for multiGet changed. https://github.com/talut/rn-secure-storage/pull/79

### What's changed in v3.0.0

- Rewritten Android module with enhanced security features.
- Android minSdkVersion is now 23 (Android 6.0 Marshmallow)
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

### setItem

To set the item, you need to pass the key and value as parameters. You can also pass the options as a third parameter. If the key exists,
the value will be updated, otherwise, it will be created. You can use the `exists` method to check if the key exists. You can also pass
the `accessible` option to set the accessibility of the keychain item (only for IOS).

```js
import RNSecureStorage, {ACCESSIBLE} from 'rn-secure-storage';

RNSecureStorage.setItem("key", "value", {accessible: ACCESSIBLE.WHEN_UNLOCKED}).then((res) => {
	console.log(res);
}).catch((err) => {
	console.log(err);
});
```

### getItem

To get the item, you need to pass the key as a parameter. If the key exists, the value will be returned, otherwise, it will return an error.

```js
RNSecureStorage.getItem("key").then((res) => {
	console.log(res);
}).catch((err) => {
	console.log(err);
});
```

### removeItem

To remove the item, you need to pass the key as a parameter. If the key exists, the value will be removed, otherwise, it will return an
error.

```js
RNSecureStorage.removeItem("key").then((res) => {
	console.log(res);
}).catch((err) => {
	console.log(err);
});
```

### exists

To check if the item exists, you need to pass the key as a parameter. If the key exists it will return `true`, otherwise, it will
return `false`. Mostly you don't need to use this method because `getItem` will return an error if the key doesn't exist. But for
performance you can just check if the key exists before calling `getItem`.

```js
RNSecureStorage.exist("key").then((res) => {
	console.log(res ? "exists" : "does not exist");
}).catch((err) => {
	console.log(err);
});
```

### getAllKeys

To get all keys, you need to call `getAllKeys` method. It will return an array of keys.

```js
RNSecureStorage.getAllKeys().then((res) => {
	console.log(res);
}).catch((err) => {
	console.log(err);
});
```

### clear

To clear all data, you need to call `clear` method. It will return `true` if the operation is successful. Otherwise, it will return
remaining keys.

```js
RNSecureStorage.clear().then((res) => {
	console.log(res);
}).catch((err) => {
	console.log(err);
});
```

### multiSet

Multi set is a new feature that allows you to set multiple values simultaneously. You need to pass an object as the first parameter

```js
import RNSecureStorage, {ACCESSIBLE} from 'rn-secure-storage';

const items = {"key_1": "multi key 1", "key_2": "multi key 2"};
RNSecureStorage.multiSet(items, {accessible: ACCESSIBLE.WHEN_UNLOCKED}).then((res) => {
	console.log(res);
}).catch((err) => {
	console.log(err);
});
```

### multiGet

With multi get you can fetch multiple values at once. You need to pass an array of keys as the first parameter.

```js
RNSecureStorage.multiGet(["key_1", "key_2"]).then((res) => {
	console.log(res);
}).catch((err) => {
	console.log(err);
});
```

### multiRemove

With multi remove you can delete multiple values at once. You need to pass an array of keys as the first parameter. If your keys are
removed/not found, it will return an array of keys that are not removed/found.

```js
RNSecureStorage.multiRemove(["key_1", "key_2"]).then((res) => {
	console.log(res);
}).catch((err) => {
	console.log(err);
});
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

#### You can also check out the sample project for more details

## iOS `Keychain` persistence

By default, the iOS Keychain items tied to your app are not cleared upon uninstall, as this is [the expected behaviour](https://developer.apple.com/forums/thread/36442?answerId=281900022#281900022). However, if you do want to change that, you can use the snippet below to clear those items upon your app's first launch.

```objc
// AppDelegate.mm

/**
 Deletes all Keychain items accessible by this app if this is the first time the user launches the app.
 */
static void ClearKeychainItemsUponFirstLaunch() {
    // Checks whether or not this is the first time the app is run.
    if ([[NSUserDefaults standardUserDefaults] boolForKey:@"HAS_RUN_BEFORE"] == NO) {
        // Sets the appropriate value so we don't clear next time the app is launched.
        [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"HAS_RUN_BEFORE"];

        NSArray *secItemClasses = @[
            (__bridge id)kSecClassGenericPassword,
            (__bridge id)kSecClassInternetPassword,
            (__bridge id)kSecClassCertificate,
            (__bridge id)kSecClassKey,
            (__bridge id)kSecClassIdentity
        ];

        // Maps through all Keychain classes and deletes all items that match.
        for (id secItemClass in secItemClasses) {
            NSDictionary *spec = @{(__bridge id)kSecClass: secItemClass};
            SecItemDelete((__bridge CFDictionaryRef)spec);
        }
    }
}

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    self.moduleName = @"MyRnApp";

    self.initialProps = @{};

    // Adds this line to call the new function from above.
    ClearKeychainItemsUponFirstLaunch();

    // ...
}

// ...

@end
```

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
