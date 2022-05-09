/**
 * This is the primary part of the program which house the majority of the logic and is responsible for handling the
 * users selected option.
 *
 * Depending on what option the user has selected when they run the program it will perform various functions as
 * selected by the user.
 */

// Imports for the program.
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// This class will contain the methods for handling the users inputs and is called in the 'Main' class
public class HandleUserInput {

    /**
     * This method performs the majority of the actions calling other methods to perform functions including database
     * manipulation.
     *
     * @throws SQLException
     */
    // This method shows a menu of options for the user performing various options based on their selection.
    public static void showMenu() throws SQLException {
        // This contains the project objects passed from 'DatabaseManagement'.
        ArrayList<Project> projectList = DatabaseMangement.readDatabase();

        // This is used to make the while loop run until the user selects '5' to exit the program.
        boolean showUserMenu = true;

        // This while loop runs asking the user to make a selection from the menu.
        while (showUserMenu) {
            try {
                int userChoice = Integer.parseInt(JOptionPane.showInputDialog("""
                        Welcome! Please type the numbers below to select that option from the menu.
                        1: Add a new project to the database.
                        2: To select a project to update or finalise.
                        3: View stats about projects such as the uncompleted projects.
                        4: Search for a program using the project number or project name.
                        5: Exit the program.
                        """));
                /*
                if the user selects '1' then it will allow the user to enter details for a new project and add it to
                the arraylist of Project objects. After that it will add it to the database.
                 */
                if (userChoice == 1) {
                    String viewProjects = JOptionPane.showInputDialog("Would you like to view the current projects " +
                            "and the people assigned to the projects ? Type 'y' for yes or 'n' for no.");
                    if (viewProjects.equalsIgnoreCase("y")) {
                        currentProjects(projectList);
                    } else if (viewProjects.equalsIgnoreCase("n")) {
                        System.out.println("You have select to not view the current projects in the list.");
                    } else {
                        System.out.println("Sorry please make sure you type only 'y' for yes or 'n' for no.");
                    }

                    Project newProject = addProject(projectList);
                    projectList.add(newProject);
                    System.out.println("Your project has been added to list of projects and the database");
                    DatabaseMangement.writeToDatabase(newProject);


                }

                /*
                If the user has selected '2' it will allow the user to select a project from the list of projects
                using the project number they enter they will then be able to finalise or update the project.
                 */
                else if (userChoice == 2) {
                    String selectedProjectNumber = null;
                    String selectedProjectName = null;

                    String searchOption = JOptionPane.showInputDialog("Select the option you would you like to use to " +
                            "search for the project you wish to update. Please type the corresponding number. " +
                            "e.g. '1' for option 1." +
                            "\n1. Project Number: " +
                            "\n2. Project Name: ");

                    if (searchOption.equalsIgnoreCase("1")) {
                        selectedProjectNumber = JOptionPane.showInputDialog("Please enter the project number that " +
                                "you would like to select.");
                    } else if (searchOption.equalsIgnoreCase("2")) {
                        selectedProjectName = JOptionPane.showInputDialog("Please enter the project name that " +
                                "you would like to to select.");
                    }


                    for (Project project : projectList) {
                        if (project.getProjectNumber().equalsIgnoreCase(selectedProjectNumber)
                                || project.getProjectName().equalsIgnoreCase(selectedProjectName)) {
                            int projectIndex = projectList.indexOf(project);

                            String userOption = JOptionPane.showInputDialog("Select from the menu if you like to " +
                                    "update the project or finalise it, Please type the corresponding number." +
                                    "e.g. '1' for option 1." +
                                    "\n1. Update the project: " +
                                    "\n2. Finalise the project: ");

                            if (userOption.equalsIgnoreCase("1")) {
                                String updateChoice = JOptionPane.showInputDialog("Select from the menu the detail " +
                                        "you wish to update, Please type the corresponding number." +
                                        "e.g. '1' for option 1." +
                                        "\n1. Update project total fee: " +
                                        "\n2. Update project total paid: " +
                                        "\n3. Update project deadline: ");

                                if (updateChoice.equalsIgnoreCase("1")) {
                                    double newTotalFee = Double.parseDouble(JOptionPane.showInputDialog("The current " +
                                            "total fee for the project is: R" + project.getTotalFee() + "\nPlease type " +
                                            "the new total fee."));
                                    project.setTotalFee(newTotalFee);
                                    System.out.println("Total fee has been updated to " + newTotalFee);

                                    String sqlStatement = "UPDATE projects " +
                                            "SET total_fee='"+String.valueOf(newTotalFee)+"'" +
                                            "WHERE project_number='"+selectedProjectNumber+"' " +
                                            "OR project_name='"+selectedProjectName+"'";
                                    DatabaseMangement.updateDatabase(sqlStatement);

                                } else if (updateChoice.equalsIgnoreCase("2")) {
                                    double newTotalPaid = Double.parseDouble(JOptionPane.showInputDialog("The current " +
                                            "total paid for the project is: R" + project.getTotalPaid() + "\nPlease type " +
                                            "the new total paid."));
                                    project.setTotalPaid(newTotalPaid);
                                    System.out.println("Total paid has been updated to " + newTotalPaid);

                                    String sqlStatement = "UPDATE projects " +
                                            "SET total_paid='"+String.valueOf(newTotalPaid)+"'" +
                                            "WHERE project_number='"+selectedProjectNumber+"' " +
                                            "OR project_name='"+selectedProjectName+"'";
                                    DatabaseMangement.updateDatabase(sqlStatement);

                                } else if (updateChoice.equalsIgnoreCase("3")) {
                                    String newDeadline = JOptionPane.showInputDialog("The current deadline for the " +
                                            "project is: " + project.getDeadline() + "\nPlease type the new deadline " +
                                            "for the project using the same format.");
                                    project.setDeadline(newDeadline);
                                    System.out.println("Deadline has been updated to " + newDeadline);

                                    String sqlStatement = "UPDATE projects " +
                                            "SET deadline='"+String.valueOf(newDeadline)+"'" +
                                            "WHERE project_number='"+selectedProjectNumber+"' " +
                                            "OR project_name='"+selectedProjectName+"'";
                                    DatabaseMangement.updateDatabase(sqlStatement);

                                }


                            }

                            else if (userOption.equalsIgnoreCase("2")) {
                                String currentStatus = project.getCompletion();

                                if (currentStatus.equals("yes")) {
                                    System.out.println("Sorry that project has already been marked as completed");
                                }
                                else {
                                    project.setCompletion("yes");
                                    System.out.println("The project has been marked as completed.");

                                    String updateProjectStatus = "UPDATE projects " +
                                            "SET project_status='yes'" +
                                            "WHERE project_number='"+selectedProjectNumber+"' " +
                                            "OR project_name='"+selectedProjectName+"'";
                                    DatabaseMangement.updateDatabase(updateProjectStatus);
                                    System.out.println("Database has been updated with the new project status.");

                                    String chosenCompletionDate = JOptionPane.showInputDialog("Would you like to set the " +
                                            "completion date for the project to " + getCurrentDate() +
                                            "Please type the corresponding option. e.g. '1' for option 1." +
                                            "\n1. Set the completion date to the current date." +
                                            "\n2. Choose the completion date for the project.");

                                    String completionDate = null;

                                    if (chosenCompletionDate.equalsIgnoreCase("1")) {
                                        completionDate = getCurrentDate();

                                    } else if (chosenCompletionDate.equalsIgnoreCase("2")) {
                                        completionDate = JOptionPane.showInputDialog("Please enter the new completion " +
                                                "date for the project using this format '15-05-2022'.");
                                    }

                                    project.setCompletionDate(completionDate);
                                    System.out.println("The project completion date has been set to " +
                                            completionDate);

                                    String updateCompletionDate = "UPDATE projects " +
                                            "SET completion_date='"+completionDate+"'" +
                                            "WHERE project_number='"+selectedProjectNumber+"' " +
                                            "OR project_name='"+selectedProjectName+"'";
                                    DatabaseMangement.updateDatabase(updateCompletionDate);
                                    System.out.println("The database has been updated with the new completion date.");

                                    double fee = project.getTotalFee();
                                    double totalPaid = project.getTotalPaid();
                                    double totalOutstanding = fee - totalPaid;

                                    if (totalOutstanding > 0) {
                                        System.out.println("\nCustomer Invoice:" +
                                                "\nFirst Name: " + project.getCustomer().getfName() +
                                                "\nLast Name: " + project.getCustomer().getlName() +
                                                "\nEmail: " + project.getCustomer().getEmail() +
                                                "\nContact Number: " + project.getCustomer().getNumber() +
                                                "\n\nTotal amount outstanding: R" + totalOutstanding
                                        );
                                    }
                                }
                            }
                        }
                    }
                }

                /*
                If the user selects this option it will prompt them allowing them to view the stats of uncompleted or
                overdue projects.
                 */
                else if (userChoice == 3) {
                    String statChoice = JOptionPane.showInputDialog("""
                            Please type the numbers below to select that option from the menu.
                            Please type the corresponding number. e.g. '1' for option 1.
                            1: View uncompleted projects.
                            2: View overdue projects.
                            """);

                    if (statChoice.equalsIgnoreCase("1")) {
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
                    } else if (statChoice.equalsIgnoreCase("2")) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String todayDate = getCurrentDate();
                        LocalDate today = LocalDate.parse(todayDate, dtf);
                        System.out.println("Below is the overdue projects.");

                        int count = 1;

                        for (Project project : projectList) {
                            String dueDate = project.getDeadline();
                            LocalDate deadline = LocalDate.parse(dueDate, dtf);
                            if (project.getCompletion().equalsIgnoreCase("no")) {
                                if (today.isAfter(deadline)) {
                                    System.out.println("\nOverdue project number: " + count);
                                    System.out.println(project);
                                    count++;
                                }
                            }
                        }
                    }
                }

                // This option allows the user to search for a project via the project number or project name.
                else if (userChoice == 4) {
                    String userSearchOption = JOptionPane.showInputDialog("""
                            Please select from the menu the method you would like to use to search for a project.
                            Please type the corresponding number. e.g. type '1' for option 1.
                            1. Search using the project number.
                            2. Search using the project name.
                            """);

                    if (userSearchOption.equalsIgnoreCase("1")) {
                        String projectNumber = JOptionPane.showInputDialog("Please enter the project number for " +
                                "the project you are searching for.");

                        selectedProject(projectList, projectNumber);
                    } else if (userSearchOption.equalsIgnoreCase("2")) {
                        String projectName = JOptionPane.showInputDialog("Please enter the project name for " +
                                "the project you are searching for.");

                        selectedProject(projectList, projectName);
                    }
                }

                // Finally exits the loop.
                else if (userChoice == 5) {
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
            }
        }
    }

    /**
     * This method finds a project based on either the project name or project number, Showing the user their selected
     * project.
     *
     * @param projectList
     * @param searchQuery
     */
    // This method prints off to the user a selected project used for when they are searching for a project.
    private static void selectedProject(ArrayList<Project> projectList, String searchQuery) {
        for (Project project : projectList) {
            if (project.getProjectNumber().equalsIgnoreCase(searchQuery)) {
                System.out.println("Selected Project Details:\n" + project);
            } else if (project.getProjectName().equalsIgnoreCase(searchQuery)) {
                System.out.println("Selected Project Details:\n" + project);
            }
        }
    }

    /**
     * The method returns the current date used for comparing and inserting dates.
     *
     * @return
     */
    // Gets the current date used for inserting or comparing dates.
    private static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate now = LocalDate.now();
        String today = dtf.format(now);
        return today;
    }

    /**
     * The method is used for creating a new Project object based on input provided by the user.
     *
     * @param projectList
     * @return
     */
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
                        "'15-04-2022': ");
                String completionDate = null;
                String completion = JOptionPane.showInputDialog("Is the project completed please type e.g. 'yes' or " +
                        "'no'.");

                /*
                Using the Person class to create an object for the architect, contractor and customer using the 'addPerson'
                method. to then be added as inputs for the Project object.
                */
                Person engineer = addPerson("Engineer");
                Person architect = addPerson("Architect");
                Person contractor = addPerson("Contractor");
                Person customer = addPerson("Customer");

                if (projectName.equals("")) {
                    projectName = buildingType + " " + customer.getlName();
                    System.out.println("Project name has been left blank and has been set to " + projectName);
                }

                if (completion.equalsIgnoreCase("yes")) {
                    completionDate = JOptionPane.showInputDialog("Please enter the completion date for the " +
                            "project e.g. '15-04-2022': ");
                } else if (completion.equalsIgnoreCase("no")) {
                    completionDate = "-" ;
                }

                Project projectDetails = new Project(projectNumber, projectName, buildingType, projectAddress,
                        erfNumber, totalFee, totalPaid, deadline, completionDate, completion, engineer, architect, 
                        contractor, customer);

                validateProjects(projectList, projectDetails);

                // Creating the project object using all the users entered data and then returning it.
                return projectDetails;


            } catch (IllegalArgumentException e) {
                System.out.println("Please enter only the correct data.");
            }
        }
    }

    /**
     * This method is responsible for creating a new Person object based on input provided by the user.
     *
     * @param personsRole
     * @return
     */
    // This method is used to create each of the objects for the architect, contractor and customer.
    public static Person addPerson(String personsRole) {
        while (true) {
            try {
                String firstName = JOptionPane.showInputDialog("\nPlease enter the first name of the " + personsRole);
                String lastName = JOptionPane.showInputDialog("Please enter the last name of the " + personsRole);
                String number = JOptionPane.showInputDialog("Please enter the number of the " + personsRole);
                String email = JOptionPane.showInputDialog("Please enter the email for the " + personsRole);
                String address = JOptionPane.showInputDialog("Please enter the address for the " + personsRole);

                if (firstName.equals("") || lastName.equals("") || number.equals("") || email.equals("") || address.equals("")) {
                    continue;
                }

                // Returning the new details object.
                return new Person(personsRole, firstName, lastName, number, email, address);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error sorry please try again.");
            }
        }
    }

    /**
     * This method compares the current projects with the new one and validates it making sure it doesnt have the same
     * project number or name.
     *
     * @param projects
     * @param currentProject
     */
    // Used for checking if projects with the same name or number already exists and asking the user for new input if so.
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

    /**
     * This allows the user to see the current list of projects.
     *
     * @param projects
     */
    // gets the current projects and those assigned to the project from the project list.
    public static void currentProjects(ArrayList<Project> projects) {
        System.out.println("Below are all the current projects and the people assigned to them.");
        for (Project project : projects) {
            System.out.println("Details for project number: " + project.getProjectNumber());
            System.out.println(project);
        }
    }
}
