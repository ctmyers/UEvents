package com.uevents.app.uevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Daniel on 11/29/16.
 */

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Toast toast;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_fragment, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.list_recycler_view);
        recyclerView.setHasFixedSize(true); // optimization

        Event[] events = EventList.allEvents.toArray(new Event[EventList.allEvents.size()]);
        adapter = new ListViewAdapter(events, getActivity());
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);


        return fragmentView;
    }

    public void update(){
        Event[] events = EventList.allEvents.toArray(new Event[EventList.allEvents.size()]);
        adapter = new ListViewAdapter(events,getActivity());
        recyclerView.setAdapter(adapter);
    }

}
