package com.au.amaysim.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jefferson on 2/1/2017.
 */
public class ManageSettingsPage {
    private WebDriver driver;

    @FindBy(linkText = "My Settings")
    private WebElement mySettingsLink;

    private WebElement waitFor(String xpath){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element;
    }

    public ManageSettingsPage(WebDriver driver) {
        this.driver = driver;
        driver.findElement(By.linkText("Activate your SIM")).click();
    }

    public String getActivateMySimText() {
        return waitFor("//*[@id=\"associate_sim_form\"]/div[2]/div/h1").getText();
    }

    public String getMySettingsText() {
        return waitFor("//*[@id=\"settings_sim_nickname\"]/div/div[1]/div/div[1]").getText();
    }

    public void clickMySettingsLink(){
        mySettingsLink.click();
    }

    public void clickEditNickname() {
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement editCallForwardingLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit_settings_phone_label")));
        editCallForwardingLink.click();
    }

    public void setNewNameAndSave(String name){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("my_amaysim2_setting_phone_label")));
        nameField.clear();
        nameField.sendKeys(name);
        WebElement saveButton = driver.findElement(By.cssSelector("#edit_settings_sim_nickname > div.row.border-top.padding-top > div > input"));
        saveButton.click();
    }

    public void clickEditCallForwardLink(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement editCallForwardingLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit_settings_call_forwarding")));
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);",editCallForwardingLink);
        editCallForwardingLink.click();
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement confirmBtn = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.className("confirm_popup_confirm")));
        js.executeScript("arguments[0].click();", confirmBtn);
    }

    public void setCallForwardRadioButton(String selection){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        List<WebElement> callForwardRadioBtn = driver.findElements(By.cssSelector("#update_call_forwarding_form > div:nth-child(2) > div"));
        System.out.println(callForwardRadioBtn.size());
        for(WebElement e: callForwardRadioBtn) {
            System.out.println(e.getText());
        }
    }
}