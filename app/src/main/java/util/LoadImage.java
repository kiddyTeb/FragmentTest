package util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by asus on 2016/7/25.
 */
public class LoadImage {
    public interface Listener{
        void succed(final Bitmap bitmap);
    }

    //http://img5.duitang.com/uploads/item/201507/14/20150714175013_UZ8VF.jpeg
    public static void loadImage(final String address , final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap =null ;
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);
                    InputStream inputStream = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    listener.succed(bitmap);
                }catch (ProtocolException e) {
                    e.printStackTrace();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}
