package com.company.database;


import com.company.accounting.Kontingent;

import java.io.*;
import java.util.ArrayList;


public class FileHandler {

    public void clearFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.close();
        } catch (Exception e) {
            System.out.println("Can't clear file..");
        }
    }

    public ArrayList<String[]> getLinesInFile (String filePath) {
        ArrayList<String[]> linesInFile = new ArrayList<>();

        String line = "";
        String splitBy = ";";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) {   //Returns a Boolean value
                String[] lineSeperated = line.split(splitBy);
                linesInFile.add(lineSeperated);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return linesInFile;
    }


}
