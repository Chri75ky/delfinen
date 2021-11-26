package com.company;

import com.company.domain.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.start();
    }
}
