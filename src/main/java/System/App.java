package System;

import domain.Activity;
import domain.Developer;
import domain.Project;
import time.DateServer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Calendar;
import java.util.HashMap;

public class App {

    public Developer activeDeveloper;
    public HashMap<String, Developer> developerHM = new HashMap<String, Developer>();
    protected HashMap<String, Project> projectHM = new HashMap<String, Project>();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    protected DateServer dateServer = new DateServer();

    public void registerDeveloper(Developer developer) {
        String ID;
        if (developerHM.size() > 9) {
            ID = developer.getFirstName().substring(0,2).toUpperCase() + developer.getLastName().substring(0,2).toUpperCase() + (developerHM.size()+1);
        } else {
            ID = developer.getFirstName().substring(0,2).toUpperCase() + developer.getLastName().substring(0,2).toUpperCase() + 0 + (developerHM.size()+1);
        }
        developer.setID(ID);
        developerHM.put(developer.getID(),developer);
    }

    public void registerActivityToProject(String name, String projectID) {
        if (projectHM.containsKey(projectID)) {
           Activity activity = new Activity(name);
           boolean nameExists = false;
            for (Activity a : projectHM.get(projectID).getActivityList()) {
                if (a.getName().equals(name)) {
                    nameExists = true;
                }
            }
            if (!nameExists) {
                projectHM.get(projectID).getActivityList().add(activity);
            }
            else {
                System.out.println("activity name already exists");
            }
        }
    }

//    public static void main(String[] args) {
//      new  App();
//    }

    public void registerProject(Project project) {
        project.setID(makeProjectID());
        projectHM.put(project.getID(),project);
    }

//    public App() {
//        registerDeveloper(new Developer("Hans","Hansen"));
//        System.out.println(developerHM.get("HAHA01"));
//    }

    public String makeProjectID() {
        String weekNumber = Integer.toString(getDate().get(Calendar.WEEK_OF_YEAR));
        String year = Integer.toString(getDate().get(Calendar.YEAR)).substring(2);
        String runningNumber = Integer.toString(projectHM.size()+1);
        return year + weekNumber + runningNumber;
    }

    public void setProjectLeader(Project project, Developer developer) {
        if (project.getProjectLeader() != null || project.getProjectLeader() == this.activeDeveloper) {
            project.setProjectLeader(developer);
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
//       Stream.of(developerHM.values().toString()).forEach(System.out::println);
        for (Developer developer : developerHM.values()) {
            System.out.println(developer);
        }
    }
    public void getProjectValues(){
        for (Project project : projectHM.values()){
            System.out.println(project);
        }
    }

    public Calendar getDate() {
        return dateServer.getDate();
    }

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getActiveDeveloperID() {
        if(activeDeveloper == null) {
            return null;
        }
        return activeDeveloper.getFirstName();
    }

    public Developer getActiveDeveloper() {
        if(activeDeveloper == null) {
            return null;
        }
        return activeDeveloper;
    }

    public boolean devHmEmpty(){
        if (developerHM.isEmpty()){
            System.out.println("Developer list is empty");
        } return true;
    }
    public boolean projectHmEmpty(){
        if (projectHM.isEmpty()){
            System.out.println("No projects to show");
        } return true;
    }

    public void setProjectName(String ID, String name) {
        if (projectHM.containsKey(ID)) {
            projectHM.get(ID).setName(name);
        }
        //if (!initialized || Activedeveloper == this.projectLeader)
    }

    public void setInterval() {

    }


}
