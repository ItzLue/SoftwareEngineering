package ui;

import System.App;
import domain.Developer;
import domain.Project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

public class Console implements PropertyChangeListener {
    App app = new App();
    Scanner in = new Scanner(System.in);

    public Console() {
        app.addObserver(this);
    }

    public static void main(String[] args) throws Exception {
        Console console = new Console();
        new Console().mainMenu(console);

    }


    private Console mainMenu(Console console) throws Exception {
        System.out.println("Welcome to SoftwareHuset A/S");
        int choice;
        do {
            System.out.println("Select a number (0-5): ");
            System.out.println("   1) Set active developer");
            System.out.println("   2) Show developers");
            System.out.println("   3) Add developers");
            System.out.println("   4) Show projects");
            System.out.println("   5) Add projects");
            System.out.println("   0) Exit");

            System.out.println("Insert selection: ");

            choice = in.nextInt();
            switch (choice) {
                case 1:
                    return setActiveDeveloperMenu(console);
                case 2:
                    return showDeveloperMenu(console);
                case 3:
                    return addDeveloperMenu(console);
                case 4:
                    return showProjectMenu(console);
                case 5:
                    return addProjectMenu(console);
                default:
                    System.out.println("The choice was invalid!");
            }


        }
        while (choice != 5);
        return console;
    }

    private Console addProjectMenu(Console console) throws Exception {
        System.out.println("Write the name for the project");
        app.registerProject(new Project(in.next()));
        System.out.println("The project was added \n");
        return mainMenu(console);
    }

    private Console showProjectMenu(Console console) throws Exception {
        app.getProjectValues();
        return mainMenu(console);
    }

    private Console showDeveloperMenu(Console console) throws Exception {
        app.getDevValues();

        if (app.activeDeveloper == null){
            return mainMenu(console);
        }else if (app.getActiveDeveloper().equals("haha01")){
            return showDeveloperMenu(console);
        } else {
            return showDeveloperMenu(console);
        }

    }

    private Console addDeveloperMenu(Console console) throws Exception {
        String firstName;
        String lastName;
        System.out.println("Write first name and last name");
        firstName = in.next();
        lastName = in.next();
        app.registerDeveloper(new Developer(firstName, lastName));
        System.out.println("The developer " + firstName + " " + lastName + " was added" + "\n" + "Your id is: " + " ID \n");
        return mainMenu(console);
    }

    private Console setActiveDeveloperMenu(Console console) throws Exception {
        System.out.println("Insert developer's ID:");
        app.setActiveDeveloper(in.next());
        if (app.getActiveDeveloper().equals("haha01")){
            return console.activeDeveloperMenu(console);
        } else{
            return console.mainMenu(console);
        }
    }

    private Console activeDeveloperMenu(Console console) throws Exception {
        System.out.println("Welcome " + app.getActiveDeveloper());
        int choice;
        do {
            System.out.println("Select a number (0-6): ");
            System.out.println("   1) Change active developer");
            System.out.println("   2) Show developers");
            System.out.println("   3) Add developers");
            System.out.println("   4) Show projects");
            System.out.println("   5) add projects");
            System.out.println("   6) Set worked hours");
            System.out.println("   0) Exit");
            System.out.println("Insert selection: ");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    return setActiveDeveloperMenu(console);
                case 2:
                    return showDeveloperMenu(console);
                case 3:
                    return addDeveloperMenu(console);
                case 4:
                    return showProjectMenu(console);
                case 5:
                    return addProjectMenu(console);
                case 6:
                    return setWorkHoursMenu(console);
                default:
                    System.out.println("The choice was invalid!");
            }
        } while (choice != 6);
        return console;
    }

    private Console setWorkHoursMenu(Console console) throws Exception {
        System.out.println("Select an activity that you want to register");

        return activeDeveloperMenu(console);
    }

    private Console projectLeaderMenu(Console console) throws Exception{
        System.out.println("Welcome " + app.getActiveDeveloper());
        int choice;
        do {
            System.out.println("Select a number (0-9): ");
            System.out.println("   1) Change active developer");
            System.out.println("   2) Show developers");
            System.out.println("   3) Add developers");
            System.out.println("   4) Show projects");
            System.out.println("   5) add projects");
            System.out.println("   6) Set worked hours");
            System.out.println("   7) Select project");
            System.out.println("   0) Exit");
            System.out.println("Insert selection: ");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    return setActiveDeveloperMenu(console);
                case 2:
                    return showDeveloperMenu(console);
                case 3:
                    return addDeveloperMenu(console);
                case 4:
                    return showProjectMenu(console);
                case 5:
                    return addProjectMenu(console);
                case 6:
                    return setWorkHoursMenu(console);
                case 7:
                    return selectProject(console);
                default:
                    System.out.println("The choice was invalid!");
            }
        } while (choice != 6);
        return console;
    }

    private Console selectProject(Console console) throws Exception {
        System.out.println("Here is the projects you are project leader on");
        app.getProjectHM();
        System.out.println("Write the ID for the project you want to view");
        in.nextLine();

        if (app.getProjectHM() != null){
            //app.getActivities();
            return console.ActivityMenu(console);
        } else {
            System.out.println("Please select a valid project");
        }
        return console;
    }

    private Console ActivityMenu(Console console) throws Exception {
        int choice;
        do {
            System.out.println("Select a number (0-9): ");
            System.out.println("   1) Add more activites");
            System.out.println("   2) Change activites");
            System.out.println("   3) ");
            System.out.println("   4) ");
            System.out.println("   5) ");
            System.out.println("   6) ");
            System.out.println("   7) ");
            System.out.println("   0) Exit");
            System.out.println("Insert selection: ");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    return setActiveDeveloperMenu(console);
                case 2:
                    return showDeveloperMenu(console);
                case 3:
                    return addDeveloperMenu(console);
                case 4:
                    return showProjectMenu(console);
                case 5:
                    return addProjectMenu(console);
                case 6:
                    return setWorkHoursMenu(console);
                case 7:
                    return selectProject(console);
                default:
                    System.out.println("The choice was invalid!");
            }
        } while (choice != 6);
        return console;
    }


    public void propertyChange(PropertyChangeEvent evt) {
        String type = evt.getPropertyName();
        if (NotificationType.ACTIVE_DEVELOPER.equals(type)) {
            if (app.getActiveDeveloper() != null) {
                System.out.println("Active developer: " + app.getActiveDeveloper());
            }
        }
    }
}
