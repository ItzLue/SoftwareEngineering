package domain;

import time.Interval;

import java.util.ArrayList;

public class Developer {
    protected String firstName;
    protected String lastName;
    protected String ID;
    protected ArrayList<Activity> activityList = new ArrayList<Activity>();
    protected ArrayList<PersonalActivity> personalActivityList = new ArrayList<>();
    protected double workedHours;

    // Joachim
    public Developer(String firstName, String lastName) {
        if(firstName.matches("^[a-zA-Z]*$") && lastName.matches("^[a-zA-Z]*$") && firstName.length() > 1 && lastName.length() > 1) {
            this.firstName = firstName;
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Developer names must be 2 letters or larger and can only contain alphabetic letters");
        }
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void addPersonalActivity(PersonalActivity activity) {
        personalActivityList.add(activity);
    }

    public void removePersonalActivity(PersonalActivity activity) {
        personalActivityList.remove(activity);
    }

    /*
    Boolean condition
     */

    // Regin
    public boolean isAvailable(Interval interval) {
        int counter = 0;
        for (PersonalActivity personalActivity : getPersonalActivityList()) {
            if(personalActivity.getInterval().getEndDate().before(interval.getStartDate()) || personalActivity.getInterval().getStartDate().after(interval.getEndDate())) {
                continue;
            } else {
                return false;
            }
        }
        for (Activity activity : getActivityList()) {
            if(activity.getInterval().getEndDate().before(interval.getStartDate()) || activity.getInterval().getStartDate().after(interval.getEndDate())) {
                continue;
            } else {
                counter++;
            }
        }
        return counter<10;
    }

    /*
    toString method
     */

    // Loui
    @Override
    public String toString() {
        return "[ First name:'" + firstName + '\'' +
                ", Last name:'" + lastName + '\'' +
                ", ID:'" + ID + '\'' + " ]";
    }

    /*
    Get/Set methods
     */

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    // Chritian
    public void setWorkedHours(double workedHours, Activity activity) throws IllegalAccessException {
        if (activity.getInterval().getStartDate() != null) {
            if (workedHours > 0.5) {
                this.workedHours = workedHours;
                activity.setWorkedHours(workedHours);
            } else {
                throw new IllegalArgumentException("Please provide a valid input");
            }
        } else {
            throw new IllegalArgumentException("The activity is not started yet");
        }
    }

    // Loui
    public Activity getActivity(String activityName) {
        for (Activity activity: activityList) {
            if (activityName.equals(activity.getName())) { return activity; }
        }
        return null;
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    // Regin
    public PersonalActivity getPersonalActivity(String personalActivityName) {
        for (PersonalActivity personalActivity: personalActivityList) {
            if (personalActivityName.equals(personalActivity.getName())) {
                return personalActivity;
            }
        }
        return null;
    }

    public ArrayList<PersonalActivity> getPersonalActivityList() {
        return personalActivityList;
    }
}
