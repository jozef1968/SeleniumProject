package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class BankingHome
{
    WebDriver driver;
    int addedUserNb = 0 ;
    public BankingHome(WebDriver driver) {
        this.driver = driver;
    }

    public void waitTime(int time)   {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickBankManagerLogin() {
        //driver.findElement(By.xpath("//button[contains(., 'Bank Manager Login')]")).click();
        driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.borderM.box.padT20 > div:nth-child(3) > button")).click();
    }

    public int clickCreateCustomer() {
        driver.findElement(By.xpath("//button[contains(., 'Add Customer')]")).click();
        CharSequence firstName = new StringBuffer("Jozef");
        CharSequence lastName = new StringBuffer("Radecki");
        CharSequence postalCode = new StringBuffer("56-120");
        driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.ng-scope > div > div > form > div:nth-child(1) > input")).sendKeys(firstName);
        driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.ng-scope > div > div > form > div:nth-child(2) > input")).sendKeys(lastName);
        driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.ng-scope > div > div > form > div:nth-child(3) > input")).sendKeys(postalCode);
        driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.ng-scope > div > div > form > button")).click();
        String[] extractdUserNb = driver.switchTo().alert().getText().split(":");
        addedUserNb = Integer.parseInt(extractdUserNb[1]);
        driver.switchTo().alert().accept();
        return addedUserNb;
    }

    public void cpenAccountClick() {
        Random randomCurrency = new Random();
        driver.findElement(By.xpath("//button[contains(., 'Open Account')]")).click();
        driver.findElement(By.xpath("//*[@id=\"userSelect\"]")).click();
        WebElement listOfUsers = driver.findElement(By.tagName("select"));
        List<WebElement> usersFromList = listOfUsers.findElements(By.tagName("option"));
        for (WebElement e : usersFromList) {
            if (!e.getDomAttribute("value").isEmpty()) {
                if (Integer.parseInt(e.getDomAttribute("value")) == addedUserNb) {
                    driver.findElement(By.xpath("//*[@id=\"userSelect\"]")).sendKeys(e.getText());
                }
            }
        }
        driver.findElement(By.xpath("//*[@id=\"currency\"]")).click();
        WebElement listOfCurrencies = driver.findElement(By.xpath("//*[@id=\"currency\"]"));
        List<WebElement> currenciesFromList = listOfCurrencies.findElements(By.tagName("option"));
        int randomCurrencyId = randomCurrency.nextInt(currenciesFromList.size() - 1) + 1;
        driver.findElement(By.xpath("//*[@id=\"currency\"]")).sendKeys(currenciesFromList.get(randomCurrencyId).getDomAttribute("value"));
        driver.findElement(By.cssSelector("body > div > div > div.ng-scope > div > div.ng-scope > div > div > form > button")).click();
        waitTime(3000);
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//button[contains(., 'Home')]")).click();
    }
}
