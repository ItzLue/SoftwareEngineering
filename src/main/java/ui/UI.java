package ui;

import Exceptions.InvalidActivityNameException;
import System.App;
import domain.Activity;
import domain.Developer;
import domain.Project;
import io.bretty.console.view.ActionView;
import io.bretty.console.view.MenuView;
import time.Interval;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class UI extends ActionView {
    App app = new App();
    Project project;
    Developer developer;
    public MenuView activeDeveloperMenu;
    public MenuView projectLeaderMenu;

    public static void main(String[] args) {
        new UI("", "");
    }

    public UI(String runningTitle, String nameInParentMenu) {
        super(runningTitle, nameInParentMenu);

        MenuView projectMenu = getProjectMenu();

        MenuView developerMenu = getDeveloperMenu();

        MenuView activityMenu = getActivityMenu();


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
        developerMenu.addMenuItem(new SetWorkHoursAction());
        return developerMenu;
    }

    /*
    Project menus
     */
    private MenuView getProjectMenu() {
        MenuView projectMenu = new MenuView("Project menu", "Project menu");
        projectMenu.addMenuItem(new AddProjectLeaderAction());
        projectMenu.addMenuItem(new ShowProjectsAction());
        projectMenu.addMenuItem(new AddProjectAction());
        projectMenu.addMenuItem(new setIntervalAction());
        projectMenu.addMenuItem(new ChangeProjectNameAction());
        projectMenu.addMenuItem(getActivityMenu());
        return projectMenu;
    }

    /*
    Activity menu
     */

    public MenuView getActivityMenu() {
        MenuView activityMenu = new MenuView("Activity menu", "activity menu");
        activityMenu.addMenuItem(new AddActivityAction());
        activityMenu.addMenuItem(new removeActivityFromProjectAction());
        return activityMenu;
    }

    public void setActiveDeveloperMenu() {
        String ID = this.prompt("Please enter the ID of the developer: ", String.class);
        app.setActiveDeveloper(ID);
        activeDeveloperMenu = new MenuView("Welcome " + app.getActiveDeveloper().getFirstName(), "");
        activeDeveloperMenu.addMenuItem(getDeveloperMenu());
        activeDeveloperMenu.addMenuItem(getProjectMenu());
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
            } catch (NullPointerException e) {
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
                System.out.println("Not a valid input: " + e);
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

    class SetWorkHoursAction extends ActionView {
        public SetWorkHoursAction() {
            super("Enter your worked hours", "Set work hours");
        }

        public void executeCustomAction() {
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
                if (app.getProjectHM().get(projectID).getProjectLeader() != (null)) {
                    boolean confirmed = this.confirmDialog("A project leader already exists for this project. Continue?");
                    if (confirmed) {
                        this.println("Continuing");
                    }
                }
                String developerID = this.prompt("Enter the new project leader's ID: ", String.class);
                app.setProjectLeader(projectID, developerID);

                if (app.getProjectHM().get(projectID).getProjectLeader().equals(app.getDeveloperHM().get(developerID))) {
                    this.actionSuccessful();
                }
            } catch (NullPointerException e) {
                System.out.println("Not a valid input " + e);
            }
            //FIXME
            // Throw exception if invalid input or no input at all
        }
    }


    class AddActivityAction extends ActionView {
        public AddActivityAction() {
            super("Add activity", "add activity");
        }

        @Override
        public void executeCustomAction() {

            String name = this.prompt("Enter the name for the activity: ", String.class);
            try {
                if (name.matches(".*\\d.*")) {
                    throw new IllegalArgumentException();
                } else {
                    String ID = this.prompt("Enter the ID for the project: ", String.class);
                    app.registerActivityToProject(new Activity(name), ID);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid name " + e);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    //FIXME
    // Throw exception if invalid input or no input at all


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
            String name = this.prompt("Please a name for the project: ", String.class);
            app.registerProject(new Project(name));
            actionSuccessful();
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
                System.out.println("The project does not exist " + e);
            }

        }
    }


    class removeActivityFromProjectAction extends ActionView {

        public removeActivityFromProjectAction() {
            super("Remove activity from project", "Remove activity from project");
        }

        public void executeCustomAction() {

            Activity name = this.prompt("Enter activity name: ", Activity.class);

            String ID = this.prompt("Enter ID of the project: ", String.class);

            app.removeActivityFromProject(name, ID);
        }
    }

    class setIntervalAction extends ActionView {
        public setIntervalAction() {
            super("Set start end date for a project", "Set start end date for a project");
        }

        @Override
        public void executeCustomAction() {
            String ID = this.prompt("Enter a valid ID for a project: ", String.class);
            if (app.getProjectHM().containsKey(ID)) {
                Interval Start = this.prompt("Enter a start date for project: ", Interval.class);
                project.setInterval(Start);
            }
        }
    }
}





