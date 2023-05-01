package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
    WebDriver driver;
public LoginPageObjects(WebDriver driver){
    this.driver=driver;
    PageFactory.initElements(driver,this);
}
    @FindBy(id="log-in")
    public WebElement lnkLogin;
    @FindBy(id = "username")
    public WebElement txtUsername;
    @FindBy(id = "password")
    public WebElement txtPassword;
    @FindBy(xpath = "//th[@id=\"amount\"]")
    public WebElement amountHeader;
    @FindBy(xpath = "//div[text()='Both Username and Password must be present ']")
    public WebElement BothFieldsBlankErrorMessage;
    @FindBy(xpath = "//div[text()='Password must be present']")
    public WebElement PasswordFieldBlankErrorMessage;
    @FindBy(xpath = "//div[text()='Username must be present']")
    public WebElement UsernameFieldBlankErrorMessage;
}
