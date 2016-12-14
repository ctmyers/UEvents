package com.uevents.app.uevents;

import java.util.Calendar;

/**
 * Created by rebeccahe on 11/30/16.
 */
public class Event {

    // general variables
    Category category;
    String title;
    String description;

    // location variables
    float lat;
    float lon;

    // time variables
    Calendar startTime;
    Calendar endTime;

    // attendance variables
    int maxAttendance;
    int currAttendance;

    // username
    String creator;


    public enum Category {
        SPORT, STUDY, SOCIAL, CLUB
    }

    // Standard constructor that sets the class variables
    public Event(Category category, String title, String description, float lat, float lon,
                 Calendar startTime, Calendar endTime, int maxAttendance, String creator) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxAttendance = maxAttendance;
        this.currAttendance = 1; // Assumption: the event creator is attending his own event
        this.creator = creator;
    }

    /**
     * This method increments the attendance count if possible. If increment is successful, method
     * returns true, otherwise returns false. Increment can only be successful if the attendance
     * is less than the maximum attendance.
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
