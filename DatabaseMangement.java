/**
 * This class is responsible for reading project values from the text file, Creating new objects using those values and
 * saving the objects to an ArrayList.
 */

// Imports for the program.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
This program and method is for reading each project values from 'projects.txt' and will create Project objects with
values from the text file and add these objects to an ArrayList called 'projectList'.
 */
public class DatabaseMangement {
    public static ArrayList<Project> readFile() {
        ArrayList<Project> projectList = new ArrayList<>();
            try {
                File path = new File("Task 2/projects.txt");
                Scanner readFile = new Scanner(path);

                while (readFile.hasNextLine()) {
                    String project = readFile.nextLine();
                    String[] splitProject = project.split(", ");

                    String number = splitProject[0];
                    String name = splitProject[1];
                    String building = splitProject[2];
                    String address = splitProject[3];
                    String erf = splitProject[4];
                    double fee = Double.parseDouble(splitProject[5]);
                    double paid = Double.parseDouble(splitProject[6]);
                    String deadline = splitProject[7];
                    String completion = splitProject[8];

                    String architectRole = splitProject[9];
                    String architectFName = splitProject[10];
                    String architectLName = splitProject[11];
                    String architectNumber = splitProject[12];
                    String architectEmail = splitProject[13];
                    String architectAddress = splitProject[14];
                    Person architectDetails = new Person(architectRole, architectFName, architectLName, architectNumber, architectEmail,
                            architectAddress);

                    String contractorRole = splitProject[15];
                    String contractorFName = splitProject[16];
                    String contractorLName = splitProject[17];
                    String contractorNumber = splitProject[18];
                    String contractorEmail = splitProject[19];
                    String contractorAddress = splitProject[20];
                    Person contractorDetails = new Person(contractorRole, contractorFName, contractorLName, contractorNumber,
                            contractorEmail, contractorAddress);

                    String customerRole = splitProject[21];
                    String customerFName = splitProject[22];
                    String customerLName = splitProject[23];
                    String customerNumber = splitProject[24];
                    String customerEmail = splitProject[25];
                    String customerAddress = splitProject[26];
                    Person customerDetails = new Person(customerRole, customerFName, customerLName, customerNumber, customerEmail,
                            customerAddress);

                    Project projectDetails = new Project(number, name, building, address, erf, fee, paid, deadline,
                            completion, architectDetails, contractorDetails, customerDetails);

                    projectList.add(projectDetails);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Sorry file does not exist.");
            }
            return projectList;
    }
}
