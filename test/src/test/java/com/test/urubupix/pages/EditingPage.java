package com.test.urubupix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditingPage {
    private WebDriver driver;
    private By nameInput = By.cssSelector("[placeholder='Insira seu novo nome']");
    private By emailInput = By.cssSelector("[placeholder='Insira seu novo e-mail']");
    private By passwordInput = By.cssSelector("[placeholder='Insira sua nova senha']");
    private By pixInput = By.cssSelector("[placeholder='Insira sua nova chave do pix']");
    private By confirmButton = By.cssSelector("button.sc-pNWRh.jrTSJM");

    public EditingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void WriteNameValue(String nameValue){
        driver.findElement(nameInput).sendKeys(nameValue);
    }

    public void WriteEmailValue(String emailValue){
        driver.findElement(emailInput).sendKeys(emailValue);
    }

    public void WritePasswordValue(String passwordValue){
        driver.findElement(passwordInput).sendKeys(passwordValue);
    }

    public void WritePixValue(String pixValue){
        driver.findElement(pixInput).sendKeys(pixValue);
    }

    public void clickConfirmButton(){
        driver.findElement(confirmButton).click();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
