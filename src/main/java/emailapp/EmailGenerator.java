package emailapp;

public class EmailGenerator {

    public static String generateEmail(FormData formData) {
        String firstName = formData.getFirstName().toLowerCase();
        String lastName = formData.getLastName().toLowerCase();
        String department = formData.getDepartment().toLowerCase();
        String company = formData.getCompany().toLowerCase();
        boolean useDomain = formData.useDomain();

        if (firstName.isEmpty() || lastName.isEmpty() || department.isEmpty() || company.isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled to generate an email.");
        }

        if (useDomain) {
            return generateEmailWithDomain(firstName, lastName, department, company);
        } else {
            return generateEmailWithoutDomain(firstName, company);
        }
    }

    private static String generateEmailWithDomain(String firstName, String lastName, String department,
                                                  String company) {
        return firstName + "." + lastName + "@" + department + "." + company + ".com";
    }

    private static String generateEmailWithoutDomain(String firstName, String company) {
        return firstName + "." + company + "@gmail.com";
    }
}
