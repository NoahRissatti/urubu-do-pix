package com.test.urubupix;

import com.test.urubupix.pages.LoginPage;
import com.test.urubupix.pages.TransactionPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransactionTest {
    private WebDriver driver;
    final String baseUel = "http://localhost:3000";
    private TransactionPage transactionPage;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(baseUel);
        transactionPage = new TransactionPage(driver);
        loginPage = new LoginPage(driver);
    }

    @DisplayName("Test transaction greater than 0 that does not trigger Pilantragem")
    @ParameterizedTest
    @MethodSource("providePositiveTransactionValueTest")
    void testNotTriggeringPilantragemTransaction(String userEmail, String userPassword, String moneyInput, String result) throws InterruptedException {
        loginPage.writeEmailValue(userEmail);
        Thread.sleep(1000);
        loginPage.writePasswordValue(userPassword);
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);
        transactionPage.writeTransferValue(moneyInput);
        Thread.sleep(1000);
        transactionPage.clickTransferButton();
        Thread.sleep(1000);
        assertThat(transactionPage.getTransferResult()).isEqualTo(result);
    }

    static Stream<Arguments> providePositiveTransactionValueTest() {
        return Stream.of(
                Arguments.of(
                        "teste@email.com",
                        "1234",
                        "250",
                        "Transferência realizada!"
                )
        );
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
