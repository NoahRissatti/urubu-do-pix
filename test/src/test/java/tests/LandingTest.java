package tests;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LandingPage;
import pages.LoginPage;
import pages.RegisterPage;

import java.time.Duration;

import static config.TestConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LandingTest {
    private WebDriver driver;
    private LandingPage landingPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @BeforeEach
    public void init() {
        String driverPath = "src/test/resources/drivers/chromedriver";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @AfterEach
    void quit(){
        driver.quit();
    }

    @Test
    @DisplayName("Should contain correct user data")
    void shouldContainCorrectUserData() {
        User user = loginPage.loginTestUser();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(LANDING_URL));
        assertThat(landingPage.getTitle()).contains(user.getName());
        assertThat(landingPage.getUserEmail()).isEqualTo(user.getEmail());
        // assertThat(landingPage.getUserKey()).isEqualTo(user.getPixKey());
    }
}
