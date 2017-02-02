package com.au.amaysim.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Jefferson on 2/1/2017.
 */
public class LoginPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"my_amaysim2_user_session_login\"]")
    private WebElement amaysimNumber;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"login_button\"]")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.amaysim.com.au/my-account/my-amaysim/login");
        PageFactory.initElements(driver, this);
    }

    public void setAmaysimNumber(String amaysimNumber) {
        this.amaysimNumber.sendKeys(amaysimNumber);
    }

    public void setPassword(String password) {
        this.password.sendKeys(password);
    }

    public ManageSettingsPage clickLogin(){
        loginBtn.click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Activate your SIM")));
        return PageFactory.initElements(driver, ManageSettingsPage.class);
    }
}
