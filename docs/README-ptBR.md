# RNSecureStorage

Armazenamento Seguro para React Native (Android & iOS)

*Obs: Esse pacote é uma versão melhorada de [react-native-secure-key-store](https://github.com/pradeep1991singh/react-native-secure-key-store), RNSecureStorage possui suporte para "abaixo da api 23"*

**[Veja o FAQ para mais informações.](#faq)**

### IOS

RNSecureStorage usa o Keychain para o armazenamento seguro.

### Android

Abaixo da API 23 RNSecureStorage usa [secure-preferences](https://github.com/scottyab/secure-preferences/) by [@scottyab](https://github.com/scottyab)

Acima da API 23 RNSecureStorage usa [Android Keystore](https://developer.android.com/training/articles/keystore)

## Começando

**Com o NPM**
```
npm install --save rn-secure-storage
```

**Com o YARN**
```
yarn add rn-secure-storage
```

**Link Automático**

```
react-native link rn-secure-storage
```

**Link Manual**

**[Instalação Manual](/docs/manual-installation.md)** (Se algo der errado com o link doreact-native)


## Uso


```javascript

import RNSecureStorage, { ACCESSIBLE } from 'rn-secure-storage'

```

**SET**
```javascript
// {accessible: ACCESSIBLE.WHEN_UNLOCKED} -> Para IOS
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
console.log(value) // Retorna um valor direto
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

## Opções

| Chave | Plataforma | Descrição | Padrão |
|---|---|---|---|
|**`accessible`**|apenasiOS|Diz quando um item da keychain é acessível, veja os valores possiveis em `Keychain.ACCESSIBLE`. |*`Keychain.ACCESSIBLE.WHEN_UNLOCKED`*|

### enum `Keychain.ACCESSIBLE`

| Chave | Descrição |
|-----|-------------|
|**`WHEN_UNLOCKED`**|O dado no item da keychain pode ser acessado apenas enquanto o dispositivo é desbloqueado pelo usuário.|
|**`AFTER_FIRST_UNLOCK`**|O dado no item da keychain não pode ser acessado depois de reiniciar até que o dispositivo seja desbloqueado pelo menos uma vez pelo usuário.|
|**`ALWAYS`**|O dado no item da keychain pode sempre ser acessado, independente do dispositivo estar desbloqueado ou não.|
|**`WHEN_PASSCODE_SET_THIS_DEVICE_ONLY`**|O dado no item da keychain pode ser acessado apenas quando o dispositivo é desbloqueado. Disponível apenas se uma senha é inserida no dispositivo. Itens com esse atributo não podem migrar para outro dispositivo.|
|**`WHEN_UNLOCKED_THIS_DEVICE_ONLY`**|O dado no item da keychain pode ser acessado apenas enquanto o dispositivo é desbloqueado pelo usuário. Itens com esse atributo não podem migrar para outro dispositivo.|
|**`AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY`**|O dado no item da keychain não pode ser acessado depois de reiniciar até que o dispositivo seja desbloqueado pelo menos uma vez pelo usuário. Itens com esse atributo não podem migrar para outro dispositivo.|
|**`ALWAYS_THIS_DEVICE_ONLY`**|O dado no item da keychain pode sempre ser acessado, independente do dispositivo estar desbloqueado ou não. Itens com esse atributo não podem migrar para outro dispositivo.|


### Exemplos

Você pode encontrar exemplos de uso do pacote na pasta `example`.

```console
git clone https://github.com/talut/rn-secure-package

cd rn-secure-package/example

npm install

react-native run-ios/android
```



### Versão 1.0.7
- Valores/mensagens de retorno para Android & IOS foram atualizados.
- [Issue:1](https://github.com/talut/rn-secure-storage/issues/1) resolvido.

### FAQ

- **Por que eu deveria usar esse pacote?**
- *Você pode usar outros pacotes como o `react-native-keychain`. Eu sei que este pacote possui mais opções. Mas você pode armazenar apenas nome de usuário e senha, enquanto que com o RNSecureStorage você pode guardar inúmeros itens de [chave,valor]*
- **Porque você não deveria usar o react-native-secure-key-store**
- *Você pode usar esse pacote mas você não pode ter nenhuma boa solução com as APIs mais baixas do android. E ainda por cima esse pacote é configurado como minSDK:18. Isso significa que você terá alguns problemas.*
- **Ei, eu posso confiar no seu code/package?**
- *Você pode ver meu código no repositório, então você pode avaliá-lo. E se quiser você também pode fazer um fork e alterar o quê lhe incomoda. Esse pacote possui Licença MIT, então não posso te dar nenhuma garantia.*  **Mas esteja dito que eu utilizo esse pacote nos meus projetos.**
- **Você vai manter esse pacote?**
- *Pretendo. Mas claro, as coisas podem mudar com o tempo.*
-**Como eu posso te dar apoio?**
-*Se você está usando meu pacote isso já é o suficiente para mim.*

## Agradecimentos

-  [Obrigado @pradeep1991singh pelo react-native-secure-key-store](https://github.com/pradeep1991singh/)

## Licença

Esse projeto possui Licença MIT - veja o arquivo [LICENSE.md](LICENSE.md) para mais detalhes




