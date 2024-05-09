package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BankingLogin
{
    WebDriver driver;
    int addedUserNb;
    public BankingLogin(WebDriver driver, int addedUserNb) {
        this.driver = driver;
        this.addedUserNb = addedUserNb;
    }

    public void clickSignUpPageOfBankAsNewAddedUser() {
        driver.findElement(By.xpath("//button[contains(., 'Customer Login')]")).click();
        driver.findElement(By.xpath("//*[@id=\"userSelect\"]")).click();
        WebElement listOfUsers = driver.findElement(By.tagName("select"));
        List<WebElement> usersFromList = listOfUsers.findElements(By.tagName("option"));
        for (WebElement user : usersFromList) {
            if (!user.getDomAttribute("value").isEmpty()) {
                if (Integer.parseInt(user.getDomAttribute("value")) == addedUserNb) {
                    driver.findElement(By.xpath("//*[@id=\"userSelect\"]")).sendKeys(user.getText());
                }
            }
        }
        driver.findElement(By.xpath("//*[@id=\"userSelect\"]")).sendKeys(Keys.TAB);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Login')]")));
        button.click();
    }
}

