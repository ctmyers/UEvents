package com.uevents.app.uevents;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;

import static android.support.v7.cardview.R.color.cardview_shadow_end_color;
import static android.support.v7.recyclerview.R.attr.layoutManager;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private boolean filterClub = true;
    private boolean filterSport = true;
    private boolean filterStudy = true;
    private boolean filterSocial = true;

    private EditText mSearchText;
    private ImageButton mClubFilterButton;
    private ImageButton mSportFilterButton;
    private ImageButton mStudyFilterButton;
    private ImageButton mSocialFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Search");
        setSupportActionBar(toolbar);

        // Back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.onBackPressed();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);
        mRecyclerView.setHasFixedSize(true); // optimization

        Event[] events = EventList.allEvents.toArray(new Event[EventList.allEvents.size()]);
        mAdapter = new ListViewAdapter(events, this);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        //set up the filtering buttons
        mClubFilterButton = (ImageButton) findViewById(R.id.clubFilterButton);
        mClubFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!filterClub){
                    filterClub = true;
                    mClubFilterButton.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorImageFilterBackgroundSeclected));
                    filter();
                } else{
                    filterClub = false;
                    mClubFilterButton.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorImageFilterBackgroundUnseclected));
                    filter();
                }
            }
        });

        mSocialFilterButton = (ImageButton) findViewById(R.id.socialFilterButton);
        mSocialFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!filterSocial){
                    filterSocial = true;
                    mSocialFilterButton.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorImageFilterBackgroundSeclected));
                    filter();
                } else{
                    filterSocial = false;
                    mSocialFilterButton.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorImageFilterBackgroundUnseclected));
                    filter();
                }
            }
        });

        mSportFilterButton = (ImageButton) findViewById(R.id.sportFilterButton);
        mSportFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!filterSport){
                    filterSport = true;
                    mSportFilterButton.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorImageFilterBackgroundSeclected));
                    filter();
                } else{
                    filterSport = false;
                    mSportFilterButton.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorImageFilterBackgroundUnseclected));
                    filter();
                }
            }
        });

        mStudyFilterButton = (ImageButton) findViewById(R.id.studyFilterButton);
        mStudyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!filterStudy){
                    filterStudy = true;
                    mStudyFilterButton.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorImageFilterBackgroundSeclected));
                    filter();
                } else{
                    filterStudy = false;
                    mStudyFilterButton.setBackgroundColor(ContextCompat.getColor(SearchActivity.this, R.color.colorImageFilterBackgroundUnseclected));
                    filter();
                }
            }
        });


        // set up search text

        mSearchText = (EditText) findViewById(R.id.searchText);
        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }




    private void filter(){
        ArrayList<Event> events = new ArrayList<Event>();

        for(Event e : EventList.allEvents){
            boolean add = false;
            if(filterClub && e.category.equals(Event.Category.CLUB)){
                add = true;
            }
            else if(filterStudy && e.category.equals(Event.Category.STUDY)){
                add = true;
            }
            else if(filterSport && e.category.equals(Event.Category.SPORT)){
                add = true;
            }
            else if(filterSocial && e.category.equals(Event.Category.SOCIAL)){
                add = true;
            }

            if(add && (e.description.toLowerCase().contains(mSearchText.getText()) || e.title.toLowerCase().contains(mSearchText.getText()))){
                events.add(e);
            }
        }

        Event[] eventArr = new Event[events.size()];
        eventArr = events.toArray(eventArr);

        mAdapter = new ListViewAdapter(eventArr, this);
        mRecyclerView.setAdapter(mAdapter);

    }
}




