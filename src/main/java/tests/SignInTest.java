package tests;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import utils.ExcelHelpers;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignInTest {
    private String urlHP = "https://daotao.vku.udn.vn/";

    WebDriver driver = new ChromeDriver();
    SignInPage signInPage = new SignInPage(driver);
    HomePage homePage = new HomePage(driver);
    public ExcelHelpers excel() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("D:\\Selenium\\VKU\\test.xlsx", "SignIn");
        return excel;
    }

    public void openHomePage(){
        signInPage.openWindow(urlHP);
        driver.manage().window().maximize();
        String expectedPageTitle = "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng";
        Assert.assertTrue(homePage.validPageTitle(expectedPageTitle));
    }

//    @AfterTest
//    public void teardown(){
//        driver.close();
//    }

    @Test (priority = 1)
    public void testSignIn_case1() throws InterruptedException {
        openHomePage();

        List<WebElement> navBarItems = homePage.navbarItems;

        for (WebElement navBarItem : navBarItems) {
            if(navBarItem.getText().equals("Đăng nhập")){
                //CLick into ĐĂNG NHẬP
                navBarItem.click();
                Thread.sleep(500);

            List<WebElement> navBarSubmenu = driver.findElements(By.xpath("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[1]"));

            for (WebElement navBarSubmenuItem : navBarSubmenu) {
                //Click into SINH VIÊN
                if(navBarSubmenuItem.getText().equals("Sinh viên")){
                    navBarSubmenuItem.click();

                    //check navigate true page
                    Assert.assertTrue(signInPage.validPageTitle("TRƯỜNG ĐẠI HỌC CNTT & TT VIỆT - HÀN"));

                    //click SignIn button
                    WebElement signInBtn = signInPage.SignInBtn.findElement(By.tagName("a"));
//                    WebElement signInBtn = driver.findElement(By.xpath("//body/div[@id='row']/div[2]/div[1]/div[1]/center[1]/button[1]/a[1]"));
                    signInBtn.click();

                    //check navigate true page
                    Assert.assertTrue(signInPage.validPageTitle("Đăng nhập - Tài khoản Google"));
                }
            }
            }
        }
    }

    @Test
    public void testSignIn_case2(){
        signInPage.openWindow("https://accounts.google.com/o/oauth2/auth/identifier?client_id=935710250797-u6mphikjd6n1q4p8qog8d4ljsloli42h.apps.googleusercontent.com&redirect_uri=https%3A%2F%2Fdaotao.vku.udn.vn%2Fcallback%2Fgoogle&scope=openid%20profile%20email&response_type=code&state=3ECZdVwSM31L7EzlRia0dPDTs2uZ7rgS6F0Akn63&service=lso&o2v=1&flowName=GeneralOAuthFlow");
        driver.manage().window().maximize();
        String expectedPageTitle = "Đăng nhập - Tài khoản Google";
        Assert.assertTrue(homePage.validPageTitle(expectedPageTitle));
    }

    @Test
    public void testSignIn_case3() throws InterruptedException {
        testSignIn_case1();
        WebElement email = signInPage.emailInput;

        String expectedEmail = "Email hoặc số điện thoại";
        Assert.assertTrue(email.getAttribute("aria-label").equals(expectedEmail));
    }

    @Test
    public void testSignIn_case4() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",1));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;

        String expectedPass = "Nhập mật khẩu của bạn";
        Assert.assertTrue(password.getAttribute("aria-label").equals(expectedPass));
    }

    @Test
    public void testSignIn_case5() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",2));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("password",2));

        WebElement checkBox = signInPage.checkBox;

        Assert.assertTrue(password.getAttribute("type").equals("password"));
        Thread.sleep(1000);
        checkBox.click();
        Thread.sleep(1000);
        Assert.assertTrue(password.getAttribute("type").equals("text"));
    }

    //case 6
    @Test
    public void testSignIn_case6() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",2));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("password",2));

        Actions actions = new Actions(driver);
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        String expectedPageTitle = "Hệ thống quản lý đào tạo - Trường Đại học CNTT&TT Việt - Hàn";
        Assert.assertTrue(homePage.validPageTitle(expectedPageTitle));

        WebElement userName = signInPage.userName;
        String expectedUserName = excel().getCellData("userName",2);
        Assert.assertTrue(userName.getText().equals(expectedUserName));
    }

    //case 7
    @Test
    public void testSignIn_case7() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());

        String expectedErrorGmail = excel().getCellData("errorMsg",3);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    //case 8
    @Test
    public void testSignIn_case8() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",4));
        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("errorMsg",4);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));

        Thread.sleep(1000);
    }

    //case 9
    @Test
    public void testSignIn_case9() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",5));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("errorMsg",5);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void testSignIn_case10() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",6));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("errorMsg",6);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void testSignIn_case11() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",7));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("errorMsg",7);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void testSignIn_case12() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;

        for(int i = 0; i < 10; i++){
            mail.sendKeys(Keys.BACK_SPACE);
        }

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("errorMsg",8);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void testSignIn_case13() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",9));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("errorMsg",9);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void testSignIn_case14() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",10));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("errorMsg",10);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void testSignIn_case15() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",11));

        mail.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

        WebElement errorGmail = signInPage.errorGmail;
        Assert.assertTrue(errorGmail.isDisplayed());
        String expectedErrorGmail = excel().getCellData("errorMsg",11);
        Assert.assertTrue(errorGmail.getText().equals(expectedErrorGmail));
    }

    @Test
    public void testSignIn_case16() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",12));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("errorMsg",12);

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void testSignIn_case17() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",13));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;

        for (int i = 0; i < 10; i++) {
            password.sendKeys(Keys.BACK_SPACE);
        }

        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("errorMsg",13);

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void testSignIn_case18() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",14));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click   ();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("password",14));
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("errorMsg",14);

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void testSignIn_case19() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",14));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click   ();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("password",15));
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("errorMsg",15);

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void testSignIn_case20() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",16));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click   ();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("password",16));
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement errorPass = signInPage.errorPass;
        Assert.assertTrue(errorPass.isDisplayed());
        String expectedErrorPass = excel().getCellData("errorMsg",14);

        Thread.sleep(500);
        Assert.assertTrue(errorPass.getText().equals(expectedErrorPass));
    }

    @Test
    public void testSignIn_case21() throws Exception {
        testSignIn_case1();

        WebElement mail = signInPage.emailInput;
        mail.sendKeys(excel().getCellData("gmail",17));

        WebElement nextBtn = signInPage.nextBTN;
        nextBtn.click   ();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement password = signInPage.passInput;
        password.sendKeys(excel().getCellData("password",17));
        password.sendKeys(Keys.ENTER);

        Thread.sleep(5000);


    }

}

