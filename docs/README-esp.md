# RNSecureStorage

Almacenamiento seguro para React Native (Android y iOS)

Nota: este paquete es una versión más mejorada de [react-native-secure-key-store], RNSecureStorage tiene soporte "bajo api 23"

**[Ir a F.A.Q para más información.]**

¿No es tu idioma principal? Consulta las traducciones aquí.

##IOS

RNSecureStorage utiliza un llavero para un almacenamiento seguro.

##Androide

Bajo API 23, RNSecureStorage usa las [preferencias seguras] de [@scottyab]

Por encima de API 23, RNSecureStorage está utilizando el almacén de [claves de Android]

##Empezando

**Con NPM**

npm install --save rn-secure-storage

**Con YARN**

yarn add rn-secure-storage

**Vinculación automática**

react-native link rn-secure-storage

**Enlace manual**
**Instalación manual (si algo salió mal con el enlace de reacción nativa)**

##Uso

import RNSecureStorage, {Accessible} from 'rn-Secure-Storage'

**CONJUNTO**

// {accesible: ACCESSIBLE.WHEN_UNLOCKED} -> Esto para IOS
RNSecureStorage.set ("key1", "this is a value", {accesible: ACCESSIBLE.WHEN_UNLOCKED})
.then ((res) => {
console.log (res);
}, (err) => {
console.log (err);
});

**OBTENER**

RNSecureStorage.get ("key1").then((value) => {
console.log (value) // Devolverá el valor directo
}). catch ((err) => {
console.log (err)
})

**RETIRAR**

RNSecureStorage.remove ("key1").then((val) => {
console.log (val)
}). catch ((err) => {
console.log (err)
});

##Opciones
|Clave |Plataforma |Descripción |Predeterminado

solo iOS accesible Esto indica que cuando se puede acceder a un elemento de llavero, vea los valores posibles en Keychain.ACCESSIBLE. Llavero.ACCESIBLE.CONTRECHA
##Llavero. ACCESIBLE enumeración
Descripción clave
**WHEN_UNLOCKED** Solo se puede acceder a los datos en el elemento del llavero mientras el usuario desbloquea el dispositivo.
**AFTER_FIRST_UNLOCK** No se puede acceder a los datos en el elemento del llavero después de un reinicio hasta que el dispositivo haya sido desbloqueado una vez por el usuario.
SIEMPRE Se puede acceder a los datos en el elemento del llavero, independientemente de si el dispositivo está bloqueado.
**WHEN_PASSCODE_SET_THIS_DEVICE_ONLY** Solo se puede acceder a los datos en el llavero cuando el dispositivo está desbloqueado. Solo disponible si se establece un código de acceso en el dispositivo. Los elementos con este atributo nunca migran a un nuevo dispositivo.
**WHEN_UNLOCKED_THIS_DEVICE_ONLY** Solo se puede acceder a los datos en el elemento del llavero mientras el usuario desbloquea el dispositivo. Los elementos con este atributo no migran a un nuevo dispositivo.
**AFTER_FIRST_UNLOCK_THIS_DEVICE_ONLY** No se puede acceder a los datos en el elemento del llavero después de un reinicio hasta que el dispositivo haya desbloqueado el dispositivo una vez. Los elementos con este atributo nunca migran a un nuevo dispositivo.
**ALWAYS_THIS_DEVICE_ONLY** Siempre se puede acceder a los datos en el elemento del llavero, independientemente de si el dispositivo está bloqueado. Los elementos con este atributo nunca migran a un nuevo dispositivo.d

##Ejemplo

Puede encontrar el ejemplo de uso del paquete en la carpeta de ejemplos.

git clone https://github.com/talut/rn-secure-package

cd rn-secure-package / example

npm install

react-native run-ios/andriod

##Versión 1.0.7

    Android / IOS returing valor / mensajes se actualizan.
    [Problema: 1] está resuelto.

##Traducciones

    Francés por @Vinetos
    Indonesia por @mfaridzia
    Alemán por @msdeibel

PREGUNTAS MÁS FRECUENTES

   **¿Por qué debería usar este paquete?**
    Puede usar otros paquetes como react-native-keychain. Sé que el paquete tiene más opciones. Pero solo puede almacenar el nombre de usuario y la contraseña, mientras que con RNSecureStorage puede almacenar muchos pares de [clave, valor]
    **¿Por qué no debería usar react-native-secure-key-store?**
    Puede usar ese paquete pero no puede obtener ninguna buena solución con la API más baja de Android. También ese paquete se establece en minSDK: 18. Eso significa que puedes encontrar algunos problemas ...
    **Oye, ¿puedo confiar en tu código / paquete?**
    Puedes ver todo mi código en el repositorio y puedes revisarlo. Además, si lo deseas, puedes fácilmente bifurcar mi repo y cambiar lo que te molesta. Este paquete está bajo licencia MIT. Así que no puedo darte ninguna garantía. Pero debes saber, estoy usando este paquete en mis proyectos.
    **¿Mantendrás este paquete?**
    Sí, estoy planeando hacerlo. Pero sabes que el tiempo puede cambiarlo todo. -¿Cómo puedo apoyarte? -Si estas usando mi paquete es suficiente para mi

##Gracias

    Gracias a usted [@pradeep1991singh] por reaccionar-nativo-seguro-clave-tienda

##Licencia

Este proyecto está licenciado bajo la Licencia MIT - vea el archivo LICENSE.md para más detalles
