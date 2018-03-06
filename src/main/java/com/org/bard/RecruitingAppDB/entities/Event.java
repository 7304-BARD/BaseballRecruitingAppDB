package com.org.bard.RecruitingAppDB.entities;

public class Event {
    long id;

    public long start;
    public long end;
    public String title;

    public Event(long start, long end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
    }
}
