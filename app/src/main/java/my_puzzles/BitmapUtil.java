package my_puzzles;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 *
 *
 * @author Ismail, Qadar and Siyad
 */

public class BitmapUtil {
    public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }
}
