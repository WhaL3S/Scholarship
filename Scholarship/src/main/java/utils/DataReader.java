package utils;

import models.Student;

import java.io.FileInputStream;
import java.util.Scanner;
import models.Scholarship;

public class DataReader {

    public static Scholarship readFromFile(String filePath1, String filePath2, List outputData, Scholarship scholarship) {
        FileInputStream fileStream = null;
        Scanner scanner = null;

        try {
            fileStream = new FileInputStream(filePath1);
            scanner = new Scanner(fileStream);
            while (scanner.hasNextLine()) 
            {
                String[] lines = scanner.nextLine().split(";");
                outputData.add(new Student(Integer.parseInt(lines[0]), lines[1], lines[2], Integer.parseInt(lines[3])));
            }
            fileStream.close();
            fileStream = new FileInputStream(filePath2);
            scanner = new Scanner(fileStream);
            String[] lines = scanner.nextLine().split(";");
            scholarship = new Scholarship(Integer.parseInt(lines[0]), Double.parseDouble(lines[1]));
            int counter = 0;
            while (scanner.hasNextLine()) 
            {
                lines = scanner.nextLine().split(";");
                int serial = Integer.parseInt(lines[0]);
                for(Object s : outputData)
                {
                    Student sn = (Student) s;
                    if(sn.getSerial() == serial)
                    {
                        sn.setGroup(lines[1]);
                        int numberOfGrades = Integer.parseInt(lines[2]);
                        sn.setNumberOfGrades(numberOfGrades);
                        String[] lineOfGrades = lines[3].split(" ");
                        sn.gradeAdd(lineOfGrades, numberOfGrades);
                        s = sn;
                    }
                }
                counter++;
            }
            fileStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return scholarship;
    }
}
