package com.example.denky.greattimetable;

/**
 * Created by denky on 2017-03-18.
 */
public class ListItem {

    private String[] mData;
    final int columnCnt = 3;

    public ListItem(String[] data){
        mData = data;
    }

    public ListItem(String url, String updater, String view){
        mData = new String[columnCnt];
        mData[0] = url;
        mData[1] = updater;
        mData[2] = view;
    }


    public String[] getmData(){
        return mData;
    }

    public String getData(int index){
        return mData[index];
    }
    public void setData(String[] data){
        mData = data;
    }
}

