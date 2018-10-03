# RNSecureStorage

Penyimpanan aman untuk React Native (Android & iOS)

*Catatan: Paket ini adalah versi yang lebih ditingkatkan dari [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store), RNSecureStorage memiliki dukungan "di bawah api 23" 

**[Pergi ke F.A.Q untuk informasi lebih lanjut.](#faq)**

### IOS

RNSecureStorage menggunakan Keychain untuk penyimpanan yang lebih aman.

### Android

Di bawah API 23 RNSecureStorage menggunakan [secure-preferences](https://github.com/scottyab/secure-preferences/) oleh [@scottyab](https://github.com/scottyab)

Di atas API 23 RNSecureStorage menggunakan [Android Keystore](https://developer.android.com/training/articles/keystore)

## Memulai

**Dengan NPM**
```
npm install --save rn-secure-storage
```

**Dengan YARN**
```
yarn add rn-secure-storage
```

**Tautan Otomatis**

```
react-native link rn-secure-storage
```

**Tautan Manual**

**[Instalasi Manual](/docs/manual-installation.md)** (Jika ada yang salah dengan tautan react-native)

## Penggunaan


```javascript

import RNSecureStorage, { ACCESSIBLE } from 'rn-secure-storage'

```

**SET**
```javascript
// {accessible: ACCESSIBLE.WHEN_UNLOCKED} -> Ini untuk IOS
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

| Kunci | Platform | Deskripsi | Default |
|---|---|---|---|
|**`accessible`**|hanya iOS|Ini menentukan kapan item keychain dapat diakses, melihat nilai yang mungkin di `Keychain.ACCESSIBLE`. |*`Keychain.ACCESSIBLE.WHEN_UNLOCKED`*|

### `Keychain.ACCESSIBLE` enum

| Kunci | Deskripsi |
|-----|-------------|
|**`WHEN_UNLOCKED`**|Data dalam item keychain hanya dapat diakses saat perangkat dibuka kuncinya oleh pengguna.|
|**`AFTER_FIRST_UNLOCK`**|Data dalam item keychain tidak dapat diakses setelah restart sampai perangkat telah dibuka satu kali oleh pengguna.|
|**`ALWAYS`**|Data dalam item keychain selalu dapat diakses terlepas dari apakah perangkat terkunci.|
|**`WHEN_PASSCODE_SET_THIS_DEVICE_ONLY`**|Data dalam keychain hanya dapat diakses ketika perangkat dibuka kuncinya. Hanya tersedia jika kode sandi diatur pada perangkat. Item dengan atribut ini tidak pernah bermigrasi ke perangkat baru.|
|**`WHEN_UNLOCKED_THIS_DEVICE_ONLY`**|Data dalam item keychain hanya dapat diakses saat perangkat dibuka kuncinya oleh pengguna. Item dengan atribut ini tidak bermigrasi ke perangkat baru.|
|**`AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY`**|Data dalam item keychain tidak dapat diakses setelah restart sampai perangkat telah dibuka satu kali oleh pengguna. Item dengan atribut ini tidak pernah bermigrasi ke perangkat baru.|
|**`ALWAYS_THIS_DEVICE_ONLY`**|Data dalam item keychain selalu dapat diakses terlepas dari apakah perangkat terkunci. Item dengan atribut ini tidak pernah bermigrasi ke perangkat baru.|


### Contoh

Anda bisa menemukan contoh penggunaan dari paket di folder example

```console
git clone https://github.com/talut/rn-secure-package

cd rn-secure-package/example

npm install

react-native run-ios/android
```



### Versi 1.0.7
- Android & IOS returing value/messages are updated.
- [Issue:1](https://github.com/talut/rn-secure-storage/issues/1) terpecahkan

### F.A.Q

- **Mengapa saya harus menggunakan paket ini?**
- *Anda dapat menggunakan paket lain seperti react-native-keychain. Saya tahu paket itu memiliki lebih banyak opsi. Tetapi Anda hanya dapat menyimpan nama pengguna, kata sandi tetapi Anda dapat menyimpan banyak [kunci, nilai] dengan RNSecureStorage*
- **Mengapa seharusnya saya tidak menggunakan react-native-secure-key-store**
- *Anda dapat menggunakan paket itu tetapi Anda tidak bisa mendapatkan solusi yang bagus dengan API Android terendah. Paket itu juga sudah diisi minSDK: 18. Itu berarti Anda akan mengalami beberapa masalah.*
- **Hai, bisakah saya mempercayai kode/paket Anda?**
- *Anda dapat melihat kode saya di repo sehingga Anda dapat meninjau kode saya. Juga jika Anda mau, Anda dapat dengan mudah dapat membayar repo saya dan mengubah apa yang mengganggu Anda. Paket ini berada di bawah lisensi MIT. Jadi saya tidak bisa memberi Anda garansi apa pun.* **Tetapi Anda harus tahu, saya menggunakan paket ini dalam proyek-proyek saya.**
- **Apakah Anda akan mempertahankan paket ini?**
- *Ya saya akan merencanakan ini. Tetapi Anda tahu waktu dapat mengubah segalanya.*
-**Bagaimana saya bisa mendukungmu?**
-*Jika Anda menggunakan paket saya itu sudah cukup bagi saya.*

## Terimakasih

-  [Terimakasih untukmu @pradeep1991singh untuk react-native-secure-key-store](https://github.com/pradeep1991singh/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
