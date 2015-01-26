package com.diegosantos.parsedataoffline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;


public class MainActivity extends Activity {

    EditText ed_Name;
    Button btn_Save;
    Button btn_List;
    ParseObject parseObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_Name = (EditText)findViewById(R.id.ed_Name);
        btn_Save = (Button)findViewById(R.id.btnSave);
        btn_List = (Button)findViewById(R.id.btnList);

        // Set the button handler
        SaveNameHandler add = new SaveNameHandler();
        btn_Save.setOnClickListener(add);

        ListHandler list = new ListHandler();
        btn_List.setOnClickListener(list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public class SaveNameHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            // Create the Parse object
            parseObject = new ParseObject("Names");

            String name = ed_Name.getText().toString();

            parseObject.put("Name", name);
            parseObject.pinInBackground();
            parseObject.saveEventually();

            Toast.makeText(MainActivity.this, "Name saved!", Toast.LENGTH_SHORT).show();

            if (ed_Name != null){ed_Name.setText("");}


        }
    }

    public class ListHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Intent i = new Intent(MainActivity.this, ListNamesActivity.class);
            startActivity(i);

        }
    }
}
