package com.aware.phone;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aware.Aware;
import com.aware.Aware_Preferences;

import java.util.ArrayList;




public class SenseDB_ActionTypeList_Activity extends AppCompatActivity {


    public static final String ACTIVITY_WALKING = "WALKING: Phone in pocket";
    public static final String ACTIVITY_WALKING_PHONE_IN_USE = "WALKING: Phone in use";

    public static final String ACTIVITY_CLIMBING_STAIRS = "CLIMBING STAIRS: Phone in pocket";
    public static final String ACTIVITY_CLIMBING_STAIRS_PHONE_IN_USE = "CLIMBING STAIRS: Phone in use";

    public static final String ACTIVITY_STANDING_PHONE_IN_USE = "STANDING: Phone in use";

    public static final String ACTIVITY_RUNNING = "RUNNING";
    public static final String ACTIVITY_AT_DESK = "AT DESK";

    private Context awareContext;

    View previouslySelectedItem = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        int sel;
        awareContext = getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sense_db_activity_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.aware_toolbar);
        //toolbar.setTitle(getTitle() != null ? getTitle() : "");
        toolbar.setTitle("Activity Selector");
        toolbar.inflateMenu(R.menu.sense_db_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String[] values = new String[] {ACTIVITY_AT_DESK,ACTIVITY_WALKING,ACTIVITY_WALKING_PHONE_IN_USE,ACTIVITY_CLIMBING_STAIRS,ACTIVITY_CLIMBING_STAIRS_PHONE_IN_USE,ACTIVITY_STANDING_PHONE_IN_USE,ACTIVITY_RUNNING};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        ListView listview = (ListView) findViewById(R.id.senseDBListView);
        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String item = (String) adapterView.getItemAtPosition(i);
                setLabelInSharedPreference(item);
                highlightItem(view);      //Highlights Selected Item
                switch (i){
                    case 0:
                        Toast.makeText(SenseDB_ActionTypeList_Activity.this, item, Toast.LENGTH_SHORT).show();
                        Aware.stopAccelerometer(awareContext);
                        break;

                    case 1:
                        Toast.makeText(SenseDB_ActionTypeList_Activity.this, item, Toast.LENGTH_SHORT).show();
                        Aware.startAccelerometer(awareContext);
                        break;

                    case 2:
                        Toast.makeText(SenseDB_ActionTypeList_Activity.this, item, Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        Toast.makeText(SenseDB_ActionTypeList_Activity.this, item, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(SenseDB_ActionTypeList_Activity.this, item, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(SenseDB_ActionTypeList_Activity.this, item, Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(SenseDB_ActionTypeList_Activity.this, item, Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(SenseDB_ActionTypeList_Activity.this, item, Toast.LENGTH_SHORT).show();
                        break;
                }

             }

        });

    }

    private void highlightItem(View view) {
        if (previouslySelectedItem != null)
        {
            previouslySelectedItem.setBackgroundColor(Color.TRANSPARENT);
        }

        view.setBackgroundColor(getResources().getColor(R.color.custom_green));
        previouslySelectedItem = view;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    void  setLabelInSharedPreference(String activityString){

        Aware.setSetting(getApplicationContext(),Aware_Preferences.ACTIVITY_STRING,activityString);

    }

}
