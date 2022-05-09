/**
 * This class is responsible for creating the objects for each project and also for writing the projects values in
 * the list of projects, To the text file when the user selects that option in when the program runs.
 */

// This class will be used for creating the Projects.
public class Project {
    private String projectNumber;
    private String projectName;
    private String buildingType;
    private String projectAddress;
    private String erfNumber;
    private double totalFee;
    private double totalPaid;
    private String deadline;
    private String completionDate;
    private String completion;
    private Person engineer;
    private Person architect;
    private Person contractor;
    private Person customer;

    // The constructor for the 'Project' class assigning data to the various objects.
    public Project(String projectNumber, String projectName, String buildingType, String projectAddress, String erfNumber,
                   double totalFee, double totalPaid, String deadline, String completionDate, String completion,Person engineer,
                   Person architect, Person contractor, Person customer) {
        this.setProjectNumber(projectNumber);
        this.setProjectName(projectName);
        this.setBuildingType(buildingType);
        this.setProjectAddress(projectAddress);
        this.setErfNumber(erfNumber);
        this.setTotalFee(totalFee);
        this.setTotalPaid(totalPaid);
        this.setDeadline(deadline);
        this.setCompletionDate(completionDate);
        this.setCompletion(completion);
        this.setEngineer(engineer);
        this.setArchitect(architect);
        this.setContractor(contractor);
        this.setCustomer(customer);
    }

    // This will return the details for the project in a readable format.
    public String toString() {
        String output = "\nProject Number: " + getProjectNumber();
        output += "\nProject Name: " + getProjectName();
        output += "\nType Of Building: " + getBuildingType();
        output += "\nAddress For The Project: " + getProjectAddress();
        output += "\nThe ERF Number: " + getErfNumber();
        output += "\nThe Total Fee For The Project: " + getTotalFee();
        output += "\nThe Total Amount Paid To Date: " + getTotalPaid();
        output += "\nThe Deadline For The Project: " + getDeadline();
        output += "\nThe Completion Date For The Project: " + getCompletionDate();
        output += "\nIs the project completed: " + getCompletion() + "\n";

        output += engineer.toString();
        output += architect.toString();
        output += contractor.toString();
        output += customer.toString();

        return output;
    }

    // Setters and getters.
    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getErfNumber() {
        return erfNumber;
    }

    public void setErfNumber(String erfNumber) {
        this.erfNumber = erfNumber;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public void setEngineer(Person engineer) {
        this.engineer = engineer;
    }

    public void setArchitect(Person architect) {
        this.architect = architect;
    }

    public void setContractor(Person contractor) {
        this.contractor = contractor;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public Person getEngineer() {
        return engineer;
    }

    public Person getArchitect() {
        return architect;
    }

    public Person getContractor() {
        return contractor;
    }

    public Person getCustomer() {
        return customer;
    }
}

