package domain;

import time.Interval;

public class Activity {
    protected String name;
    protected Interval interval;
    protected double plannedHours;
    protected double workedHours;

    public Activity(String name) {
        if(name.matches("^[a-zA-Z]*$")) {
            this.name = name;
            this.interval = new Interval();
        } else {
            throw new IllegalArgumentException("Activity names can only contain alphabetic letters");
        }
    }

    public Interval getInterval() { return this.interval; }

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
