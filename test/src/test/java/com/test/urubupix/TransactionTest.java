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

    @DisplayName("Test transaction greater than 250 that triggers Pilantragem")
    @ParameterizedTest
    @MethodSource("providePilantragemTransactionValueTest")
    void testTriggeringPilantragemTransaction(String userEmail, String userPassword, String moneyInput, String result) throws InterruptedException {
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

    @DisplayName("Test transaction with negative number")
    @ParameterizedTest
    @MethodSource("provideNegativeTransactionValueTest")
    void testNegativeValueTransaction(String userEmail, String userPassword, String result) throws InterruptedException {
        loginPage.writeEmailValue(userEmail);
        Thread.sleep(1000);
        loginPage.writePasswordValue(userPassword);
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);
        transactionPage.clickTransferField();
        Thread.sleep(1000);
        transactionPage.selectDownKey();
        Thread.sleep(1000);
        transactionPage.clickTransferButton();
        Thread.sleep(1000);
        assertThat(transactionPage.getTransferResult()).isEqualTo(result);
    }

    @DisplayName("Test letter input for transaction field")
    @ParameterizedTest
    @MethodSource("provideLetterInputTest")
    void testLetterInputForTransactionField(String userEmail, String userPassword, String moneyInput, String result) throws InterruptedException{
        loginPage.writeEmailValue(userEmail);
        Thread.sleep(1000);
        loginPage.writePasswordValue(userPassword);
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);
        transactionPage.writeTransferValue(moneyInput);
        Thread.sleep(1000);
        assertThat(transactionPage.getMoneyInputFieldValue()).isEqualTo(result);
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

    static Stream<Arguments> provideNegativeTransactionValueTest() {
        return Stream.of(
                Arguments.of(
                        "teste@email.com",
                        "1234",
                        null
                )
        );
    }

    static Stream<Arguments> providePilantragemTransactionValueTest() {
        return Stream.of(
                Arguments.of(
                        "teste@email.com",
                        "1234",
                        "251",
                        "Ops!"
                )
        );
    }

    static Stream<Arguments> provideLetterInputTest() {
        return Stream.of(
                Arguments.of(
                        "teste@email.com",
                        "1234",
                        "a",
                        null
                )
        );
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
