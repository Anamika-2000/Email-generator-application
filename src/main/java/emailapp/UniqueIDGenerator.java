package emailapp;

public class UniqueIDGenerator {
    public static String generateUniqueID(FormData formData) {
        String phoneNumber = formData.getPhoneNumber();

        String uniqueId = "786" + phoneNumber;

        return uniqueId;
    }
}
