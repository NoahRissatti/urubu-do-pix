package tests;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.RegisterPage;

import static config.TestConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegisterTest {
    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeEach
    public void init() {
        String driverPath = "src/test/resources/drivers/chromedriver";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver(options);
        driver.get(REGISTER_URL);
        registerPage = new RegisterPage(driver);
    }

    @AfterEach
    void quit(){
        driver.quit();
    }

    @Test
    @DisplayName("Should register a new user with success")
    void shouldRegisterANewUserWithSuccess() {
        User user = User.getFakerUser();
        registerPage.writeNameInput(user.getName());
        registerPage.writeEmailInput(user.getEmail());
        registerPage.writePassInput(user.getPass());
        registerPage.selectEmailRadio();
        registerPage.writeKeyInput(user.getPixKey());
        registerPage.clickRegisterBtn();
        assertThat(registerPage.getModalMessage()).isEqualTo(REGISTER_SUCCESS_MSG);
    }

    @Test
    @DisplayName("Should not register a user already registered")
    void shouldNotRegisterAUserAlreadyRegistered() {
        User user = User.getTestUser();
        registerPage.writeNameInput(user.getName());
        registerPage.writeEmailInput(user.getEmail());
        registerPage.writePassInput(user.getPass());
        registerPage.selectEmailRadio();
        registerPage.writeKeyInput(user.getPixKey());
        registerPage.clickRegisterBtn();
        assertThat(registerPage.getAlertMessage()).isEqualTo(REGISTER_USER_ALREADY_EXISTS_MSG);
    }

    @Test
    @DisplayName("Should not register with an empty field")
    void shouldNotRegisterWithEmptyName() {
        User user = User.getFakerUser();
        registerPage.writeEmailInput(user.getEmail());
        registerPage.writePassInput(user.getPass());
        registerPage.selectEmailRadio();
        registerPage.writeKeyInput(user.getPixKey());
        registerPage.clickRegisterBtn();
        assertThat(registerPage.getAlertMessage()).isEqualTo(REGISTER_EMPTY_MSG);
    }

    @Test
    @DisplayName("Should not register an invalid email")
    void shouldNotRegisterAnInvalidEmail() {
        User user = User.getFakerUser();
        registerPage.writeNameInput(user.getName());
        registerPage.writeEmailInput("inv@li.d");
        registerPage.writePassInput(user.getPass());
        registerPage.selectEmailRadio();
        registerPage.writeKeyInput(user.getPixKey());
        registerPage.clickRegisterBtn();
        assertThat(registerPage.getAlertMessage()).isEqualTo(REGISTER_INVALID_EMAIL_MSG);
    }
    
}
