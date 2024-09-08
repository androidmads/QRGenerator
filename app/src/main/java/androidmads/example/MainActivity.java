package androidmads.example;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import androidmads.library.qrgenearator.BarcodeEncoder;
import androidmads.library.qrgenearator.BarcodeFormat;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class MainActivity extends AppCompatActivity {

    private EditText edtValue;
    private ImageView qrImage;
    private String inputValue;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private AppCompatActivity activity;
    private EditText mColorPreviewWhite, mColorPreviewBlack;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrImage = findViewById(R.id.qr_image);
        edtValue = findViewById(R.id.edt_value);
        mColorPreviewWhite = findViewById(R.id.preview_selected_firstcolor);
        mColorPreviewBlack = findViewById(R.id.preview_selected_secondcolor);

        activity = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/QRCode/";
        }

        findViewById(R.id.generate_barcode).setOnClickListener(view -> {
            inputValue = edtValue.getText().toString().trim();
            if (inputValue.length() > 0) {
                try {

                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder(inputValue,
                            BarcodeFormat.CODE_128, 800);
                    bitmap = barcodeEncoder.getBitmap(2);  // Margin of 2 pixels

// Now you can use this bitmap as needed, e.g., display it in an ImageView

                    qrImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                edtValue.setError(getResources().getString(R.string.value_required));
            }
        });


        findViewById(R.id.generate_qrcode).setOnClickListener(view -> {
            inputValue = edtValue.getText().toString().trim();
            if (inputValue.length() > 0) {
                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                int smallerDimension = Math.min(width, height);
                smallerDimension = smallerDimension * 3 / 4;

                qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);
                qrgEncoder.setColorBlack(Color.parseColor(mColorPreviewBlack.getText().toString()));
                qrgEncoder.setColorWhite(Color.parseColor(mColorPreviewWhite.getText().toString()));
                try {
                    bitmap = qrgEncoder.getBitmap();
                    qrImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                edtValue.setError(getResources().getString(R.string.value_required));
            }
        });

        findViewById(R.id.save_barcode).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                try {
                    boolean save = new QRGSaver().save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    String result = save ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
                    edtValue.setText(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        });

    }

    public void goto_CQBarcode(View view) {
        startActivity(new Intent(getApplicationContext(), GenDataActivity.class));
    }
}