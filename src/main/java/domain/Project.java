package domain;

import time.Interval;

import java.util.ArrayList;

public class Project {

    protected Developer projectLeader;

    protected ArrayList<Activity> activityList = new ArrayList<Activity>();
    protected boolean initialized;
    protected Interval interval;
    private String ID = "";
    protected String name;

    public Project(String name) {
        this.name = name;
        this.initialized = false;
        this.interval = new Interval();
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
                    ", Project Leader: " + '\'' +projectLeader.getID() + '\'' +
                    ", Activity list: " + activityList.toString();
        } else {
            return "Name:'" + name + '\'' +
                    ", ID: '" + ID + '\'' +
                    ", Project Leader: " + projectLeader + '\'' +
                    ", Activity list: " + activityList.toString();
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

}
