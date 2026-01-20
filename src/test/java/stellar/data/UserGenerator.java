package stellar.data;

import stellar.model.User;
import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class UserGenerator {

    private static final Faker faker = new Faker(new Locale("en"));
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static User createValidUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomId = timestamp.substring(timestamp.length() - 6);
        int threadId = counter.getAndIncrement();

        return new User()
                .setEmail("testuser_" + randomId + "_" + threadId + "@example.com")
                .setPassword("Password" + randomId + threadId)
                .setName("User_" + randomId + "_" + threadId);
    }

    public static User createUserWithShortPassword() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomId = timestamp.substring(timestamp.length() - 6);
        int threadId = counter.getAndIncrement();

        return new User()
                .setEmail("shortpass_" + randomId + "_" + threadId + "@example.com")
                .setPassword("12345") // Менее 6 символов
                .setName("ShortPassUser_" + randomId + "_" + threadId);
    }

    public static User createRandomUser() {
        int threadId = counter.getAndIncrement();
        String baseEmail = faker.internet().emailAddress();
        // Вставляем threadId в email перед @
        String email = baseEmail.replace("@", "_" + threadId + "@");

        return new User()
                .setEmail(email)
                .setPassword(faker.internet().password(6, 12, true, true, true))
                .setName(faker.name().fullName() + "_" + threadId);
    }
}