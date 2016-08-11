package util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Bitmap的缓存类
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private static final int MAX_SIZE = (int) (Runtime.getRuntime().maxMemory());
    private LruCache<String , Bitmap> mLruCache ;

    public BitmapCache(){
        mLruCache = new LruCache<String, Bitmap>(MAX_SIZE){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mLruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mLruCache.put(url , bitmap) ;
    }
}
