package com.company.database;


import java.io.*;


public class FileHandler {


    public void clearFile(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, false);
            fw.close();
        } catch (Exception e) {
            System.out.println("Can't clear file..");
        }
    }


}
