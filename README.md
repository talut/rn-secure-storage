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
  import RNSecureStorage, { ACCESSIBLE } from "rn-secure-storage"
  // {accessible: ACCESSIBLE.WHEN_UNLOCKED} -> This is only for IOS
  const [keys, setKeys] = useState([])
  const [values, setValues] = useState()

  const storeItem = () => {
    RNSecureStorage.set("token", "^W((nXWi~M`$Gtu<s+;$`M1SotPG^~", { accessible: ACCESSIBLE.WHEN_UNLOCKED })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const getItem = () => {
    RNSecureStorage.get("token")
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const removeItem = () => {
    RNSecureStorage.remove("token")
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const removeAll = () => {
    RNSecureStorage.removeAll()
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const itemExist = () => {
    RNSecureStorage.exist("token").then(res => {
      console.log(res)
    })
  }

  const getAllKeys = () => {
    RNSecureStorage.getAllKeys()
      .then(res => {
        setKeys(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const multiGet = () => {
    RNSecureStorage.multiGet(keys)
      .then(res => {
        setValues(res)
      })
      .catch(err => {
        console.log(err)
      })
  }
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
### Version 3.0.0
- Get All Key api

### [Version 2.0.4](./README-v2.0.4.md)

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
-*You can use Patreon: [patreon.com/talut](https://patreon.com/talut)*


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details




