package com.uevents.app.uevents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by carson on 12/12/16.
 */

public class EventList {

    // static array of all events, prepopulated to be convinent for programming
    // if this was the real app this would be a whole backend to connect to our server
    // to get the events (not running in the UI thread of course)
    public static ArrayList<Event> allEvents = new ArrayList<Event>(){{
        add(new Event(Event.Category.SPORT, "Ultimate Frisbee", "Casual game of ultimate. Weather's nice. Open to all levels.", 38.987951f, -76.937650f, Calendar.getInstance(), Calendar.getInstance(), 12, "JoeShmoe"));
        add(new Event(Event.Category.SOCIAL, "Human Jenga", "Human jenga - who's in?", 38.990182f, -76.937247f, Calendar.getInstance(), Calendar.getInstance(), 15, "RebeccaHe"));
        add(new Event(Event.Category.STUDY, "CHEM231 PSet #1", "Houck's orgo problem set. Really hard, stuck on last page.", 38.990845f, -76.938325f, Calendar.getInstance(), Calendar.getInstance(), 10, "DanielCha"));
        add(new Event(Event.Category.CLUB, "AMSA GBM #3", "Come listen to our guest speaker from NIH.", 38.9903619f, -76.9400295f, Calendar.getInstance(), Calendar.getInstance(), 40, "CarsonMyers"));
        add(new Event(Event.Category.SPORT, "Soccer", "Who wants to play some soccer? It's a nice day outside.", 38.9882058f, -76.9402019f, Calendar.getInstance(), Calendar.getInstance(), 10, "AndreaSoto"));
        add(new Event(Event.Category.SPORT, "Football", "Casual game of touch football. Weather's nice. Open to all levels.", 38.987951f, -76.937650f, Calendar.getInstance(), Calendar.getInstance(), 12, "JoeShmoe"));
        add(new Event(Event.Category.SOCIAL, "Human chess", "come be my menions", 38.990182f, -76.937247f, Calendar.getInstance(), Calendar.getInstance(), 15, "RebeccaHe"));
        add(new Event(Event.Category.STUDY, "CMSC434 IA05", "Let's work on this together", 38.990845f, -76.948325f, Calendar.getInstance(), Calendar.getInstance(), 10, "DanielCha"));
        add(new Event(Event.Category.CLUB, "UAS", "Come help us build a uav.", 38.9903619f, -76.9400295f, Calendar.getInstance(), Calendar.getInstance(), 40, "CarsonMyers"));
        add(new Event(Event.Category.SPORT, "water polo", "the water's great!", 38.9882058f, -76.9402019f, Calendar.getInstance(), Calendar.getInstance(), 10, "AndreaSoto"));

    }};
    // static array of events created by the user
    public static ArrayList<Event> myEvents = new ArrayList<Event>();
    // static array of events the user is attending
    public static ArrayList<Event> attendingEvents = new ArrayList<Event>();

    // adds an event to the allEvents list
    public static void addEvent(Event e){
        allEvents.add(e);
    }

    // when the user attends an event
    public static void attendEvent(Event e){
        attendingEvents.add(e);
    }

    // for user made events
    public static void createdEvent(Event e){
        myEvents.add(e);
        allEvents.add(e);
    }

    // return event object using title
    public static Event getEvent(String title){
        for(Event e:allEvents){
            if(e.title.equals(title)){
                return e;
            }
        }
        return null;
    }

    //returns true if the user is attending the event based on the title
    public static boolean isAttending(String title){
        for(Event e:attendingEvents){
            if(e.title.equals(title)){
                return true;
            }
        }
        return false;
    }

    //marks the given event (based on title) as attending
    public static void markAttending(String title){
        for(Event e:allEvents){
            if(e.title.equals(title)){
                attendingEvents.add(e);
                break;
            }
        }
    }

    //removes the given event (based on title) from the attending list
    public static void cancelAttending(String title){
        for(Event e:allEvents){
            if(e.title.equals(title)){
                attendingEvents.remove(e);
                break;
            }
        }
    }
}

