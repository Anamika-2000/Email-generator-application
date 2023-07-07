package Email_app;
import java.util.Scanner;
import java.sql.*;

public class Email_app {

    public static void main(String[] args) {
        String firstName, lastName;
        Scanner scan = new Scanner(System.in);
        System.out.println("Hi team member, Welcome to our company! Hope you will enjoy working with us!");
        System.out.println("ENTER YOUR FIRST NAME");
        firstName = scan.next();
        System.out.println("ENTER YOUR LAST NAME");
        lastName = scan.next();
        System.out.println("Enter your Phone number");
        long phoneNumber = scan.nextLong();
        String initial = String.valueOf(firstName.charAt(0));
        String employeeId = initial + phoneNumber;
        System.out.println("Your employee_id is: " + employeeId);
        Email em1 = new Email(firstName, lastName);
        System.out.println("Enter an alternative mail-id: ");
        String altMail = scan.next();
        em1.setAlternateEmail(altMail);
        System.out.println("Your alternate email id is: " + em1.getAlternateEmail());
        System.out.println("Enter your new password: ");
        String newPassword = scan.next();

        try {
            // Establish database connection
            Class.forName("com.mysql.jdbc.Driver");
            String databaseURL = "jdbc:mysql://localhost:3306/EmailData";
            String username = "root";
            String password = "Anamika@2000";
            try (Connection connection = DriverManager.getConnection(databaseURL, username, password);
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO Dataemail (employee_id, email, password) VALUES (?, ?, ?)")) {
                // Set parameter values
                statement.setString(1, employeeId);
               // statement.setString(2, em1.Email());
                statement.setString(3, newPassword);

                // Execute the query
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Record inserted successfully.");
                } else {
                    System.out.println("Failed to insert the record.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
