package com.test.urubupix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    @Test
    @DisplayName("Should open and close chrome browser")
    void shouldOpenAndCloseChromeBrowser() throws InterruptedException {
// sets the driver path
        WebDriver driver = new FirefoxDriver(); // creates a driver to interact with the browser
        driver.get("https://www.google.com"); // request the page
        Thread.sleep(1000); // waits for 1s.
        driver.quit(); // quits all WebDriver instances and closes the browser
    }
}


