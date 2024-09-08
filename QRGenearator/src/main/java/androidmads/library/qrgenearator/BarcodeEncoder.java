package androidmads.library.qrgenearator;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

public class BarcodeEncoder {

    private int WHITE = 0xFFFFFFFF;
    private int BLACK = 0xFF000000;
    private int dimension = 500;  // Default size
    private String contents = null;
    private androidmads.library.qrgenearator.BarcodeFormat format = androidmads.library.qrgenearator.BarcodeFormat.CODE_128;  // Default to CODE_128

    public void setColorWhite(int color) {
        this.WHITE = color;
    }

    public void setColorBlack(int color) {
        this.BLACK = color;
    }

    public int getColorWhite() {
        return this.WHITE;
    }

    public int getColorBlack() {
        return this.BLACK;
    }

    public BarcodeEncoder(String data, androidmads.library.qrgenearator.BarcodeFormat format) {
        this.contents = data;
        this.format = format;
    }

    public BarcodeEncoder(String data, androidmads.library.qrgenearator.BarcodeFormat format, int dimension) {
        this.contents = data;
        this.format = format;
        this.dimension = dimension;
    }

    public Bitmap getBitmap() {
        return getBitmap(0);
    }

    public Bitmap getBitmap(int margin) {
        if (contents == null || contents.isEmpty()) return null;
        try {
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.MARGIN, margin);
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix result = writer.encode(contents, convertToZXingFormat(format), dimension, dimension, hints);
            int width = result.getWidth();
            int height = result.getHeight();
            int[] pixels = new int[width * height];

            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = result.get(x, y) ? getColorBlack() : getColorWhite();
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BarcodeFormat convertToZXingFormat(androidmads.library.qrgenearator.BarcodeFormat aFormat) {
        if (aFormat == null) {
            throw new IllegalArgumentException("Format cannot be null");
        }

        switch (aFormat) {
            case AZTEC:
                return BarcodeFormat.AZTEC;
            case CODABAR:
                return BarcodeFormat.CODABAR;
            case CODE_39:
                return BarcodeFormat.CODE_39;
            case CODE_93:
                return BarcodeFormat.CODE_93;
            case CODE_128:
                return BarcodeFormat.CODE_128;
            case DATA_MATRIX:
                return BarcodeFormat.DATA_MATRIX;
            case EAN_8:
                return BarcodeFormat.EAN_8;
            case EAN_13:
                return BarcodeFormat.EAN_13;
            case ITF:
                return BarcodeFormat.ITF;
            case MAXICODE:
                return BarcodeFormat.MAXICODE;
            case PDF_417:
                return BarcodeFormat.PDF_417;
            case QR_CODE:
                return BarcodeFormat.QR_CODE;
            case RSS_14:
                return BarcodeFormat.RSS_14;
            case RSS_EXPANDED:
                return BarcodeFormat.RSS_EXPANDED;
            case UPC_A:
                return BarcodeFormat.UPC_A;
            case UPC_E:
                return BarcodeFormat.UPC_E;
            case UPC_EAN_EXTENSION:
                return BarcodeFormat.UPC_EAN_EXTENSION;
            default:
                throw new IllegalArgumentException("Unsupported format: " + aFormat);
        }
    }
}
