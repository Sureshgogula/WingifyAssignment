package Scripts;

import PageObjects.LoginPageObjects;
import ScreenClick.ScreenShotClass;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TestCase {

    WebDriver driver;
    ScreenShotClass screenShot = new ScreenShotClass();


    FileInputStream fs = new FileInputStream("C:\\Users\\suresh gogula\\IdeaProjects\\wingifyAssignmen\\config.properties");
    Properties prop = new Properties();


    public TestCase() throws IOException {
        prop.load(fs);
    }


    @BeforeMethod
    public void setup() {
        System.setProperty("WebDrive.chrome.Driver", "C:\\Users\\suresh gogula\\OneDrive\\Desktop\\chro01\\chromedriver.exe");
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        ScreenShotClass screenShot = new ScreenShotClass();
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sakshingp.github.io/assignment/login.html");
    }


    @Test(description = "TC002-This test verifies validation message displayed when user login with blank user name and password", priority = 1)
    public void TC_2() throws IOException {
        LoginPageObjects objects = new LoginPageObjects(driver);
        objects.lnkLogin.click();
        String actualMessage = String.valueOf(objects.BothFieldsBlankErrorMessage);
        //Take the Screenshot Only, If the Test is failed.
        if (actualMessage.contains("Both Username and Password must be present ")) {
            System.out.println("Test Case Passed");
            Reporter.log("Username and Password are required validation message is displayed");
        } else {
            System.out.println("Test Case Failed");
            Reporter.log("Username and Password are required validation message is not displayed");
            screenShot.snopShort(driver, "C:\\Users\\suresh gogula\\IdeaProjects\\wingifyAssignmen\\ScreenShot\\img1.jpg");
        }
    }

    @Test(description = "TC003-This test verifies that user can login with valid credentials", priority = 2)
    public void TC_3() throws IOException {
        LoginPageObjects objects = new LoginPageObjects(driver);
        objects.txtUsername.sendKeys("username");
        objects.txtPassword.sendKeys("password");
        objects.lnkLogin.click();
        Reporter.log("Login Successful");
        String HomePageURL = "https://sakshingp.github.io/assignment/home.html";
        Assert.assertEquals(driver.getCurrentUrl(), HomePageURL);
    }

    @Test(description = "TC001-This test verifies LoginPage Title,username and Password fields present,login Button is enabled", priority = 0 )
    public void TC_1() {
        LoginPageObjects objects = new LoginPageObjects(driver);
        Assert.assertNotNull(driver.getTitle(), "Title is not Present");
        Reporter.log("Title is Displayed");
        Assert.assertTrue(objects.txtPassword.isDisplayed());
        Reporter.log("Password Test Box is Present");
        Assert.assertTrue(objects.txtUsername.isDisplayed());
        Reporter.log("Username Test Box is Present");
        Assert.assertTrue(objects.lnkLogin.isEnabled());
        Reporter.log("Login Button is enabled");
    }


    @Test(description = "TC004-This test verifies validation message displayed when user login with valid username and blank password", priority = 3)
    public void TC_4() throws IOException {
        LoginPageObjects objects = new LoginPageObjects(driver);
        objects.txtUsername.sendKeys("username");
        objects.lnkLogin.click();
        String ActualMessage = String.valueOf(objects.PasswordFieldBlankErrorMessage);
        //Take the Screenshot Only, If the Test is failed.
        if (ActualMessage.contains("Password must be present")) {
            System.out.println("Test Case Passed");
            Reporter.log("Password must be present Message is Displayed");
        } else {
            System.out.println("Test Case Failed");
            Reporter.log("Password must be present Message is not Displayed");
            screenShot.snopShort(driver, "C:\\Users\\suresh gogula\\IdeaProjects\\wingifyAssignmen\\ScreenShot\\img3.jpg");
        }

    }

    @Test(description = "TC005-This test verifies validation message displayed when user login with valid password and blank username", priority = 4)
    public void TC_5() throws IOException {
        LoginPageObjects objects = new LoginPageObjects(driver);
        objects.txtPassword.sendKeys("password");
        objects.lnkLogin.click();
        String ActualMessage = String.valueOf(objects.UsernameFieldBlankErrorMessage);
        //Take the Screenshot Only, If the Test is failed.
        if (ActualMessage.contains("Username must be present")) {
            System.out.println("Test Case Passed");
            Reporter.log("username must be present Message is Displayed");
        } else {
            System.out.println("Test Case Failed");
            Reporter.log("username must be present Message is not Displayed");
            screenShot.snopShort(driver, "C:\\Users\\suresh gogula\\IdeaProjects\\wingifyAssignmen\\ScreenShot\\img4.jpg");
        }
    }

    @Test(description = "click the AMOUNT header in the transaction table and check the values are sorted", dependsOnMethods = "TC_2", priority = 5)
    public void TC_6() {
        LoginPageObjects objects = new LoginPageObjects(driver);
        objects.txtUsername.sendKeys("username");
        objects.txtPassword.sendKeys("password");
        objects.lnkLogin.click();
        //click the amount header to sort the values
        objects.amountHeader.click();
        // find all values in the Amount column of the transaction table
        WebElement amountValues = driver.findElement(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));


    }


    @AfterMethod()
    public void closedTest() {
        driver.close();
        Reporter.log("Method Closed");

    }
}
