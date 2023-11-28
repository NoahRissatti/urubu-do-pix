package com.test.urubupix;
;
import com.test.urubupix.pages.LoginPage;
import com.test.urubupix.pages.RegisterPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

    @DisplayName("test the cancel button click")
    @ParameterizedTest
    @MethodSource("provideCancelButton")
    void testCancelButtonClick(String result) throws InterruptedException {
        registerPage.clickCancelButton();
        Thread.sleep(1000);
        assertThat(registerPage.getCurrentURL()).isEqualTo(result);
    }

    static Stream<Arguments> provideCancelButton() {
        return Stream.of(
                Arguments.of(
                        "http://localhost:3000/"
                )
        );
    }
}
