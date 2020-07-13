# RNSecureStorage

Secure Storage for React Native (Android & iOS) - Keychain & Keystore

### With v3, this package's IOS part rewrote with Swift.

**[Go to F.A.Q for more information.](#faq)**  

### IOS

RNSecureStorage is using Keychain for secure storing with Swift.

### Android

Under API 23 RNSecureStorage is using

Above API 23 RNSecureStorage is using

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
- IOS part rewrote with Swift.
- Get all keys feature added.
- Remove all stored values feature added.
- Multi getting values by keys feature added.

##### [Version 2.0.4](./README-v2.0.4.md)


```javascript
import RNSecureStorage, { ACCESSIBLE } from 'rn-secure-storage'
```

```javascript

 // {accessible: ACCESSIBLE.WHEN_UNLOCKED} -> This is only for IOS
 import React from "react"
 import { Button, SafeAreaView, ScrollView, StatusBar } from "react-native"
 import RNSecureStorage, { ACCESSIBLE } from "rn-secure-storage"
 const App = () => {
  const setItem = () => {
    RNSecureStorage.setItem("token", "^W((nXWi~M`$Gtu<s+;$`M1SotPG^~", { accessible: ACCESSIBLE.WHEN_UNLOCKED })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const getItem = () => {
    RNSecureStorage.getItem("token")
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const removeItem = () => {
    RNSecureStorage.removeItem("token")
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const removeAll = () => {
    RNSecureStorage.clear()
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const itemExist = () => {
    RNSecureStorage.exist("@refreshToken")
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const getAllKeys = () => {
    RNSecureStorage.getAllKeys()
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const multiSet = () => {
    const pair_one = ["@idToken", "id_token_value"]
    const pair_two = ["@accessToken", "access_token_value"]
    const pair_three = ["@refreshToken", "refresh_token_value"]
    RNSecureStorage.multiSet([pair_one, pair_two, pair_three], { accessible: ACCESSIBLE.WHEN_UNLOCKED })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const multiGet = () => {
    RNSecureStorage.multiGet(["@idToken", "@accessToken", "@refreshToken"])
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const multiRemove = () => {
    RNSecureStorage.multiRemove(["@refreshToken", "token"])
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

  const getSupportedBiometryType = () => {
    RNSecureStorage.getSupportedBiometryType()
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }

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


### Example

You can find the usage example of the package in the example folder. 

```console
git clone https://github.com/talut/rn-secure-package

cd rn-secure-package/example

npm install

react-native run-ios/android
```

### F.A.Q

- **Hey can I trust your code/package?**
- *You can see all of my code in the repo and can review it. Also if you want, you can easily can fork my repo and change what bothers you. This package is under MIT license. So I can't give you any warranty.*  **But you should know, I'm using this package in my projects.**

- **Will you maintain this package?**
- *Yeah, I'm planning to do so.3*

- **How can I support you?**
- *You can use Patreon: [patreon.com/talut](https://patreon.com/talut)*


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details




