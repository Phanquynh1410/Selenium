package pages;

import utils.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BaseSetup {

    @FindBy(id = "logIn")
    @CacheLookup
    public WebElement SignInBtn;

    @FindBy(id = "identifierId")
    @CacheLookup
    public WebElement emailInput;

    @FindBy(xpath = "//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")
    @CacheLookup
    public WebElement nextBTN;

    @FindBy(name = "password")
    @CacheLookup
    public WebElement passInput;

    @FindBy(className = "VfPpkd-Jh9lGc")
    @CacheLookup
    public WebElement nextBTN2;

    @FindBy(className = "VfPpkd-muHVFf-bMcfAe")
    @CacheLookup
    public WebElement checkBox;

    @FindBy(xpath = "//h2[contains(text(),'Quỳnh')]")
    @CacheLookup
    public WebElement userName;

    @FindBy(className = "o6cuMc")
    @CacheLookup
    public WebElement errorGmail;


    @FindBy(xpath ="//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/span")
    @CacheLookup
    public WebElement errorPass;



    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean validPageTitle(String expectedResult){
        String actualPageTitle = driver.getTitle();
//        System.out.println("actualPageTitle: " + actualPageTitle);
        return actualPageTitle.equals(expectedResult);
    }
}
