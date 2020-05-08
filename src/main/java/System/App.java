package System;

import domain.Activity;
import domain.Developer;
import domain.PersonalActivity;
import domain.Project;
import time.DateServer;
import time.Interval;

import java.util.ArrayList;
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
        assert developer.getFirstName() != null : "Precondition first name";
        assert developer.getLastName() != null : "Precondition last name";
        developer.setID(makeDeveloperID(developer));
        developerHM.put(developer.getID(), developer);
        assert developerHM.get(developer.getID()).getID().equals(developer.getID()) : "Postcondition added";
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
    public void removeDeveloper(String developerID) {
        assert developerHM.containsKey(developerID) : "Precondition developer ";
        
        for (Activity a : developerHM.get(developerID).getActivityList()) {
            a.developerHM.remove(developerID);

        }
        developerHM.remove(developerID);
        assert !developerHM.containsKey(developerID): "Post condition removed ";
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
        for (Developer developer : developerHM.values()) { System.out.println(developer); }
    }

    public void setWorkedHoursForActivity(String activityName, String projectID, double hours) throws IllegalAccessException {
        if (projectHM.get(projectID).getActivity(activityName).developerHM.containsKey(activeDeveloper.getID())) {
            developerHM.get(activeDeveloper.getID()).setWorkedHours(hours, activeDeveloper.getActivity(activityName));
        } else {
            throw new IllegalAccessException("You dont have access");
        }
    }

    /*
    Project
     */

    public void registerProject(Project project) {
        assert project.getName() != null : "Precondition name";
        project.setID(makeProjectID());
        projectHM.put(project.getID(), project);
        assert getProjectHM().containsKey(project.getID()) : "Postcondition name";
    }

    public String makeProjectID() {
        String weekNumber = Integer.toString(getDate().get(Calendar.WEEK_OF_YEAR));
        String year = Integer.toString(getDate().get(Calendar.YEAR)).substring(2);
        String runningNumber = "";
        if (projectHM.size() < 9) {
            runningNumber = "0" + (projectHM.size() + 1);
        } else {
            runningNumber = Integer.toString(projectHM.size() + 1);
        }
        return year + weekNumber + runningNumber;
    }
    public void removeProject(String projectID) throws IllegalAccessException{
        assert projectHM.containsKey(projectID) : "Precondition project";
        if(!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
            if (projectHM.containsKey(projectID)) {
                for(Activity a : projectHM.get(projectID).getActivityList()) {
                    removeActivityFromProject(a.getName(),projectID);
                    if(!projectHM.get(projectID).getActivityList().contains(a)) {
                        break;
                    }
                }
                projectHM.get(projectID).getActivityList().clear();
                projectHM.remove(projectID);
            }
        }else {
            throw new IllegalAccessException("Only the project leader has access to remove the project");
        }
        assert !projectHM.containsKey(projectID) : "Postcondition removed";
    }

    public void setProjectLeader(String projectID, String developerID) {
        if (projectHM.containsKey(projectID) && developerHM.containsKey(developerID)) {
            projectHM.get(projectID).setProjectLeader(developerHM.get(developerID));
        } else {
            throw new NullPointerException("Incorrect project ID or developer ID");
        }
    }

    public HashMap<String, Project> getProjectHM() { return this.projectHM; }

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

    public void setProjectName(String projectID, String name) {
        if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
            if (projectHM.containsKey(projectID)) {
                projectHM.get(projectID).setName(name);
            } else {
                throw new NullPointerException("Project with ID: " + projectID + " doesn't exist");
            }
        } else {
            throw new NullPointerException("Only the project leader can change the name of an initialized project");
        }
    }

    /*
    Activity
     */
    public void registerActivityToProject(Activity activity, String projectID) throws IllegalAccessException {
        assert projectHM.containsKey(projectID) : "Precondition";
        if (projectHM.containsKey(projectID)) {
            boolean nameExists = false;
                if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
                    for (Activity a : projectHM.get(projectID).getActivityList()) {
                        if (a.getName().equals(activity.getName())) {
                            nameExists = true;
                            break;
                        }
                    }
                    if (!nameExists) {
                        projectHM.get(projectID).getActivityList().add(activity);
                    } else {
                        throw new IllegalArgumentException("Not a valid name");
                    }
                } else {
                    throw new IllegalAccessException("Only the project leader has access to add activities when the project is initialized");
                }
        } else {
            throw new NullPointerException("Project does not exist");
        }
    }

    public void removeActivityFromProject(String activityName, String projectID) throws IllegalAccessException {
        if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
            int counter = 0;
            if (projectHM.containsKey(projectID)) {
                for (Activity a : projectHM.get(projectID).getActivityList()) {
                    if (a.getName().equals(activityName)) {
                        // Removing the activities from the developers activitylist
                        for (Developer d : a.developerHM.values()) {
                            d.getActivityList().remove((a));
                        }
                        projectHM.get(projectID).getActivityList().remove(a);

                        counter++;
                        if (!projectHM.get(projectID).getActivityList().contains(a)) {
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
        } else {
            throw new IllegalAccessException("Only the project leader has access to remove activities from a project");
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

    public void setDeveloperToActivity(String activityName, String projectID, String developerID) throws IllegalAccessException {
        if(projectHM.containsKey(projectID)) {
            if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
                if (!projectHM.get(projectID).getActivity(activityName).developerHM.containsKey(developerID)) {
                    projectHM.get(projectID).getActivity(activityName).addDeveloper(developerHM.get(developerID));
                    developerHM.get(developerID).addActivity(projectHM.get(projectID).getActivity(activityName));
                } else {
                    throw new IllegalArgumentException("The developer is already assigned to this activity");
                }
            } else {
                throw new IllegalAccessException("Only the project leader has access to assign developers to activities");
            }
        } else {
            throw new NullPointerException("The project with ID: " + projectID + " does not exist");
        }
    }

    public void setPlannedHoursForActivity(String activityName, String projectID, double hours) throws IllegalAccessException {
        if (!projectHM.get(projectID).isInitialized() || projectHM.get(projectID).getProjectLeader() == activeDeveloper) {
            projectHM.get(projectID).getActivity(activityName).setPlannedHours(hours);
        } else {
            throw new IllegalAccessException("You don't have access");
        }
    }

    public double getPlannedHoursForActivity(String activityName, String projectID) {
        return projectHM.get(projectID).getActivity(activityName).getPlannedHours();
    }

    public ArrayList<Developer> searchAvailableDevelopers(String projectID, String activityName) throws IllegalAccessException {
        ArrayList<Developer> availableDevelopers = new ArrayList<>();
        if(activeDeveloper == projectHM.get(projectID).getProjectLeader()) {
            for (Developer developer : developerHM.values()) {

                if (!projectHM.get(projectID).getActivity(activityName).developerHM.containsValue(developer) && developer.isAvailable(projectHM.get(projectID).getActivity(activityName).getInterval())) {
                    availableDevelopers.add(developer);
                }
            }
        } else {
            throw new IllegalAccessException("Only the project leader may search for available developers");
        }
        return availableDevelopers;
    }

    public void addPersonalActivity(PersonalActivity personalActivity, String developerID) throws IllegalAccessException {
        assert developerHM.containsKey(developerID) : "Precondition developer";
        if(developerHM.get(developerID) == activeDeveloper) {
            activeDeveloper.addPersonalActivity(personalActivity);
        } else{
            throw new IllegalAccessException("You have to be an active developer to add personal activities");
        }
        assert activeDeveloper.getPersonalActivityList().size() == 1;
    }

    public void setPersonalActivityDate(boolean startOrEnd, String personalActivityName, int year, int week) {
        assert activeDeveloper != null : "Precondtion developer";
        if(startOrEnd) {
            activeDeveloper.getPersonalActivity(personalActivityName).getInterval().setStartDate(year,week);
        } else {
            activeDeveloper.getPersonalActivity(personalActivityName).getInterval().setEndDate(year,week);
        }
        assert activeDeveloper.getPersonalActivity(personalActivityName).getInterval().getStartWeek() != 0 || activeDeveloper.getPersonalActivity(personalActivityName).getInterval().getStartYear() != 0;
    }

    public Calendar getDate() { return dateServer.getDate(); }

    public void setDateServer(DateServer dateServer) { this.dateServer = dateServer; }

}
