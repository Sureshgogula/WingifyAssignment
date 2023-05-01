package DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver()
    {
        if(driver==null)
            createDriver();
        return driver;
    }

    public static void createDriver()
    {
        switch("CHROME")
        {
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
                options.addArguments("start-maximized");
                options.addArguments("--disabled-popup-blocking");
                {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                }
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);

                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();

                break;
            default:
                System.out.println("No matching browser found");
                System.exit(0);
                break;
        }

    }

}
