package activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.liangdekai.fragmenttest.R;

/**
 * Created by asus on 2016/7/26.
 */
public class ThreadActivity extends Activity {
    private static final int MESSAGE_UI_HANDLER = 0X11;
    private static final int MESSAGE_THREAD_HANDLER = 0X12;
    private TextView mTvThread ;
    private Handler mUiHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_UI_HANDLER :
                    mTvThread.setText("更新成功");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        mTvThread = (TextView) findViewById(R.id.thread_test);
        new ThreadLoop().start();
    }

    class ThreadLoop extends Thread{
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            Handler handler = new Handler(Looper.myLooper()){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what){
                        case MESSAGE_THREAD_HANDLER :
                            mUiHandler.sendEmptyMessageDelayed(MESSAGE_UI_HANDLER , 5000);
                            break;
                    }
                }
            };
            handler.sendEmptyMessageDelayed(MESSAGE_THREAD_HANDLER , 5000);
            Looper.loop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
