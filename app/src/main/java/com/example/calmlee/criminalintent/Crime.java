package com.example.calmlee.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by calmlee on 2016/8/6.
 */

//模型类（M）
public class Crime {
    private String mTitle;
    //定义一个全球唯一的ID
    private UUID mId;
    private Date mDate;
    private boolean mSolved;

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    //创建对象时生成一个全球唯一的UUID
    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
