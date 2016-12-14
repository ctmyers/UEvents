package com.uevents.app.uevents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MyEventsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sets the content view to the activity_view_events. This is also used in the AttendingActivity class
        setContentView(R.layout.activity_view_events);

        //Sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Events");
        setSupportActionBar(toolbar);

        // Back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyEventsActivity.this.onBackPressed();
            }
        });

        //Sets up the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);
        mRecyclerView.setHasFixedSize(true); // optimization

        //Gets the events created by the user and sets it to the recycler view
        Event[] events = EventList.myEvents.toArray(new Event[EventList.myEvents.size()]);
        mAdapter = new ListViewAdapter(events, this);
        mRecyclerView.setAdapter(mAdapter);

        //if the list is empty, make sure the user knows its empty with a toast message
        if(EventList.myEvents.size() == 0){
            Toast.makeText(this,"You have no events =(",Toast.LENGTH_LONG).show();
        }

        //sets the manager for the recycler view
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    //on resume refresh the list in case it's changed
    @Override
    public void onResume(){
        super.onResume();

        Event[] events = EventList.myEvents.toArray(new Event[EventList.myEvents.size()]);
        mAdapter = new ListViewAdapter(events, this);
        mRecyclerView.setAdapter(mAdapter);

    }

}




