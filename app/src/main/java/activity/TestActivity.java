package activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liangdekai.fragmenttest.R;

import util.BitmapTest;

/**
 * Created by asus on 2016/7/25.
 */
public class TestActivity extends Activity implements View.OnClickListener{
    private Button mBtload ;
    private ImageView mIvImage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitmap);
        int max = (int) (Runtime.getRuntime().maxMemory()/1024/1024);
        Log.d("test" , max+"kb");
        init();
    }

    private void init(){
        mBtload = (Button) findViewById(R.id.main_load);
        mIvImage = (ImageView) findViewById(R.id.main_image);
        mBtload.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_load :
                //mIvImage.setImageBitmap(BitmapTest.decodeBitmap(getResources() , R.drawable.first , 100 , 100));
                Bitmap bitmap = BitmapTest.decodeBitmap(getResources() , R.drawable.first , 100 , 100);
                Log.d("test" , bitmap.getByteCount()/1024+"kb-bitmap");
                mIvImage.setImageBitmap(bitmap);
                break;
        }
    }
}
