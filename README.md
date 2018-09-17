
# react-native-rn-secure-storage

## Getting started

`$ npm install react-native-rn-secure-storage --save`

### Mostly automatic installation

`$ react-native link react-native-rn-secure-storage`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-rn-secure-storage` and add `RNRnSecureStorage.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNRnSecureStorage.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.taluttasgiran.rnsecurestorage.RNRnSecureStoragePackage;` to the imports at the top of the file
  - Add `new RNRnSecureStoragePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-rn-secure-storage'
  	project(':react-native-rn-secure-storage').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-rn-secure-storage/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-rn-secure-storage')
  	```


## Usage
```javascript
import RNRnSecureStorage from 'react-native-rn-secure-storage';

// TODO: What to do with the module?
RNRnSecureStorage;
```
  