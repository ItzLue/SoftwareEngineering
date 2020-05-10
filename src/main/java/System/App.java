package System;

import domain.Activity;
import domain.Developer;
import domain.PersonalActivity;
import domain.Project;
import time.DateServer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class App {

    // the activeDeveloper is the developer using the app
    public Developer activeDeveloper;
    public HashMap<String, Developer> developerHM = new HashMap<String, Developer>();
    protected HashMap<String, Project> projectHM = new HashMap<String, Project>();
    public DateServer dateServer = new DateServer();

    /*
    Developer
     */

    // Christian
    public void registerDeveloper(Developer developer) {
        assert developer.getFirstName() != null : "Precondition first name";
        assert developer.getLastName() != null : "Precondition last name";
        developer.setID(makeDeveloperID(developer));
        developerHM.put(developer.getID(), developer);
        assert developerHM.get(developer.getID()).getID().equals(developer.getID()) : "Postcondition added";
    }

    // Joachim
    public void removeDeveloper(String developerID) {
        if (!developerHMContains(developerID)) {
            throw new NullPointerException("The developer with ID: " + developerID + " does not exist");
        }
        assert developerHMContains(developerID) : "Precondition developer ";

        for (Activity a : developerHM.get(developerID).getActivityList()) {
            a.developerHM.remove(developerID);
        }
        developerHM.remove(developerID);

        assert !developerHMContains(developerID) : "Post condition removed ";
    }

    // Christian
    public String makeDeveloperID(Developer developer) {
        String ID;
        if (developerHM.size() >= 9) {
            ID = developer.getFirstName().substring(0, 2).toUpperCase() + developer.getLastName().substring(0, 2).toUpperCase() + (developerHM.size() + 1);
        } else {
            ID = developer.getFirstName().substring(0, 2).toUpperCase() + developer.getLastName().substring(0, 2).toUpperCase() + 0 + (developerHM.size() + 1);
        }
        return ID;
    }

    // Joachim
    public void setActiveDeveloper(String ID) {
        if (developerHM.containsKey(ID)) {
            setActiveDeveloper(developerHM.get(ID));
        } else {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    public void setActiveDeveloper(Developer developer) {
        this.activeDeveloper = developer;
    }

    // Loui
    public Developer getActiveDeveloper() {
        if (activeDeveloper == null) {
            return null;
        }
        return activeDeveloper;
    }

    // Regin
    public ArrayList<Developer> searchAvailableDevelopers(String projectID, String activityName) throws IllegalAccessException {
        ArrayList<Developer> availableDevelopers = new ArrayList<>();
        if (isActiveDeveloperProjectLeader(projectID)) {
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

    public HashMap<String, Developer> getDeveloperHM() {
        return this.developerHM;
    }

    // Loui
    public void getDevValues() {
        for (Developer developer : developerHM.values()) {
            System.out.println(developer);
        }
    }

    /*
    Project
     */

    // Christian
    public void registerProject(Project project) {
        assert project.getName() != null : "Precondition name";
        project.setID(makeProjectID());
        projectHM.put(project.getID(), project);
        assert getProjectHM().containsKey(project.getID()) : "Postcondition name";
    }

    // Christian
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

    // Joachim
    public void removeProject(String projectID) throws IllegalAccessException {
        if (!projectHMContains(projectID)) {
            throw new NullPointerException("The project with ID: " + projectID + " does not exist");
        }
        assert projectHMContains(projectID) : "Precondition project";

        if (!isProjectInitialized(projectID) || isActiveDeveloperProjectLeader(projectID)) {
            if (projectHMContains(projectID)) {
                for (Activity a : projectHM.get(projectID).getActivityList()) {
                    removeActivityFromProject(a.getName(), projectID);
                    if (!projectHM.get(projectID).getActivityList().contains(a)) {
                        break;
                    }
                }
                projectHM.get(projectID).getActivityList().clear();
                projectHM.remove(projectID);
            }
        } else {
            throw new IllegalAccessException("Only the project leader has access to remove the project");
        }
        assert !projectHMContains(projectID) : "Postcondition removed";
    }

    // Joachim
    public void setProjectLeader(String projectID, String developerID) {
        if (projectHMContains(projectID) && developerHMContains(developerID)) {
            projectHM.get(projectID).setProjectLeader(developerHM.get(developerID));
        } else {
            throw new NullPointerException("Incorrect project ID or developer ID");
        }
    }

    // Christian
    public void setProjectName(String projectID, String name) throws IllegalAccessException {
        if (projectHMContains(projectID)) {
            if (!isProjectInitialized(projectID) || isActiveDeveloperProjectLeader(projectID)) {
                projectHM.get(projectID).setName(name);
            } else {
                throw new IllegalAccessException("Only the project leader can change the name of an initialized project");
            }
        } else {
            throw new NullPointerException("Project doesn't exist");
        }
    }

    // Christian
    public void setProjectDate(boolean startOrEnd, String projectID, int year, int week) throws IllegalAccessException {
        if (projectHMContains(projectID)) {
            if (!isProjectInitialized(projectID) || isActiveDeveloperProjectLeader(projectID)) {
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

    public HashMap<String, Project> getProjectHM() {
        return this.projectHM;
    }

    // Regin
    public void getProjectValues() {
        for (Project project : projectHM.values()) {
            System.out.println(project);
        }
    }

    /*
    Activity
     */

    // Joachim
    public void registerActivityToProject(Activity activity, String projectID) throws IllegalAccessException {
        assert projectHMContains(projectID) : "Precondition";
        if (projectHMContains(projectID)) {
            boolean nameExists = false;
            if (!isProjectInitialized(projectID) || isActiveDeveloperProjectLeader(projectID)) {
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

    // Joachim
    public void removeActivityFromProject(String activityName, String projectID) throws IllegalAccessException {
        if (!isProjectInitialized(projectID) || isActiveDeveloperProjectLeader(projectID)) {
            int counter = 0;
            if (projectHMContains(projectID)) {
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

    // Joachim
    public void setDeveloperToActivity(String activityName, String projectID, String developerID) throws IllegalAccessException {
        if (projectHMContains(projectID)) {
            if (!isProjectInitialized(projectID) || isActiveDeveloperProjectLeader(projectID)) {
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

    // Christian
    public void setActivityDate(boolean startOrEnd, String projectID, String activityName, int year, int week) throws IllegalAccessException {
        if (projectHMContains(projectID) && projectHM.get(projectID).activityExists(activityName)) {
            if (!isProjectInitialized(projectID) || isActiveDeveloperProjectLeader(projectID)) {
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

    // Christian
    public void setPlannedHoursForActivity(String activityName, String projectID, double hours) throws IllegalAccessException {
        if (!isProjectInitialized(projectID) || isActiveDeveloperProjectLeader(projectID)) {
            projectHM.get(projectID).getActivity(activityName).setPlannedHours(hours);
        } else {
            throw new IllegalAccessException("You don't have access");
        }
    }

    public double getPlannedHoursForActivity(String activityName, String projectID) {
        return projectHM.get(projectID).getActivity(activityName).getPlannedHours();
    }


    // Loui
    public void setWorkedHoursForActivity(String activityName, String projectID, double hours) throws IllegalAccessException {
        if (projectHM.get(projectID).getActivity(activityName).developerHM.containsKey(activeDeveloper.getID())) {
            developerHM.get(activeDeveloper.getID()).setWorkedHours(hours, activeDeveloper.getActivity(activityName));
        } else {
            throw new IllegalAccessException("You dont have access");
        }
    }

    // Joachim
    public void addPersonalActivity(PersonalActivity personalActivity, String developerID) throws IllegalAccessException {
        assert developerHMContains(developerID) : "Precondition developer";
        if (isActiveDeveloper(developerID)) {
            activeDeveloper.addPersonalActivity(personalActivity);
        } else {
            throw new IllegalAccessException("You have to be an active developer to add personal activities");
        }
        assert developerHM.get(developerID).getPersonalActivityList().size() > 0 : "Post condition personal activity";

    }

    // Regin
    public void setPersonalActivityDate(boolean startOrEnd, String personalActivityName, int year, int week) {
        assert activeDeveloper != null : "Precondtion developer";
        if (startOrEnd) {
            activeDeveloper.getPersonalActivity(personalActivityName).getInterval().setStartDate(year, week);
        } else {
            activeDeveloper.getPersonalActivity(personalActivityName).getInterval().setEndDate(year, week);
        }
        assert activeDeveloper.getPersonalActivity(personalActivityName).getInterval().getStartWeek() != 0 || activeDeveloper.getPersonalActivity(personalActivityName).getInterval().getStartYear() != 0;
    }

    /*
    Calendar
     */

    public Calendar getDate() {
        return dateServer.getDate();
    }

    /*
    boolean conditions
     */

    public boolean isActiveDeveloperProjectLeader(String projectID) {
        return projectHM.get(projectID).getProjectLeader() == activeDeveloper;
    }

    public boolean isProjectInitialized(String projectID) {
        return projectHM.get(projectID).isInitialized();
    }

    public boolean isActiveDeveloper(String developerID) {
        return developerHM.get(developerID) == activeDeveloper;
    }

    public boolean projectHMContains(String projectID) {
        return projectHM.containsKey(projectID);
    }

    public boolean developerHMContains(String developerID) {
        return developerHM.containsKey(developerID);
    }
}
