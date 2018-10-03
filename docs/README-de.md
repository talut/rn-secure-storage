# RNSecureStorage

Secure Storage for React Native (Android & iOS) 

*Hinweis: Dieses Paket ist eine verbesserte Version von [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store), RNSecureStorage hat "unterhalb api 23" Unterstützung*

**[Lies die F.A.Q für weitere Informationen.](#faq)**

### IOS

RNSecureStorage verwendet Keychain für sicheres Speichern.

### Android

Unterhalb von API 23 verwendet RNSecureStorage [secure-preferences](https://github.com/scottyab/secure-preferences/) by [@scottyab](https://github.com/scottyab)

Über API 23 verwendet RNSecureStorage [Android Keystore](https://developer.android.com/training/articles/keystore)

## Los geht's

**Mit NPM**
```
npm install --save rn-secure-storage
```

**Mit YARN**
```
yarn add rn-secure-storage
```

**Automatisches linking**
```
react-native link rn-secure-storage
```

**Manuelles Linking**
**[Manuelle Installation](/docs/manual-installation.md)** (Falls etwas mit dem react-native Link schief gegagen ist)

## Anwendung

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

## Optionen

| Schlüsselwort | Plattform | Beschreibung | Standard |
|---|---|---|---|

|**`accessible`**|nur iOS |Zeigt an ob ein Element zugänglich ist, mögliche Werte findest du in `Keychain.ACCESSIBLE`. |*`Keychain.ACCESSIBLE.WHEN_UNLOCKED`*|

### `Keychain.ACCESSIBLE` enum

| Schlüsselwort | Beschreibung |
|---------------|--------------|
|**`WHEN_UNLOCKED`**|Auf die Daten im Schlüsselbundelement kann nur zugegriffen werden während das Gerät durch den Benutzer entsperrt ist.|
|**`AFTER_FIRST_UNLOCK`**|Nach einem Neustart kann auf die Daten im Schlüsselbundelement nicht zugegriffen werden, bis das Geräte einmal von Benutzer entsperrt wurde.|
|**`ALWAYS`**|Auf die Daten im Schlüsselbundelement kann immer zugegriffen werden, unabhängig davon, ob das Gerät entsperrt ist.|
|**`WHEN_PASSCODE_SET_THIS_DEVICE_ONLY`**|Auf die Daten im Schlüsselbundelement kann wenn das Gerät entsperrt ist. Nur verfügbar wenn ein Passcode im Gerät gesetzt ist. Elemente mit diesem Attribut werden nie auf ein neues Gerät migriert.|
|**`WHEN_UNLOCKED_THIS_DEVICE_ONLY`**|Auf die Daten im Schlüsselbundelement kann nur zugegriffen werden während das Gerät durch den Benutzer entsperrt ist. Elemente mit diesem Attribut werden nie auf ein neues Gerät migriert.|
|**`AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY`**|Nach einem Neustart kann auf die Daten im Schlüsselbundelement nicht zugegriffen werden, bis das Geräte einmal von Benutzer entsperrt wurde. Elemente mit diesem Attribut werden nie auf ein neues Gerät migriert.|
|**`ALWAYS_THIS_DEVICE_ONLY`**|Auf die Daten im Schlüsselbundelement kann immer zugegriffen werden, unabhängig davon, ob das Gerät entsperrt ist. Elemente mit diesem Attribut werden nie auf ein neues Gerät migriert.|


### Beispiel

Anwendungsbeispiele für das Paket findest du im ```example``` Ordner.

```console
git clone https://github.com/talut/rn-secure-package

cd rn-secure-package/example

npm install

react-native run-ios/android
```

### Version 1.0.7
- Android & IOS returing value/messages aktualisert.
- [Issue:1](https://github.com/talut/rn-secure-storage/issues/1) gelöst.

### F.A.Q

- **Warum sollte ich dieses Paket verwenden?**
- *Du kannst andere Pakete wie `react-native-keychain` verwenden, ich weiß dass dieses Paket mehr Optionen hat. Dort kannst du jedoch nur Benutzername und Passwor speichern, während RNSecureStorage das Speichern vieler [Schlüssel, Wert] Paare erlaubt*
- **Warum sollte ich nicht `react-native-secure-key-store` verwenden?**
- *Du kannst dieses Paket verwenden, wirst aber kein gutes Ergebnis mit der niedrigsten Android API bekommen. Außerdem ist dieses Paket beschränkt auf `minSDK:18`, daher wirst du wahrscheinlich auf einige Probleme treffen ...*
- **Hey, kann ich deinem Code/Paket vertrauen?**
- *Du kannst dir den gesammten Code im Repo ansehen und prüfen. Außerdem kannst du das Repo einfache forken und ändern was du willst. Dieses Paket steht unter der MIT Lizenz, damit gebe keinerlei Garantie oder Gewährleistung.* **Allerdings solltest du wissen, dass ich das Paket auch in meinen eigenen Projekten verwende.**
- **Wirst du dieses Paket pflegen?**
- *Zumindest plane ich das, aber die Zeit ändert manchmal die Dinge ...*
-**Wie kann ich dich unterstützen?**
-*Es reicht wenn du mein Paket verwendest.*

## Dank

-  [Danke an @pradeep1991singh für react-native-secure-key-store](https://github.com/pradeep1991singh/)

## Lizenz

Dieses Projekt steht unter der MIT Lizenz - siehe [LICENSE.md](LICENSE.md) Datei für Details.




