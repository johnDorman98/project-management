// This class will be used for the architect, contractor and customer objects.
public class Person {
    String role;
    String name;
    String number;
    String email;
    String address;

    // Creating the constructor for the Person class
    public Person(String role, String name, String number, String email, String address) {
        this.role = role;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    // This is used to change the number of the contractor at a later stage by tapping into '.setNumber()'.
    public void setNumber(String number) {
        this.number = number;
    }

    // This method is used to store all the details for this class and return it in a readable format.
    public String toString() {
        String output = "\nDetails for: " + role;
        output += "\nName: " + name;
        output += "\nNumber: " + number;
        output += "\nEmail: " + email;
        output += "\nAddress: " + address;

        return output;
    }
}