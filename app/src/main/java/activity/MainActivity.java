package activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liangdekai.fragmenttest.R;

import java.util.ArrayList;
import java.util.List;

import fragment.FirstFragment;
import fragment.SecondFragment;
import fragment.ThirdFragment;

/**
 * Created by asus on 2016/7/18.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private ViewPager mViewPager ;
    private FragmentPagerAdapter mAdapter ;
    private List<Fragment> mFragments = new ArrayList<>();

    private LinearLayout mTabFirst ;
    private LinearLayout mTabSecond ;
    private LinearLayout mTabThird ;

    private ImageView mImageOne ;
    private ImageView mImageTWO ;
    private ImageView mImageThree ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
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
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int currentIndex;
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
                currentIndex = position ;
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
        mImageTWO = (ImageView) findViewById(R.id.bottom_image_two);
        mImageThree = (ImageView) findViewById(R.id.bottom_image_three);

        mTabFirst.setOnClickListener(this);
        mTabSecond.setOnClickListener(this);
        mTabThird.setOnClickListener(this);

        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();

        mFragments.add(firstFragment);
        mFragments.add(secondFragment);
        mFragments.add(thirdFragment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tab_bottom_one :
                
        }
    }
}
