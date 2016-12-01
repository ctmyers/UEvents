package com.uevents.app.uevents;

import java.util.Calendar;

/**
 * Created by rebeccahe on 11/30/16.
 */
public class Event {

    // fields
    Category category;
    String title;
    String description;
    float lat;
    float lon;

    Calendar startTime;
    Calendar endTime;

    int maxAttendance;
    int currAttendance;

    String creator;


    public enum Category {
        SPORT, STUDY, SOCIAL, CLUB
    }

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
        this.currAttendance = 1; // Assumption: the event creator is attending
        this.creator = creator;
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
