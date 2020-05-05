package domain;

import time.Interval;

import java.util.Calendar;

public class Activity {
    protected String name;
    protected Interval interval;
    protected double plannedHours;
    protected double workedHours;

    public Activity(String name) {
        if(name.length() > 1) {
            this.name = name;
            this.interval = new Interval();
        } else {
            throw new IllegalArgumentException("Activity names must be longer than one letter");
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
    return      "{ name: '" + name + '\'' +
                ", Start date: " + "Week: " + '\'' + interval.getStartDate().get(Calendar.WEEK_OF_YEAR) + '\'' + ", Year: " +
                '\'' + interval.getStartDate().get(Calendar.YEAR) + '\''
//                ", plannedHours=" + plannedHours +
//                ", workedHours=" + workedHours +
                + "} \n";
    }
}
