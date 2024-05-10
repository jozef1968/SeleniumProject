package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class BankingAutomation {

    public static void main (String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        BankingHome homePageOfBank = new BankingHome(driver);
        homePageOfBank.clickBankManagerLogin();
        int userAddedId = homePageOfBank.clickCreateCustomer();
        homePageOfBank.cpenAccountClick();
        BankingLogin signupPageOfBank = new BankingLogin(driver, userAddedId);
        signupPageOfBank.clickSignUpPageOfBankAsNewAddedUser();
        homePageOfBank.waitTime(2000);
        BankingTransaction transaction = new BankingTransaction(driver);
        for (int i = 0; i < 4; i++) {
            transaction.createTransaction("Deposit");
        }
        homePageOfBank.waitTime(2000);
        for (int i = 0; i < 4; i++) {
            transaction.createTransaction("Withdrawl");
        }
        homePageOfBank.waitTime(2000);
        transaction.reviewTransaction();
    }
}
