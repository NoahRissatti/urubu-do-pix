package com.test.urubupix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditingPage {
    private WebDriver driver;
    private By nameInput = By.cssSelector("input:nth-child(2)");
    private By emailInput = By.cssSelector("input[name='email']");
    private By passwordInput = By.cssSelector("input[name='senha']");
    private By pixInput = By.cssSelector("#root input[name='chavePix']");

    private By confirmButton = By.cssSelector(("button:nth-child(1)"));

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

    public String getNameInputValue() {
        return driver.findElement(nameInput).getAttribute("value");
    }

    public String getEmailInputValue() {
        return driver.findElement(emailInput).getAttribute("value");
    }

    public String getPasswordInputValue() {
        return driver.findElement(passwordInput).getAttribute("value");
    }

    public String getPixInputValue() {
        return driver.findElement(pixInput).getAttribute("value");
    }

}
