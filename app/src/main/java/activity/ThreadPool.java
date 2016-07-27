package activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.liangdekai.fragmenttest.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import util.PriorityRunnable;

/**
 * Created by asus on 2016/7/27.
 */
public class ThreadPool extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);

        //testFixedThreadPool();
        //testSingleThreadPool();
        //testCachedThreadPool();
        //testScheduledThreadPool();
        testMyThreadPool();
    }

    private void testMyThreadPool(){
        ExecutorService executorService = new ThreadPoolExecutor(2 , 2 , 0L , TimeUnit.SECONDS , new PriorityBlockingQueue<Runnable>());
        for (int i = 0 ; i< 10 ; i++){
            final int priority = i ;
            executorService.execute(new PriorityRunnable(priority) {
                @Override
                public void doWork() {
                    String ThreadName = Thread.currentThread().getName();
                    Log.d("test" , "线程："+ThreadName + "正在执行,优先级为"+priority);
                }
            });
        }
    }

    private void testFixedThreadPool(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0 ; i <12 ; i++){
            final int index = i ;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String ThreadName = Thread.currentThread().getName();
                    Log.d("test" , "线程："+ThreadName + "正在执行第"+index+"任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void testSingleThreadPool(){
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0 ; i <12 ; i++){
            final int index = i ;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String ThreadName = Thread.currentThread().getName();
                    Log.d("test" , "线程："+ThreadName + "正在执行第"+index+"任务");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void testCachedThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0 ; i <12 ; i++){
            final int index = i ;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String ThreadName = Thread.currentThread().getName();
                    Log.d("test" , "线程："+ThreadName + "正在执行第"+index+"任务");
                    try {
                        long time = index*500;
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    private void testScheduledThreadPool(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        //延迟两秒执行该任务
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {

            }
        } , 2 , TimeUnit.SECONDS);
        //延迟一秒执行，每隔两秒执行
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String ThreadName = Thread.currentThread().getName();
                Log.d("test" , "线程："+ThreadName + "正在执行任务");
            }
        }, 1 , 2 , TimeUnit.SECONDS);
    }
}
