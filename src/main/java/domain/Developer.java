package domain;

import time.Interval;

import java.util.ArrayList;
import java.util.Calendar;

public class Developer {
    protected String firstName;
    protected String lastName;
    protected String ID;
    protected ArrayList<Activity> activityList = new ArrayList<Activity>();
    protected ArrayList<PersonalActivity> personalActivityList = new ArrayList<>();
    protected double workedHours;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getID() { return ID; }

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

    public double getWorkedHours() { return workedHours; }

    public ArrayList<Activity> getActivityList() { return activityList; }

    public void setID(String ID) { this.ID = ID; }

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
        return "[ First name:'" + firstName + '\'' +
                ", Last name:'" + lastName + '\'' +
                ", ID:'" + ID + '\'' + " ]";
    }

    public Activity getActivity(String activityName) {
        for (Activity activity: activityList) {
            if (activityName.equals(activity.getName())) { return activity; }
        }
        return null;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }


    public boolean isAvailable(Interval interval) {
        int counter = 0;

        int startWeek = 0;
        int endWeek = 0;
        int startWeekThis = 0;
        int endWeekThis = 0;
        int startShift = 0;
        int endShift = 0;

        for (PersonalActivity personalActivity : getPersonalActivityList()) {
            startWeek = interval.getStartWeek();
            endWeek = interval.getEndWeek();
            startWeekThis = personalActivity.getInterval().getStartWeek();
            endWeekThis = personalActivity.getInterval().getEndWeek();

            startShift = personalActivity.getInterval().getStartDate().get(Calendar.YEAR) - interval.getStartDate().get(Calendar.YEAR);
            endShift = personalActivity.getInterval().getEndDate().get(Calendar.YEAR) - interval.getEndDate().get(Calendar.YEAR);

            if (startShift > 0) {
                startWeekThis = personalActivity.getInterval().getStartWeek() + (startShift*52);
            } else if (startShift < 0) {
                startWeek = interval.getStartWeek() + (-startShift*52);
            }
            if (endShift > 0) {
                endWeekThis = personalActivity.getInterval().getEndWeek() + (endShift*52);
            } else if (endShift < 0) {
                endWeek = interval.getEndWeek() + (-endShift*52);
            }

            if((startWeekThis >= startWeek && startWeekThis <= endWeek ||
                      (endWeekThis >= startWeek && endWeekThis <= endWeek ||
                      (startWeekThis <= startWeek && endWeekThis >= endWeek)))) {
                  return false;
              }

            if(personalActivity.getInterval().getEndDate().before(interval.getStartDate()) || personalActivity.getInterval().getStartDate().after(interval.getEndDate())) {
                continue;
            } else {
                return false;
            }

//            if((personalActivity.getInterval().getStartWeek() >= interval.getStartWeek() && personalActivity.getInterval().getStartWeek() <= interval.getEndWeek()) ||
//                    (personalActivity.getInterval().getEndWeek() >= interval.getStartWeek() && personalActivity.getInterval().getEndWeek() <= interval.getEndWeek()) ||
//                    (personalActivity.getInterval().getStartWeek() <= interval.getStartWeek() && personalActivity.getInterval().getEndWeek() >= interval.getEndWeek())) {
//                return false;
//            }

//            if((personalActivity.getInterval().getStartDate().after(interval.getStartDate()) && personalActivity.getInterval().getStartDate().before(interval.getEndDate())) ||
//                    (personalActivity.getInterval().getEndDate().after(interval.getStartDate()) && personalActivity.getInterval().getEndDate().before(interval.getEndDate())) ||
//                    (personalActivity.getInterval().getStartDate().before(interval.getStartWeek()) && personalActivity.getInterval().getEndDate().after(interval.getEndWeek()))) {
//                return false;
//            }
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

    public void addPersonalActivity(PersonalActivity activity) {
        personalActivityList.add(activity);
    }

    public ArrayList<PersonalActivity> getPersonalActivityList() {
        return personalActivityList;
    }

    public PersonalActivity getPersonalActivity(String personalActivityName) {
        for (PersonalActivity personalActivity: personalActivityList) {
            if (personalActivityName.equals(personalActivity.getName())) {
                return personalActivity;
            }
        }
        return null;
    }


}
