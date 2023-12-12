package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    private final WebDriver driver;
    private final By title = By.cssSelector("text:nth-child(1)");
    private final By userEmail = By.cssSelector("text:nth-child(3)");
    private final By userKey = By.cssSelector("text:nth-child(5)");
    private final By amountInput = By.cssSelector("input[type='number']");
    private final By editBtn = By.cssSelector("button:nth-child(1)");
    private final By deleteBtn = By.cssSelector("button:nth-child(2)");
    private final By transferBtn = By.cssSelector("button:nth-child(3)");

    LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public String getUserEmail() {
        return driver.findElement(userEmail).getText();
    }

    public String getUserKey() {
        return driver.findElement(userKey).getText();
    }

    public void writeAmountInput(int amount) {
        driver.findElement(amountInput).sendKeys(String.valueOf(amount));
    }

    public void clickEditBtn() {
        driver.findElement(editBtn).click();
    }

    public void clickDeleteBtn() {
        driver.findElement(deleteBtn).click();
    }

    public void clickTransferBtn() {
        driver.findElement(transferBtn).click();
    }
}
