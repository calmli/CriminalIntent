package com.example.calmlee.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.database.CrossProcessCursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment{
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateUI();
        return view;
    }

    public void updateUI(){
        List<Crime> crimes = CrimeLab.get(getContext()).getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    public class CrimeHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        private TextView mDate;
        private CheckBox mSolved;
        private Crime mCrime;

        public CrimeHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.list_item_text_view_crime_title);
            mDate = (TextView) itemView.findViewById(R.id.list_item_text_view_crime_date);
            mSolved = (CheckBox) itemView.findViewById(R.id.list_item_checkbox_solved);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CrimeListFragment",mCrime.getmId().toString());
                    Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getmId());
                    startActivity(intent);
                }
            });
        }

        public void bindCrime(Crime crime){
           mCrime = crime;
           // Log.d("Text",mCrime.toString());
           this.mTitle.setText(crime.getmTitle());
           this.mDate.setText(crime.getmDate().toString());
           this.mSolved.setChecked(crime.ismSolved());
        }
    }

    public class CrimeAdapter extends RecyclerView.Adapter{

        private List<Crime> mCrimes;

        //创建CrimeAdapter时把模型层的数据传过来
        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        //创建ViewHolder
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //解析xml文件成View对象
            View view = inflater.inflate(R.layout.list_item_crime,parent,false);
            //将View对象传给要创建的CrimeHolder
            return new CrimeHolder(view);

        }

        //找到目标位置的数据并存放在holder视图中
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            CrimeHolder crimeHolder = (CrimeHolder) holder;
            crimeHolder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
