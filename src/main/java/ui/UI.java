package ui;

import System.App;
import domain.Activity;
import domain.Developer;
import domain.PersonalActivity;
import domain.Project;
import io.bretty.console.view.ActionView;
import io.bretty.console.view.MenuView;

import java.util.InputMismatchException;

public class UI extends ActionView {
    App app = new App();
    Project project;
    Developer developer;
    public MenuView activeDeveloperMenu;
    public MenuView projectLeaderMenu;


    public static void main(String[] args) throws Exception {
        new UI("", "");
    }

    public UI(String runningTitle, String nameInParentMenu) throws Exception {
        super(runningTitle, nameInParentMenu);

        MenuView projectMenu = getProjectMenu();

        MenuView developerMenu = getDeveloperMenu();

        MenuView activityMenu = getActivityMenu();

//        Developer developer = new Developer("Jane", "Doe");
//        Activity activity = new Activity("frontend");
//        Project project = new Project("Minecraft");
//
//        app.registerProject(project);
//        app.registerActivityToProject(activity, project.getID());
//        app.setActivityDate(true, "201901", "frontend", 2020, 27);
//        app.setActivityDate(false, "201901", "frontend", 2020, 29);
//        app.registerDeveloper(developer);
//        app.setPlannedHoursForActivity(activity.getName(), project.getID(), 20);
//        app.getProjectHM().get(project.getID()).getActivity(activity.getName()).addDeveloper(developer);

  /*
    Root menu
     */
        MenuView rootMenu = new MenuView("Welcome to SoftwareHuset A/S", "");
        rootMenu.addMenuItem(developerMenu);
        rootMenu.addMenuItem(projectMenu);
        rootMenu.display();
    }

    public void executeCustomAction() {
    }

    /*
    Developer menu
     */
    private MenuView getDeveloperMenu() {
        MenuView developerMenu = new MenuView("Devs menu", "Developer menu");
        developerMenu.addMenuItem(new setActiveDeveloperAction());
        developerMenu.addMenuItem(new ShowDevelopersAction());
        developerMenu.addMenuItem(new AddDeveloperAction());
        developerMenu.addMenuItem(new SetWorkedHoursAction());
        developerMenu.addMenuItem(new RemoveDeveloperAction());
        return developerMenu;
    }

    /*
    Project menus
     */
    private MenuView getProjectMenu() {
        MenuView projectMenu = new MenuView("Project menu", "Project menu");
        projectMenu.addMenuItem(new ShowProjectsAction());
        projectMenu.addMenuItem(new AddProjectAction());
        projectMenu.addMenuItem(getActivityMenu());
        projectMenu.addMenuItem(new AddProjectLeaderAction());
        projectMenu.addMenuItem(new SetStartDateProjectAction());
        projectMenu.addMenuItem(new SetEndDateProjectAction());
        projectMenu.addMenuItem(new ChangeProjectNameAction());
        projectMenu.addMenuItem(new RemoveProjectAction());
        return projectMenu;
    }

    /*
    Activity menu
     */

    public MenuView getActivityMenu() {
        MenuView activityMenu = new MenuView("Activity menu", "Activity menu");
        activityMenu.addMenuItem(new ShowActivitiesAction());
        activityMenu.addMenuItem(new AddActivityAction());
        activityMenu.addMenuItem(new RemoveActivityFromProjectAction());
        activityMenu.addMenuItem(new AddDeveloperToActivityAction());
        activityMenu.addMenuItem(new SetPlannedHoursAction());
        activityMenu.addMenuItem(new SearchForAvailableDevelopers());
        return activityMenu;
    }

    public void setActiveDeveloperMenu() {
        String ID = this.prompt("Please enter the ID of the developer: ", String.class);
        app.setActiveDeveloper(ID);
        activeDeveloperMenu = new MenuView("Welcome " + app.getActiveDeveloper().getFirstName(), "");
        activeDeveloperMenu.addMenuItem(getDeveloperMenu());
        activeDeveloperMenu.addMenuItem(getProjectMenu());
        activeDeveloperMenu.addMenuItem(new ShowActivitiesActiveDeveloperAction());
        activeDeveloperMenu.addMenuItem(new ShowPersonalActivitiesActiveDeveloperAction());
        activeDeveloperMenu.addMenuItem(new ShowWorkedHours());
        activeDeveloperMenu.addMenuItem(new AddPersonalActivity());
        activeDeveloperMenu.addMenuItem(new RemovePersonalActivity());
        this.setParentView(activeDeveloperMenu);
        this.actionSuccessful();
        this.display();
    }

    class setActiveDeveloperAction extends ActionView {
        public setActiveDeveloperAction() {
            super("Set an active developer", "Set an active developer");
        }

        @Override
        public void executeCustomAction() throws NullPointerException {
            try {
                setActiveDeveloperMenu();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class AddDeveloperAction extends ActionView {
        public AddDeveloperAction() {
            super("Add developer", "Add developer");
        }

        public void executeCustomAction() throws InputMismatchException {

            try {
                String firstName = this.prompt("Enter the first name: ", String.class);
                String lastName = this.prompt("Enter the last name: ", String.class);
                app.registerDeveloper(new Developer(firstName, lastName));
                this.actionSuccessful();

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class RemoveDeveloperAction extends ActionView {
        public RemoveDeveloperAction() {
            super("Remove developer", "Remove developer");
        }

        @Override
        public void executeCustomAction() {
            try {
                String developerID = this.prompt("Enter the ID: ", String.class);
                boolean confirmed = this.confirmDialog("Are you sure that you want to remove " + developerID);
                if (confirmed) {
                    app.removeDeveloper(developerID);
                    this.actionSuccessful();
                }
            } catch(NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class ShowDevelopersAction extends ActionView {
        public ShowDevelopersAction() {
            super("Table of developers", "Show developers");
        }

        @Override
        public void executeCustomAction() throws NullPointerException {
            try {
                app.getDevValues();
            } catch (NullPointerException e) {
                System.out.println("No developers here " + e);
            }
        }
    }

    class ShowActivitiesAction extends ActionView {
        public ShowActivitiesAction() {
            super("Show activities", "Show activities");
        }

        public void executeCustomAction() {
            String ID = this.prompt("Enter project ID: ", String.class);
            if (ID.equals("")) {
                System.out.println("Please try again");
            } else {
                System.out.println(app.getProjectHM().get(ID).getActivityList());
            }
        }
    }

    class ShowActivitiesActiveDeveloperAction extends ActionView {
        public ShowActivitiesActiveDeveloperAction() {
            super("Show your activities", "Show your activities");
        }

        public void executeCustomAction() {
            System.out.println(app.getActiveDeveloper().getActivityList());
        }
    }

    class ShowPersonalActivitiesActiveDeveloperAction extends ActionView {
        public ShowPersonalActivitiesActiveDeveloperAction() {
            super("Show your personal activities", "Show your personal activities");
        }

        public void executeCustomAction() {
            System.out.println(app.getActiveDeveloper().getPersonalActivityList());
        }
    }

    class SetWorkedHoursAction extends ActionView {
        public SetWorkedHoursAction() {
            super("Enter your worked hours", "Set worked hours");
        }

        public void executeCustomAction() {
            try {
                String activityName = this.prompt("Enter the activity: ", String.class);
                String projectID = this.prompt("Enter project ID: ", String.class);
                String hours = this.prompt("How many hours have you worked? ", String.class);
                if (hours.equals("")) {
                    hours = "0";
                }
                double dhours = Double.parseDouble(hours);
                app.setWorkedHoursForActivity(activityName, projectID, dhours);
                this.actionSuccessful();
            } catch (IllegalAccessException | NullPointerException e) {
                e.getMessage();
            }
        }
    }

    /*
        Project actions
    */
    class AddProjectLeaderAction extends ActionView {
        public AddProjectLeaderAction() {
            super("Add project leader", "Add project leader");
        }

        @Override
        public void executeCustomAction() throws NullPointerException {
            try {
                String projectID = this.prompt("Enter the project's ID: ", String.class);
                if (app.getProjectHM().get(projectID).getProjectLeader() != null) {
                    boolean confirmed = this.confirmDialog("A project leader already exists for this project. Continue?");
                    if (confirmed) {
                        this.println("Continuing");
                    }
                }
                String developerID = this.prompt("Enter the new project leader's ID: ", String.class);
                app.setProjectLeader(projectID, developerID);
                this.actionSuccessful();

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class AddActivityAction extends ActionView {
        public AddActivityAction() {
            super("Add activity", "Add activity");
        }

        @Override
        public void executeCustomAction() {

            try {
                String name = this.prompt("Enter the name for the activity: ", String.class);
                String ID = this.prompt("Enter the ID for the project: ", String.class);
                app.registerActivityToProject(new Activity(name), ID);
                int startWeek = this.prompt("Enter the start week for this activity: ", Integer.class);
                int startYear = this.prompt("Enter the start year for this activity: ", Integer.class);
                app.setActivityDate(true, ID, name, startYear, startWeek);
                int endWeek = this.prompt("Enter the end week for this activity: ", Integer.class);
                int endYear = this.prompt("Enter the end year for this activity: ", Integer.class);
                app.setActivityDate(false, ID, name, endYear, endWeek);
                this.actionSuccessful();

            } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class ShowWorkedHours extends ActionView {
        public ShowWorkedHours() {
            super("Show worked hours", "Show worked hours");
        }

        @Override
        public void executeCustomAction() {
            String ID = app.getActiveDeveloper().getID();
            System.out.println(app.getDeveloperHM().get(ID).getWorkedHours());
        }
    }

    class AddPersonalActivity extends ActionView {
        public AddPersonalActivity() {
            super("Add personal Activity", "Add personal Activity");
        }

        @Override
        public void executeCustomAction() {
            String name = this.prompt("Please enter a name for the personal activity: ", String.class);
            String ID = app.getActiveDeveloper().getID();
            // Add a personal activity to the Active developer's personalActivityList
            try {
                app.addPersonalActivity(new PersonalActivity(name), ID);
            } catch (IllegalAccessException e) {
                e.getMessage();
            }
            int startWeek = this.prompt("Enter the start week for this personal activity: ", Integer.class);
            int startYear = this.prompt("Enter the start year for this personal activity: ", Integer.class);
            app.setPersonalActivityDate(true, name, startYear, startWeek);
            int endWeek = this.prompt("Enter the end week for this personal activity: ", Integer.class);
            int endYear = this.prompt("Enter the end year for this personal activity: ", Integer.class);
            app.setPersonalActivityDate(false, name, endYear, endWeek);
            this.actionSuccessful();
        }
    }

    class RemovePersonalActivity extends ActionView {
        public RemovePersonalActivity() {
            super("Remove personal Activity", "Remove personal Activity");
        }

        @Override
        public void executeCustomAction() {
            String personalActivityName = this.prompt("Enter the name of your personal activity: ", String.class);
            app.getActiveDeveloper().removePersonalActivity(app.getActiveDeveloper().getPersonalActivity(personalActivityName));

        }
    }

    class ShowProjectsAction extends ActionView {
        public ShowProjectsAction() {
            super("Table over projects", "Show projects");
        }

        @Override
        public void executeCustomAction() {
            app.getProjectValues();
        }
    }

    class AddProjectAction extends ActionView {
        public AddProjectAction() {
            super("Add a project could be something like 'Marcosoft'", "Add project");
        }

        @Override
        public void executeCustomAction() {
            try {
                String name = this.prompt("Please enter a name for the project: ", String.class);
                app.registerProject(new Project(name));
                actionSuccessful();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class RemoveProjectAction extends ActionView {
        public RemoveProjectAction() {
            super("Remove project", "Remove project");
        }

        @Override
        public void executeCustomAction() {
            String projectID = this.prompt("Enter the ID: ", String.class);
            try {
                boolean confirmed = this.confirmDialog("Are you sure that you want to remove " +projectID);
                if (confirmed) {
                    app.removeProject(projectID);
                    this.actionSuccessful();
                }
            } catch (IllegalAccessException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
        Project leader menus
    */
    class ChangeProjectNameAction extends ActionView {
        public ChangeProjectNameAction() {
            super("Change name", "Change name");
        }

        @Override
        public void executeCustomAction() throws NullPointerException {
            try {
                String ID = this.prompt("Enter a valid ID for a project: ", String.class);
                boolean confirmed = this.confirmDialog("Do you want to change the name for the project " + app.getProjectHM().get(ID).getName());
                if (confirmed) {
                    String name = this.prompt("Please enter the new name for the project: ", String.class);
                    app.setProjectName(ID, name);
                    this.actionSuccessful();
                }
            } catch (NullPointerException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    class RemoveActivityFromProjectAction extends ActionView {

        public RemoveActivityFromProjectAction() {
            super("Remove activity from project", "Remove activity from project");
        }

        public void executeCustomAction() {
            try {
                String activityName = this.prompt("Enter activity name: ", String.class);
                String ID = this.prompt("Enter ID of the project: ", String.class);
                app.removeActivityFromProject(activityName, ID);
                this.actionSuccessful();
            } catch (NullPointerException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class AddDeveloperToActivityAction extends ActionView {
        public AddDeveloperToActivityAction() {
            super("Add developer to activity", "Add developer to activity");
        }

        @Override
        public void executeCustomAction() {
            try {
                String projectID = this.prompt("Enter the ID for the project: ", String.class);
                String activityName = this.prompt("Enter name for the activity: ", String.class);
                String developerID = this.prompt("Enter ID of the developer: ", String.class);
                app.setDeveloperToActivity(activityName, projectID, developerID);
                this.actionSuccessful();
            } catch (IllegalAccessException | IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class SetStartDateProjectAction extends ActionView {
        public SetStartDateProjectAction() {
            super("Set start date for a project", "Set start date for a project");
        }

        @Override
        public void executeCustomAction() {
            String ID = this.prompt("Enter ID for project: ", String.class);
            int week = this.prompt("Enter the week: ", Integer.class);
            int year = this.prompt("Enter the year: ", Integer.class);
            try {
                app.setProjectDate(true, ID, year, week);
                this.actionSuccessful();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class SetEndDateProjectAction extends ActionView {
        public SetEndDateProjectAction() {
            super("Set end date for a project", "Set end date for a project");
        }

        @Override
        public void executeCustomAction() {
            String ID = this.prompt("Enter ID for project: ", String.class);
            int week = this.prompt("Enter the week: ", Integer.class);
            int year = this.prompt("Enter the year: ", Integer.class);
            try {
                app.setProjectDate(false, ID, year, week);
                this.actionSuccessful();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class SetPlannedHoursAction extends ActionView {
        public SetPlannedHoursAction() {
            super("Set the planned hours for an activity", "Set the planned hours for an activity");
        }

        @Override
        public void executeCustomAction() {
            try {
                String projectID = this.prompt("Enter the ID of the project: ", String.class);
                String activityName = this.prompt("Enter the name of the activity: ", String.class);
                String hours = this.prompt("Enter the planned hours: ", String.class);
                if (hours.equals("")) {
                    hours = "0";
                }
                double dhours = Double.parseDouble(hours);
                app.getProjectHM().get(projectID).getActivity(activityName).setPlannedHours(dhours);
                this.actionSuccessful();
            } catch (NullPointerException e) {
                System.out.println("Error in input. Please try again");
            }
        }
    }

    class SearchForAvailableDevelopers extends ActionView {
        public SearchForAvailableDevelopers() {
            super("Search for available developers", "Search for available developers");
        }

        @Override
        public void executeCustomAction() {
            try {
                String projectID = this.prompt("Enter the ID of the project: ", String.class);
                String activityName = this.prompt("Enter the name of the activity: ", String.class);

                System.out.println(app.searchAvailableDevelopers(projectID, activityName));

            } catch (NullPointerException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}




