package util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by asus on 2016/7/25.
 */
public class BitmapTest {
    public static int calculate(BitmapFactory.Options options , int reqWidth , int reqHeigh){
        final int width = options.outWidth;
        final int heigh = options.outHeight;
        Log.d("test" , width + "/"+heigh);
        int inSampleSize = 1 ;
        if (reqHeigh < heigh || reqWidth < width){
            final int heighRatio = (Math.round((float)heigh / (float)reqHeigh));
            final int widthRatio = (Math.round((float)width / (float)reqWidth));
            inSampleSize = heighRatio>widthRatio? widthRatio : heighRatio ;
        }
        Log.d("test" , inSampleSize+"");
        return inSampleSize;
    }

    public static Bitmap decodeBitmap(Resources resources , int resId , int reqWidth , int reqHeigh){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true ;
        BitmapFactory.decodeResource(resources , resId ,options);
        options.inSampleSize = calculate(options , reqWidth , reqHeigh);
        options.inJustDecodeBounds = false ;
        return BitmapFactory.decodeResource(resources , resId ,options);
    }
}
