package models;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class User {
    private String name;
    private String email;
    private String pass;
    private String pixKey;
    private static Faker faker = new Faker(new Locale( "pt_BR" ));

    public User(String name, String email, String pass, String pixKey) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.pixKey = pixKey;
    }

    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public static User getTestUser() {
        return new User("teste", "teste@email.com", "1234", "teste@email.com");
    }

    public static User getFakerUser(KeyType keyType) {
        String email = faker.internet().emailAddress();
        User user = new User(faker.name().firstName(), email, faker.internet().password());
        switch (keyType) {
            case EMAIL -> user.setPixKey(email);
            case CPF -> user.setPixKey(generateCpfKey());
        }
        return user;
    }

    private static String generateCpfKey() {
        Random random = new Random();

        int digit1 = random.nextInt(10);
        int digit2 = random.nextInt(10);
        int digit3 = random.nextInt(10);
        int digit4 = random.nextInt(10);
        int digit5 = random.nextInt(10);
        int digit6 = random.nextInt(10);
        int digit7 = random.nextInt(10);
        int digit8 = random.nextInt(10);
        int digit9 = random.nextInt(10);

        int sum1 = digit9 * 2 + digit8 * 3 + digit7 * 4 + digit6 * 5 + digit5 * 6 + digit4 * 7 + digit3 * 8 + digit2 * 9 + digit1 * 10;
        int remainder1 = sum1 % 11;
        int firstVerifierDigit = (remainder1 < 2) ? 0 : (11 - remainder1);

        int sum2 = firstVerifierDigit * 2 + digit9 * 3 + digit8 * 4 + digit7 * 5 + digit6 * 6 + digit5 * 7 + digit4 * 8 + digit3 * 9 + digit2 * 10 + digit1 * 11;
        int remainder2 = sum2 % 11;
        int secondVerifierDigit = (remainder2 < 2) ? 0 : (11 - remainder2);

        return String.format("%03d.%03d.%03d-%02d",
                digit1 * 100 + digit2 * 10 + digit3,
                digit4 * 100 + digit5 * 10 + digit6,
                digit7 * 100 + digit8 * 10 + digit9,
                firstVerifierDigit * 10 + secondVerifierDigit);
    }
}
