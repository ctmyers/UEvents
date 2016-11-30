package com.uevents.app.uevents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

/**
 * Created by Daniel on 11/29/16.
 */

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Event[] prepopulatedEvents = {
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12)
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_fragment, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.list_recycler_view);
        recyclerView.setHasFixedSize(true); // optimization

        adapter = new ListViewAdapter(prepopulatedEvents);
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return fragmentView;
    }

}
