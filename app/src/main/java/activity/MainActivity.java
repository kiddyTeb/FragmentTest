package activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.liangdekai.fragmenttest.R;

import java.util.ArrayList;
import java.util.List;

import fragment.FirstFragment;
import fragment.SecondFragment;
import fragment.ThirdFragment;

/**
 * Created by asus on 2016/7/18.
 */
public class MainActivity extends FragmentActivity{
    private ViewPager mViewPager ;
    private FragmentPagerAdapter mAdapter ;
    private List<Fragment> mFragments = new ArrayList<>();

    private LinearLayout mTabFirst ;
    private LinearLayout mTabSecond ;
    private LinearLayout mTabThird ;

    private ImageView mImageOne ;
    private ImageView mImageTWO ;
    private ImageView mImageThree ;

    private FirstFragment mFirstFragment ;
    private SecondFragment mSecondFragment ;
    private ThirdFragment mThirdFragment ;
    private FragmentManager mFragmentManager ;

    private final String[] data = new String[] {"选项一","选项二" ,"选项三" ,"选项四" ,"选项五" , "选项六" };
    private ArrayAdapter<String> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.draw_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        initView();
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                restore();
                switch (position){
                    case 0 :
                        mImageOne.setImageResource(R.mipmap.ynews);
                        break;
                    case 1 :
                        mImageTWO.setImageResource(R.mipmap.ypeople);
                        break;
                    case 2 :
                        mImageThree.setImageResource(R.mipmap.ysetting);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void restore(){
        mImageOne.setImageDrawable(getResources().getDrawable(R.mipmap.news));
        mImageTWO.setImageResource(R.mipmap.people);
        mImageThree.setImageResource(R.mipmap.setting);
    }

    public void initView(){
        mTabFirst = (LinearLayout) findViewById(R.id.tab_bottom_one);
        mTabSecond = (LinearLayout) findViewById(R.id.tab_bottom_two);
        mTabThird = (LinearLayout) findViewById(R.id.tab_bottom_three);

        mImageOne = (ImageView) findViewById(R.id.bottom_image_one);
        mImageOne.setImageResource(R.mipmap.ynews);
        mImageTWO = (ImageView) findViewById(R.id.bottom_image_two);
        mImageThree = (ImageView) findViewById(R.id.bottom_image_three);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.draw);
        //drawerLayout.addDrawerListener();
        mFragmentManager = getSupportFragmentManager();
        adapter = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1 ,data);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                restore();
                switch (i){
                    case 0 :
                        Toast.makeText(MainActivity.this ,"click" , Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawers();
                        change(0);
                        //mImageOne.setImageResource(R.mipmap.ynews);
                        break;
                }
            }
        });
        /*mTabFirst.setOnClickListener(this);
        mTabSecond.setOnClickListener(this);
        mTabThird.setOnClickListener(this);*/

        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();

        mFragments.add(mFirstFragment);
        mFragments.add(mSecondFragment);
        mFragments.add(mThirdFragment);
    }



   /* @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tab_bottom_one :
                change(0);
                break;
            case R.id.tab_bottom_two :
                change(1);
                break;
            case R.id.tab_bottom_three :
                change(2);
                break;
        }
    }*/

    public void change(int position){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        //hideFragment(fragmentTransaction);
        switch (position){
            case 0:
                mImageOne.setImageResource(R.mipmap.ynews);
                if (mFirstFragment == null){
                    mFirstFragment = new FirstFragment();
                    mFragments.add(mFirstFragment);
                }else {
                    /*//fragmentTransaction.show(mFirstFragment);
                    fragmentTransaction.replace(R.id.viewpager , mFirstFragment);
                    fragmentTransaction.show(mFirstFragment);
                    fragmentTransaction.addToBackStack(null);*/
                    mViewPager.setCurrentItem(0);
                }
                break;
            case 1:
                mImageTWO.setImageResource(R.mipmap.ypeople);
                if (mSecondFragment == null){
                    mSecondFragment = new SecondFragment();
                    fragmentTransaction.add(R.id.viewpager , mSecondFragment);
                }else {
                    mViewPager.setCurrentItem(1);
                }
                break;
            case 2 :
                mImageThree.setImageResource(R.mipmap.ysetting);
                if (mThirdFragment == null){
                    mThirdFragment = new ThirdFragment();
                    fragmentTransaction.add(R.id.viewpager ,mThirdFragment);
                }else {
                    fragmentTransaction.show(mThirdFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    /*public void hideFragment(FragmentTransaction fragmentTransaction){
        if (mFirstFragment != null){
            fragmentTransaction.hide(mFirstFragment);
        }
        if (mSecondFragment != null){
            fragmentTransaction.hide(mSecondFragment);
        }
        if (mThirdFragment != null){
            fragmentTransaction.hide(mThirdFragment);
        }
    }*/
}
