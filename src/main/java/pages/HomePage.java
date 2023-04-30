package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseSetup;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class HomePage extends BaseSetup {
    private WebDriver driver;
    public String urlHP = "https://daotao.vku.udn.vn/";

    //Top Bar / Left menu Items
    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/a")
    @CacheLookup
    public List<WebElement> topBarLeftMenuItems;

    //Top Bar / Right menu Items BTN
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[3]/ul[1]/li/a/button")
    @CacheLookup
    public List<WebElement> topBarRightMenuItems;

    //topbar / right menu list
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[3]/ul/li")
    @CacheLookup
    public List<WebElement> topBarRightMenuSubList;

    //Right menu Items btn
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]")
    @CacheLookup
    public WebElement P;

    //trung tâm btn
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]")
    @CacheLookup
    public WebElement TT;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[1]")
    @CacheLookup
    public WebElement DT;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]")
    @CacheLookup
    public WebElement K;

    //nvabar item
    @FindBy(className = "dropdown")
    @CacheLookup
    public List<WebElement> navbarItems;

    //navbar item text
    @FindBy(className = "dropdown-toggle")
    @CacheLookup
    public List<WebElement> navbarItemsText;

    @FindBy(className = "dropdown-menu")
    @CacheLookup
    public WebElement navbarDropMenu;


    //navbar items btn
    @FindBy(xpath = "//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[1]")
    @CacheLookup
    public WebElement GT;

    @FindBy(xpath = "//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[2]")
    @CacheLookup
    public WebElement TB;

    @FindBy(xpath = "//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[3]")
    @CacheLookup
    public WebElement TKB;

    @FindBy(xpath = "//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[4]")
    @CacheLookup
    public WebElement QT;

    @FindBy(xpath = "//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[5]")
    @CacheLookup
    public WebElement CTDT;

    @FindBy(xpath = "//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[6]")
    @CacheLookup
    public WebElement L;

    @FindBy(xpath = "//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[7]")
    @CacheLookup
    public WebElement DN;

    //search input
    @FindBy(xpath = "//input[@id='search']")
    @CacheLookup
    public WebElement searchInput;

    //search button
    @FindBy(className = "btn-search")
    @CacheLookup
    public WebElement searchButton;

    @FindBy(xpath ="//body/div[2]/div[1]/div[1]/div[1]/a[1]/img[1]")
    @CacheLookup
    public WebElement logo;

    @FindBy(xpath = "//body/div[2]/div[1]/div[1]/div[2]/div[1]/a[1]")
    @CacheLookup
    public WebElement translateImg;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean validPageTitle(String expectedResult){
        String actualPageTitle = driver.getTitle();
        return actualPageTitle.contains(expectedResult);
    }

    public void navigate(String link, String expectedResult) {
        // Find the button element and click it to open tab 2
        WebElement element = driver.findElement(By.xpath(link));
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).moveToElement(element).click().perform();

        // Wait for tab 2 to open and switch to it
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String actualTitle = driver.getTitle();

        driver.close();
        driver.switchTo().window(tabs.get(0));

        Assert.assertTrue(actualTitle.contains(expectedResult));
    }


}
