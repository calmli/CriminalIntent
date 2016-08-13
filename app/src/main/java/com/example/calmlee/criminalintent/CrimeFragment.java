package com.example.calmlee.criminalintent;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;


/**
 * Created by calmlee on 2016/8/6.
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mCrimeTitle;
    private Button mCrimeDate;
    private CheckBox mCrimeSolved;
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化mCrime
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getContext()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //实例化fragment_crime视图
        View view = inflater.inflate(R.layout.fragment_crime,container,false);

        //获得mCrimeTitle实例
        mCrimeTitle = (EditText) view.findViewById(R.id.crime_title);
        mCrimeTitle.setText(mCrime.getmTitle());
        mCrimeDate = (Button) view.findViewById(R.id.crime_date);
        mCrimeSolved = (CheckBox) view.findViewById(R.id.crime_solved);
        mCrimeSolved.setChecked(mCrime.ismSolved());
        mCrimeDate.setText(mCrime.getmDate().toString());

        //设置事件
        mCrimeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //将mCrime对象中的title设置为s(用户在输入框中输入的)
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCrimeSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setmSolved(isChecked);
            }
        });

        mCrimeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager,DIALOG_DATE);
            }
        });

        return view;
    }

    public static Fragment newInstance(UUID crimeId){
        CrimeFragment fragment = new CrimeFragment();
        //定义一个Bundle实例
        Bundle args = new Bundle();
        //作为fragment的一个argument
        args.putSerializable(ARG_CRIME_ID,crimeId);
        fragment.setArguments(args);
        return fragment;
    }


}
