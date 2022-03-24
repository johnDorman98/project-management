/**
 * This is the primary part of the program which house the majority of the logic and is responsible for handling the
 * users selected option.
 *
 * Depending on what option the user has selected when they run the program it will perform various functions as
 * selected by the user.
 */

// Imports for the program.
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

// This class will contain the methods for handling the users inputs and is called in the 'Main' class
public class HandleUserInput {
    public static void showMenu() {
        // This contains the project objects passed from 'DatabaseManagement'.
        ArrayList<Project> projectList = DatabaseMangement.readFile();

        // This is used to make the while loop run until the user selects '5' to exit the program.
        boolean showUserMenu = true;

        // This while loop runs asking the user to make a selection from the menu.
        while (showUserMenu) {
            try {
                int userChoice = Integer.parseInt(JOptionPane.showInputDialog("""
                        Welcome! Please type the numbers below to select that option from the menu.
                        1: Add a new project to list of projects.
                        2: To select a project to update or finalise on the list.
                        3: View the projects that are currently uncompleted.
                        4: View the projects that are currently overdue.
                        5: Write the updated project list to the text file containing the projects, exiting the
                         program once completed."""));
                /*
                if the user selects '1' then it will allow the user to enter details for a new project and add it to
                the arraylist of Project objects.
                 */
                if (userChoice == 1) {
                    Project firstProject = addProject(projectList);
                    projectList.add(firstProject);
                    System.out.println("Your project has been added to list of projects.");
                }

                /*
                If the user has selected '2' it will allow the user to select a project from the list of projects
                using the project number they enter they will then be able to finalise or update the project.
                 */
                else if (userChoice == 2) {
                    String selectedProject = JOptionPane.showInputDialog("Please enter the project number that " +
                            "you would like to update or finalise.");

                    for (Project project : projectList) {
                        if (project.getProjectNumber().equals(selectedProject)) {
                            int projectIndex = projectList.indexOf(project);

                            String userOption = JOptionPane.showInputDialog("Would you like to update the project or " +
                                    "finalise it, Type 'update' to update the project or 'finalise' to finalise the " +
                                    "project");

                            if (userOption.equalsIgnoreCase("finalise")) {
                                String currentStatus = project.getCompletion();

                                if (currentStatus.equals("yes")) {
                                    System.out.println("Sorry that project has already been completed");
                                }
                                else {
                                    System.out.println("The project has been marked as completed.");
                                    project.setCompletion("completed");
                                }
                            }
                            else if (userOption.equalsIgnoreCase("update")) {
                                JFrame frame = new JFrame();
                                JOptionPane.showMessageDialog(frame,"Please enter the updated details for the" +
                                        " project.");
                                Project updatedProject = addProject(projectList);
                                projectList.set(projectIndex, updatedProject);
                            }
                        }
                    }
                }

                /*
                If the user selected this option it will print out a list of project that have not been marked as
                completed.
                 */
                else if (userChoice == 3) {
                    int count = 0;
                    System.out.println("Below is a list of the currently uncompleted projects.");
                    for (Project project : projectList) {
                        String projectStatus = project.getCompletion();

                        if (projectStatus.equals("no")) {
                            count++;
                            System.out.println("\nUncompleted project number: " + count);
                            System.out.println(project);
                        }
                    }
                }

                /*
                This option if selected will compare the current date with the date of the project and will print off
                the project that are overdue and uncompleted.
                 */
                else if (userChoice == 4) {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date currentDate = new Date();
                    String today = dateFormatter.format(currentDate);

                    System.out.println("Below is the overdue projects.");

                    int count = 1;

                    for (Project project : projectList) {
                        String dueDate = project.getDeadline();
                        if (Objects.equals(project.getCompletion(), "no")) {
                            if (dueDate.compareTo(today) < 0) {
                                System.out.println("\nOverdue project number: " + count);
                                System.out.println(project);
                                count++;
                            }
                        }
                    }
                }

                // Finally, this option will write all the projects in the list to 'projects.txt' and exits the loop.
                else if (userChoice == 5) {
                    Project.writeToText(projectList);
                    System.out.println("All projects within the project list have been written to 'projects.txt'.");
                    System.out.println("Exiting the program.");
                    showUserMenu = false;
                }

                // This else is used to catch an error if the user doesn't enter 1-5.
                else {
                    System.out.println("Sorry please select only an option from the menu provided.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter 1 - 5 to select an option.");
                System.out.println(e.getMessage());

            } catch (NullPointerException e) {
                System.out.println("Error");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // This method is used to create the project object and ask the user all the information from the user.
    public static Project addProject(ArrayList<Project> projectList) {
        while (true) {
            try {
                String projectNumber = JOptionPane.showInputDialog("Please enter the project number: ");
                String projectName = JOptionPane.showInputDialog("Please enter the project name for project number " +
                        projectNumber + ": ");
                String buildingType = JOptionPane.showInputDialog("Please enter the type of building for the " +
                        "project: ");
                String projectAddress = JOptionPane.showInputDialog("Please enter the physical address for the " +
                        "project: ");
                String erfNumber = JOptionPane.showInputDialog("Please enter the ERF number for the project: ");
                double totalFee = Double.parseDouble(JOptionPane.showInputDialog("Please enter the total fee being " +
                        "charged  for the project: "));
                double totalPaid = Double.parseDouble(JOptionPane.showInputDialog("Please enter the  total amount " +
                        "paid to date: "));
                String deadline = JOptionPane.showInputDialog("Please enter the deadline for the project e.g. " +
                        "'15/04/2022': ");
                String completion = JOptionPane.showInputDialog("Is the project compelted please type e.g. 'yes' or " +
                        "'no'.");

                /*
                Using the Person class to create an object for the architect, contractor and customer using the 'addPerson'
                method. to then be added as inputs for the Project object.
                */
                Person architect = addPerson("Architect");
                Person contractor = addPerson("Contractor");
                Person customer = addPerson("Customer");

                Project projectDetails = new Project(projectNumber, projectName, buildingType, projectAddress,
                        erfNumber, totalFee, totalPaid, deadline, completion, architect, contractor, customer);

                validateProjects(projectList, projectDetails);

                // Creating the project object using all the users entered data and then returning it.
                return projectDetails;


            } catch (IllegalArgumentException e) {
                System.out.println("Please enter only the correct data.");
            }
        }
    }

    // This method is used to create each of the objects for the architect, contractor and customer.
    public static Person addPerson(String personsRole) {
        while (true) {
            try {
                String name = JOptionPane.showInputDialog("\nPlease enter the name of the " + personsRole);
                String number = JOptionPane.showInputDialog("Please enter the number of the " + personsRole);
                String email = JOptionPane.showInputDialog("Please enter the email for the " + personsRole);
                String address = JOptionPane.showInputDialog("Please enter the address for the " + personsRole);

                if (name.equals("") || number.equals("") || email.equals("") || address.equals("")) {
                    continue;
                }

                // Creating a new Person object using the users information they have entered.

                // Returning the new details object.
                return new Person(personsRole, name, number, email, address);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error sorry please try again.");
            }
        }
    }

    public static void validateProjects(ArrayList<Project> projects, Project currentProject) {
            for (Project project: projects) {
                if (project.getProjectNumber().equals(currentProject.getProjectNumber())) {
                    String newProjectNumber = JOptionPane.showInputDialog("The project number " +
                            currentProject.getProjectNumber() + " already exist please enter the new project number.");
                    System.out.println("Project number has been changed from " + currentProject.getProjectNumber() +
                            " to " + newProjectNumber);
                    currentProject.setProjectNumber(newProjectNumber);
                }
                else if (project.getProjectName().equals(currentProject.getProjectName())) {
                    String newProjectName = JOptionPane.showInputDialog("The project name " +
                            currentProject.getProjectName() + " already exists please enter the new project name.");
                    System.out.println("Project name has been changed from " + currentProject.getProjectName() +
                            " to " + newProjectName);
                    currentProject.setProjectName(newProjectName);
                }
            }
        }
}
