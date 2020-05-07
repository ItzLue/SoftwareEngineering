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
    public DateServer dateServer = new DateServer();

    /*
    Developer
     */
    public void registerDeveloper(Developer developer) {
        developer.setID(makeDeveloperID(developer));
        developerHM.put(developer.getID(), developer);
    }

    public String makeDeveloperID(Developer developer) {
        String ID;
        if (developerHM.size() >= 9) {
            ID = developer.getFirstName().substring(0, 2).toUpperCase() + developer.getLastName().substring(0, 2).toUpperCase() + (developerHM.size() + 1);
        } else {
            ID = developer.getFirstName().substring(0, 2).toUpperCase() + developer.getLastName().substring(0, 2).toUpperCase() + 0 + (developerHM.size() + 1);
        }
        return ID;
    }

    public void setActiveDeveloper(String ID) {
        if (developerHM.containsKey(ID)) {
            setActiveDeveloper(developerHM.get(ID));
        } else {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    public Developer getActiveDeveloper() {
        if (activeDeveloper == null) {
            return null;
        }
        return activeDeveloper;
    }

    public void setActiveDeveloper(Developer developer) {
        this.activeDeveloper = developer;
    }

    public HashMap<String, Developer> getDeveloperHM() {
        return this.developerHM;
    }

    public void getDevValues() {
        for (Developer developer : developerHM.values()) {
            System.out.println(developer);
        }
    }
    public void setDevhours(String developerID,double hours){
        developerHM.get(developerID).setWorkHours(hours);
    }

    public void setWorkHoursForActivity(String developerID, String activityName, String projectID, double hours) throws NullPointerException, IllegalAccessException {
        if (projectHM.get(projectID).getActivity(activityName).developerHM.containsKey(developerID) || !getActiveDeveloper().getID().equals(developerID)){
            developerHM.get(developerID).setWorkHours(hours);
            projectHM.get(projectID).getActivity(activityName).setWorkedHours(developerHM.get(developerID).getWorkHours());
        } else {
            throw new NullPointerException("The developer with ID " + developerID + " is not in the activity " + activityName);
        }
    }
    /*
    Project
     */

    public void registerProject(Project project) {
        project.setID(makeProjectID());
        projectHM.put(project.getID(), project);
    }

    public String makeProjectID() {
        String weekNumber = Integer.toString(getDate().get(Calendar.WEEK_OF_YEAR));
        String year = Integer.toString(getDate().get(Calendar.YEAR)).substring(2);
        String runningNumber = "";
        if (projectHM.size() < 9) {
            runningNumber = "0" + Integer.toString(projectHM.size() + 1);
        } else {
            runningNumber = Integer.toString(projectHM.size() + 1);
        }
        return year + weekNumber + runningNumber;
    }

    public void setProjectLeader(String projectID, String developerID) {
        if (projectHM.containsKey(projectID) && developerHM.containsKey(developerID)) {
            projectHM.get(projectID).setProjectLeader(developerHM.get(developerID));
        } else {
            throw new NullPointerException("Incorrect project ID or developer ID");
        }
    }

    public HashMap<String, Project> getProjectHM() {
        return this.projectHM;
    }

    public void getProjectValues() {
        for (Project project : projectHM.values()) {
            System.out.println(project);
        }
    }

    public void setProjectDate(boolean startOrEnd, String projectID, int year, int week) throws IllegalAccessException {
        if (projectHM.containsKey(projectID)) {
            if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
                if (startOrEnd) {
                    projectHM.get(projectID).setProjectStartDate(year, week);
                } else {
                    projectHM.get(projectID).setProjectEndDate(year, week);
                }
            } else {
                throw new IllegalAccessException("You don't have access");
            }
        } else {
            throw new NullPointerException("Project doesn't exist");
        }
    }

    public void setProjectName(String ID, String name) {
        if (projectHM.containsKey(ID)) {
            projectHM.get(ID).setName(name);
        }
    }

    /*
    Activity
     */
    public void registerActivityToProject(Activity activity, String projectID) {
        if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
            boolean nameExists = false;
            try {
                if (projectHM.containsKey(projectID)) {

                    for (Activity a : projectHM.get(projectID).getActivityList()) {
                        if (a.getName().equals(activity.getName())) {
                            nameExists = true;
                            break;
                        }
                    }
                    if (!nameExists) {
                        projectHM.get(projectID).getActivityList().add(activity);
                    } else {
                        throw new IllegalAccessException("Not a valid name");
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                if (e instanceof IllegalArgumentException) {
                    System.out.println(e.getMessage());
                }
                if (e instanceof IllegalAccessException) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void removeActivityFromProject(Activity activity, String projectID) {
        if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
            int counter = 0;
            if (projectHM.containsKey(projectID)) {
                for (Activity a : projectHM.get(projectID).getActivityList()) {
                    if (a.equals(activity)) {
                        projectHM.get(projectID).getActivityList().remove(a);
                        counter++;
                        if (projectHM.get(projectID).getActivityList().size() == 0) {
                            break;
                        }
                    }
                }
                if (counter == 0) {
                    throw new NullPointerException("The activity does not exist in the project with ID: " + projectID);
                }
            } else {
                throw new NullPointerException("The project with ID: " + projectID + " does not exist");
            }
        }
    }

    public void setActivityDate(boolean startOrEnd, String projectID, String activityName, int year, int week) throws IllegalAccessException {
        if (projectHM.containsKey(projectID) && projectHM.get(projectID).activityExists(activityName)) {
            if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
                if (startOrEnd) {
                    projectHM.get(projectID).setActivityStartDate(activityName, year, week);
                } else {
                    projectHM.get(projectID).setActivityEndDate(activityName, year, week);
                }
            } else {
                throw new IllegalAccessException("You don't have access");
            }
        } else {
            throw new NullPointerException("Project doesn't exist");
        }
    }

    public void setDeveloperToActivity(String activityName, String projectID, String developerID) throws Exception {
        if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
            projectHM.get(projectID).getActivity(activityName).addDeveloper(developerHM.get(developerID));
            developerHM.get(developerID).addActivity(projectHM.get(projectID).getActivity(activityName));
        } else {
            throw new IllegalAccessException("You don't have access");
        }
    }

    public void setPlannedHoursForActivity(String activityName,String projectID, double hours) throws IllegalAccessException {
        if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
            projectHM.get(projectID).getActivity(activityName).setPlannedHours(hours);
        }else {
            throw new IllegalAccessException("You don't have access");
        }
    }

    public double getPlannedHoursForActivity(String activityName, String projectID) throws IllegalAccessException {
        if (projectHM.get(projectID).getActivity(activityName).getPlannedHours() == 0) {
            //something
        } else {
           return projectHM.get(projectID).getActivity(activityName).getPlannedHours();
        }
        return 0;
    }
    public Calendar getDate() {
        return dateServer.getDate();
    }
    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

//    public void setInterval() {
//
//    }
}
