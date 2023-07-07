package Email_app;
import java.util.Scanner;

public class Email {
    private String firstName, lastName, password, department, alternateEmail, email;
    private int mailBoxCapacity = 500, passwordLength = 10;

    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment();
        this.email = generateEmail();
        this.password = generateRandomPassword(passwordLength);

        System.out.println("Your email id is: " + email);
        System.out.println("Your password is: " + password);
    }

    private String setDepartment() {
        System.out.println("Enter the Department");
        Scanner scan = new Scanner(System.in);
        String departmentName = scan.next();
        return departmentName;
    }

    private String generateEmail() {
        System.out.println("ENTER YOUR COMPANY NAME");
        Scanner scan = new Scanner(System.in);
        String company = scan.next();

        System.out.println("Do you want to give a company domain name? (enter 'y' for yes and 'n' for no)");
        String domainName = scan.next();

        String email;
        if (domainName.equalsIgnoreCase("y")) {
            email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department.toLowerCase() + "." + company.toLowerCase() + ".com";
        } else if (domainName.equalsIgnoreCase("n")) {
            email = firstName.toLowerCase() + "." + company.toLowerCase() + "@gmail.com";
        } else {
            System.out.println("You have not entered a valid input, so taking 'yes' by default.");
            email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department.toLowerCase() + "." + company.toLowerCase() + ".com";
        }
        return email;
    }

    private String generateRandomPassword(int length) {
        String passwordSet = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789!@#$%^&*{}[]/-+";
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(randomIndex);
        }
        return new String(password);
    }

    public void setMailBoxCapacity(int capacity) {
        this.mailBoxCapacity = capacity;
    }

    public void setAlternateEmail(String altEmail) {
        this.alternateEmail = altEmail;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public int getMailBoxCapacity() {
        return mailBoxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public String getPassword() {
        return password;
    }
}
