package domain;

import de.vandermeer.asciitable.AsciiTable;
import time.Interval;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project {
    protected Developer projectLeader;

    protected ArrayList<Activity> activityList = new ArrayList<Activity>();
    protected boolean initialized;
    protected Interval interval;
    private String ID = "";
    protected String name;

    public Calendar getTestDate() {
        return testDate;
    }

    public Calendar testDate = new GregorianCalendar();

    public Project(String name) {
        if(name.length() > 1) {
            this.name = name;
            this.initialized = false;
            this.interval = new Interval();
        } else {
            throw new IllegalArgumentException("Project names must be longer than one letter ");
        }
    }

    public boolean activityExists(String activityName) {
        for (Activity activity: activityList) {
            if (activity.getName().equals(activityName)) {
                return true;
            }
        }
        return false;
    }

    public void addActivity(Activity activity) {
        this.activityList.add(activity);
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


    @Override
    public String toString() {
        if (projectLeader != null){
            return "Name:'" + name + '\'' +
                    ", ID: '" + ID + '\'' +
                    ", Project Leader: " + '\'' + projectLeader.getID() + '\'' +
                    ", Start date: " + '\'' + "Week: " + interval.getStartDate().get(Calendar.WEEK_OF_YEAR) + " Year: " +
                    interval.getStartDate().get(Calendar.YEAR) + '\'' +
                    ", Activity list: " + activityList.toString();
        } else {
            return "Name:'" + name + '\'' +
                    ", ID: '" + ID + '\'' +
                    ", Project Leader: " + '\'' + projectLeader + '\'' +
                    ", Start date: " + '\'' + "Week: " + interval.getStartDate().get(Calendar.WEEK_OF_YEAR) + " Year: " +
                    interval.getStartDate().get(Calendar.YEAR) + '\'' +
                    ", Activity list: " + activityList;
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

    public ArrayList<Activity> getActivityList() {
        return this.activityList;
    }

    public void setProjectStartDate(int year, int week) {
        if (!invalidActivityDates(true, year, week)) {
            if (getInterval().getEndDate() == null) {
                    getInterval().setStartDate(year, week);
            } else {
                testDate.set(Calendar.YEAR, year);
                testDate.set(Calendar.WEEK_OF_YEAR, week);
                if (getInterval().getEndDate().after(testDate)) {
                    getInterval().setStartDate(year, week);
                } else {
                    throw new IllegalArgumentException("Invalid date");
                }
            }
        } else {
            throw new IllegalArgumentException("Activity start dates are before the set date");
        }
    }

    public void setProjectEndDate(int year, int week) {
        if (!invalidActivityDates(false, year, week)) {
            if (getInterval().getStartDate() == null) {
                getInterval().setEndDate(year, week);
            } else {
                        testDate.set(Calendar.YEAR, year);
                        testDate.set(Calendar.WEEK_OF_YEAR, week);
                if (getInterval().getStartDate().before(testDate)) {
                    getInterval().setEndDate(year, week);
                } else {
                    throw new IllegalArgumentException("Invalid date");
                }
            }
        } else {
            throw new IllegalArgumentException("Activity end dates are after the set date");
        }
    }

    public boolean invalidActivityDates(boolean startOrEnd, int year, int week) {
        testDate.set(Calendar.YEAR, year);
        testDate.set(Calendar.WEEK_OF_YEAR, week);
        if (startOrEnd) {
            testDate.set(Calendar.SECOND, testDate.get(Calendar.SECOND)+1); //start week can be the same as projects
            for (Activity activity: activityList) {
                if (activity.getInterval().getStartDate() != null && (activity.getInterval().getStartDate().after(testDate))) {
                    return true;
                }
            }
        }
        else {
            testDate.set(Calendar.SECOND, testDate.get(Calendar.SECOND)-1); //start week can be the same as projects
            for (Activity activity: activityList) {
                if (activity.getInterval().getEndDate() != null && (activity.getInterval().getEndDate().before(testDate))) {
                    return true;
                }
            }
        }
        return false;
    }

    public Activity getActivity(String activityName) {
        for (Activity activity: activityList) {
            if (activityName.equals(activity.getName())) {
                return activity;
            }
        }
        return null;
    }

    public void setActivityStartDate(String activityName, int year, int week) {
        if (getActivity(activityName).getInterval().getEndDate() == null && activityStartDateIsValid(year, week)) {
            getActivity(activityName).getInterval().setStartDate(year, week);
        } else {
            testDate.set(Calendar.YEAR, year);
            testDate.set(Calendar.WEEK_OF_YEAR, week);
            if (getActivity(activityName).getInterval().getEndDate().after(testDate) && activityStartDateIsValid(year, week)) {
                getActivity(activityName).getInterval().setStartDate(year, week);
            } else {
                throw new IllegalArgumentException("Invalid date");
            }
        }
    }

    public void setActivityEndDate(String activityName, int year, int week) {
        if (getActivity(activityName).getInterval().getStartDate() == null && activityEndDateIsValid(year, week)) {
            getActivity(activityName).getInterval().setEndDate(year, week);
        } else {
            testDate.set(Calendar.YEAR, year);
            testDate.set(Calendar.WEEK_OF_YEAR, week);
            if (getActivity(activityName).getInterval().getStartDate().before(testDate) && activityEndDateIsValid(year, week)) {
                getActivity(activityName).getInterval().setEndDate(year, week);
            } else {
                throw new IllegalArgumentException("Invalid date");
            }
        }
    }

    public boolean activityStartDateIsValid(int year, int week) {
        testDate.set(Calendar.YEAR, year);
        testDate.set(Calendar.WEEK_OF_YEAR, week);
        testDate.set(Calendar.SECOND, testDate.get(Calendar.SECOND)+1); //start week can be the same as projects
        if (getInterval().getStartDate() != null && getInterval().getStartDate().before(testDate)) {
            return true;
        } else if (getInterval().getStartDate() == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean activityEndDateIsValid(int year, int week) {
        testDate.set(Calendar.YEAR, year);
        testDate.set(Calendar.WEEK_OF_YEAR, week);
        testDate.set(Calendar.SECOND, testDate.get(Calendar.SECOND)-1); //end week can be the same as projects
        if (getInterval().getEndDate() != null && getInterval().getEndDate().after(testDate)) {
            return true;
        } else if (getInterval().getEndDate() == null)  {
            return true;
        } else {
            return false;
        }
    }

}
