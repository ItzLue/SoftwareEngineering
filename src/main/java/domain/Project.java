package domain;

import de.vandermeer.asciitable.AsciiTable;
import io.bretty.console.view.ActionView;
import time.Interval;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project {
    AsciiTable at = new AsciiTable();
    protected Developer projectLeader;

    protected ArrayList<Activity> activityHM = new ArrayList<Activity>();
    protected boolean initialized;
    protected Interval interval;
    private String ID = "";
    protected String name;
    protected Calendar testDate = new GregorianCalendar();

    public Project(String name) {
        this.name = name;
        this.initialized = false;
        this.interval = new Interval();
    }



    public void addActivity(Activity activity) {
        this.activityHM.add(activity);
    }

    public void initProject() {
        this.initialized = true;
    }

    public void setProjectLeader(Developer developer) {
        if (!initialized) {
            initProject();
        }
        this.projectLeader = developer;
    }

    public Developer getProjectLeader() {
        return projectLeader;
    }

    public void setName(String name) { this.name = name; }

    public void setID(String ID) {
        if (this.ID.equals("")) {
            this.ID = ID;
        }
    }

    public String getID() {
        return this.ID;
    }

    public String getName() {
        return name;
    }

    public String Table (){
        at.addRule();
        at.addRow("Name","ID","Project leader","Activity list");
        at.addRule();
        at.addRow(name,ID,projectLeader.getID(),activityList.toString());
        at.addRule();
        return at.render();
    }

    @Override
    public String toString() {
        if (projectLeader != null){
            return "Name:'" + name + '\'' +
                    ", ID: '" + ID + '\'' +
                    ", Project Leader: " + '\'' +projectLeader.getID() + '\'' +
                    ", Activity list: " + activityHM.toString();
        } else {
            return "Name:'" + name + '\'' +
                    ", ID: '" + ID + '\'' +
                    ", Project Leader: " + projectLeader + '\'' +
                    ", Activity list: " + activityHM.toString();
        }
    }

    public boolean isInitialized() {
        return initialized;
    }

    public Interval getInterval() {
        return this.interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public ArrayList<Activity> getActivityHM() {
        return this.activityHM;
    }

    public void setProjectStartDate(int week, int year) {
        if (!invalidActivityDates(true, week, year)) {
            if (getInterval().getEndDate() == null) {
                    getInterval().setStartDate(week, year);
            } else {
                testDate.set(Calendar.YEAR, year);
                testDate.set(Calendar.WEEK_OF_YEAR, week);
                if (getInterval().getEndDate().after(testDate)) {
                    getInterval().setStartDate(week, year);
                } else {
                    throw new IllegalArgumentException("Invalid date");
                }
            }
        } else {
            throw new IllegalArgumentException("Activity start dates are before the set date");
        }
    }

    public void setProjectEndDate(int week, int year) {
        if (!invalidActivityDates(true, week, year)) {
            if (getInterval().getStartDate() == null) {
                getInterval().setEndDate(week, year);
            } else {
                testDate.set(Calendar.YEAR, year);
                testDate.set(Calendar.WEEK_OF_YEAR, week);
                if (getInterval().getStartDate().before(testDate)) {
                    getInterval().setEndDate(week, year);
                } else {
                    throw new IllegalArgumentException("Invalid date");
                }
            }
        } else {
            throw new IllegalArgumentException("Activity end dates are after the set date");
        }
    }
    
    public boolean invalidActivityDates(boolean startOrEnd, int week, int year) {
        boolean invalid = false;
        testDate.set(Calendar.YEAR, year);
        testDate.set(Calendar.WEEK_OF_YEAR, week);
        if (startOrEnd) {
            for (Activity activity: activityHM) {
                if (activity.getInterval().getStartDate().before(testDate)) {
                    invalid = true;
                }
            }
        }
        else {
            for (Activity activity: activityHM) {
                if (activity.getInterval().getEndDate().after(testDate)) {
                    invalid = true;
                }
            }
        }
        return invalid;
    }

    public void setActivityStartDate(Activity activity, int year, int week) {
        if (dateIsValid(year,week)) {
            activity.getInterval().setStartDate(year,week);
        }
    }

    public void setActivityEndDate(Activity activity, int year, int week) {
        if (dateIsValid(year,week)) {
            activity.getInterval().setEndDate(year,week);
        }
    }

    public boolean dateIsValid(int year, int week) {
        Calendar calendarStart = new GregorianCalendar();
        calendarStart.set(Calendar.YEAR, year);
        calendarStart.set(Calendar.WEEK_OF_YEAR, week);

        Calendar calendarEnd = new GregorianCalendar();
        calendarEnd.set(Calendar.YEAR, year);
        calendarEnd.set(Calendar.WEEK_OF_YEAR, week-1);

        if (getInterval().getStartDate().before(calendarStart) && getInterval().getEndDate().after(calendarEnd) ) {
            return true;
        } else {
            return false;
        }
    }

}
