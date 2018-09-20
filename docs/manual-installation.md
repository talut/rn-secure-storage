## Manual Installation of RNSecureStorage
#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `rn-secure-storage` and add `RNSecureStorage.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNSecureStorage.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
- Add `import import com.taluttasgiran.rnsecurestorage.RNSecureStoragePackage;` to the imports at the top of the file
- Add `new RNSecureStoragePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
```
include ':rn-secure-storage'
project(':rn-secure-storage').projectDir = new File(rootProject.projectDir, '../node_modules/rn-secure-storage/android')
```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
```
implementation project(':rn-secure-storage')
```
