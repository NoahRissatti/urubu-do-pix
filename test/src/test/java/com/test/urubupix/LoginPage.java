package com.test.urubupix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By emailInput = By.cssSelector("#root>div>div>div:nth-child(2)>div:nth-child(1)>input");
    private By passwordInput = By.cssSelector("#root>div>div>div:nth-child(2)>div:nth-child(2)>input");
    private By registerButton = By.cssSelector("button:nth-child(1)");
    private By loginButton = By.cssSelector("button:nth-child(2)");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void writeEmailValue(String emailValue){
        driver.findElement(emailInput).sendKeys(emailValue);
    }

    public void writePasswordValue(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }

}
