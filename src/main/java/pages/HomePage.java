package pages;

import utils.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExcelHelpers;

import java.util.List;


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

    //nvabar item
    @FindBy(className = "dropdown")
    @CacheLookup
    public List<WebElement> navbarItems;

    //navbar item text
    @FindBy(className = "dropdown-toggle")
    @CacheLookup
    public List<WebElement> navbarItemsText;

    //search input
    @FindBy(xpath = "//input[@id='search']")
    @CacheLookup
    public WebElement searchInput;

    //search button
    @FindBy(className = "btn-search")
    @CacheLookup
    public WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean validPageTitle(String expectedResult){
        String actualPageTitle = driver.getTitle();
        return actualPageTitle.equals(expectedResult);
    }


}
