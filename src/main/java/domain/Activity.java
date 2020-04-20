package domain;

import time.Interval;

public class Activity {
    protected String description;
    protected String ID;
    protected Interval interval;

    public Activity() {

    }

    public String getID() {
        return this.ID;
    }

}
