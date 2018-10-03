# RNSecureStorage

Beveiligde Opslag voor Native React (Android & iOS) 

*Opmerking: Deze package is een verbeterde versie van [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store), RNSecureStorage heeft ondersteuning "onder api 23"*

**[Ga naar F.A.Q voo meer informatie.](#faq)**

### IOS

RNSecureStorage gebruikt Keychain voor beveiligde opslag.

### Android

Onder API 23 gebruikt RNSecureStorage [secure-preferences](https://github.com/scottyab/secure-preferences/) door [@scottyab](https://github.com/scottyab)

Boven API 23 gebruikt RNSecureStorage [Android Keystore](https://developer.android.com/training/articles/keystore)

## Beginnen

**Met NPM**
```
npm install --save rn-secure-storage
```

**Met YARN**
```
yarn add rn-secure-storage
```

**Automatisch linken**

```
react-native link rn-secure-storage
```

**Handmatig Linken**

**[Manuale Installatie](/docs/manual-installation.md)** (Als er iets mis ging met react-native link)


## Gebruik


```javascript

import RNSecureStorage, { ACCESSIBLE } from 'rn-secure-storage'

```

**SET**
```javascript
// {accessible: ACCESSIBLE.WHEN_UNLOCKED} -> Dit is voor IOS
RNSecureStorage.set("sleutel1", "dit is een waarde", {accessible: ACCESSIBLE.WHEN_UNLOCKED})
.then((res) => {
console.log(res);
}, (err) => {
console.log(err);
});
```

**GET**
```javascript
RNSecureStorage.get("sleutel1").then((value) => {
console.log(value) // Geeft directe value terug
}).catch((err) => {
console.log(err)
})
```

**Verwijder**
```javascript
RNSecureStorage.remove("key1").then((val) => {
console.log(val)
}).catch((err) => {
console.log(err)
});
```

## Opties

| Sleutel | Platform | Beschrijving | Standaard |
|---|---|---|---|
|**`Toegankelijk`**|enkel iOS|Dit vertelt wanneer een keychain item toegankelijk is, zie mogelijke waarden in `Keychain.ACCESSIBLE`. |*`Keychain.ACCESSIBLE.WHEN_UNLOCKED`*|

### `Keychain.ACCESSIBLE` enum

| Sleutel | Beschrijving |
|-----|-------------|
|**`WHEN_UNLOCKED`**|De data in het keychain item is enkel toegankelijk wanneer het apparaat ontgrendeld is door de gebruiker.|
|**`AFTER_FIRST_UNLOCK`**|De data in het keychain item is na een reboot niet toegankelijk tot het apparaat eenmaal door de gebruiker ontgrendeld is.|
|**`ALWAYS`**|De data in het keychain item is toegankelijk ongeacht of het apparaat vergrendeld is.|
|**`WHEN_PASSCODE_SET_THIS_DEVICE_ONLY`**|De data in het keychain item is enkel toegankelijk als het apparaat ontgrendeld is. Enkel beschikbaar als er een toegangscode op het apparaat werd ingesteld. Items met dit attribuut migreren nooit naar een nieuw apparaat.|
|**`WHEN_UNLOCKED_THIS_DEVICE_ONLY`**|De data in het keychain item is enkel toegankelijk als he apparaat door de gebruiker ontrendeld is. Items met dit attribuut migreren niet naar een nieuw apparaat.|
|**`AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY`**|De data in het keychain item is niet toegankelijk na een reboot tot het apparaat eenmaal door de gebruiker ontgrendeld is. Items met dit attribuut migreren niet naar een nieuw apparaat.|
|**`ALWAYS_THIS_DEVICE_ONLY`**|De data in het keychain item is toegankelijk ongeacht of het apparaat vergrendeld is. Items met dit attribuut migreren niet naar een nieuw apparaat.|

### Example

Je kan de gebruiksvoorbeelden van de package vinden in de voorbeeld folder.

```console
git clone https://github.com/talut/rn-secure-package

cd rn-secure-package/example

npm install

react-native run-ios/android
```



### Version 1.0.7
- Android & IOS returing value/messages are updated.
- [Issue:1](https://github.com/talut/rn-secure-storage/issues/1) is solved.

### F.A.Q

- **Waarom zou ik deze package gebruiken?**
- *Je kan andere packages gebruiken zoals react-native-keychain, ik weet dat deze package meer opties heeft. Je kan er enkel de gebruikersnaam/password maar met RNSecureStorage kan je meerdere sleutel/waarde - paren opslaan.*
- **Waarom zou ik react-native-secure-key-store niet gebruiken?**
- *Je kan de package gebruiken maar je kan geen goede oplossing krijgen met de laagste API van Android. Alsook staat de package op minSDK:18 ingesteld. Dit betekent dat er problemen kunnen opduiken.*
- **Hey, kan ik je code/package vertrouwen?**
- *Je kan mijn code terugvinden in de repo dus je kan zelf een oordeel vellen. Alsook kan je mijn repo forken en zelf aanpassingen maken. Deze package is onder MIT license. Zodus kan ik je geen garantie geven.*  **MAar je moet wel weten dat ik deze package in mijn porjecten gebruik.**
- **Ga je deze package onderhouden?**
- *Ja. Maar tijden kunnen veranderen.*
-**Hoe kan ik helpen?**
-*Als je mijn package gebruikt is dat genoeg voor mij.*

## Dank

-  [Dank aan @pradeep1991singh voor react-native-secure-key-store](https://github.com/pradeep1991singh/)

## License

Dit project is erkend onder de MIT License - zie [LICENSE.md](LICENSE.md) voor details.


