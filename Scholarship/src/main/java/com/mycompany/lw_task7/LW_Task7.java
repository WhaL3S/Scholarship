/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.lw_task7;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import models.Scholarship;
import models.Student;
import utils.DataReader;
import utils.LinkedList;
import utils.LinkedList.Node;

/**
 *
 * @author WhaLeS
 */
public class LW_Task7 {

    public static void main(String[] args) throws IOException {
        // Files and clearing Results.txt file
        String filePath1 = "U7a.txt";
        String filePath2 = "U7b.txt";
        String fileName = "Results.txt";
        PrintStream printStream = new PrintStream(new FileOutputStream(fileName));
        printStream.flush();
        printStream.close();
        
        // Scholarship object and two lists
        Scholarship scholarship = new Scholarship();
        LinkedList<Student> students_llist = new LinkedList<>();
        LinkedList<Student> forerunners_llist = new LinkedList<>();
        
        // Variables for the number of students for scholarship and forerunners
        int numberScholarshipStudent = 0;
        int numberOfForerunners = 0;
        
        // Reading data from two files
        scholarship = DataReader.readFromFile(filePath1, filePath2, students_llist, scholarship);
        
        // Printing list into the console and txt file
        print(students_llist,"Initial");
        printFile(students_llist,"Initial");

        // Setting scholarship and forerunner status, counting number of such students and distributing scholarship
        for(Student s : students_llist)
        {
            s.averageStudentsGrade();
            s.status(scholarship.getGrade());
            s.forerunnerCreator(); 
            if(s.getStatus())
                numberScholarshipStudent++;
            if(s.getForerunnerStatus())
                numberOfForerunners++;
        }
        for(Student s : students_llist)
            s.scholarshipDistribution(scholarship.getFund(), numberScholarshipStudent, numberOfForerunners);
        
        // Printing list into the console and txt file
        print(students_llist, "Scholarship organized");
        printFile(students_llist,"Scholarship organized");
        
        // Sorting list by scholarship amount and name
        sort(students_llist);
        
        // Printing list into the console and txt file
        print(students_llist, "Sorted by amount and name");
        printFile(students_llist,"Sorted by amount and name");
        
        // Removing students who did not receive scholarship
        for(Student s : students_llist)
            if(!s.getStatus())
                students_llist.remove(s);
        
        // Printing list into the console and txt file
        print(students_llist, "Students removed");
        printFile(students_llist,"Students removed");
        
        // Reading from the console
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        System.out.println("Type group name:");
        String groupName = reader.readLine();
        System.out.println();
        
        // Writing into txt file
        printStream = new PrintStream(new FileOutputStream(fileName, true));
        System.setOut(printStream);
        System.out.println("Type group name:");
        System.out.println(groupName);
        System.out.println();
        printStream.close();
        PrintStream consoleStream = new PrintStream(new FileOutputStream(FileDescriptor.out));
        System.setOut(consoleStream);
        
        // Adding forerunners into the new list
        for(Student s : students_llist)
            if(s.getForerunnerStatus() && (s.getGroup().equals(groupName)))
                forerunners_llist.add(s);
        
        // Printing list into the console and txt file
        if(forerunners_llist.get(0) != null)
        {
            print(forerunners_llist, "Forerunner(s) of the group: " + groupName);
            printFile(forerunners_llist,"Forerunner(s) of the group: " + groupName);
        }
        else
        {
            System.out.println("There is no such student");
            printStream = new PrintStream(new FileOutputStream(fileName, true));
            System.setOut(printStream);
            System.out.println("There is no such student");
            printStream.close();
        }
    }
    
    public static void print(LinkedList<Student> list, String header)
    {
        System.out.println(header);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Serial | Name and Surname | Group | Telephone | Scholarship | Forerunner | Amount");
        System.out.println("----------------------------------------------------------------------------------------");
        for(Student s : list)
            System.out.println(s.toString());
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println();
    }
    
    public static void printFile(LinkedList<Student> list, String header) throws FileNotFoundException
    {
        String fileName = "Results.txt";
        PrintStream printStream = new PrintStream(new FileOutputStream(fileName, true));
        System.setOut(printStream);
        print(list, header);
        PrintStream consoleStream = new PrintStream(new FileOutputStream(FileDescriptor.out));
        System.setOut(consoleStream);
    }
    
    public static void sort(LinkedList<Student> list) 
    {
        Node head = list.head;
        Node current = head, index;
        Student temp;
        if(head == null) {} 
        else
            while(current != null)
            {
                index = current.Next;
                while(index != null)
                {
                    Student sCurrent = (Student) current.Data;
                    if(sCurrent.compare(index.Data))
                    {
                        temp = (Student) current.Data;
                        current.Data = index.Data;
                        index.Data = temp;
                    }
                    index = index.Next;
                }
                current = current.Next;
            }
    }
}
