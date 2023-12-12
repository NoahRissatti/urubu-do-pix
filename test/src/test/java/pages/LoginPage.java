package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private By emailInput = By.name("email");
    private By passInput = By.name("senha");
    private By registerBtn = By.cssSelector("button:nth-child(1)");
    private By loginBtn = By.cssSelector("button:nth-child(2)");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void writeEmailInput(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void writePassInput(String pass){
        driver.findElement(passInput).sendKeys(pass);
    }

    public void clickRegisterBtn(){
        driver.findElement(registerBtn).click();
    }

    public void clickLoginBtn(){
        driver.findElement(loginBtn).click();
    }

    public String getAlertMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public User loginTestUser() {
        User user = User.getTestUser();
        writeEmailInput(user.getEmail());
        writePassInput(user.getPass());
        clickLoginBtn();
        return user;
    }
}
