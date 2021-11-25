package com.company;

import com.company.domain.Controller;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Controller controller = new Controller();
        controller.start();
    }
}
