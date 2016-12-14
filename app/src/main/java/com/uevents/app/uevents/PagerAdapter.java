package com.uevents.app.uevents;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//Adapter that manages the map and list view for our main page
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    MapFragment tab1;
    ListFragment tab2;

    //Initialize the adapter with a fragment manager and number of tabs
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    //Functionality for displaying the selected tab
    @Override
    public Fragment getItem(int position) {

        //First tab is the map fragment and second tab is the list fragment
        switch (position) {
            case 0:
                tab1 = new MapFragment();
                return tab1;
            case 1:
                tab2 = new ListFragment();
                return tab2;

            default:
                return null;
        }
    }

    //Returns the number of tabs
    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    //Updates the events list for each tab
    public void update(){
        tab1.update();
        tab2.update();
    }


}
