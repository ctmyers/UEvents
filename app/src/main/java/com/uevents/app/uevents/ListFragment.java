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
            new Event(Event.Category.SOCIAL, "Human Jenga", "Human jenga - who's in?", "Eppley", new Date(), new Date(), 15),
            new Event(Event.Category.STUDY, "CHEM231 PSet #1", "Houck's orgo problem set.", "Engineering Library", new Date(), new Date(), 10),
            new Event(Event.Category.CLUB, "AMSA GBM #3", "Come listen to our guest speaker from NIH.", "JMZ0205", new Date(), new Date(), 40),
            new Event(Event.Category.SPORT, "Soccer", "Who wants to play some soccer?", "Field", new Date(), new Date(), 10),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice.", "McKeldin Mall", new Date(), new Date(), 12),
            new Event(Event.Category.SOCIAL, "Human Jenga", "Human jenga - who's in?", "Eppley", new Date(), new Date(), 15),
            new Event(Event.Category.STUDY, "CHEM231 PSet #1", "Houck's orgo problem set.", "Engineering Library", new Date(), new Date(), 10),
            new Event(Event.Category.CLUB, "AMSA GBM #3", "Come listen to our guest speaker from NIH.", "JMZ0205", new Date(), new Date(), 40),
            new Event(Event.Category.SPORT, "Soccer", "Who wants to play some soccer?", "Field", new Date(), new Date(), 10),
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
