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
                File path = new File("projects.txt");
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
                    String architectName = splitProject[10];
                    String architectNumber = splitProject[11];
                    String architectEmail = splitProject[12];
                    String architectAddress = splitProject[13];
                    Person architectDetails = new Person(architectRole, architectName, architectNumber, architectEmail,
                            architectAddress);

                    String contractorRole = splitProject[14];
                    String contractorName = splitProject[15];
                    String contractorNumber = splitProject[16];
                    String contractorEmail = splitProject[17];
                    String contractorAddress = splitProject[18];
                    Person contractorDetails = new Person(contractorRole, contractorName, contractorNumber,
                            contractorEmail, contractorAddress);

                    String customerRole = splitProject[19];
                    String customerName = splitProject[20];
                    String customerNumber = splitProject[21];
                    String customerEmail = splitProject[22];
                    String customerAddress = splitProject[23];
                    Person customerDetails = new Person(customerRole, customerName, customerNumber, customerEmail,
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
