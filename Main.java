import java.sql.SQLException;

// This is the main that will that will call and run the primary method from 'HandleUserInput'.

/**
 * This is a program to manage projects for an example company.
 *
 * <br>
 *
 * Below I am declaring the main class and method.
 *
 * <br>
 *
 * It will call the primary method that handles most of the program and will explain further within the
 * 'HandleUserInput' class.
 *
 * <br>
 *
 * @author John Dorman
 * @version 3.00, 09/05/2022
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        HandleUserInput.showMenu();
    }
}

