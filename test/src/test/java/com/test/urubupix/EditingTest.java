package com.test.urubupix;

import com.test.urubupix.pages.LoginPage;
import com.test.urubupix.pages.EditingPage;
import com.test.urubupix.pages.TransactionPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EditingTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://localhost:3000/");
    }

    @Test
    @Order(1)
    public void testIfAnyInputAreEmpty() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver);
        TransactionPage transactionPage = new TransactionPage(driver);
        Thread.sleep(1000);
        loginPage.writeEmailValue("teste@teste.com");
        Thread.sleep(1000);
        loginPage.writePasswordValue("123");
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);
        transactionPage.clickUpdateButton();
        Thread.sleep(1000);
        EditingPage editingPage = new EditingPage(driver);
        Assertions.assertTrue(editingPage.getNameInputValue().isEmpty());
        Assertions.assertTrue(editingPage.getEmailInputValue().isEmpty());
        Assertions.assertTrue(editingPage.getPasswordInputValue().isEmpty());
        Assertions.assertTrue(editingPage.getPixInputValue().isEmpty());
    }
    @Test
    public void testIfAllInputsAreCorrect() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver);
        TransactionPage transactionPage = new TransactionPage(driver);
        Thread.sleep(1000);
        loginPage.writeEmailValue("teste@teste.com");
        Thread.sleep(1000);
        loginPage.writePasswordValue("123");
        Thread.sleep(1000);
        loginPage.clickLoginButton();
        Thread.sleep(1000);
        transactionPage.clickUpdateButton();
        Thread.sleep(1000);
        EditingPage editingPage = new EditingPage(driver);
        Thread.sleep(1000);
        editingPage.WriteNameValue("name");
        Thread.sleep(1000);
        editingPage.WriteEmailValue("teste@teste.com");
        Thread.sleep(1000);
        editingPage.WritePasswordValue("123");
        Thread.sleep(1000);
        editingPage.WritePixValue("pixTest");
        Thread.sleep(1000);

        Assertions.assertEquals("name", editingPage.getNameInputValue());
        Thread.sleep(1000);
        Assertions.assertEquals("teste@teste.com", editingPage.getEmailInputValue());
        Thread.sleep(1000);
        Assertions.assertEquals("123", editingPage.getPasswordInputValue());
        Thread.sleep(1000);
        Assertions.assertEquals("pixTest", editingPage.getPixInputValue());
        Thread.sleep(1000);

        editingPage.clickConfirmButton();
    }

    @AfterEach
    public void tearDown() {
        // Fecha o navegador após cada teste
        if (driver != null) {
            driver.quit();
        }
    }
}
