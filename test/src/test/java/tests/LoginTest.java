package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import static config.TestConstants.BASE_URL;
import static config.TestConstants.LOGIN_ERROR_MSG;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void init() {
        String driverPath = "src/test/resources/drivers/chromedriver";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void quit(){
        driver.quit();
    }

    @Test
    @DisplayName("Should not login with a not registered user")
    void shouldNotLoginWithANotRegisteredUser() {
        Faker faker = new Faker();
        loginPage.writeEmailInput(faker.internet().emailAddress());
        loginPage.writePassInput(faker.internet().password());
        loginPage.clickLoginBtn();
        assertThat(loginPage.getAlertMessage()).isEqualTo(LOGIN_ERROR_MSG);
    }



}
