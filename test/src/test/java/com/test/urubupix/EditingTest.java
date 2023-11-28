package com.test.urubupix;

import com.test.urubupix.pages.EditingPage;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class EditingTest {
    private WebDriver driver;
    private EditingPage editingPage;
    final String editindUrl = "http://localhost:3000/landing";

    @BeforeEach
    public void setUp(){
        driver = new FirefoxDriver();
        driver.get(editindUrl);
        editingPage = new EditingPage(driver);
    }
}
