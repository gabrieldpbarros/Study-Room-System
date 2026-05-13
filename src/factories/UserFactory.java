package factories;

import observers.Report;
import observers.User;

public class UserFactory {
    private UserFactory(){}

    public static User createUser(int userId, String name, String role) {
        return new User(userId, name, role);
    }
    public static Report createReporter(int userId) {
        return new Report(userId);
    }
}