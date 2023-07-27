package emailapp;

import javax.swing.JOptionPane;

public class FormValidator {

    public static boolean validateFields(FormData formData) {
        String firstName = formData.getFirstName().trim();
        String lastName = formData.getLastName().trim();
        String department = formData.getDepartment().trim();
        String company = formData.getCompany().trim();
        String phoneNumber = formData.getPhoneNumber().trim();
        String altEmail = formData.getAltEmail().trim();
        String newPassword = formData.getNewPassword().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || department.isEmpty() || company.isEmpty()
                || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
            return false;
        }

        if (!isValidString(firstName, "first name")) {
            return false;
        }

        if (!isValidString(lastName, "last name")) {
            return false;
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            return false;
        }

        if (!isValidString(department, "department name")) {
            return false;
        }

        if (!isValidString(company, "company name")) {
            return false;
        }

        if (!isValidAltEmail(altEmail)) {
            return false;
        }

        if (!isValidPassword(newPassword)) {
            return false;
        }

        return true;
    }

    private static boolean isValidString(String input, String fieldName) {
        if (!input.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid " + fieldName + ".");
            return false;
        }
        return true;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid 10-digit phone number.");
            return false;
        }
        return true;
    }
    
    private static boolean isValidAltEmail(String altEmail) {
        if (altEmail == null || altEmail.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alternate email cannot be empty.");
            return false;
        }
        return true;
    }
    
    private static boolean isValidPassword(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "New password cannot be empty.");
            return false;
        }
        return true;
    }
}
