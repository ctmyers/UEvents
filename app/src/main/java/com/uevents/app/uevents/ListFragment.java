package com.uevents.app.uevents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Daniel on 11/29/16.
 */

// List fragment resides in a tab that displays recent events in a list format
public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_fragment, container, false);

        // Set up the RecyclerView
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.list_recycler_view);
        recyclerView.setHasFixedSize(true); // optimization

        // Filter list of events into the ListViewAdapter
        Event[] events = EventList.allEvents.toArray(new Event[EventList.allEvents.size()]);
        adapter = new ListViewAdapter(events, getActivity());
        recyclerView.setAdapter(adapter);

        // Set up LayoutMAnager
        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return fragmentView;
    }

    /**
     * When called, this method retrieves the set of events (back end) and updates the view with
     * an updated adapter.
     */
    public void update() {
        Event[] events = EventList.allEvents.toArray(new Event[EventList.allEvents.size()]);
        adapter = new ListViewAdapter(events,getActivity());
        recyclerView.setAdapter(adapter);
    }

}
