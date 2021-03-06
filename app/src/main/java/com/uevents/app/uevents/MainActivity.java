package com.uevents.app.uevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Calendar;

//Main activity for managing tabs and creating events
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView mSearchButton;
    public FloatingActionButton myfab;
    public PagerAdapter adapter;

    //On create method for initializing the drawer, toolbar, and tabbed layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Setup the navigation drawer to pull out from the side
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Setup menu items for navigation drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Attach an onClickListener to the imageView to start the search activity
        mSearchButton = (ImageView) findViewById(R.id.searchButton);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //Initialize tablayout for map and list view
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Nearby").setIcon(R.drawable.map));
        tabLayout.addTab(tabLayout.newTab().setText("Happening Now").setIcon(R.drawable.list));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Setup Pager adapter to listen for the change of the different tabs
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //Display selected tab
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        myfab = (FloatingActionButton) findViewById(R.id.fab);
        //On click listener for creating an event by clicking the fab
        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createEvent = new Intent(MainActivity.this, CreateEventActivity.class);
                startActivityForResult(createEvent,1);

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.view_events) {
            // Handle the view events
            Intent createEvent = new Intent(MainActivity.this, AttendingActivity.class);
            startActivity(createEvent);
        } else if (id == R.id.my_events) {
            // Handle the my events
            Intent createEvent = new Intent(MainActivity.this, MyEventsActivity.class);
            startActivity(createEvent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String title=data.getStringExtra("Title");
                String description=data.getStringExtra("Description");
                String attendees = data.getStringExtra("Attendees");
                float lat = data.getFloatExtra("lat",0f);
                float lon = data.getFloatExtra("long",0f);
                Calendar start =(Calendar) data.getSerializableExtra("start");
                Calendar end =(Calendar) data.getSerializableExtra("end");
                String cat = data.getStringExtra("Category");
                Event.Category c=null;
                switch(cat){
                    case "Clubs": c = Event.Category.CLUB;
                        break;
                    case "Sports": c = Event.Category.SPORT;
                        break;
                    case "Social": c = Event.Category.SOCIAL;
                        break;
                    case "Study": c = Event.Category.STUDY;
                        break;
                }
                EventList.createdEvent(new Event(c,title,description,lat,lon,start,end,Integer.parseInt(attendees),"DanielCha"));
                adapter.update();

            }

        }
    }
}
