package com.example.denky.greattimetable;

/**
 * Created by denky on 2017-03-17.
 */

public class Listview {
  //  private int icon;
    private String name;
    private String title;
    private String context;

    //public int getIcon(){return icon;}

    public String getName(){return name;}
    public Listview(String name, String title, String context){
        //this.icon=icon;
        this.name=name;
        this.context = context;
        this.title = title;

    }
}