package com.uevents.app.uevents;

import java.util.ArrayList;

/**
 * Created by carson on 12/12/16.
 */

public class EventList {

    public static ArrayList<Event> allEvents = new ArrayList<Event>();
    public static ArrayList<Event> myEvents = new ArrayList<Event>();
    public static ArrayList<Event> attendingEvents = new ArrayList<Event>();

    public static void addEvent(Event e){
        allEvents.add(e);
    }

    // when the user attends an event
    public static void attendEvent(Event e){
        myEvents.add(e);
    }

    // for user made events
    public static void createdEvent(Event e){
        myEvents.add(e);
        allEvents.add(e);
    }


}
