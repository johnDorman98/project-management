/**
 * This class is responsible for connecting to and manipulating the database and its contents.
 *
 */

// Imports for the program.
import java.sql.*;
import java.util.ArrayList;

public class DatabaseMangement {
    // Variables to be used for connecting and querying statements.
    private static String dbUrl = "jdbc:mysql://localhost:3306/poisepms?useSSL=false";
    private static String user = "altuser";
    private static String pass = "testingpass";
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet results = null;
    static int rowsAffected;

    /**
     * This method is used for connecting to and reading from the database for the project creating Project objects and
     * adding them to the list of projects.
     *
     * @return
     * @throws SQLException
     */
    /*
    This method connects to the database and reads the tables within the database returning aa arraylist of Project objects
    that can be used within 'HandleUserInput'.
     */
    public static ArrayList<Project> readDatabase() throws SQLException {
        ArrayList<Project> projectList = new ArrayList<>();

        try {
            // Get a connection to the database.
            connection = DriverManager.getConnection(dbUrl, user, pass);

            System.out.println("Database connection has been established\n");

            // Create a statement.
            statement = connection.createStatement();

            results = statement.executeQuery("SELECT * FROM projects");

            while (results.next()) {
                String number = results.getString("projects.project_number");
                String name = results.getString("project_name");
                String building = results.getString("building_type");
                String address = results.getString("project_address");
                String erf = results.getString("erf_number");
                double fee = results.getFloat("total_fee");
                double paid = results.getFloat("total_paid");
                String deadline = results.getString("deadline");
                String completionDate = results.getString("completion_date");
                String completion = results.getString("project_status");

                int engineerId = results.getInt("engineer_id");
                String engineerSql = "SELECT * FROM engineer WHERE engineer_id='"+engineerId+"'";
                Person engineerDetails = readPersonDetails(connection, engineerSql);

                int architectId = results.getInt("architect_id");
                String architectSql = "SELECT * FROM architect WHERE architect_id='"+architectId+"'";
                Person architectDetails = readPersonDetails(connection, architectSql);

                int contractorId = results.getInt("contractor_id");
                String contractorSql = "SELECT * FROM contractor WHERE contractor_id='"+contractorId+"'";
                Person contractorDetails = readPersonDetails(connection, contractorSql);

                int customerId = results.getInt("customer_id");
                String customerSql = "SELECT* FROM customer WHERE customer_id='"+customerId+"'";
                Person customerDetails = readPersonDetails(connection, customerSql);

                Project projectDetails = new Project(number, name, building, address, erf, fee, paid, deadline,
                        completionDate, completion, engineerDetails, architectDetails, contractorDetails,
                        customerDetails);

                projectList.add(projectDetails);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (results != null) {
                results.close();
            } else if (statement != null) {
                statement.close();
            } else if (connection != null) {
                connection.close();
            }
        }
        return projectList;
    }

    /**
     * This method creates a Person object used for adding to the Project object when reading from the database.
     *
     * @param connection
     * @param slqStatement
     * @return
     * @throws SQLException
     */
    /*
    This method creates a Person object from for each of the required roles from their respective tables.
     */
    public static Person readPersonDetails(Connection connection, String slqStatement) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(slqStatement);
        try {
            Person personTemplate = null;
            while (results.next()) {
                String role = results.getString("role");
                String f_name = results.getString("f_name");
                String l_name = results.getString("l_name");
                String number = results.getString("number");
                String email = results.getString("email");
                String address = results.getString("address");

                personTemplate = new Person(role, f_name, l_name, number, email, address);
            }
            return personTemplate;

        } finally {
            if (results != null) {
                results.close();
            } else if (statement != null) {
                statement.close();
            }
        }
    }

    /**
     * This is used for writing new projects to the database.
     *
     * @param project
     */
    // This method takes in a new Project object and adds it to the database.
    public static void writeToDatabase(Project project) {
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
            statement = connection.createStatement();

                rowsAffected = statement.executeUpdate(
                        "INSERT IGNORE INTO engineer (role, f_name, l_name, number, email, address)" +
                                "VALUES (" +
                                "'"+project.getEngineer().getRole()+"'," +
                                "'"+project.getEngineer().getfName()+"'," +
                                "'"+project.getEngineer().getlName()+"'," +
                                "'"+project.getEngineer().getNumber()+"'," +
                                "'"+project.getEngineer().getEmail()+"'," +
                                "'"+project.getEngineer().getAddress()+"'" +
                                ")"
                );

                rowsAffected = statement.executeUpdate(
                        "INSERT IGNORE INTO architect (role, f_name, l_name, number, email, address)" +
                                "VALUES (" +
                                "'"+project.getArchitect().getRole()+"'," +
                                "'"+project.getArchitect().getfName()+"'," +
                                "'"+project.getArchitect().getlName()+"'," +
                                "'"+project.getArchitect().getNumber()+"'," +
                                "'"+project.getArchitect().getEmail()+"'," +
                                "'"+project.getArchitect().getAddress()+"'" +
                                ")"
                );

                rowsAffected = statement.executeUpdate(
                        "INSERT IGNORE INTO contractor (role, f_name, l_name, number, email, address)" +
                                "VALUES (" +
                                "'"+project.getContractor().getRole()+"'," +
                                "'"+project.getContractor().getfName()+"'," +
                                "'"+project.getContractor().getlName()+"'," +
                                "'"+project.getContractor().getNumber()+"'," +
                                "'"+project.getContractor().getEmail()+"'," +
                                "'"+project.getContractor().getAddress()+"'" +
                                ")"
                );

                rowsAffected = statement.executeUpdate(
                        "INSERT IGNORE INTO customer (role, f_name, l_name, number, email, address)" +
                                "VALUES (" +
                                "'"+project.getCustomer().getRole()+"'," +
                                "'"+project.getCustomer().getfName()+"'," +
                                "'"+project.getCustomer().getlName()+"'," +
                                "'"+project.getCustomer().getNumber()+"'," +
                                "'"+project.getCustomer().getEmail()+"'," +
                                "'"+project.getCustomer().getAddress()+"'" +
                                ")"
                );

                rowsAffected = statement.executeUpdate(
                                "INSERT IGNORE INTO projects (project_number, project_name, building_type, project_address, " +
                                    "erf_number, total_fee, total_paid, deadline, completion_date, project_status, engineer_id, " +
                                    "architect_id, contractor_id, customer_id)" +
                                    "SELECT" +
                                    "'"+project.getProjectNumber()+"'," +
                                    "'"+project.getProjectName()+"'," +
                                    "'"+project.getBuildingType()+"'," +
                                    "'"+project.getProjectAddress()+"'," +
                                    "'"+project.getErfNumber()+"'," +
                                    "'"+project.getTotalFee()+"'," +
                                    "'"+project.getTotalPaid()+"'," +
                                    "'"+project.getDeadline()+"'," +
                                    "'"+project.getCompletionDate()+"'" +
                                    "'"+project.getCompletion()+"'," +

                                    "(SELECT engineer_id FROM engineer " +
                                    "WHERE engineer.f_name='"+project.getEngineer().getfName()+"' " +
                                    "AND engineer.l_name='"+project.getEngineer().getlName()+"')," +

                                    "(SELECT architect_id FROM architect " +
                                    "WHERE architect.f_name='"+project.getArchitect().getfName()+"' " +
                                    "AND architect.l_name='"+project.getArchitect().getlName()+"')," +

                                    "(SELECT contractor_id FROM contractor " +
                                    "WHERE contractor.f_name='"+project.getContractor().getfName()+"' " +
                                    "AND contractor.l_name='"+project.getContractor().getlName()+"')," +

                                    "(SELECT customer_id FROM customer " +
                                    "WHERE customer.f_name='"+project.getCustomer().getfName()+"' " +
                                    "AND customer.l_name='"+project.getCustomer().getlName()+"')"
                        );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This updates the database based on the SQL Statement that is passed into it.
     *
     * @param sqlStatement
     */
    // This updates the database based on a SQL statement passed in.
    public static void updateDatabase(String sqlStatement) {
        try {
            connection = DriverManager.getConnection(dbUrl, user, pass);
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlStatement);
            System.out.println("Database has been updated " + rowsAffected + " rows updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
