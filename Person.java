/**
 * This class is responsible for creating object for the architect, contractor and customer.
 *
 * It is passed into the 'Project.java' class.
 */

// This class is for creating a person object and is passed into the 'Project.java' class.
public class Person {
    private String role;
    private String name;
    private String number;
    private String email;
    private String address;

    // Constructor for the class.
    public Person(String role, String name, String number, String email, String address) {
        this.setRole(role);
        this.setName(name);
        this.setNumber(number);
        this.setEmail(email);
        this.setAddress(address);
    }

    // This method is for formatting the output when the Person object is printed.
    public String toString() {
        String output = "\nDetails for: " + getRole();
        output += "\nName: " + getName();
        output += "\nNumber: " + getNumber();
        output += "\nEmail: " + getEmail();
        output += "\nAddress: " + getAddress();

        return output;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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