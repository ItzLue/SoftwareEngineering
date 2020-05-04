package System;

import domain.Activity;
import domain.Developer;
import domain.Project;
import time.DateServer;

import java.util.Calendar;
import java.util.HashMap;

public class App {

    public Developer activeDeveloper;
    public HashMap<String, Developer> developerHM = new HashMap<String, Developer>();
    protected HashMap<String, Project> projectHM = new HashMap<String, Project>();
    protected DateServer dateServer = new DateServer();

    public void registerDeveloper(Developer developer){
        developer.setID(makeDeveloperID(developer));
        developerHM.put(developer.getID(), developer);
    }

    public String makeDeveloperID(Developer developer) {
        String ID;
        if (developerHM.size() > 9) {
            ID = developer.getFirstName().substring(0, 2).toUpperCase() + developer.getLastName().substring(0, 2).toUpperCase() + (developerHM.size() + 1);
        } else {
            ID = developer.getFirstName().substring(0, 2).toUpperCase() + developer.getLastName().substring(0, 2).toUpperCase() + 0 + (developerHM.size() + 1);
        }
        return ID;
    }

    public void registerActivityToProject(Activity activity, String projectID){
        if (projectHM.containsKey(projectID)) {
            boolean nameExists = false;
            for (Activity a : projectHM.get(projectID).getActivityHM()) {
                if (a.getName().equals(activity.getName())) {
                    nameExists = true;
                }
            }
            if (!nameExists) {
                projectHM.get(projectID).getActivityHM().add(activity);
            } else {
                System.out.println("activity name already exists");
            }
        }
    }

    public void removeActivityFromProject(Activity activity, String projectID) {
        if (projectHM.containsKey(projectID)) {
            for (Activity a : projectHM.get(projectID).getActivityHM()) {
                if (a.equals(activity)) {
                    projectHM.get(projectID).getActivityHM().remove(a);
                }
            }
        }
    }

    public void registerProject(Project project) {
        project.setID(makeProjectID());
        projectHM.put(project.getID(), project);
    }

    public App() {
        registerDeveloper(new Developer("Hans","Hansen"));
        registerProject(new Project("Minecraft"));
    }

    public String makeProjectID() {
        String weekNumber = Integer.toString(getDate().get(Calendar.WEEK_OF_YEAR));
        String year = Integer.toString(getDate().get(Calendar.YEAR)).substring(2);
        String runningNumber = "";
        if (projectHM.size() > 9) {
            runningNumber = "0" + Integer.toString(projectHM.size() + 1);
        } else {
            runningNumber = Integer.toString(projectHM.size() + 1);
        }
        return year + weekNumber + runningNumber;
    }

    public void setProjectLeader(String projectID, String developerID) {
        if (projectHM.containsKey(projectID) && developerHM.containsKey(developerID)) {
//            if (projectHM.get(projectID).getProjectLeader() == null || projectHM.get(projectID).getProjectLeader() == this.activeDeveloper) {
            projectHM.get(projectID).setProjectLeader(developerHM.get(developerID));

//            }
        } else {
            throw new NullPointerException("Projæ dont exis or developer");
        }
    }

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    public void setActiveDeveloper(String ID) {
        if (developerHM.containsKey(ID)) {
            setActiveDeveloper(developerHM.get(ID));
        }
    }

    public void setActiveDeveloper(Developer developer) {
        this.activeDeveloper = developer;
    }

    public HashMap<String, Developer> getDeveloperHM() {
        return this.developerHM;
    }

    public HashMap<String, Project> getProjectHM() {
        return this.projectHM;
    }

    public void getDevValues() {
        for (Developer developer : developerHM.values()) {
            System.out.println(developer);
        }
    }

    public void getProjectValues() {
        for (Project project : projectHM.values()) {
            System.out.println(project);
        }
    }

    public Calendar getDate() {
        return dateServer.getDate();
    }

    public Developer getActiveDeveloper() {
        if (activeDeveloper == null) {
            return null;
        }
        return activeDeveloper;
    }

    public void setProjectName(String ID, String name) {
        if (projectHM.containsKey(ID)) {
            projectHM.get(ID).setName(name);
        }
    }

    public void setInterval() {

    }

    public void setProjectStartDate(String projectID, int week, int year) throws IllegalAccessException {
        if (projectHM.containsKey(projectID)) {
            if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
                projectHM.get(projectID).setProjectStartDate(week, year);
            } else {
                throw new IllegalAccessException("You dont have access");
            }
        } else {
            throw new NullPointerException("Project doesnt exist");
        }
    }

    public void setProjectEndDate(String projectID, int week, int year) throws IllegalAccessException {
        if (projectHM.containsKey(projectID)) {
            if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
                projectHM.get(projectID).setProjectEndDate(week, year);
            } else {
                throw new IllegalAccessException("You dont have access");
            }
        } else {
            throw new NullPointerException("Project doesnt existP");
        }
    }

    public void setActivityStartDate(String projectID, String activityName, int week, int year) {
        if (projectHM.containsKey(projectID) && projectHM.get(projectID).getActivityHM().contains(activityName)) {
            if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
                projectHM.get(projectID).setProjectEndDate(week, year);
            } else {
                throw new IllegalAccessException("You dont have access");
            }
        } else {
            throw new NullPointerException("Project doesnt existP");
        }
    }

    public void setActivityEndDate() {

    }

}
