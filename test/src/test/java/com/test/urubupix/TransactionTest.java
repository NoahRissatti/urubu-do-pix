package com.test.urubupix;

import com.test.urubupix.pages.TransactionPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TransactionTest {
    private WebDriver driver;
    final String baseUel = "http://localhost:3000/landing";
    private TransactionPage transactionPage;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(baseUel);
        transactionPage = new TransactionPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
