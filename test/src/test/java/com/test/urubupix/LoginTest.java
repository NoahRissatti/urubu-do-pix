package com.test.urubupix;

import com.test.urubupix.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.stream.Stream;

public class LoginTest {
    private WebDriver driver;
    private String baseUrl = "http://localhost:3000";
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

    static Stream<Arguments> provideUserSuccessTest() {
        return Stream.of(
                Arguments.of(
                        "teste@email.com",
                        "1234",
                        "http://localhost:3000/landing"
                )
        );
    }
}
