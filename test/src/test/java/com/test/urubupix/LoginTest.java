package com.test.urubupix;

import com.test.urubupix.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;
import java.util.stream.Stream;

public class LoginTest {
    private WebDriver driver;
    final String baseUrl = "http://localhost:3000";
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
    }

    @DisplayName("test the user success login")
    @ParameterizedTest
    @MethodSource("provideUserSuccessTest")
    void testUserSuccessLogin(String userEmail, String userPassword, String result) throws InterruptedException {
        loginPage.writeEmailValue(userEmail);
        Thread.sleep(1000);
        loginPage.writePasswordValue(userPassword);
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);
        assertThat(loginPage.getCurrentURL()).isEqualTo(result);
    }

    @DisplayName("test the user failed login")
    @ParameterizedTest
    @MethodSource("provideUserFailedTest")
    void testUserFailedLogin(String userEmail, String userPassword, String result) throws InterruptedException {
        loginPage.writeEmailValue(userEmail);
        Thread.sleep(1000);
        loginPage.writePasswordValue(userPassword);
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);

        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        final String alertText = alert.getText();
        alert.accept();
        assertThat(alertText).isEqualTo(result);
    }

    @DisplayName("test if the email input are empty")
    @ParameterizedTest
    @MethodSource("provideEmptyInput")
    void testEmptyInputEmail(String userPassword, String result) throws InterruptedException {
        loginPage.writePasswordValue(userPassword);
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);

        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        final String alertText = alert.getText();
        alert.accept();
        assertThat(alertText).isEqualTo(result);
    }

    @DisplayName("test if the password input are empty")
    @ParameterizedTest
    @MethodSource("provideEmptyInput")
    void testPasswordInputEmail(String userEmail, String result) throws InterruptedException {
        loginPage.writeEmailValue(userEmail);
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);

        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        final String alertText = alert.getText();
        alert.accept();
        assertThat(alertText).isEqualTo(result);
    }

    static Stream<Arguments> provideUserSuccessTest() {
        return Stream.of(
                Arguments.of(
                        "teste@email.com",
                        "1234",
                        "http://localhost:3000/landing"
                )
        );
    }

    static Stream<Arguments> provideUserFailedTest() {
        return Stream.of(
                Arguments.of(
                        "errorerror@gmail.com",
                        "!@#$!@",
                        "Email ou senha incorretos. Tente novamente."
                )
        );
    }

    static Stream<Arguments> provideEmptyInput() {
        return Stream.of(
                Arguments.of(
                        "!@#@#%!@#",
                        "Por favor, preencha todos os campos antes de fazer o login."
                )
        );
    }

}
