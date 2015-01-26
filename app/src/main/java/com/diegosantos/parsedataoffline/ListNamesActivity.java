package com.diegosantos.parsedataoffline;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListNamesActivity extends Activity {

    String[] names = new String[3];
    ListView l;

    private ArrayAdapter<String> listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_names);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        l = (ListView)findViewById(R.id.lv_Names);

        makeList();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_names, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Names");
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> nameList, ParseException e) {

                if (e == null) {
                    Log.d("Query", "Ok");
                    int j = nameList.size();

                    names = new String[j];

                    for (int i = 0; i<j; i++){

                        String name  = nameList.get(i).getString("Name");

                        names[i] = name;


                    }

                    // Create and populate a List of planet names.
//                    String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
//                            "Jupiter", "Saturn", "Uranus", "Neptune"};
                    ArrayList<String> nameArrayList = new ArrayList<String>();
                    nameArrayList.addAll( Arrays.asList(names) );

                    // Create ArrayAdapter using the planet list.
                    listAdapter = new ArrayAdapter<String>(ListNamesActivity.this, R.layout.row, nameArrayList);

                    // Set the ArrayAdapter as the ListView's adapter.
                    l.setAdapter( listAdapter );


                } else {

                    Log.d("Query", "Fail");
                }
            }

        });

    }
}
