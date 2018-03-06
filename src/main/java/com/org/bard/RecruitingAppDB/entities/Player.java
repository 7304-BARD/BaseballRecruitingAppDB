package com.org.bard.RecruitingAppDB.entities;

public class Player {

    public long pgid;

    public String name;
    public short year;
    public String pos1;
    public String pos2;
    public short age;
    public float height;
    public float weight;
    public String bt;
    public String hs;
    public String town;
    public String team_summer;
    public String team_fall;

    public Player() {};

    public Player(long pgid, String name, short year, String pos1, String pos2,
                  short age, float height, float weight, String bt, String hs,
                  String town, String team_summer, String team_fall)
    {
        this.pgid = pgid;
        this.name = name;
        this.year = year;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.bt = bt;
        this.hs = hs;
        this.town = town;
        this.team_summer = team_summer;
        this.team_fall = team_fall;
    }

}
