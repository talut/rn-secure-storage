# RNSecureStorage

Secure Storage for React Native (Android & iOS) 

*Catatan: Paket ini merupakan versi yang diperbaharui dari [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store), RNSecureStorage has "under api 23" support*

**[Periksa F.A.Q untuk informasi lebih lanjut.](#faq)**

### IOS

RNSecureStorage Menggunakan Keychain untuk keamanan storaging.

### Android

API 23 kebawah RNSecureStorage menggunakan [secure-preferences](https://github.com/scottyab/secure-preferences/) by [@scottyab](https://github.com/scottyab)

API 23 keatas RNSecureStorage menggunakan [Android Keystore](https://developer.android.com/training/articles/keystore)

## Mulai

**Menggunakan NPM**
```
npm install --save rn-secure-storage
```

**Menggunakan YARN**
```
yarn add rn-secure-storage
```

**Linking otomatis**

```
react-native link rn-secure-storage
```

**Linking Manual**

**[Manual Installation](/docs/manual-installation.md)** (Jika terdapat kesalahan terhadap react-native link)


## Penggunaan


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

## Pilihan

| Key | Platform | Deskripsi | Default |
|---|---|---|---|
|**`accessible`**|Untuk iOS|Menentukan kapan keychain item dapat diakses, lihat values yang memungkinkan di `Keychain.ACCESSIBLE`. |*`Keychain.ACCESSIBLE.WHEN_UNLOCKED`*|

### `Keychain.ACCESSIBLE` enum

| Key | Deskripsi |
|-----|-------------|
|**`WHEN_UNLOCKED`**|Data dalam keychain item dapat diakses hanya ketika device sudah dibuka oleh user.|
|**`AFTER_FIRST_UNLOCK`**|Data dalam keychain item tidak dapat diakses setelah restart sampai device dibuka oleh user.|
|**`ALWAYS`**|Data dalam keychain item dapat selalu diakses walaupun device terkunci.|
|**`WHEN_PASSCODE_SET_THIS_DEVICE_ONLY`**|Data dalam keychain hanya dapat diakses ketika device terbuka. Bisa dilakukan jika passcode sudah dibuat dalam device. Items dalam attribute ini tidak pernah berpindah ke device baru.|
|**`WHEN_UNLOCKED_THIS_DEVICE_ONLY`**|Data dalam keychain item dapat diakses hanya jika device dibuka oleh user. Items dalam attribute ini tidak berpindah ke device baru.|
|**`AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY`**|Data dalam keychain item ini tidak dapat diakses setelah restart sampai device dibuka oleh user. Items dalam attribute ini tidak pernah berpindah ke device baru.|
|**`ALWAYS_THIS_DEVICE_ONLY`**|Data dalam keychain item ini dapat selalu diakses walaupun device terkunci. Items dalam attribute ini tidak pernah berpindah ke device baru.|


### Contoh

Anda bisa mencari contoh penggunaan paket di folder contoh. 

```console
git clone https://github.com/talut/rn-secure-package

cd rn-secure-package/example

npm install

react-native run-ios/android
```



### Version 1.0.7
- Android & IOS keluaran value/messages sudah diupdate.
- [Issue:1](https://github.com/talut/rn-secure-storage/issues/1) is solved.

### F.A.Q

- **Kenapa saya harus menggunakan paket ini?**
- *Anda bisa menggunakan paket ini seperti react-native-keychain walaupun paket tersebut lebih memiliki banyak pilihan. Tapi anda hanya bisa memasukkan username, password dan tidak dapat memasukkan [key,value] dengan RNSecureStorage*
- **Kenapa saya tidak menggunakan react-native-secure-key-store**
- *Anda bisa menggunakan paket tersebut tapi anda tidak dapat solusi yang bagus dengan lowest API pada Android. Dan juga paket tersebut diset minSDK:18.  Yang berarti anda bisa mendapatkan banyak masalah..*
- **Apakah saya bisa mempercayai code/package?**
- *Anda dapat melihat code saya di repo dan melakukan review. Anda juga dapat melakukan fork terhadap repo saya dan mengubah sesuai keinginan anda. Paket ini memiliki lisensi MIT. Saya tidak bisa memberi anda jaminan.*  **Tapi anda perlu tahu, saya menggunakan paket ini dalam project saya.**
- **Apakah kamu akan tetap memaintain paket ini?**
- *Ya, saya sudah merencanakan ini. Tapi waktu bisa berubah.*
-**Bagaimana saya bisa membantu anda?**
-*Anda cukup dengan menggunakan paket ini*

## Terimakasih

-  [Terimakasih untuk @pradeep1991singh for react-native-secure-key-store](https://github.com/pradeep1991singh/)

## Lisensi

Project ini memiliki lisensi MIT - lihat [LICENSE.md](LICENSE.md) file untuk informasi lebih lanjut




