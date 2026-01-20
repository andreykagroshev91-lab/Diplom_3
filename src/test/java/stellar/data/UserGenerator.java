package stellar.data;

import stellar.model.User;
import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class UserGenerator {

    private static final Faker faker = new Faker(new Locale("en"));

    public static User createValidUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomId = timestamp.substring(timestamp.length() - 6);

        return new User()
                .setEmail("testuser_" + randomId + "@example.com")
                .setPassword("Password" + randomId)
                .setName("User_" + randomId);
    }

    public static User createUserWithShortPassword() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomId = timestamp.substring(timestamp.length() - 6);

        return new User()
                .setEmail("shortpass_" + randomId + "@example.com")
                .setPassword("12345") // Менее 6 символов
                .setName("ShortPassUser_" + randomId);
    }

    public static User createRandomUser() {
        // Делаем случайную длину от 6 до 12 символов
        int randomLength = 6 + ThreadLocalRandom.current().nextInt(7); // 6-12
        String randomString = generateRandomString(randomLength);

        return new User()
                .setEmail(faker.internet().emailAddress())
                .setPassword("Pass@" + randomString + ThreadLocalRandom.current().nextInt(100, 999))
                .setName(faker.name().firstName() + " " + faker.name().lastName());
    }

    private static String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
        }
        return result.toString();
    }
}