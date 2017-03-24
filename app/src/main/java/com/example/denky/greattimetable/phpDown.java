package com.example.denky.greattimetable;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by denky on 2017-03-21.
 */

public class phpDown {
    int type ;
    String myJSON;
    ListView listview;
    Activity activity;
    private static final String TAG_RESULTS="result";
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String UPDATER ="updater";
    private static final String CONTEXT ="context";
    ArrayList<HashMap<String, String>> personList;

    JSONArray recommend_sites = null;
    public phpDown (int type, ListView listview, Activity activity) {
        this.type = type; // 순서대로 1카테고리 = type 1, 5카테고리 = type 5
        this.listview = listview;
        this.activity = activity;
        personList = new ArrayList<HashMap<String,String>>();
       // Log.d("php_result", "type :"+this.type + "\nlistview : "+this.listview +"\nactivity : "+this.activity);
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            recommend_sites = jsonObj.getJSONArray(TAG_RESULTS);
            for(int i = 0; i< recommend_sites.length(); i++){
                JSONObject c = recommend_sites.getJSONObject(i);
                String temp_id = c.getString(TITLE);
                String temp_url = c.getString(URL);
                String temp_updater = c.getString(UPDATER);
                String temp_context = c.getString(CONTEXT);
                HashMap<String,String> persons = new HashMap<String,String>();
                persons.put(TITLE,temp_id);
                persons.put(URL,temp_url);
                persons.put(UPDATER,temp_updater);
                persons.put(CONTEXT, temp_context);
                personList.add(persons);
            }
           // Log.d("php_result", type+ ","+TITLE +","+URL);
            switch (type){
                case 1 :  ListAdapter adapter = new SimpleAdapter(
                        activity, personList, R.layout.list_item,
                        new String[]{TITLE,CONTEXT,URL},
                        new int[]{R.id.list_text, R.id.list_context}
                );
                    listview.setAdapter(adapter);
                    break;
                case 2 : ListAdapter adapter2 = new SimpleAdapter( //default_adapter
                        activity, personList, R.layout.list_item,
                        new String[]{UPDATER, TITLE, URL},
                        new int[]{R.id.list_text, R.id.list_context}
                );
                    listview.setAdapter(adapter2);
                    break;
                case 3 : ListAdapter adapter3 = new SimpleAdapter( //default_adapter
                        activity, personList, R.layout.list_item,
                        new String[]{CONTEXT, TITLE},
                        new int[]{R.id.list_text, R.id.list_context}
                );
                    listview.setAdapter(adapter3);
                    break;
                case 4 : ListAdapter adapter4 = new SimpleAdapter( //default_adapter
                        activity, personList, R.layout.list_item,
                        new String[]{UPDATER, TITLE},
                        new int[]{R.id.list_text, R.id.list_context}
                );
                    listview.setAdapter(adapter4);
                    break;

                case 5 :
                        ListAdapter adapter5 = new SimpleAdapter( //default_adapter
                        activity, personList, R.layout.list_item,
                        new String[]{TITLE, URL},
                        new int[]{R.id.list_text, R.id.list_context}
                );
                    listview.setAdapter(adapter5);
                    break;
                case 6 :
                    ListAdapter adapter6 = new SimpleAdapter( //default_adapter
                            activity, personList, R.layout.list_litem_site_board,
                            new String[]{TITLE, URL},
                            new int[]{R.id.list_text, R.id.list_context}
                    );
                    listview.setAdapter(adapter6);
                    break;

                default :
                    break;


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
             //  Log.d("php_result", "type : " +type+ result);
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
