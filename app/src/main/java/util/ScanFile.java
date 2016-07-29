package util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by asus on 2016/7/28.
 */
public class ScanFile {

    public interface ScanListener{
        void succeed();
        void failed();
    }

    public static void scanImageFile(final Context context , final ScanListener listener){
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            listener.failed();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> imageList = new ArrayList<String>();
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI ;
                ContentResolver contentResolver = context.getContentResolver();
                Cursor cursor = contentResolver.query(uri , null , MediaStore.Images.Media.MIME_TYPE +"=? or "+ MediaStore.Images.Media.MIME_TYPE+"=?" ,
                        new String[]{"image/jpg","image/png"} , MediaStore.Images.Media.DATE_MODIFIED );
                while (cursor.moveToNext()){
                    List<String> temporty ;
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File imageFile = new File(path).getParentFile();
                    temporty = Arrays.asList(imageFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File file, String s) {
                            if (s.endsWith(".jpg")||s.endsWith(".png")){
                                return true;
                            }
                            return false;
                        }
                    }));
                    for (int i = 0 ; i < temporty.size() ; i++){
                        imageList.add(temporty.get(i));
                    }
                    temporty.clear();
                }
                Log.d("test" , imageList.size()+"");
                listener.succeed();
                cursor.close();
            }
        }).start();
    }
}
