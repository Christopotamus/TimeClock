package com.mynameischrisbudd.timeclock;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase trackDB;
    private Task Tasks[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupDB();
        selectRecentTasks();
    }

    private void setupDB() {
        try {
            trackDB = openOrCreateDatabase("trackDB.sqlite", MODE_PRIVATE, null);

            trackDB.execSQL("CREATE TABLE IF NOT EXISTS "
            + "TaskLog"
            + " (TaskID INTEGER, TaskName TEXT, LogTime DATETIME);");


        }catch(Exception e) {

        }
    }
    private void selectRecentTasks(){
        try{
            if(trackDB.isOpen()){
                Cursor c = trackDB.rawQuery("SELECT * FROM TaskLog LIMIT 10", null);
                int c_TaskID = c.getColumnIndex("TaskID");
                int c_TaskName = c.getColumnIndex("TaskName");
                int c_LogTime = c.getColumnIndex("LogTime");

                c.moveToFirst();
                if(c != null){
                    do{
                        String TaskName = c.getString(c_TaskName);
                        DateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date TaskDate = dfmt.parse(c.getString(c_LogTime));
                        c.moveToNext();
                    }while(c != null);
                }
            }
        }catch(Exception e){

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
