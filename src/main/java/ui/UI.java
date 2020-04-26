package ui;

import System.App;
import domain.Developer;
import domain.Project;
import io.bretty.console.view.ActionView;
import io.bretty.console.view.MenuView;

public class UI extends ActionView {
    App app = new App();
    Project project;
    public MenuView activeDeveloperMenu;
    public MenuView projectLeaderMenu;

    public static void main(String[] args) {
        new UI("", "");

    }

    public UI(String runningTitle, String nameInParentMenu) {
        super(runningTitle, nameInParentMenu);

        MenuView projectMenu = new MenuView("Project menu", "Project menu");
        projectMenu.addMenuItem(new AddProjectLeaderAction());
        projectMenu.addMenuItem(new AddActivityAction());
        projectMenu.addMenuItem(new ShowProjectsAction());
        projectMenu.addMenuItem(new AddProjectAction());

        MenuView developerMenu = new MenuView("Devs menu","Developer menu");
        developerMenu.addMenuItem(new SetActiveDeveloperAction());
        developerMenu.addMenuItem(new ShowDevelopersAction());
        developerMenu.addMenuItem(new AddDeveloperAction());


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
    private void RootMenu() {

    }

    /*
    Project menu
     */


    /*
    Developer menus
     */
    public void SetActiveDeveloperMenu() {
        String ID = this.prompt("Please enter the ID of the developer: ", String.class);
        if (app.developerHM.containsKey(ID)) {
            app.setActiveDeveloper(ID);
        } else {

        }
        activeDeveloperMenu = new MenuView("Welcome " + app.getActiveDeveloper().getFirstName(), "");
        activeDeveloperMenu.addMenuItem(new ChangeActiveDeveloperAction());
        activeDeveloperMenu.addMenuItem(new ShowDevelopersAction());
        activeDeveloperMenu.addMenuItem(new AddDeveloperAction());
        activeDeveloperMenu.addMenuItem(new AddProjectAction());
        activeDeveloperMenu.addMenuItem(new ShowProjectsAction());
        activeDeveloperMenu.addMenuItem(new SetWorkHoursAction());
        this.setParentView(activeDeveloperMenu);
        this.actionSuccessful();
        this.display();
    }

    class SetActiveDeveloperAction extends ActionView {
        public SetActiveDeveloperAction() {
            super("Set an active developer", "Set an active developer");
        }

        @Override
        public void executeCustomAction() {
            SetActiveDeveloperMenu();
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
            SetActiveDeveloperMenu();
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
            String ID = this.prompt("Enter the ID for the project leader: ", String.class);
            project.setProjectLeader(app.getDeveloperHM().get(ID));
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
            this.actionSuccessful();
        }
    }
    /*
        Project leader menus
    */


}
