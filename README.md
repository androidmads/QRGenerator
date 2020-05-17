# QRGenerator
QR Generator Library and Saves the QR Code as Image

### Featured In:
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-QRGenerator-green.svg?style=true)](https://android-arsenal.com/details/1/3890)
### How to Import the Library:
<b>Gradle:</b>
```groovy
implementation 'androidmads.library.qrgenearator:QRGenearator:1.0.4'
implementation 'com.google.zxing:core:3.3.0'
```

### Whats New in 1.0.4:
1. QR code color can be changed dynamically
2. Android X support is included
3. Minimum support from version 14 is included

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

// Getting QR-Code as Bitmap
bitmap = qrgEncoder.getBitmap();
// Setting Bitmap to ImageView
qrImage.setImageBitmap(bitmap);
```

Save QR Code as Image 
```java
// Save with location, value, bitmap returned and type of Image(JPG/PNG).
QRGSaver qrgSaver = new QRGSaver();
qrgSaver.save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
```

For more Details [Click Here](https://github.com/androidmads/QRGenerator/blob/master/app/src/main/java/androidmads/example/MainActivity.java)

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
