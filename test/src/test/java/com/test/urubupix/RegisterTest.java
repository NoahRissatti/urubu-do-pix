package com.test.urubupix;
;
import com.test.urubupix.pages.LoginPage;
import com.test.urubupix.pages.PixType;
import com.test.urubupix.pages.RegisterPage;
import org.junit.jupiter.api.AfterEach;
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

import java.time.Duration;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegisterTest {
    private WebDriver driver;
    final String baseUrl = "http://localhost:3000";
    private RegisterPage registerPage;
    private LoginPage loginPage;
    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
    }

    @AfterEach
    public void quitPage(){
        driver.quit();
    }

    @DisplayName("test the cancel button click")
    @ParameterizedTest
    @MethodSource("provideCancelButton")
    void testCancelButtonClick(String result) throws InterruptedException {
        registerPage.clickCancelButton();
        Thread.sleep(1000);
        assertThat(registerPage.getCurrentURL()).isEqualTo(result);
    }

    @DisplayName("test user already in use")
    @ParameterizedTest
    @MethodSource("provideUserInUse")
    void testUserInUse(String userName, String userEmail, String userPassword, String result) throws InterruptedException {
        registerPage.writeInputName(userName);
        Thread.sleep(1000);
        registerPage.writeInputEmail(userEmail);
        Thread.sleep(1000);
        registerPage.writeInputPassword(userPassword);
        Thread.sleep(1000);
        registerPage.writeInputPixKey(userEmail);
        registerPage.selectTypeKey(PixType.EMAIL);
        Thread.sleep(1000);
        registerPage.clickRegisterButton();
        Thread.sleep(1000);
        Thread.sleep(1000);

        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        final String alertText = alert.getText();
        alert.accept();
        assertThat(alertText).isEqualTo(result);
    }

    @DisplayName("test empty inputs")
    @ParameterizedTest
    @MethodSource("provideEmptyInputs")
    void testEmptyInputs(String result) throws InterruptedException {
        registerPage.clickRegisterButton();
        Thread.sleep(1000);
        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();

        assertThat(alertText).isEqualTo(result);
    }

    @DisplayName("test successful user register")
    @ParameterizedTest
    @MethodSource("provideSuccessRegister")
    void testSuccessfulRegister(String userName, String userEmail, String userPassword, String result) throws InterruptedException {
        registerPage.writeInputName(userName);
        Thread.sleep(1000);
        registerPage.writeInputEmail(userEmail);
        Thread.sleep(1000);
        registerPage.writeInputPassword(userPassword);
        Thread.sleep(1000);
        registerPage.writeInputPixKey(userEmail);
        registerPage.selectTypeKey(PixType.EMAIL);
        Thread.sleep(1000);
        registerPage.clickRegisterButton();
        Thread.sleep(2000);
        final String registeredUserText = registerPage.getRegisterizedUser();
        assertThat(registeredUserText).isEqualTo(result);
    }

    static Stream<Arguments> provideCancelButton() {
        return Stream.of(
                Arguments.of(
                        "http://localhost:3000/"
                )
        );
    }
    static Stream<Arguments> provideUserInUse() {
        return Stream.of(
                Arguments.of(
                        "teste",
                       "teste@email.com",
                        "1234",
                        "O usuário já existe."
                )
        );
    }
    static Stream<Arguments> provideEmptyInputs() {
        return Stream.of(
                Arguments.of(
                        "Por favor, preencha todos os campos antes de enviar."
                )
        );
    }

    static Stream<Arguments> provideSuccessRegister() {
        return Stream.of(
                Arguments.of(
                        "novoUser",
                        "novouser@gmail.com",
                        "12345",
                        "Sua conta foi criada com sucesso."
                )
        );
    }
}
