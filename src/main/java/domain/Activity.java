package domain;

import time.Interval;

import java.util.Calendar;
import java.util.HashMap;

public class Activity {
    protected String name;
    protected Interval interval;
    protected double plannedHours;
    protected double workedHours;
    public HashMap<String, Developer> developerHM = new HashMap<String, Developer>();


    public Activity(String name) {
        if (name.length() > 1) {
            this.name = name;
            this.interval = new Interval();
        } else {
            throw new IllegalArgumentException("Activity names must be longer than one letter");
        }
    }

    public Interval getInterval() {
        return this.interval;
    }

    public double getPlannedHours() {
        return this.plannedHours;
    }

    public void setPlannedHours(double hours) {
        this.plannedHours = hours;
    }

    public void setWorkedHours(double workedHours) throws IllegalArgumentException {
        if (workedHours + this.workedHours <= plannedHours) {
            this.workedHours += workedHours;
        } else {
            this.workedHours += workedHours;
            System.out.println("Planned hours have been exceeded by " + (workedHours - plannedHours));
        }
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{ name: " + '\'' + name + '\'' +
                ", Start date: " + "Week: " + '\'' + interval.getStartWeek() + '\'' + ", Year: " +
                '\'' + interval.getStartYear() + '\'' + ", End date: " + "Week: " + '\'' + interval.getEndWeek() +
                '\'' + ", Year: " + '\'' + interval.getEndYear() + '\'' +
                ", plannedHours: " + plannedHours +
                ", workedHours: " + workedHours + " } \n";
    }

    public void addDeveloper(Developer developer) {
        developerHM.put(developer.getID(), developer);
        developer.addActivity(this);
    }
}
