package com.example.calmlee.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;
import java.util.UUID;

//CrimeFragment的托管Activity
public class CrimePagerActivity extends FragmentActivity{

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    public static final String EXTRA_CRIME_ID = "com.example.calmlee.criminalintent.crime_id";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //把带有ViewPager的视图显示出来
        setContentView(R.layout.activity_fragment_pager);

        //获得CrimeListActivity传递过来的数据
        Intent intent = getIntent();
        UUID crimeId = (UUID) intent.getSerializableExtra(EXTRA_CRIME_ID);

        //获得ViewPager控件
        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();

        //得到FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();

        //为mViewPager设置PagerAdapter，参数要用FragmentManager对象
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getmId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        //初始化mViewPager的位置
        for(int i=0; i<mCrimes.size(); i++){
            if(mCrimes.get(i).getmId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    //启动Activity（自身）的最佳写法
    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }

}
