# RNSecureStorage

Secure Storage for React Native (Android & iOS) - Keychain & Keystore


### After v2 this package has AndroidX support. If you want to use without AndroidX please use *v1.1.1*

### Facebook RN blog post about v0.60 and AndroidX support: [https://facebook.github.io/react-native/blog/2019/07/03/version-60](https://facebook.github.io/react-native/blog/2019/07/03/version-60)

**[Go to F.A.Q for more information.](#faq)**  

**[Not your main language ? Check out the translations here](#translations)**

### IOS

RNSecureStorage is using Keychain for secure storing.

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

**IOS**

If you don't have CocoaPods installed: `sudo gem install cocoapods`

```sh
cd ios && pod install
```

**Android**
There is no required action for Android.

**Manual Linking**

**[Manual Installation](/docs/manual-installation.md)** (If something went wrong with react-native link)


## Usage

Note: Don't use any special chars at key like `test@key`. This kinda key names can be a problem for IOS/Android

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

**EXISTS**
```javascript
// res -> is can be True or False
RNSecureStorage.exists("key1")
.then((res) => {
console.log(res ? "Key exists": "Key not exists")
}, (err) => {
console.log(err);
});
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


### Example

You can find the usage example of the package in the example folder. 

```console
git clone https://github.com/talut/rn-secure-package

cd rn-secure-package/example

npm install

react-native run-ios/android
```
### Version 2.0.4
- [https://github.com/talut/rn-secure-storage/pull/44](https://github.com/talut/rn-secure-storage/pull/44) merged.
- [https://github.com/talut/rn-secure-storage/pull/46](https://github.com/talut/rn-secure-storage/pull/46) merged.

### Version 2.0.3
- [https://github.com/talut/rn-secure-storage/pull/40](https://github.com/talut/rn-secure-storage/pull/40) merged.

### Version 2.0.2
- When phone default locale including RTL then this workaround is setting English locale before generating a key pair and changing it back after all.

### Version 2.0.1 (AndroidX Support added)
- Update to AndroidX. Make sure to enable it in your project's android/gradle.properties.

#### Version 1.1.1
- Exists method added. Thanks [@kirin-p](https://github.com/kirin-p)

#### Version 1.1.0
- TypeScript support added. Thanks [@akiver](https://github.com/akiver)

#### Version 1.0.9
- Gradle version updated.
- Log messages updated. (For IOS)
- **IOS keychain service name updated right now this package is using main bundle name. If you already using this package in production After this update all IOS user will log out from app automatically.**

#### Version 1.0.82 (a little bug fix)
- google() repo added because Gradle v3.1.4 can't found.

#### Version 1.0.7
- Android & IOS returing value/messages are updated.
- [Issue:1](https://github.com/talut/rn-secure-storage/issues/1) is solved.

### Translations
- [French](docs/README-fr.md) by [@Vinetos](https://github.com/vinetos)
- [Indonesia](docs/README-id.md) by [@mfaridzia](https://github.com/mfaridzia)
- [German](docs/README-de.md) by [@msdeibel](https://github.com/msdeibel)
- [Dutch](docs/README-nl.md) by [@fpkmatthi](https://github.com/fpkmatthi)
- [Brazilian Portuguese](docs/README-ptBR.md) by [@HenryFilho](https://github.com/HenryFilho)

### F.A.Q

- **Why should I use this package?**
- *You can use other packages like react-native-keychain I know that package has more options. But you can store only username and password, while with RNSecureStorage you can store a lot of [key,value] pairs*
- **Why shouldn't I use react-native-secure-key-store**
- *You can use that package but you can't get any good solution with lowest API of Android. Also that package is set to minSDK:18.  Thats means you might encounter some problems ...*
- **Hey can I trust your code/package?**
- *You can see all of my code in the repo and can review it. Also if you want, you can easily can fork my repo and change what bothers you. This package is under MIT license. So I can't give you any warranty.*  **But you should know, I'm using this package in my projects.**
- **Will you maintain this package?**
- *Yeah, I'm planning to do so. But you know time can change everything.*
-**How can I support you?**
-*If you're using my package that's enough for me*

*Note: This package is more improved version of [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store), RNSecureStorage has "under api 23" support*  


## Thanks
- Thanks to you [@cagriyilmaz](https://github.com/cagriyilmaz) for IOS part.
- Thanks to you [@akiver](https://github.com/akiver) for TypeScript definitions.
- Thanks to you [@kirin-p](https://github.com/kirin-p) for exits method.
- [Thanks to you @pradeep1991singh for react-native-secure-key-store](https://github.com/pradeep1991singh/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details




