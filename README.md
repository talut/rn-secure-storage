# RNSecureStorage

Secure Storage for React Native (Android & iOS) 

Note: This package is more improved version of [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store) With RNSecureStorage now under api 23 is supporting too.

###IOS

RNSecureStorage is using Keychain for secure storaging.

###Android

Under API 23 RNSecureStorage is using [secure-preferences](https://github.com/scottyab/secure-preferences/) by [@scottyab](https://github.com/scottyab)

Above API 23 RNSecureStorage is using [Android Keystore](https://developer.android.com/training/articles/keystore)

## Getting Started

*With NPM*
```
npm install --save rn-secure-storage
```

*With YARN*
```
yarn add rn-secure-storage
```

`Automatic linking`

```
react-native link rn-secure-storage
```
## Usage

```javascript

import RNSecureStorage from 'rn-secure-storage'

```

