package com.example.calmlee.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by calmlee on 2016/8/7.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    public Fragment creatFragment() {
        return new CrimeListFragment();
    }
}
