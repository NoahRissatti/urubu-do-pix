package tests;

import com.github.javafaker.Faker;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import static config.TestConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private Faker faker;

    @BeforeEach
    public void init() {
        String driverPath = "src/test/resources/drivers/chromedriver";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
        faker = new Faker();
    }

    @AfterEach
    void quit(){
        driver.quit();
    }

    @Test
    @DisplayName("Should not login with a not registered user")
    void shouldNotLoginWithANotRegisteredUser() {
        loginPage.writeEmailInput(faker.internet().emailAddress());
        loginPage.writePassInput(faker.internet().password());
        loginPage.clickLoginBtn();
        assertThat(loginPage.getAlertMessage()).isEqualTo(LOGIN_ERROR_MSG);
    }

    @Test
    @DisplayName("Should login with success")
    void shouldLoginWithSuccess() {
        loginPage.writeEmailInput(User.getTestUser().getEmail());
        loginPage.writePassInput(User.getTestUser().getPass());
        loginPage.clickLoginBtn();
        assertThat(driver.getCurrentUrl()).isEqualTo(LANDING_URL);
    }
}
