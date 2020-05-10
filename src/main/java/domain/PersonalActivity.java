// Regin
package domain;

import time.Interval;

public class PersonalActivity {
    protected String name;
    protected Interval interval;

    public PersonalActivity(String name) {
        if (name.length() > 1) {
            this.name = name;
            this.interval = new Interval();
        } else {
            throw new IllegalArgumentException("Activity names must be longer than one letter");
        }
    }

    /*
    toString method
     */

    @Override
    public String toString() {
        return "{ name: " + '\'' + name + '\'' +
                ", Start date: " + "Week: " + '\'' + interval.getStartWeek() + '\'' + ", Year: " +
                '\'' + interval.getStartYear() + '\'' + ", End date: " + "Week: " + '\'' + interval.getEndWeek() +
                '\'' + ", Year: " + '\'' + interval.getEndYear() + '\'' + " } \n";
    }

    /*
    Get/Set methods
     */

    public String getName() {
        return this.name;
    }

    public Interval getInterval() {
        return this.interval;
    }

}
