package com.au.amaysim.tests;

import com.au.amaysim.pages.LoginPage;
import com.au.amaysim.pages.ManageSettingsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jefferson on 2/1/2017.
 */
public class TestSuite {
    public WebDriver driver;
    LoginPage loginPage = null;
    ManageSettingsPage manageSettingsPage = null;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown(){
        driver.close();
        driver.quit();
    }

    @Test
    public void visitLoginPage(){
        loginPage = new LoginPage(driver);
        loginPage.setAmaysimNumber("0468827174");
        loginPage.setPassword("theHoff34");
    }

    @Test(dependsOnMethods = "visitLoginPage")
    public void visitManageSettings(){
        manageSettingsPage = loginPage.clickLogin();
        Assert.assertEquals(manageSettingsPage.getActivateMySimText(),"Enter your ICCID number to validate your SIM card");
    }

    @Test(dependsOnMethods = "visitManageSettings")
    public void editName(){
        manageSettingsPage.clickMySettingsLink();
        Assert.assertEquals(manageSettingsPage.getMySettingsText(),"SIM Nickname");
        manageSettingsPage.clickEditNickname();
        manageSettingsPage.setNewNameAndSave(UUID.randomUUID().toString());
    }

    @Test(dependsOnMethods = "editName")
    public void editCallForwardSettings(){
        manageSettingsPage.clickEditCallForwardLink();
        //manageSettingsPage.setCallForwardRadioButton("");

    }
}
