package ui;

import System.App;
import domain.Developer;
import domain.Project;
import io.bretty.console.view.ActionView;
import io.bretty.console.view.MenuView;

public class UI extends ActionView {
    App app = new App();
    public MenuView activeDeveloperMenu;
    public MenuView projectLeaderMenu;
    public static void main(String[] args) {
        new UI("","");

    }
    public UI(String runningTitle, String nameInParentMenu) {
        super(runningTitle, nameInParentMenu);
        RootMenu();
    }
    public void executeCustomAction() {
    }

    /*
    Root menu
     */
    private void RootMenu() {
        MenuView rootMenu = new MenuView("Welcome to SoftwareHuset A/S","");
        rootMenu.addMenuItem(new SetActiveDeveloperAction());
        rootMenu.addMenuItem(new ShowDevelopersAction());
        rootMenu.addMenuItem(new AddDeveloperAction());
        rootMenu.addMenuItem(new ShowProjects());
        rootMenu.addMenuItem(new AddProject());
        rootMenu.display();
    }
    /*
    Developer menus
     */
    private void SetActiveDeveloperMenu() {
        String ID = this.prompt("Please enter the ID of the developer: ",String.class);
        app.setActiveDeveloper(ID);
        activeDeveloperMenu = new MenuView("Welcome " + app.getActiveDeveloper(),"");
        activeDeveloperMenu.addMenuItem(new ChangeActiveDeveloperAction());
        activeDeveloperMenu.addMenuItem(new ShowDevelopersAction());
        activeDeveloperMenu.addMenuItem(new AddDeveloperAction());
        activeDeveloperMenu.addMenuItem(new AddProject());
        activeDeveloperMenu.addMenuItem(new ShowProjects());
        this.setParentView(activeDeveloperMenu);
        this.actionSuccessful();

    }
    class SetActiveDeveloperAction extends ActionView{
        public SetActiveDeveloperAction(){
            super("Set an active developer","Set an active developer");
        }
        @Override
        public void executeCustomAction(){
            String ID = this.prompt("Please enter the ID of the developer: ",String.class);
            app.setActiveDeveloper(ID);
            activeDeveloperMenu = new MenuView("Welcome " + app.getActiveDeveloper(),"");
            activeDeveloperMenu.addMenuItem(new ChangeActiveDeveloperAction());
            activeDeveloperMenu.addMenuItem(new ShowDevelopersAction());
            activeDeveloperMenu.addMenuItem(new AddDeveloperAction());
            activeDeveloperMenu.addMenuItem(new AddProject());
            activeDeveloperMenu.addMenuItem(new ShowProjects());
            activeDeveloperMenu.addMenuItem(new SetWorkHours());
            this.setParentView(activeDeveloperMenu);
            this.actionSuccessful();
        }
    }
    class AddDeveloperAction extends ActionView{
        public AddDeveloperAction() {
            super("Add developer", "Add developer");
        }

        public void executeCustomAction() {
            String firstName = this.prompt("Enter the first name: ",String.class);
            String lastName = this.prompt("Enter the last name: ",String.class);
            app.registerDeveloper(new Developer(firstName,lastName));
            this.actionSuccessful();
        }
    }
    class ShowDevelopersAction extends ActionView{
        public ShowDevelopersAction(){
            super("Show developers","Show developers");
        }
        @Override
        public void executeCustomAction(){
            app.getDevValues();
        }
    }
    class ChangeActiveDeveloperAction extends ActionView{
        public ChangeActiveDeveloperAction() {
            super("Change active developer", "Change active developer");
        }

        public void executeCustomAction() {
            String ID = this.prompt("Please enter the ID of the developer: ",String.class);
            app.setActiveDeveloper(ID);
            activeDeveloperMenu = new MenuView("Welcome " + app.getActiveDeveloper(),"");
            activeDeveloperMenu.addMenuItem(new ChangeActiveDeveloperAction());
            activeDeveloperMenu.addMenuItem(new ShowDevelopersAction());
            activeDeveloperMenu.addMenuItem(new AddProject());
            activeDeveloperMenu.addMenuItem(new ShowProjects());
            activeDeveloperMenu.addMenuItem(new SetWorkHours());
            this.setParentView(activeDeveloperMenu);
            this.actionSuccessful();
        }
    }

    class SetWorkHours extends ActionView{
        public SetWorkHours() {
            super("Enter your worked hours", "Set work hours");
        }
        public void executeCustomAction() {

        }
    }
    /*
        Project menus
    */
    class ShowProjects extends ActionView{
        public ShowProjects(){
            super("Table over projects","Show projects");
        }
        @Override
        public void executeCustomAction(){
            app.getProjectValues();
        }
    }
    class AddProject extends ActionView{
        public AddProject(){
            super("Add a project could be something like 'Marcosoft'","Add project");
        }
        @Override
        public void executeCustomAction(){
            String name = this.prompt("Please a name for the project: ",String.class);
            app.registerProject(new Project(name));
            this.actionSuccessful();
        }
    }
    /*
        Project leader menus
    */
    



}
