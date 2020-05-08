package domain;

import time.Interval;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PersonalActivity {
    protected String name;
    protected Interval interval;

    public Calendar testDate = new GregorianCalendar();

    public PersonalActivity(String name) {
        if (name.length() > 1) {
            this.name = name;
            this.interval = new Interval();
        } else {
            throw new IllegalArgumentException("Activity names must be longer than one letter");
        }
    }

    public String getName() {
        return this.name;
    }

    public Interval getInterval() {
        return this.interval;
    }

    @Override
    public String toString() {
        return "{ name: " + '\'' + name + '\'' +
                ", Start date: " + "Week: " + '\'' + interval.getStartWeek() + '\'' + ", Year: " +
                '\'' + interval.getStartYear() + '\'' + ", End date: " + "Week: " + '\'' + interval.getEndWeek() +
                '\'' + ", Year: " + '\'' + interval.getEndYear() + '\'' + " } \n";
    }


}
