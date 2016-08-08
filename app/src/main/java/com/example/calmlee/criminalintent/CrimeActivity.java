package com.example.calmlee.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    //从CrimeListFragment传过来的键值
    public static final String EXTRA_CRIME_ID = "com.example.calmlee.criminalintent.crime_id";

    //重写SingleFragment中的方法
    @Override
    public Fragment creatFragment() {
        //取出从CrimeListFragment传过来的Id
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        //返回一个CrimeFragment实例，并把Id号作为参数传给CrimeFragment，让CrimeFragment构建自身的arguement
        return CrimeFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext,UUID crimeId){
        Intent intent = new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }
}
