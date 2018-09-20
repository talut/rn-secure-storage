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

import RNSecureStorage from 'rn-secure-storage'

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





