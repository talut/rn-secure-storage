# RNSecureStorage

Stockage sécurisé pour React Native (Android & iOS) 

*Note: Ce projet est une amélioration de [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store), RNSecureStorage fonctionne "sous l'api 23"*

**[Voir la FAQ pour plus d'informations.](#faq)**

### IOS

RNSecureStorage utilise le Trousseau pour le stockage sécurisé.

### Android

En dessous de l'API 23, RNSecureStorage utilise [secure-preferences](https://github.com/scottyab/secure-preferences/) par [@scottyab](https://github.com/scottyab)

Au-dessus de l'API 23 RNSecureStorage utilise [Android Keystore](https://developer.android.com/training/articles/keystore)

## Commencer

**Avec NPM**
```
npm install --save rn-secure-storage
```

**Avec YARN**
```
yarn add rn-secure-storage
```

**Lien automatique**

```
react-native link rn-secure-storage
```

**Lien Manuel**

**[Installation Manuel](/docs/manual-installation.md)** (Si quelquechose ne fonctionne pas avec react-native link)


## Utilisation


```javascript

import RNSecureStorage, { ACCESSIBLE } from 'rn-secure-storage'

```

**SET**
```javascript
// {accessible: ACCESSIBLE.WHEN_UNLOCKED} -> seulement si iOS
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
console.log(value) // Renvoie la valeur directe
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

## Options

| Clé | Platforme | Description | Valeur par défaut |
|---|---|---|---|
|**`accessible`**|iOS uniq.| Cela dit quand un élément trousseau est accessible, voir les valeurs possibles dans `Keychain.ACCESSIBLE`. |*`Keychain.ACCESSIBLE.WHEN_UNLOCKED`*|

### `Keychain.ACCESSIBLE` enum

| Clé | Description |
|-----|-------------|
|**`WHEN_UNLOCKED`**|Les données de l'élément de trousseau ne sont accessibles que lorsque l'utilisateur déverrouille l'appareil.|
|**`AFTER_FIRST_UNLOCK`**|L'accès aux données de l'élément de trousseau est impossible après un redémarrage tant que l'utilisateur n'a pas déverrouillé l'appareil.|
|**`ALWAYS`**|Les données de l'élément de trousseau sont toujours accessibles, que le périphérique soit verrouillé ou non..|
|**`WHEN_PASSCODE_SET_THIS_DEVICE_ONLY`**|Les données du trousseau ne sont accessibles que lorsque le périphérique est déverrouillé. Disponible uniquement si un code d'authentification est défini sur le périphérique. Les éléments avec cet attribut ne migrent jamais vers un nouveau périphérique.|
|**`WHEN_UNLOCKED_THIS_DEVICE_ONLY`**|Les données de l'élément de trousseau sont accessibles uniquement lorsque l'utilisateur déverrouille l'appareil. Les éléments avec cet attribut ne migrent pas vers un nouveau périphérique.|
|**`AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY`**|Il est impossible d’accéder aux données du trousseau après un redémarrage tant que l’utilisateur n’a pas déverrouillé le périphérique. Les éléments avec cet attribut ne migrent jamais vers un nouveau périphérique.|
|**`ALWAYS_THIS_DEVICE_ONLY`**|Les données de l'élément de trousseau sont toujours accessibles, que le périphérique soit verrouillé ou non. Les éléments avec cet attribut ne migrent jamais vers un nouveau périphérique.|


### Exemples

Vous pouvez trouver des exemples d'utilisation dans le dossier `example` du projet. 

```console
git clone https://github.com/talut/rn-secure-package

cd rn-secure-package/example

npm install

react-native run-ios/android
```



### Version 1.0.7
- Android & IOS returing value/messages are updated.
- [Issue:1](https://github.com/talut/rn-secure-storage/issues/1) is solved.

### FAQ

- **Pourquoi devrais-je utiliser ce paquet ?**
- *Vous pouvez utiliser d'autres paquets comme `react-native-keychain`. Mais je sais que ce paquet a plus d'options : Avec react-native-keychain, vous ne pouvez stocker que l'utilisateur, mot de passe alors qu'avec ce paquet vous pouvez stocker beaucoup de [clé,valeur].*
- **Pourquoi ne devrais-je pas utiliser react-native-secure-key-store**
- *Vous pouvez utiliser ce paquet, mais vous n'aurez aucun support avec les anciennes version d'Android. De plus, ce paquet est configuré minSDK: 18. Cela signifie que vous allez avoir quelques problèmes.*
- **Hé, je peux avoir confiance dans votre code/paquet ?**
- *Vous pouvez consulter le code dans ce projet afin que vous le puissiez le vérifier. De plus, si vous le souhaitez, vous pouvez facilement accéder à mon dépôt et modifier ce qui vous dérange. Ce paquet est sous licence MIT. Je ne peux donc vous donner aucune garantie. * ** Mais sachez que j'utilise ce package dans mes projets.**
- **Vas-tu maintenir ce paquet ?**
- *Oui, mais vous savez que le temps peut tout changer.*
-**Comment puis-je vous soutenir?**
-*Si vous utilisez mon paquet, ça me suffit*

## Remerciement

-  [Merci à @pradeep1991singh pour react-native-secure-key-store](https://github.com/pradeep1991singh/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details




