package com.example.denky.greattimetable;

/**
 * Created by denky on 2017-03-17.
 */

public class Listview {
  //  private int icon;
    private String name;
    private String context;

    //public int getIcon(){return icon;}

    public String getName(){
        return name;
    }
    public String getContext(){
        return context;
    }
    public Listview(String name, String title, String context){
        //this.icon=icon;
        this.name=name;
        this.context = context;

    }
}