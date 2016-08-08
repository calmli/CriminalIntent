package com.example.calmlee.criminalintent;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

//定义一个抽象类，避免CrimeListActivity和CrimeActivity中代码重复
public abstract class SingleFragmentActivity extends FragmentActivity {

    public abstract Fragment creatFragment();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //获得FragmentManager对象
        FragmentManager manager = this.getSupportFragmentManager();

        //从FragmentManager中获得容器视图资源Id为R.id.fragment_container的Fragment
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        //如果为空，则创建
        if(fragment == null){
            fragment = creatFragment();
            /*
                1,manager.beginTransaction()返回一个FragmentTransaction队列实例
                2,添加事务并提交
             */
            manager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
}
