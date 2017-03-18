package com.example.denky.greattimetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by denky on 2017-03-17.
 */

public class ListviewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Listview> data;
    private int layout;
    private int index;
    public ListviewAdapter(Context context, int layout, ArrayList<Listview> data, int index){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
        this.layout=layout;
        this.index = index;
    }
    @Override
    public int getCount(){return data.size();}
    @Override
    public String getItem(int position){
        return data.get(position).getName();
    }
    @Override
    public long getItemId(int position){return position;}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
                Listview listviewitem = data.get(position);
                TextView name = (TextView) convertView.findViewById(R.id.list_text);
                name.setText(listviewitem.getName());
                TextView title = (TextView) convertView.findViewById(R.id.list_context);
                title.setText(listviewitem.getContext());



        return convertView;

    }
}