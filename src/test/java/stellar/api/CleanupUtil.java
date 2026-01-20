package stellar.api;

import stellar.model.User;

public class CleanupUtil {

    public static void cleanupTestUser(User user) {
        try {
            if (user != null && user.getAccessToken() != null) {
                UserClient.deleteUser(user.getAccessToken());
                System.out.println("Test user cleaned up: " + user.getEmail());
            }
        } catch (Exception e) {
            System.out.println("Cleanup warning: " + e.getMessage());
        }
    }
}