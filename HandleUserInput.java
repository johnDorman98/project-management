import javax.swing.*;

// This class will contain the methods for handling the users inputs and is called in the 'Main' class
public class HandleUserInput {

    // This method calls the 'addProject' method and saves the objects data in 'firstProject'.
    public static void showMenu() {
        Project firstProject = addProject();
        // Using the object returned into 'firstProject' I can use getters to get the architect, contractor and customer
        Person architectDetails = firstProject.getArchitect();
        Person contractorDetails = firstProject.getContractor();
        Person customerDetails = firstProject.getCustomer();

        // This is used to make the while loop run until the user selects '5' to exit the program.
        boolean showUserMenu = true;

        // THis while loop runs asking the user to make a selection from the menu.
        while(showUserMenu) {
            int userChoice = Integer.parseInt(JOptionPane.showInputDialog("Welcome! Please type the numbers below to " +
                    "select that option from the menu.\n1: Change the due date of the project.\n2: Change the total " +
                    "amount of the fee paid to date.\n3: Update a contractor’s contact details.\n4: View the final " +
                    "project details.\n5: Exit."));

            // If the choice is '1' the then the user is prompted to enter the new deadline for the project.
            if (userChoice == 1) {
                String newDate = JOptionPane.showInputDialog("Please enter the new deadline for the project.");
                // Using the 'setDeadline' method created in the Project class.
                firstProject.setDeadline(newDate);
            }

            // If the user chooses '2' then the program will prompt to enter the new total that has been paid.
            else if (userChoice == 2) {
                Double newTotal = Double.parseDouble(JOptionPane.showInputDialog("Please enter the new total amount " +
                        "paid to date."));
                // This is useing the 'setTotalPaid' created in the Project class to change the totalPaid.
                firstProject.setTotalPaid(newTotal);
            }

            // This option allows the user to change the contractors contact details.
            else if (userChoice == 3) {
                String newContact = JOptionPane.showInputDialog("Please enter the new number for the contractor.");
                firstProject.getContractor().setNumber(newContact);
            }

            // If this is selected it finalizes the project printing it for the user to see with the updated changes.
            else if (userChoice == 4) {
                System.out.println("This is the final project details.\n" + firstProject + "\n" + architectDetails +
                        "\n" + contractorDetails + "\n" + customerDetails);
            }

            // If the user choses '5' it ends the while loop and exits the menu.
            else if (userChoice == 5) {
                System.out.println("You have selected '5' and exited the program.");
                showUserMenu = false;
            }

            // This else is used to catch an error if the user doesn't enter 1-5.
            else {
                System.out.println("Sorry please select only an option from the menu provided.");
            }
        }
    }

    // This method is used to create the project object and ask the user all the information from the user.
    public static Project addProject() {
        int projectNumber = Integer.parseInt(JOptionPane.showInputDialog("Please enter the project number: "));
        String projectName = JOptionPane.showInputDialog("Please enter the project name for project number " +
                projectNumber + ": ");
        String buildingType = JOptionPane.showInputDialog("Please enter the type of building for the project: ");
        String projectAddress = JOptionPane.showInputDialog("Please enter the physical address for the project: ");
        String erfNumber = JOptionPane.showInputDialog("Please enter the ERF number for the project: ");
        double totalFee = Double.parseDouble(JOptionPane.showInputDialog("Please enter the total fee being charged " +
                "for the project: "));
        double totalPaid = Double.parseDouble(JOptionPane.showInputDialog("Please enter the  total amount paid" +
                " to date: "));
        String deadline = JOptionPane.showInputDialog("Please enter the deadline for the project: ");

        /*
        Using the Person class to create an object for the architect, contractor and customer using the 'addPerson'
        method. to then be added as inputs for the Project object.
        */
        Person architect = addPerson("Architect");
        Person contractor = addPerson("Contractor");
        Person customer = addPerson("Customer");

        // Creating the project object using all the users entered data and then returning it.
        Project currentProject = new Project(projectNumber, projectName, buildingType, projectAddress, erfNumber,
                totalFee, totalPaid, deadline, architect, contractor, customer);

        return currentProject;
    }

    // This method is used to create each of the objects for the architect, contractor and customer.
    public static Person addPerson(String personsRole) {

        String role = personsRole;
        String name = JOptionPane.showInputDialog("\nPlease enter the name of the " + role);
        String number = JOptionPane.showInputDialog("Please enter the number of the " + role);
        String email = JOptionPane.showInputDialog("Please enter the email for the " + role);
        String address = JOptionPane.showInputDialog("Please enter the address for the " + role);

        // Creating a new Person object using the users information they have entered.
        Person details = new Person(role, name, number, email, address);

        // Returning the new details object.
        return details;
    }
}
