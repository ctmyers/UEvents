package com.uevents.app.uevents;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AttendingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sets the layout for the gui. This layout is also used in MyEventsActivity
        setContentView(R.layout.activity_view_events);

        //Sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Attending Events");
        setSupportActionBar(toolbar);

        // Back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttendingActivity.this.onBackPressed();
            }
        });

        // Sets up the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);
        mRecyclerView.setHasFixedSize(true); // optimization

        // load the attending events into the view
        Event[] events = EventList.attendingEvents.toArray(new Event[EventList.attendingEvents.size()]);
        mAdapter = new ListViewAdapter(events, this);
        mRecyclerView.setAdapter(mAdapter);

        //sets the adapter for the recycler view
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    // on resume reload the attending events in case it's changed
    @Override
    public void onResume(){
        super.onResume();

        Event[] events = EventList.attendingEvents.toArray(new Event[EventList.attendingEvents.size()]);
        mAdapter = new ListViewAdapter(events, this);
        mRecyclerView.setAdapter(mAdapter);

    }

}




