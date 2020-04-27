package domain;

import time.Interval;

public class Activity {
    protected String name;
    protected Interval interval;
    protected double plannedHours;

    public Activity(String name) {
        this.name = name;
        this.interval = new Interval();
    }

    public Interval getInterval() {
        return this.interval; }

    public double getPlannedHours() {
        return this.plannedHours;
    }

    public void setPlannedHours(double hours) {
        this.plannedHours = hours;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
