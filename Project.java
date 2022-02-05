// This class will be used for creating the Projects.
public class Project {
    int projectNumber;
    String projectName;
    String buildingType;
    String projectAddress;
    String erfNumber;
    double totalFee;
    double totalPaid;
    String deadline;
    Person architect;
    Person contractor;
    Person customer;

    // The constructor for the 'Project' class assigning data to the various objects.
    public Project(int projectNumber, String projectName, String buildingType, String projectAddress, String erfNumber,
                   double totalFee, double totalPaid, String deadline, Person architect, Person contractor,
                   Person customer) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.projectAddress = projectAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.totalPaid = totalPaid;
        this.deadline = deadline;
        this.architect = architect;
        this.contractor = contractor;
        this.customer = customer;

    }

    // Created getters to get the architect, contractor and customer objects from the Person class.
    public Person getArchitect() {
        return architect;
    }

    public Person getContractor() {
        return contractor;
    }

    public Person getCustomer() {
        return customer;
    }

    // These setters allows the deadline and totalPaid to be changed when used in the main class.
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    // This will return the details for the project in a readable format.
    public String toString() {
        String output = "Project Number: " + projectNumber;
        output += "\nProject Name: " + projectName;
        output += "\nType Of Building: " + buildingType;
        output += "\nAddress For The Project: " + projectAddress;
        output += "\nThe ERF Number: " + erfNumber;
        output += "\nThe Total Fee For The Project: " + totalFee;
        output += "\nThe Total Amount Paid To Date: " + totalPaid;
        output += "\nThe Deadline For The Project: " + deadline;

        return output;
    }
}
