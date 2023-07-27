package emailapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mongodb.client.*;

public class AppFrame extends JFrame implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField companyField;
    private JTextField departmentField;
    private JTextField phone_number;
    private JTextField alternate_mail;
    private JTextField new_password;
    private JRadioButton domainNameYes;
    private JRadioButton domainNameNo;
    private JButton generateEmailButton;
    private JButton generateOTPButton;
    private JButton submitButton;
    private JButton generateUniqueId;

    private boolean otpGenerated = false;

    public AppFrame() {
        super("Email Generator Application");

        // Set frame properties
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Create panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2, 10, 10));
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

        inputPanel.add(new JLabel("Alternate Email ID:"));
        alternate_mail = new JTextField();
        inputPanel.add(alternate_mail);

        inputPanel.add(new JLabel("Use Domain Name:"));
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
        inputPanel.add(new JLabel("New Password:"));
        new_password = new JTextField();
        inputPanel.add(new_password);

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

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Display the frame
        setVisible(true);
    }

    private FormData getFormData() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String department = departmentField.getText().trim();
        String company = companyField.getText().trim();
        String phoneNumber = phone_number.getText().trim();
        String altEmail = alternate_mail.getText().trim();
        String newPassword = new_password.getText().trim();
        boolean useDomain = domainNameYes.isSelected();
        return new FormData(firstName, lastName, company, department, phoneNumber, altEmail, newPassword, useDomain);
    }

    private void submitForm(FormData formData) {
        String connectionString = MongoDBConfig.CONNECTION_STRING;
        String databaseName = MongoDBConfig.DATABASE_NAME;
        String collectionName = MongoDBConfig.COLLECTION_NAME;

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDBManager mongoDBManager = new MongoDBManager(mongoClient, databaseName, collectionName);
            mongoDBManager.insertFormData(formData);
            JOptionPane.showMessageDialog(this, "Form submitted successfully.");

            // Clear input fields after saving data
            firstNameField.setText("");
            lastNameField.setText("");
            companyField.setText("");
            departmentField.setText("");
            phone_number.setText("");
            alternate_mail.setText("");
            domainNameNo.setSelected(true);
            new_password.setText("");
            otpGenerated = false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error while submitting form: " + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateUniqueId) {
            if (FormValidator.validateFields(getFormData())) {
                String uniqueId = phone_number.getText();
                JOptionPane.showMessageDialog(this, "Your Unique ID: " + uniqueId);
            }
        } else if (e.getSource() == generateEmailButton) {
            if (FormValidator.validateFields(getFormData())) {
                boolean useDomain = getFormData().useDomain();
                if (useDomain) {
                    String email = EmailGenerator.generateEmail(getFormData());
                    JOptionPane.showMessageDialog(this, "Generated Email: " + email);
                } else {
                    String email = EmailGenerator.generateEmail(getFormData());
                    JOptionPane.showMessageDialog(this, "Generated Email: " + email);
                }
            }
        } else if (e.getSource() == generateOTPButton) {
            if (FormValidator.validateFields(getFormData())) {
                if (!otpGenerated) {
                    String otp = OTPGenerator.generateRandomOTP();
                    JOptionPane.showMessageDialog(this, "Generated OTP: " + otp);
                    otpGenerated = true;
                }
            }
        } else if (e.getSource() == submitButton) {
            if (FormValidator.validateFields(getFormData())) {
                submitForm(getFormData());
            }
        }
    }

}
