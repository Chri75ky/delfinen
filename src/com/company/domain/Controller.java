package com.company.domain;

import com.company.ui.UserInterface;

public class Controller {
    boolean isRunning = true;
    private UserInterface ui = new UserInterface();

    public void start() {
        while (isRunning) {
            ui.menu();
            String userInput = ui.userInput();
            switch (userInput) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    login();
                    break;
                case "2":
                    createUser();
                    break;
                case "3":
                    seeContingent();
                    break;
                case "4":
                    seeMembers();
                    break;
                case "5":
                    topFive();
                    break;
            }
        }
    }

    private void login() {
    }

    private void createUser() {
    }

    private void seeContingent() {
    }

    private void seeMembers() {
    }

    private void topFive() {
    }
}
