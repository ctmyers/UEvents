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
    public FloatingActionButton myfab;
    Toast toast;

    private Event[] prepopulatedEvents = {
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice. Open to all levels.", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 12, "JoeShmoe"),
            new Event(Event.Category.SOCIAL, "Human Jenga", "Human jenga - who's in?", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 15, "RebeccaHe"),
            new Event(Event.Category.STUDY, "CHEM231 PSet #1", "Houck's orgo problem set. Really hard, stuck on last page.", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 10, "DanielCha"),
            new Event(Event.Category.CLUB, "AMSA GBM #3", "Come listen to our guest speaker from NIH.", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 40, "CarsonMyers"),
            new Event(Event.Category.SPORT, "Soccer", "Who wants to play some soccer? It's a nice day outside.", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 10, "AndreaSoto"),
            new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice. Open to all levels.", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 12, "JoeShmoe"),
            new Event(Event.Category.SOCIAL, "Human Jenga", "Human jenga - who's in?", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 15, "RebeccaHe"),
            new Event(Event.Category.STUDY, "CHEM231 PSet #1", "Houck's orgo problem set. Really hard, stuck on last page.", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 10, "DanielCha"),
            new Event(Event.Category.CLUB, "AMSA GBM #3", "Come listen to our guest speaker from NIH.", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 40, "CarsonMyers"),
            new Event(Event.Category.SPORT, "Soccer", "Who wants to play some soccer? It's a nice day outside.", 38.990849f, -76.944141f, Calendar.getInstance(), Calendar.getInstance(), 10, "AndreaSoto"),
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.list_fragment, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.list_recycler_view);
        recyclerView.setHasFixedSize(true); // optimization

        adapter = new ListViewAdapter(prepopulatedEvents, getActivity());
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        myfab = (FloatingActionButton) fragmentView.findViewById(R.id.fab);
        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Hello!",Toast.LENGTH_SHORT).show();
                Intent createEvent = new Intent(getActivity(), CreateEventActivity.class);
                startActivity(createEvent);
            }
        });
        return fragmentView;
    }

}
