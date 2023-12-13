package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final By nameInput = By.name("nome");
    private final By emailInput = By.name("email");
    private final By passInput = By.name("senha");
    private final By keyInput = By.name("chavePix");
    private final By cpfRadio = By.cssSelector("input[type='radio'][value='CPF']");
    private final By emailRadio = By.cssSelector("input[type='radio'][value='Email']");
    private final By cancelBtn = By.cssSelector("button:nth-child(1)");
    private final By registerBtn = By.cssSelector("button:nth-child(2)");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void writeNameInput(String name){
        driver.findElement(nameInput).sendKeys(name);
    }

    public void writeEmailInput(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void writePassInput(String pass){
        driver.findElement(passInput).sendKeys(pass);
    }

    public void writeKeyInput(String key){
        driver.findElement(keyInput).sendKeys(key);
    }

    public void selectCpfRadio(){
        driver.findElement(cpfRadio).click();
    }

    public void selectEmailRadio(){
        driver.findElement(emailRadio).click();
    }

    public void clickCancelBtn(){
        driver.findElement(cancelBtn).click();
    }

    public void clickRegisterBtn() {
        driver.findElement(registerBtn).click();
    }

    public String getModalMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
        return webElement.getText();
    }

    public String getAlertMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

}
