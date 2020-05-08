package ui;

import System.App;
import domain.Activity;
import domain.Developer;
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

        Developer developer = new Developer("Jane", "Doe");
        Activity activity = new Activity("frontend");
        Project project = new Project("Minecraft");

        app.registerProject(project);
        app.registerActivityToProject(activity, project.getID());
        app.registerDeveloper(developer);
        app.setPlannedHoursForActivity(activity.getName(), project.getID(), 20);
        app.getProjectHM().get(project.getID()).getActivity(activity.getName()).addDeveloper(developer);
//        app.registerActivityToProject(new Activity("backend"),"20191");
//        app.setActivityDate(true,"20191","backend",2020,26);
//        app.setActivityDate(true,"20191","frontend",2020,27);


        MenuView rootMenu = new MenuView("Welcome to SoftwareHuset A/S", "");
        rootMenu.addMenuItem(developerMenu);
        rootMenu.addMenuItem(projectMenu);
        rootMenu.display();
    }

    public void executeCustomAction() {
    }

    /*
    Root menu
     */

    /*
    Developer menu
     */
    private MenuView getDeveloperMenu() {
        MenuView developerMenu = new MenuView("Devs menu", "Developer menu");
        developerMenu.addMenuItem(new setActiveDeveloperAction());
        developerMenu.addMenuItem(new ShowDevelopersAction());
        developerMenu.addMenuItem(new AddDeveloperAction());
        developerMenu.addMenuItem(new SetWorkedHoursAction());
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
        projectMenu.addMenuItem(new setStartDateProjectAction());
        projectMenu.addMenuItem(new setEndDateProjectAction());
        projectMenu.addMenuItem(new ChangeProjectNameAction());
        return projectMenu;
    }

    /*
    Activity menu
     */

    public MenuView getActivityMenu() {
        MenuView activityMenu = new MenuView("Activity menu", "Activity menu");
        activityMenu.addMenuItem(new showActivitiesAction());
        activityMenu.addMenuItem(new AddActivityAction());
        activityMenu.addMenuItem(new removeActivityFromProjectAction());
        activityMenu.addMenuItem(new addDeveloperToActivityAction());
        activityMenu.addMenuItem(new setPlannedHoursAction());
        return activityMenu;
    }

    public void setActiveDeveloperMenu() {
        String ID = this.prompt("Please enter the ID of the developer: ", String.class);
        app.setActiveDeveloper(ID);
        activeDeveloperMenu = new MenuView("Welcome " + app.getActiveDeveloper().getFirstName(), "");
        activeDeveloperMenu.addMenuItem(getDeveloperMenu());
        activeDeveloperMenu.addMenuItem(getProjectMenu());
        activeDeveloperMenu.addMenuItem(new showActivitiesActiveDeveloperAction());
        activeDeveloperMenu.addMenuItem(new showWorkedHours());
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
                System.out.println("Not a valid ID");
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

    class showActivitiesAction extends ActionView {
        public showActivitiesAction() {
            super("Show activities", "Show activities");
        }

        public void executeCustomAction() {
            String ID = this.prompt("Enter project ID: ",String.class);
            if(ID.equals("")) {
                System.out.println("Please try again");
            } else {
                System.out.println(app.getProjectHM().get(ID).getActivityList());
            }
        }
    }

    class showActivitiesActiveDeveloperAction extends ActionView {
        public showActivitiesActiveDeveloperAction() {
            super("Show your activities", "Show your activities");
        }

        public void executeCustomAction() {
            System.out.println(app.getActiveDeveloper().getActivityList());
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
                if(hours.equals("")) {
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
                int weekPlanned = this.prompt("Enter the start week for this activity: ", Integer.class);
                int yearPlanned = this.prompt("Enter the start year for this activity: ", Integer.class);
                app.setActivityDate(true, ID, name, yearPlanned, weekPlanned);
                this.actionSuccessful();

            } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class showWorkedHours extends ActionView {
        public showWorkedHours() {
            super("Show worked hours", "Show worked hours");
        }

        @Override
        public void executeCustomAction() {
            String ID = app.getActiveDeveloper().getID();
            System.out.println(app.getDeveloperHM().get(ID).getWorkedHours());
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
                String name = this.prompt("Please a name for the project: ", String.class);
                app.registerProject(new Project(name));
                actionSuccessful();
            } catch (IllegalArgumentException e) {
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
                boolean confirmed = this.confirmDialog("Do u want to change the name for the project " + app.getProjectHM().get(ID).getName());
                if (confirmed) {
                    String name = this.prompt("Please enter the new name for the project: ", String.class);
                    app.setProjectName(ID, name);
                    this.actionSuccessful();
                }
            } catch (NullPointerException e) {
                System.out.println("The project does not exist ");
            }

        }
    }

    class removeActivityFromProjectAction extends ActionView {

        public removeActivityFromProjectAction() {
            super("Remove activity from project", "Remove activity from project");
        }

        public void executeCustomAction() {
            try {
                String activityName = this.prompt("Enter activity name: ", String.class);
                String ID = this.prompt("Enter ID of the project: ", String.class);
                app.removeActivityFromProject(activityName, ID);
                this.actionSuccessful();
            } catch ( NullPointerException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class addDeveloperToActivityAction extends ActionView {
        public addDeveloperToActivityAction() {
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

    class setStartDateProjectAction extends ActionView {
        public setStartDateProjectAction() {
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

    class setEndDateProjectAction extends ActionView {
        public setEndDateProjectAction() {
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

    class setPlannedHoursAction extends ActionView {
        public setPlannedHoursAction() {
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
            } catch(NullPointerException e) {
                System.out.println("Error in input. Please try again");
            }
        }
    }
}




