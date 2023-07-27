package emailapp;

public class FormData {
    private String firstName;
    private String lastName;
    private String company;
    private String department;
    private String phoneNumber;
    private String altEmail; 
    private String newPassword;
    private String mailID; // New field for generated mail ID
    private String employeID; // New field for generated unique ID
	private boolean useDomain;
	
    public FormData(String firstName, String lastName, String company, String department, String phoneNumber,
            String altEmail, String newPassword, boolean useDomain) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.altEmail = altEmail;
        this.newPassword = newPassword;
        this.useDomain = useDomain; 
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    // New getter and setter methods for mailID and uniqueID
    public String getMailID() {
        return mailID;
    }

    public void setMailID(String mailID) {
        this.mailID = mailID;
    }

    public String getUniqueID() {
        return employeID;
    }

    public void setUniqueID(String employeID) {
        this.employeID = employeID;
    }
    public boolean useDomain() {
        return useDomain;
    }
}