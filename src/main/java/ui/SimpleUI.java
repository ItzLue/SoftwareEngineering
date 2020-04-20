package ui;

import System.App;
import domain.Developer;

import java.io.IOException;
import java.util.Scanner;

public class SimpleUI{
    App app = new App();

    public static void main(String[] args) throws Exception {
        new SimpleUI().mainLoop();
    }

    public void mainLoop() throws IOException {
        Scanner in = new Scanner(System.in);
        int choice;

        while (true){
            showMenu();

            choice = (in.nextInt());
            if (choice == 1) {
                System.out.println("Insert developer's ID:");
                app.setActiveDeveloper(in.next());
            }
            else if (choice == 2){
                if (!app.devHmEmpty()){
                    app.getDevValues();
                } else app.getDevValues();

            }
            else if (choice == 3){
                System.out.println("Write first name and last name");
                app.registerDeveloper(new Developer(in.next(),in.next()));
            }
            else if (choice == 4){
                app.getProjectValues();

            }
            else if (choice == 5){
                System.out.println("Write project name");

            }
            else if (choice == 6) {

            }
            else if (choice == 0) {
                break;
            }
        }
    }

    public void showMenu() {
        System.out.println("Active developer: " + app.getActiveDeveloper());
        System.out.println("Select a number (1-5): ");
        System.out.println("   1) Set active developer");
        System.out.println("   2) Show developers");
        System.out.println("   3) Add developers");
        System.out.println("   4) Show projects");
        System.out.println("   5) add projects");
        if(app.activeDeveloper != null) {
            System.out.println("   6) Set worked hours");
        }
        System.out.println("   0) Exit");
    }
}