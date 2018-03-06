package com.org.bard.RecruitingAppDB.entities;

import java.sql.Date;

public class Tournament {
        public static final String [] keys = {"title", "location", "date"};

        public long id;

        private String title;
        private Date date;
        private String location;

        public Tournament(String title, Date date, String location) {
            this.title = title;
            this.date = date;
            this.location = location;
        }
}
