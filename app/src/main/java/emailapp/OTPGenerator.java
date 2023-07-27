package emailapp;

public class OTPGenerator {
    private static final int OTP_LENGTH = 6;
    private static final String PASSWORD_SET = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789!@#$%^&*{}[]/-+";

    public static String generateRandomOTP() {
        StringBuilder otpBuilder = new StringBuilder(OTP_LENGTH);

        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = (int) (Math.random() * PASSWORD_SET.length());
            otpBuilder.append(PASSWORD_SET.charAt(randomIndex));
        }

        return otpBuilder.toString();
    }
}
