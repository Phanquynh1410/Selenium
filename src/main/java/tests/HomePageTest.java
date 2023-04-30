package tests;

import org.apache.bcel.generic.SWITCH;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import utils.ExcelHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class HomePageTest {
    WebDriver driver = new ChromeDriver();
    HomePage homePage = new HomePage(driver);


    public ExcelHelpers excel() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("D:\\Selenium\\VKU\\test.xlsx", "HP");
        return excel;
    }

    @BeforeClass
    public void setUp() {
        homePage.openWindow(homePage.urlHP);
        String expectedPageTitle = "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng";
        Assert.assertTrue(homePage.validPageTitle(expectedPageTitle));
    }


    @AfterClass
    public void close() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }


    @Test (priority = 1)
    public void HP_case1() throws InterruptedException {
        //zoom out page 50%
        homePage.zoom(50);
        //zoom in page 150%
        homePage.zoom(150);
        //zoom in page 100%
        homePage.zoom(100);
    }


    @Test (priority = 2)
    public void HP_case2() throws Exception {
        //number of items
        Assert.assertTrue(homePage.topBarLeftMenuItems.size() == 7);

        //get expect data to list expectedTopBarLeft
        List<String> expectedTopBarLeft = new ArrayList<>();
        expectedTopBarLeft.add(excel().getCellData("value","topBar_leftMenu"));
        //convert expectedTopBarLeft to list of string
        List<String> expectedTopBarLeftList = Arrays.asList(expectedTopBarLeft.get(0).split(","));

        //get actual data to list actualTopBarLeft
        List<String> actualTopBarLeft = new ArrayList<>();
        for (WebElement item : homePage.topBarLeftMenuItems) {
            actualTopBarLeft.add(item.getText());
        }

        //number of items
        Assert.assertTrue(homePage.topBarLeftMenuItems.size() == 7);
    }


    @Test (priority = 3)
    public void HP_case3() {
        for (WebElement item : homePage.topBarLeftMenuItems) {

            //Hover mouse to item
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check underline item when hover
            Assert.assertTrue(item.getCssValue("text-decoration").contains("underline"));

            //check mouse hover
            Assert.assertTrue(item.getCssValue("cursor").contains("pointer"));

        }
    }


    @Test (priority = 4)
    public void HP_case4() throws Exception {
        //number of items
        Assert.assertEquals(homePage.topBarRightMenuItems.size(), 4);

        //get expect data to list expectedTopBarLeft
        List<String> expectedTopBarRight = new ArrayList<>();
        expectedTopBarRight.add(excel().getCellData("value","topBar_rightMenu"));
        //convert expectedTopBarLeft to list of string
        List<String> expectedTopBarRightList = Arrays.asList(expectedTopBarRight.get(0).split(","));

        //get actual data to list actualTopBarRight
        List<String> actualTopBarRight = new ArrayList<>();
        for (WebElement item : homePage.topBarRightMenuItems) {
            actualTopBarRight.add(item.getText());
        }

        //check content
        Assert.assertTrue(expectedTopBarRightList.equals(actualTopBarRight));
    }


    @Test (priority = 5)
    public void HP_case5() throws InterruptedException {
        int i = 1;
        for (WebElement item : homePage.topBarRightMenuItems) {

            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check mouse hover
            Assert.assertTrue(item.getCssValue("cursor").contains("pointer"));

            //check hover BG color
            Assert.assertTrue(item.getCssValue("background-color").contains("rgba(40, 96, 144, 1)"));

            //check hover border color
            Assert.assertTrue(item.getCssValue("border-color").contains("rgb(32, 77, 116)"));

            //check display subitem when hover item
            WebElement subMenu = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]/ul"));
            Assert.assertTrue(subMenu.isDisplayed());

            i++;

            Thread.sleep(1000);
        }
    }


    @Test (priority = 6)
    public void HP_case6() throws Exception {
        //get expect data to list Đoàn thể
        List<String> expectDT = new ArrayList<>();
        expectDT.add(excel().getCellData("value","DoanThe"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectDTList = Arrays.asList(expectDT.get(0).split(","));

        //get expect data to list trung tâm
        List<String> expectTT = new ArrayList<>();
        expectTT.add(excel().getCellData("value","TrungTam"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectTTList = Arrays.asList(expectTT.get(0).split(","));

        //get expect data to list phòng
        List<String> expectP = new ArrayList<>();
        expectP.add(excel().getCellData("value","Phong"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectPList = Arrays.asList(expectP.get(0).split(","));

        //get expect data to list khoa
        List<String> expectK = new ArrayList<>();
        expectK.add(excel().getCellData("value","Khoa"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectKList = Arrays.asList(expectK.get(0).split(","));

        List<String> actualDT = new ArrayList<>();
        List<String> actualTT = new ArrayList<>();
        List<String> actualP = new ArrayList<>();
        List<String> actualK = new ArrayList<>();

        int i = 1;
        for (WebElement item : homePage.topBarRightMenuItems) {

            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            WebElement subItem = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]/ul"));

            List<WebElement> subItems = subItem.findElements(By.xpath("./li/a"));

            switch(i) {
                case 1:
                    for (WebElement sub : subItems){
                        actualDT.add(sub.getText());
                    }
                    break;
                case 2:
                    for (WebElement sub : subItems){
                        actualTT.add(sub.getText());
                    }
                    break;
                case 3:
                    for (WebElement sub : subItems){
                        actualP.add(sub.getText());
                    }
                    break;
                case 4:
                    for (WebElement sub : subItems){
                        actualK.add(sub.getText());
                    }
                    break;
            }
            i++;
            Thread.sleep(1000);
        }

        Assert.assertTrue(exepectDTList.equals(actualDT));
        Assert.assertTrue(exepectTTList.equals(actualTT));
        Assert.assertTrue(exepectPList.equals(actualP));
        Assert.assertTrue(exepectKList.equals(actualK));
    }


    @Test (priority = 7)
    public void HP_case7() throws InterruptedException {
        int i = 1;
        for (WebElement item : homePage.topBarRightMenuItems) {

            WebElement link = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]"));

            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check content of subitem
            List<WebElement> subItem = link.findElements(By.xpath("./ul/li"));
            WebElement subItemText = link.findElement(By.xpath("./ul/li/a"));

            for (WebElement subItems : subItem) {
                //check BG of subitem
                Assert.assertTrue(subItems.getCssValue("background-color").contains("rgba(0, 0, 0, 1)"));

                //check color of subitem
                Assert.assertTrue(subItems.getCssValue("color").contains("rgba(51, 51, 51, 1)"));

                Actions action1 = new Actions(driver);
                action1.moveToElement(subItemText).build().perform();
            }
            i++;
            Thread.sleep(1000);
        }
    }


    @Test (priority = 8)
    public void HP_case8() {

    }


    @Test (priority = 9)
    public void HP_case9() throws Exception {
        //check size of navbar
        Assert.assertTrue(homePage.navbarItems.size() == 7);

        //get expect data to list
        List<String> expectNavBar = new ArrayList<>();
        expectNavBar.add(excel().getCellData("value","navBar"));
        //convert expectedTopBarLeft to list of string
        List<String> expectNavBarList = Arrays.asList(expectNavBar.get(0).split(","));

        //get actual data to list of string
        List<String> actualNavbar = new ArrayList<>();
        for (WebElement item : homePage.navbarItems) {
            actualNavbar.add(item.getText());
        }

        //check actual data equal expect data
        Assert.assertTrue(expectNavBarList.equals(actualNavbar));
    }


    //case 9
    @Test (priority = 10)
    public void HP_case10() throws InterruptedException {
        for (WebElement item : homePage.navbarItemsText) {
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check BG of navbar when hover
            Assert.assertTrue(item.getCssValue("background-color").contains("rgba(255, 255, 255, 1)"));

            //check mouse hover
            Assert.assertTrue(item.getCssValue("cursor").contains("pointer"));
        }
        Thread.sleep(1000);
    }


    @Test (priority = 11)
    public void HP_case11() throws Exception {
        //get expect data to list Đoàn thể
        List<String> expectGT = new ArrayList<>();
        expectGT.add(excel().getCellData("value","navBar_GT"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectGTList = Arrays.asList(expectGT.get(0).split(","));

        //get expect data to list trung tâm
        List<String> expectTB = new ArrayList<>();
        expectTB.add(excel().getCellData("value","navBar_TB"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectTBList = Arrays.asList(expectTB.get(0).split(","));

        //get expect data to list phòng
        List<String> expectTKB = new ArrayList<>();
        expectTKB.add(excel().getCellData("value","navBar_TKB"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectTKBList = Arrays.asList(expectTKB.get(0).split(","));

        //get expect data to list khoa
        List<String> expectQT = new ArrayList<>();
        expectQT.add(excel().getCellData("value","navBar_QT"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectQTList = Arrays.asList(expectQT.get(0).split(","));

        //get expect data to list
        List<String> expectCTDT = new ArrayList<>();
        expectCTDT.add(excel().getCellData("value","navBar_CTDT"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectCTDTList = Arrays.asList(expectCTDT.get(0).split(","));

        //get expect data to list
        List<String> expectL = new ArrayList<>();
        expectL.add(excel().getCellData("value","navBar_L"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectLList = Arrays.asList(expectL.get(0).split(","));

        //get expect data to list
        List<String> expectK = new ArrayList<>();
        expectK.add(excel().getCellData("value","navBar_K"));
        //convert expectedTopBarLeft to list of string
        List<String> exepectKList = Arrays.asList(expectK.get(0).split(","));

        List<String> actualGT = new ArrayList<>();
        List<String> actualTB = new ArrayList<>();
        List<String> actuaTKB = new ArrayList<>();
        List<String> actualQT = new ArrayList<>();
        List<String> actualCTDT = new ArrayList<>();
        List<String> actualL = new ArrayList<>();
        List<String> actualK = new ArrayList<>();


        int i = 1;
        for (WebElement item : homePage.navbarItems) {

            Actions action = new Actions(driver);
            action.moveToElement(item).click().perform();
            WebElement subItem = driver.findElement(By.xpath("//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[" + i + "]/ul"));

            List<WebElement> subItems = subItem.findElements(By.xpath("./li"));

            switch(i) {
                case 1:
                    for (WebElement sub : subItems){
                        actualGT.add(sub.getText());
                    }
                    break;
                case 2:
                    for (WebElement sub : subItems){
                        actualTB.add(sub.getText());
                    }
                    break;
                case 3:
                    for (WebElement sub : subItems){
                        actuaTKB.add(sub.getText());
                    }
                    break;
                case 4:
                    for (WebElement sub : subItems){
                        actualQT.add(sub.getText());
                    }
                    break;
                case 5:
                    for (WebElement sub : subItems){
                        actualCTDT.add(sub.getText());
                    }
                    break;
                case 6:
                    for (WebElement sub : subItems){
                        actualL.add(sub.getText());
                    }
                    break;
                case 7:
                    for (WebElement sub : subItems){
                        actualK.add(sub.getText());
                    }
                    break;
            }
            i++;
            Thread.sleep(1000);
        }

        Assert.assertTrue(exepectGTList.equals(actualGT));
        Assert.assertTrue(exepectTBList.equals(actualTB));
        Assert.assertTrue(exepectTKBList.equals(actuaTKB));
        Assert.assertTrue(exepectQTList.equals(actualQT));
        Assert.assertTrue(exepectCTDTList.equals(actualCTDT));
        Assert.assertTrue(exepectLList.equals(actualL));
        Assert.assertTrue(exepectKList.equals(actualK));
    }


    @Test (priority = 12)
    public void HP_case12() {

    }


    @Test (priority = 13)
    public void HP_case14() {
        Assert.assertTrue(homePage.searchInput.getAttribute("placeholder").contains("Tìm kiếm"));

        Actions action = new Actions(driver);
        action.moveToElement(homePage.searchInput).click().perform();

        Assert.assertTrue(homePage.searchInput.getAttribute("placeholder").contains("Tìm kiếm"));
    }


    @Test (priority = 14)
    public void HP_case15() {
        homePage.navigate("//body/div[1]/div[1]/div[1]/a[1]", "Tin tức sự kiện");
    }


    @Test (priority = 15)
    public void HP_case16() {
        homePage.navigate("//body/div[1]/div[1]/div[1]/a[2]", "Thông báo");
    }


    @Test (priority = 16)
    public void HP_case17() {
        homePage.navigate("//body/div[1]/div[1]/div[1]/a[3]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 17)
    public void HP_case18() {
        homePage.navigate("//body/div[1]/div[1]/div[1]/a[4]", "Mẫu đơn | Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 18)
    public void HP_case19() {
        homePage.navigate("//body/div[1]/div[1]/div[1]/a[5]", "Lịch Tuần thứ 40: 01/5/2023 - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 19)
    public void HP_case20() {
        homePage.navigate("//body/div[1]/div[1]/div[1]/a[6]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 20)
    public void HP_case21() {
        homePage.navigate("//body/div[1]/div[1]/div[1]/a[7]", "Email");
    }


    @Test (priority = 21)
    public void HP_case22() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.P).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[1]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 22)
    public void HP_case23() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.P).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[2]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 23)
    public void HP_case24() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.P).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[3]", "Phòng Công tác sinh viên - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 24)
    public void HP_case25() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.P).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[4]", "Phòng Quản lý Khoa học và Hợp tác quốc tế - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 25)
    public void HP_case26() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.P).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[5]", "Phòng Khảo thí và đảm bảo chất lượng giáo dục - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 26)
    public void HP_case27() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.P).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[6]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 27)
    public void HP_case28() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.P).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[7]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 28)
    public void HP_case29() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.P).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[8]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 29)
    public void HP_case30() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.TT).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]/ul[1]/li[1]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 30)
    public void HP_case31() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.TT).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]/ul[1]/li[2]", "Trung tâm ngoại ngữ tin hoc");
    }


    @Test (priority = 31)
    public void HP_case32() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.TT).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]/ul[1]/li[3]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 32)
    public void HP_case33() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.TT).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]/ul[1]/li[4]", "Trang chủ | VKU");
    }


    @Test (priority = 33)
    public void HP_case34() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.DT).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/ul[1]/li[1]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 34)
    public void HP_case35() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.DT).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/ul[1]/li[2]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 35)
    public void HP_case36() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.DT).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/ul[1]/li[3]", "Đoàn thanh niên - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 36)
    public void HP_case37() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.K).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]/ul[1]/li[1]", "Khoa Khoa học máy tính - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 37)
    public void HP_case38() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.K).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]/ul[1]/li[2]", "Khoa Kỹ thuật máy tính và Điện tử - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 38)
    public void HP_case39() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.K).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]/ul[1]/li[3]", "Khoa Kinh tế số & Thương mại điện tử - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 39)
    public void HP_case40() {
        Actions action = new Actions(driver);
        action.moveToElement(homePage.K).build().perform();

        homePage.navigate("//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]/ul[1]/li[4]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 40)
    public void HP_case41(){
        homePage.navigate("//body/div[2]/div[1]/div[1]/div[1]/a[1]/img[1]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 41)
    public void HP_case42(){
        homePage.translateImg.click();
        WebElement dropdown = homePage.translateImg.findElement(By.xpath("./li"));
        Assert.assertTrue(dropdown.isDisplayed());
    }


    @Test (priority = 42)
    public void HP_case43() throws InterruptedException {
        int i = 1;
        for (WebElement item : homePage.navbarItems) {

            Actions action = new Actions(driver);
            action.moveToElement(item).click().build().perform();

            //check display subitem when hover item
            WebElement subMenu = driver.findElement(By.xpath("//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[" + i + "]/ul"));
            Assert.assertTrue(subMenu.isDisplayed());

            i++;

            Thread.sleep(1000);
        }
    }

    @Test (priority = 43)
    public void HP_case44(){
        homePage.GT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 44)
    public void HP_case45(){
        homePage.GT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[2]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 45)
    public void HP_case46(){
        homePage.GT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[3]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 46)
    public void HP_case47(){
        homePage.GT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[4]", "1659495674_1647940335-225");
    }


    @Test (priority = 47)
    public void HP_case48(){
        homePage.TB.click();
        homePage.navigate( "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[1]", "Thông báo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 48)
    public void HP_case49(){
        homePage.TB.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[2]", "Thông báo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 49)
    public void HP_case50(){
        homePage.TB.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[3]", "Thông báo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 50)
    public void HP_case51(){
        homePage.TKB.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[1]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 51)
    public void HP_case52(){
        homePage.TKB.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[2]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 52)
    public void HP_case53(){
        homePage.TB.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[3]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 53)
    public void HP_case54(){
        homePage.TKB.click();
        homePage.navigate( "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[4]", "Thời khóa biểu giảng viên - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 54)
    public void HP_case55(){
        homePage.TKB.click();
        homePage.navigate( "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[5]", "Danh sách lớp sinh hoạt K21 - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 55)
    public void HP_case56(){
        homePage.QT.click();
        homePage.navigate( "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[4]/ul[1]/li[1]", "Mẫu đơn | Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test (priority = 56)
    public void HP_case57(){
        homePage.QT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[4]/ul[1]/li[2]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 57)
    public void HP_case58(){
        homePage.QT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[4]/ul[1]/li[3]", "Quy trình sinh viên - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 58)
    public void HP_case59(){
        homePage.CTDT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[5]/ul[1]/li[1]", "Kế hoạch năm học - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 59)
    public void HP_case60(){
        homePage.CTDT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[5]/ul[1]/li[2]", "Chương trình đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 60)
    public void HP_case61(){
        homePage.CTDT.click();
        homePage.navigate( "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[5]/ul[1]/li[3]", "Global Career Program's VKU – Tự hào là Trường Đại học công lập hàng đầu và duy nhất tại Miền Trung – Tây Nguyên đào tạo chuyên sâu Công nghệ Thông tin – Truyền thông và Kinh tế số");
    }


    @Test (priority = 61)
    public void HP_case62(){
        homePage.CTDT.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[5]/ul[1]/li[2]", "ĐÈ ÁN MỞ NGÀNH 2022 - Google Drive");
    }


    @Test (priority = 62)
    public void HP_case63(){
        homePage.L.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[1]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 63)
    public void HP_case64(){
        homePage.L.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[2]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 64)
    public void HP_case65(){
        homePage.L.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[3]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 65)
    public void HP_case66(){
        homePage.L.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[4]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 66)
    public void HP_case67(){
        homePage.L.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[5]",  "Hệ thống quản lý đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 66)
    public void HP_case68(){
        homePage.DN.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[1]",  "TRƯỜNG ĐẠI HỌC CNTT & TT VIỆT - HÀN");
    }


    @Test (priority = 67)
    public void HP_case69(){
        homePage.DN.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[2]", "Hệ thống quản lý đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 68)
    public void HP_case70(){
        homePage.DN.click();
        homePage.navigate("//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[3]", "TRƯỜNG ĐẠI HỌC CNTT & TT VIỆT - HÀN");
    }


    @Test (priority = 69)
    public void HP_case71(){
        homePage.DN.click();
        homePage.navigate( "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[3]", "Hệ thống dành cho phụ huynh VKU - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test (priority = 70)
    public void HP_case72() {  // Find the button element and click it to open tab 2
        Actions actions = new Actions(driver);
        homePage.searchInput.sendKeys("VKU");
        actions.keyDown(Keys.CONTROL).moveToElement(homePage.searchInput).sendKeys(Keys.ENTER).perform();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String expectedResult = "VKU site:https://daotao.vku.udn.vn/ - Tìm trên Google";
        String validPageTitle = driver.getTitle();

        driver.close();
        driver.switchTo().window(tabs.get(0));

        Assert.assertTrue(validPageTitle.contains(expectedResult));
    }


    @Test (priority = 71)
    public void HP_case73() {
        Actions actions = new Actions(driver);
        homePage.searchInput.sendKeys("VKU");
        actions.keyDown(Keys.CONTROL).moveToElement(homePage.searchButton).click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String expectedResult = "VKU site:https://daotao.vku.udn.vn/ - Tìm trên Google";
        String validPageTitle = driver.getTitle();

        driver.close();
        driver.switchTo().window(tabs.get(0));

        Assert.assertTrue(validPageTitle.contains(expectedResult));
    }


    @Test (priority = 72)
    public void HP_case74() {
        Actions actions = new Actions(driver);
        homePage.searchInput.sendKeys("alo");
        actions.keyDown(Keys.CONTROL).moveToElement(homePage.searchInput).sendKeys(Keys.ENTER).perform();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String result_stats = driver.findElement(By.id("result-stats")).getText();

        driver.close();
        driver.switchTo().window(tabs.get(0));

        Assert.assertTrue(result_stats.contains("Khoảng 0 kết quả"));
    }

}
