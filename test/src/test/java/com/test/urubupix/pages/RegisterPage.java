package com.test.urubupix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    private final By registerButton = By.cssSelector("button:nth-child(2)");
    private final By cancelButton = By.cssSelector("button");
    private final By nameInput = By.cssSelector("form>div:nth-child(1)>input");
    private final By emailInput = By.cssSelector("form>div:nth-child(2)>input");
    private final By passwordInput = By.cssSelector("form>div:nth-child(3)>input");
    private final By pixKeyInput = By.cssSelector("form>div:nth-child(4)>input");
    private final By cpfRadioInput = By.cssSelector("form>div:nth-child(5)>div>label:nth-child(1)>input");
    private final By emailRadioInput = By.cssSelector("form>div:nth-child(5)>div>label:nth-child(2)>input");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
    }
    public void clickCancelButton(){
        driver.findElement(cancelButton).click();
    }
    public void writeInputName(String name){
        driver.findElement(nameInput).sendKeys(name);
    }
    public void writeInputEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    }
    public void writeInputPassword(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void writeInputPixKey(String pixKey){
        driver.findElement(pixKeyInput).sendKeys(pixKey);
    }
    public void selectTypeKey(PixType pixType){
        if(pixType.equals(PixType.CPF)){
            driver.findElement(cpfRadioInput).click();
        }else {
            driver.findElement(emailRadioInput).click();
        }
    }
    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }
}
