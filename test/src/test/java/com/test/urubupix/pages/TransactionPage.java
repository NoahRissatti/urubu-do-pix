package com.test.urubupix.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransactionPage {
    private WebDriver driver;
    private By updateUserButton = By.cssSelector("button:nth-child(1)");
    private By deleteUserButton = By.cssSelector("button:nth-child(2)");
    private By createTransferButton = By.cssSelector("button:nth-child(3)");
    private By cashInput = By.cssSelector("input");
    private By userEmail = By.cssSelector("#root>div>div>div:nth-child(2)>text:nth-child(2)");
    private By userPixKey = By.cssSelector("#root>div>div>div:nth-child(2)>text:nth-child(3)");

    public TransactionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickUpdateButton(){
        driver.findElement(updateUserButton).click();
    }

    public void clickDeleteButton(){
        driver.findElement(deleteUserButton).click();
    }

    public void clickTransferButton(){
        driver.findElement(createTransferButton).click();
    }

    public void writeTransferValue(String transferValue){
        driver.findElement(cashInput).sendKeys(transferValue);
    }

    public String getUserEmail(){
        return driver.findElement(userEmail).getText();
    }

    public String getUserPixKey(){
        return driver.findElement(userPixKey).getText();
    }
}
