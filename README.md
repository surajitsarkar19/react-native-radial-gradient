
# react-native-radial-gradient

## Getting started

`$ npm install react-native-radial-gradient --save`

### Mostly automatic installation

`$ react-native link react-native-radial-gradient`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-radial-gradient` and add `RNRadialGradient.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNRadialGradient.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.surajit.rnrg.RNRadialGradientPackage;` to the imports at the top of the file
  - Add `new RNRadialGradientPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-radial-gradient'
  	project(':react-native-radial-gradient').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-radial-gradient/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-radial-gradient')
  	```


## Usage
```javascript
import RNRadialGradient from 'react-native-radial-gradient';

// TODO: What to do with the module?
RNRadialGradient;
```
  