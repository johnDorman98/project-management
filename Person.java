/**
 * This class is responsible for creating object for the architect, contractor and customer.
 *
 * It is passed into the 'Project.java' class.
 */

// This class is for creating a person object and is passed into the 'Project.java' class.
public class Person {
    private String role;
    private String fName;
    private String lName;
    private String number;
    private String email;
    private String address;

    // Constructor for the class.
    public Person(String role, String fName, String lName, String number, String email, String address) {
        this.setRole(role);
        this.setfName(fName);
        this.setlName(lName);
        this.setNumber(number);
        this.setEmail(email);
        this.setAddress(address);
    }

    // This method is for formatting the output when the Person object is printed.
    public String toString() {
        String output = "\nDetails for: " + getRole();
        output += "\nFirst name: " + getfName();
        output += "\nLast name: " + getlName();
        output += "\nContact number: " + getNumber();
        output += "\nEmail: " + getEmail();
        output += "\nAddress: " + getAddress() + "\n";

        return output;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}