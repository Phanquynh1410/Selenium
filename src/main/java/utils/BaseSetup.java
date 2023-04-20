package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseSetup {

    protected WebDriver driver;

    public BaseSetup(WebDriver driver) {
        this.driver = driver;
    }

    public int getWindowHandles(){
        return driver.getWindowHandles().size();
    }


    public void zoom(int percent) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + percent + "%'" );
        Thread.sleep(5000);
    }

    @Parameters({"url"})
    @BeforeClass
    public void openWindow(String url) {
        try {
            driver.get(url);
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    public void BaseSetup() {
        // TODO Auto-generated method stub

    }
}
