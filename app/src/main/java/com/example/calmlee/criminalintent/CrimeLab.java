package com.example.calmlee.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by calmlee on 2016/8/7.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;//单例对象，定义为全局，有且仅有一个
    private  List<Crime> mCrimes;

    public   List<Crime> getCrimes() {
        return mCrimes;
    }

    public  Crime getCrime(UUID id){
        for(Crime crime:mCrimes){
            if (crime.getmId().equals(id)){
                return crime;
            }
        }
        return null;
    }

    private  CrimeLab(Context context){ //私有构造函数，只能在本类中调用
        mCrimes = new ArrayList<Crime>();
        for (int i=0 ;i<100; i++){ //添加100个Crime实例
            Crime crime = new Crime();
            crime.setmTitle("Crime#"+i);
            crime.setmSolved(i%2==0);
            mCrimes.add(crime);
        }
    }


    public static CrimeLab get(Context context){  //得到单例对象，不存在则创建再返回，存在则直接返回
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
}
