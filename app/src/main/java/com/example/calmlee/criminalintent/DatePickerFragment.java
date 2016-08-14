package com.example.calmlee.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment{
    private static final String ARGS_CRIME_DATE = "com.example.calmlee.criminalintent.crime_date";
    private DatePicker mDatePicker;
    public static final String EXTRA_DATE = "com.example.calmlee.criminalintent.data";
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //获得CrimeFragment中传过来的值
        Date date = (Date) getArguments().getSerializable(ARGS_CRIME_DATE);

        //要将Date的值封装在DatePicker中必须要用Calendar对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //将xml文件转化成View对象
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null,false);

        //获得DatePicker对象
        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);

        //为mDatePicker设置值
        mDatePicker.init(year,month,day,null);

       return  new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.crime_date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    //为OK按钮设置事件
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //从DatePicker对象中读取年月日
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();

                        //通过GregorianCanlendar把年月日封装成Date对象
                        Date date = new GregorianCalendar(year,month,day).getTime();

                        //通过此方法发送数据给绑定的Fragment
                        sendResult(Activity.RESULT_OK,date);
                    }
                })
                .create();
    }

    public void sendResult(int resultCode,Date date){
        if(getTargetFragment()==null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_DATE,date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
