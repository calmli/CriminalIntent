package com.example.calmlee.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by calmlee on 2016/8/13.
 */
public class DatePickerFragment extends DialogFragment{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       return  new AlertDialog.Builder(getActivity())
                .setTitle(R.string.crime_date_picker_title)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }
}
