package com.test.urubupix;

import com.test.urubupix.pages.LoginPage;
import com.test.urubupix.pages.EditingPage;
import com.test.urubupix.pages.TransactionPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.Assertions;

public class EditingTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://localhost:3000/");
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.writeEmailValue("admin@gmail.com");
        loginPage.writePasswordValue("1234");

        loginPage.clickLoginButton();

        TransactionPage transactionPage = new TransactionPage(driver);
        transactionPage.clickUpdateButton();
    }

    @Test
    public void testEmptyFieldsOnConfirm() {
        EditingPage editingPage = new EditingPage(driver);

        // Não preencha nenhum campo e clique no botão de confirmação
        editingPage.clickConfirmButton();

        // Verifique se há uma mensagem de erro ou outra lógica para indicar campos vazios
        Assertions.assertTrue(isAnyFieldEmpty(editingPage), "Pelo menos um campo deve estar vazio.");
    }

    private boolean isAnyFieldEmpty(EditingPage editingPage) {
        // Adicione lógica para verificar se pelo menos um campo está vazio
        String nameValue = editingPage.getNameInputValue();
        String emailValue = editingPage.getEmailInputValue();
        String passwordValue = editingPage.getPasswordInputValue();
        String pixValue = editingPage.getPixInputValue();

        return nameValue.isEmpty() || emailValue.isEmpty() || passwordValue.isEmpty() || pixValue.isEmpty();
    }

}
