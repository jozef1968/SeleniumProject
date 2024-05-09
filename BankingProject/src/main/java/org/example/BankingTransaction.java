package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.util.Random;

public class BankingTransaction {
    WebDriver driver;

    public BankingTransaction(WebDriver driver) {
        this.driver = driver;
    }

    public String calculateDeposit(int multiplayerForTransaction) {
        Random deposit = new Random();
        double depositRandom = deposit.nextDouble() * multiplayerForTransaction + 1;
        return String.format("%,.0f", depositRandom);
    }

    public void createTransaction(String transfer) {
        if (transfer.equals("Deposit")) {
            driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div:nth-child(5) > button:nth-child(2)")).click();
            driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.container-fluid.mainBox.ng-scope > div > form > div > input")).sendKeys(calculateDeposit(1000));
            driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.container-fluid.mainBox.ng-scope > div > form > button")).click();
        } else {
            driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div:nth-child(5) > button:nth-child(3)")).click();
            driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.container-fluid.mainBox.ng-scope > div > form > div > input")).sendKeys(calculateDeposit(500));
            driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.container-fluid.mainBox.ng-scope > div > form > button")).click();
        }
    }

    public void reviewTransaction() {
        driver.findElement(By.xpath("//button[contains(., 'Transactions')]")).click();
    }
}
