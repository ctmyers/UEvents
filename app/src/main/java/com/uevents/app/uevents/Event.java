package com.uevents.app.uevents;

import java.util.Date;

/**
 * Created by rebeccahe on 11/30/16.
 */
public class Event {

    // fields
    Category category;
    String title;
    String description;
    String location; // TODO: How are Google Map API locations stored?

    Date startTime;
    Date endTime;

    int maxAttendance;
    int currAttendance;


    public enum Category {
        SPORT, STUDY, SOCIAL, CLUB
    }

    public Event(Category category, String title, String description, String location,
                 Date startTime, Date endTime, int maxAttendance) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxAttendance = maxAttendance;
        this.currAttendance = 0; // TODO: Should the default attendance be 1? Do we assume the creator is attending?
    }

    /**
     * This method increments the attendance count if possible. If increment is successful, method
     * returns true, otherwise returns false.
     * @return
     */
    public boolean incrementAttendance() {
        if (currAttendance < maxAttendance) {
            currAttendance++;
            return true;
        } else {
            return false;
        }
    }


}
