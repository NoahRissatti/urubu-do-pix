package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By emailInput = By.name("email");
    private By passInput = By.name("senha");
    private By registerBtn = By.cssSelector("button:nth-child(1)");
    private By loginBtn = By.cssSelector("button:nth-child(2)");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void writeEmailInput(String emailValue){
        driver.findElement(emailInput).sendKeys(emailValue);
    }

    public void writePassInput(String password){
        driver.findElement(passInput).sendKeys(password);
    }

    public void clickRegisterBtn(){
        driver.findElement(registerBtn).click();
    }

    public void clickLoginBtn(){
        driver.findElement(loginBtn).click();
    }
}
