package domain;

import java.util.ArrayList;

public class Developer {
    protected String firstName;
    protected String lastName;
    protected String ID;
    protected ArrayList<Activity> activityList = new ArrayList<Activity>();
    protected double workHours;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getID() {
        if(ID == null) {
            return null;
        }
        return ID;
    }

    public void setWorkHours(double workHours) {
        if (workHours >= 0.5){
            this.workHours = workHours;
        }else {
            throw new IllegalArgumentException("Please provide a valid input");
        }
    }

    public double getWorkHours() {
        return workHours;
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Developer(String firstName, String lastName) {
        if(firstName.matches("^[a-zA-Z]*$") && lastName.matches("^[a-zA-Z]*$") && firstName.length() > 1 && lastName.length() > 1) {
            this.firstName = firstName;
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Developer names must be 2 letters or larger and can only contain alphabetic letters");
        }
    }
    @Override
    public String toString() {
        return "First name:'" + firstName + '\'' +
                ", Last name:'" + lastName + '\'' +
                ", ID:'" + ID + '\'';
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
}