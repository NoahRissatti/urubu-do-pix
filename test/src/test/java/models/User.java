package models;

import com.github.javafaker.Faker;

public class User {
    private String name;
    private String email;
    private String pass;
    private String pixKey;
    private String keyType;

    public User(String name, String email, String pass, String pixKey, String keyType) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.pixKey = pixKey;
        this.keyType = keyType;
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

    public String getKeyType() {
        return keyType;
    }
    public static User getTestUser() {
        return new User("teste", "teste@email.com", "1234", "teste@email.com", "Email");
    }

    public static User getFakerUser() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        return new User(faker.name().firstName(), email, faker.internet().password(), email, "Email");
    }
}