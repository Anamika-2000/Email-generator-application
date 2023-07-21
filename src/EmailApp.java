import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class EmailApp extends JFrame implements ActionListener {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField companyField;
    private JTextField departmentField;
    private JTextField phone_number;
    private JRadioButton domainNameYes;
    private JRadioButton domainNameNo;
    private JButton generateEmailButton;
    private JButton generateOTPButton;
    private JButton submitButton;
    private JButton generateUniqueId;

    private boolean otpGenerated = false;

    public EmailApp() {
        super("Email Generator Application");

        // Set frame properties
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Create panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add input fields and labels with better spacing
        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("Phone Number:"));
        phone_number = new JTextField();
        inputPanel.add(phone_number);

        inputPanel.add(new JLabel("Company:"));
        companyField = new JTextField();
        inputPanel.add(companyField);

        inputPanel.add(new JLabel("Department:"));
        departmentField = new JTextField();
        inputPanel.add(departmentField);

        inputPanel.add(new JLabel("Domain Name:"));
        JPanel domainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        domainNameYes = new JRadioButton("Yes");
        domainNameNo = new JRadioButton("No");
        domainNameNo.setSelected(true); // Default value for domainNameNo
        ButtonGroup domainGroup = new ButtonGroup();
        domainGroup.add(domainNameYes);
        domainGroup.add(domainNameNo);
        domainPanel.add(domainNameYes);
        domainPanel.add(domainNameNo);
        inputPanel.add(domainPanel);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        generateUniqueId = new JButton("Generate Unique ID");
        generateUniqueId.addActionListener(this);
        generateUniqueId.setToolTipText("Click to use Phone Number as Unique ID");
        generateEmailButton = new JButton("Generate Email");
        generateEmailButton.addActionListener(this);
        generateOTPButton = new JButton("Generate OTP");
        generateOTPButton.addActionListener(this);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        buttonPanel.add(generateUniqueId);
        buttonPanel.add(generateEmailButton);
        buttonPanel.add(generateOTPButton);
        buttonPanel.add(submitButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateUniqueId) {
            if (validateFields()) {
                String uniqueId = phone_number.getText();
                JOptionPane.showMessageDialog(this, "Your Unique ID: " + uniqueId);
            }
        } else if (e.getSource() == generateEmailButton) {
            if (validateFields()) {
                String firstName = firstNameField.getText().toLowerCase();
                String lastName = lastNameField.getText().toLowerCase();
                String department = departmentField.getText().toLowerCase();
                String company = companyField.getText().toLowerCase();

                String domainName = domainNameYes.isSelected() ? "y" : "n";
                String email;
                if (domainName.equalsIgnoreCase("y")) {
                    email = generateEmailWithDomain(firstName, lastName, department, company);
                } else {
                    email = generateEmailWithoutDomain(firstName, company);
                }

                JOptionPane.showMessageDialog(this, "Generated Email: " + email);
            }

        } else if (e.getSource() == generateOTPButton) {
            if (validateFields()) {
                if (!otpGenerated) {
                    String otp = generateRandomOTP();
                    JOptionPane.showMessageDialog(this, "Generated OTP: " + otp);
                    otpGenerated = true;
                } else {
                    JOptionPane.showMessageDialog(this, "OTP already generated. Please submit the form.");
                }
            }

        } else if (e.getSource() == submitButton) {
            if (validateFields()) {
                String altEmail = JOptionPane.showInputDialog(this, "Enter alternate email:");
                String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
                // Perform submit logic with altEmail and newPassword
                submitForm(altEmail, newPassword);
            }
        }
    }

    private boolean validateFields() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String department = departmentField.getText().trim();
        String company = companyField.getText().trim();
        String phoneNumber = phone_number.getText().trim();

        if (firstName.isEmpty() || !isValidString(firstName)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid first name (ONLY STRING IS ALLOWED).");
            return false;
        }

        if (lastName.isEmpty() || !isValidString(lastName)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid last name (ONLY STRING IS ALLOWED).");
            return false;
        }

        if (phoneNumber.isEmpty() || !isValidPhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number.");
            return false;
        }

        if (department.isEmpty() || !isValidString(department)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid department name (ONLY STRING IS ALLOWED).");
            return false;
        }

        if (company.isEmpty() || !isValidString(company)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid company name(ONLY STRING IS ALLOWED).");
            return false;
        }

        return true;
    }

    private boolean isValidString(String str) {
        return str.matches("[a-zA-Z]+");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    private String generateEmailWithDomain(String firstName, String lastName, String department, String company) {
        return firstName + "." + lastName + "@" + department + "." + company + ".com";
    }

    private String generateEmailWithoutDomain(String firstName, String company) {
        return firstName + "." + company + "@gmail.com";
    }

    private String generateRandomOTP() {
        int length = 6;
        String passwordSet = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789!@#$%^&*{}[]/-+";
        StringBuilder otpBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * passwordSet.length());
            otpBuilder.append(passwordSet.charAt(randomIndex));
        }

        return otpBuilder.toString();
    }

    private void submitForm(String altEmail, String newPassword) {
        // Perform form submission logic

        // Update database with the new password
        updatePasswordInDatabase(newPassword);

        // Clear the input fields
        firstNameField.setText("");
        lastNameField.setText("");
        companyField.setText("");
        departmentField.setText("");
        domainNameYes.setSelected(false);
        domainNameNo.setSelected(false);

        // Clear the alternate email and new password inputs
        altEmail = "";
        newPassword = "";

        JOptionPane.showMessageDialog(this, "Form submitted successfully!");
    }

    private void updatePasswordInDatabase(String newPassword) {
        String employeeId = generateEmployeeId(); // Replace with your logic to generate employee ID
        String databaseURL = "jdbc:mysql://localhost:3306/EmailData";
        String username = "root";
        String password = "Anamika@2000";

        try (Connection connection = DriverManager.getConnection(databaseURL, username, password);
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE Dataemail SET password = ? WHERE employee_id = ?")) {
            statement.setString(1, newPassword);
            statement.setString(2, employeeId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Failed to update the password.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private String generateEmployeeId() {
        // Replace with your logic to generate the employee ID
        return "EMP123";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmailApp::new);
    }
}
