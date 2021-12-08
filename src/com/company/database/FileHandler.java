package com.company.database;

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

    public ArrayList<String[]> getLinesInFile(String filePath) {
        ArrayList<String[]> linesInFile = new ArrayList<>();

        String line;
        String splitBy = ";";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) {   // Returns a Boolean value
                String[] lineSeperated = line.split(splitBy);
                linesInFile.add(lineSeperated);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return linesInFile;
    }

    public void saveToCSV(String filePath, String csvLineOrText) {
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(filePath, true));
            ps.append(csvLineOrText);
            ps.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist");
        }
    }

    public boolean checkFileForLines(String filePath) {
        boolean fileHasLines = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            if (br.readLine() != null) {
                fileHasLines = true;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileHasLines;
    }

    public boolean checkFileForName(String filePath, String name) {
        boolean nameExistsInFile = false;

        String line;
        String splitBy = ";";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) {   // Returns a Boolean value
                String[] lineInFile = line.split(splitBy);

                if (lineInFile[0].contentEquals(name)) {
                    nameExistsInFile = true;
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  nameExistsInFile;
    }

    public String getNamesInFile(String filePath) {
        StringBuilder str = new StringBuilder();

        int count = 1;
        String line;
        String splitBy = ";";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] member = line.split(splitBy);
                str.append("(" + count + ") " + member[0] + "\n");
                count++;

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return str.toString();
    }
}
