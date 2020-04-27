package ui;

import System.App;
import domain.Developer;
import domain.Project;
import io.bretty.console.view.ActionView;
import io.bretty.console.view.MenuView;
import time.Interval;

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
        return developerMenu;
    }

    /*
    Project menus
     */
    private MenuView getProjectMenu() {
        MenuView projectMenu = new MenuView("Project menu", "Project menu");
        projectMenu.addMenuItem(new AddProjectLeaderAction());
        projectMenu.addMenuItem(new AddActivityAction());
        projectMenu.addMenuItem(new ShowProjectsAction());
        projectMenu.addMenuItem(new AddProjectAction());
        //projectMenu.addMenuItem(new initializeProject());
        // projectMenu.addMenuItem(new setIntervalAction());
        return projectMenu;
    }

    public void setActiveDeveloperMenu() {
        String ID = this.prompt("Please enter the ID of the developer: ", String.class);
        if (app.developerHM.containsKey(ID)) {
            app.setActiveDeveloper(ID);
        }
        activeDeveloperMenu = new MenuView("Welcome " + app.getActiveDeveloper().getFirstName(), "");
        getDeveloperMenu().addMenuItem(new SetWorkHoursAction());
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
        public void executeCustomAction() {
            setActiveDeveloperMenu();
        }
    }

    class AddDeveloperAction extends ActionView {
        public AddDeveloperAction() {
            super("Add developer", "Add developer");
        }

        public void executeCustomAction() {
            String firstName = this.prompt("Enter the first name: ", String.class);
            String lastName = this.prompt("Enter the last name: ", String.class);
            app.registerDeveloper(new Developer(firstName, lastName));
            this.actionSuccessful();
        }
    }

    class ShowDevelopersAction extends ActionView {
        public ShowDevelopersAction() {
            super(" ", "Show developers");
        }

        @Override
        public void executeCustomAction() {
            app.getDevValues();
        }
    }

    class ChangeActiveDeveloperAction extends ActionView {
        public ChangeActiveDeveloperAction() {
            super("Change active developer", "Change active developer");
        }

        public void executeCustomAction() {
            setActiveDeveloperMenu();
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
        public void executeCustomAction() {
            String projectID = this.prompt("Enter the ID of the project: ",String.class);
            String developerID = this.prompt("Enter the ID for the project leader: ", String.class);
            app.setProjectLeader(projectID,developerID);

            actionSuccessful();
        }
    }


    class AddActivityAction extends ActionView {
        public AddActivityAction() {
            super("Add activity", "add activity");
        }

        @Override
        public void executeCustomAction() {
            String name = this.prompt("Enter the name for the activity: ", String.class);
            String ID = this.prompt("Enter the ID for the project: ", String.class);
            app.registerActivityToProject(name, ID);
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
            String name = this.prompt("Please a name for the project: ", String.class);
            app.registerProject(new Project(name));
            actionSuccessful();
        }
    }
}
    /*
        Project leader menus
    */
//    class initializeProject extends ActionView {
//        public initializeProject() {
//            super("initialize a project", "initialize project");
//        }
//
//        @Override
//        public void executeCustomAction() {
//
//            boolean confirmed = this.confirmDialog("Are you sure that you want to initialize " /* Project name */);
//            if (confirmed){
//
//            }
//        }
//        //FIXME
//        // - Init project
//    }

//    class setIntervalAction extends ActionView {
//        public setIntervalAction() {
//            super("Set start end date for a project", "Set start end date for a project");
//        }
//
//        @Override
//        public void executeCustomAction() {
//            String ID = this.prompt("Enter a valid ID for a project: ",String.class);
//            if (){
//                Interval Start = this.prompt("Enter a start date for project",Interval.class);
//                project.setInterval(Start);
//                //FIXME
//                // - Set interval
//            }
//        }
//    }