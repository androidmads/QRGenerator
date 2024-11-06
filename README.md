# QRGenerator
QR Generator Library and Saves the QR Code as Image

### Featured In:
[![](https://jitpack.io/v/androidmads/QRGenerator.svg?style=for-the-badge)](https://jitpack.io/#androidmads/QRGenerator)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-QR%20Generator-green.svg?style=for-the-badge)](https://android-arsenal.com/details/1/3890)

[![CSharpCorner](https://img.shields.io/badge/C%23-Corner-blue.svg?style=for-the-badge)](https://www.c-sharpcorner.com/article/how-to-generate-qr-code-in-android/)

[![StackOverflow](https://img.shields.io/badge/stack%20overflow-FE7A16?logo=stack-overflow&logoColor=white&style=for-the-badge)](https://rb.gy/vol1bm)

[![Androidmads](https://img.shields.io/badge/Androidmads-Blog-09BBB2?style=for-the-badge)](https://www.androidmads.info/2018/07/how-to-generate-qr-code-in-android.html)

### How to Import the Library:
Add it in your root build.gradle at the end of repositories:

<b>App-level build.gradle:</b>
``` groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
<b>Module-level build.gradle:</b>
```groovy
dependencies {
  implementation 'com.github.androidmads:QRGenerator:1.0.5'
}
```
<b>settings.gradle:</b>
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
```
Activity:
```
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
```

### Features:
1. QR code color can be changed dynamically
2. Android X support is included
3. Minimum support from version 14 is included
4. Margin of the QR code can be controlled

### Permission:
Add This Permission for saving your generated code
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
### How to use this Library:
After importing this library, use the following lines to use this library.
The following lines are used to generated the QR Code
```java
// Initializing the QR Encoder with your value to be encoded, type you required and Dimension
QRGEncoder qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);
qrgEncoder.setColorBlack(Color.RED);
qrgEncoder.setColorWhite(Color.BLUE);
try {
  // Getting QR-Code as Bitmap
  bitmap = qrgEncoder.getBitmap();
  // Setting Bitmap to ImageView
  qrImage.setImageBitmap(bitmap);
} catch (WriterException e) {
  Log.v(TAG, e.toString());
}
```
The following lines are used to generated the QR Code without margin/default border
```java
// Initializing the QR Encoder with your value to be encoded, type you required and Dimension
QRGEncoder qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);
qrgEncoder.setColorBlack(Color.RED);
qrgEncoder.setColorWhite(Color.BLUE);
try {
  // Getting QR-Code as Bitmap
  bitmap = qrgEncoder.getBitmap(0);
  // Setting Bitmap to ImageView
  qrImage.setImageBitmap(bitmap);
} catch (WriterException e) {
  Log.v(TAG, e.toString());
}
```
The following lines are used to generated the Bar Code
```java
BarcodeEncoder barcodeEncoder = new BarcodeEncoder(inputValue, BarcodeFormat.CODE_128, 800);
bitmap = barcodeEncoder.getBitmap(2);  // Margin of 2 pixels
// Now you can use this bitmap as needed, e.g., display it in an ImageView
qrImage.setImageBitmap(bitmap);
```

Save QR Code as Image 
```java
// Save with location, value, bitmap returned and type of Image(JPG/PNG).
QRGSaver qrgSaver = new QRGSaver();
qrgSaver.save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
```

For more Details [Click Here](https://github.com/androidmads/QRGenerator/blob/master/app/src/main/java/androidmads/example/MainActivity.java)

<a href="https://www.buymeacoffee.com/androidmads"><img src="https://img.buymeacoffee.com/button-api/?text=Buy me a coffee&emoji=&slug=androidmads&button_colour=40DCA5&font_colour=ffffff&font_family=Lato&outline_colour=000000&coffee_colour=FFDD00" /></a>

# License:
```
The MIT License (MIT)

Copyright (c) 2016 AndroidMad / Mushtaq M A

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
