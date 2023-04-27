package tests;

import com.google.common.base.Predicates;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ExcelHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageTest {
    WebDriver driver = new ChromeDriver();
    HomePage homePage = new HomePage(driver);
    List<WebElement> topBarLeftMenuItems = homePage.topBarLeftMenuItems;
    List<WebElement> topBarRightMenuItems = homePage.topBarRightMenuItems;
    List<WebElement> navBarItems = homePage.navbarItems;
    List<WebElement> navBarItemsText = homePage.navbarItemsText;
    WebElement topBarRightMenuList = homePage.topBarRightMenuList;
    WebElement searchInput = homePage.searchInput;
    WebElement searchBtn = homePage.searchButton;
    WebElement P = homePage.P;
    WebElement TT = homePage.TT;
    WebElement DT = homePage.DT;
    WebElement K = homePage.K;
    WebElement logo = homePage.logo;
    WebElement GT = homePage.GT;
    WebElement TB = homePage.TB;
    WebElement QT = homePage.QT;


    public ExcelHelpers excel() throws Exception {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("D:\\Selenium\\VKU\\test.xlsx", "HomePage");
        return excel;
    }


    @BeforeTest
    public void setUp() {
        homePage.openWindow(homePage.urlHP);
        String expectedPageTitle = "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng";
        Assert.assertTrue(homePage.validPageTitle(expectedPageTitle));
    }


    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }


    @Test
    public void HP_case1() throws InterruptedException {
        //zoom out page 50%
        homePage.zoom(50);
        //zoom in page 150%
        homePage.zoom(150);
        //zoom in page 100%
        homePage.zoom(100);
    }


    @Test
    public void HP_case2() throws Exception {
        //number of items
        Assert.assertTrue(topBarLeftMenuItems.size() == 7);

        //get expect data to list expectedTopBarLeft
        List<String> expectedTopBarLeft = new ArrayList<>();
        for (int i = 1; i < topBarLeftMenuItems.size() + 1; i++) {
            expectedTopBarLeft.add(excel().getCellData("topBar_leftMenu", i));
        }

        //get actual data to list actualTopBarLeft
        List<String> actualTopBarLeft = new ArrayList<>();
        for (WebElement item : topBarLeftMenuItems) {
            actualTopBarLeft.add(item.getText());
        }

        //check data
        Assert.assertTrue(expectedTopBarLeft.equals(actualTopBarLeft));
    }


    @Test
    public void HP_case3() throws InterruptedException {
        for (WebElement item : topBarLeftMenuItems) {

            //Hover mouse to item
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check underline item when hover
            Assert.assertTrue(item.getCssValue("text-decoration").contains("underline"));

            //check mouse hover

            Thread.sleep(1000);
        }
    }


    @Test
    public void HP_case4() throws Exception {
        //number of items
        Assert.assertEquals(topBarRightMenuItems.size(), 4);

        //get expect data to list expectedTopBarRight
        List<String> expectedTopBarRight = new ArrayList<>();
        for (int i = 1; i < topBarRightMenuItems.size() + 1; i++) {
            expectedTopBarRight.add(excel().getCellData("topBar_RightMenu", i));
        }

        //get actual data to list actualTopBarRight
        List<String> actualTopBarRight = new ArrayList<>();
        for (WebElement item : topBarRightMenuItems) {
            actualTopBarRight.add(item.getText());
        }

        //check content
        Assert.assertTrue(expectedTopBarRight.equals(actualTopBarRight));
    }


    @Test
    public void HP_case5() throws InterruptedException {
        int i = 1;
        for (WebElement item : topBarRightMenuItems) {

            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check hover BG color
            Assert.assertTrue(item.getCssValue("background-color").contains("rgba(40, 96, 144, 1)"));

            //check hover border color
            Assert.assertTrue(item.getCssValue("border-color").contains("rgb(32, 77, 116)"));

            //check display subitem when hover item
            WebElement subMenu = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]/ul"));
            Assert.assertTrue(subMenu.isDisplayed());

            i++;
            //check mouse hover

            Thread.sleep(1000);
        }
    }


    @Test
    public void HP_case6() throws Exception {

    }


    @Test
    public void HP_case7() throws InterruptedException {
        int i = 1;
        for (WebElement item : topBarRightMenuItems) {

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


    @Test
    public void HP_case8() {

    }


    @Test
    public void HP_case9() throws Exception {
        //check size of navbar
        Assert.assertTrue(navBarItems.size() == 7);

        List<String> expectedNavbarItem = new ArrayList<>();
        for (int i = 1; i < navBarItems.size() + 1; i++) {
            expectedNavbarItem.add(excel().getCellData("NavBar", i));
        }

        List<String> actualNavbarItem = new ArrayList<>();
        for (WebElement item : navBarItems) {
            System.out.println(item.getText());
            actualNavbarItem.add(item.getText());
        }

        //check content of navbar
        Assert.assertTrue(expectedNavbarItem.equals(actualNavbarItem));
    }


    //case 9
    @Test
    public void HP_case10() throws InterruptedException {
        for (WebElement item : navBarItemsText) {
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check BG of navbar when hover
            Assert.assertTrue(item.getCssValue("background-color").contains("rgba(255, 255, 255, 1)"));
        }
        Thread.sleep(1000);
    }


    @Test
    public void HP_case11() throws InterruptedException {
        int i = 1;
        for (WebElement item : navBarItems) {
            System.out.println(item.getText());

            Actions action = new Actions(driver);
            action.moveToElement(item).click().perform();

            List<WebElement> subMenu = driver.findElements(By.xpath("//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li[" + i + "]/ul"));

            System.out.println(subMenu.size());
            for (WebElement subItems : subMenu) {
                System.out.println(subItems.getText());
            }
            i++;

            Thread.sleep(1000);
        }
    }


    @Test
    public void HP_case12() {

    }


    @Test
    public void HP_case14() {
        Assert.assertTrue(searchInput.getAttribute("placeholder").contains("Tìm kiếm"));

        Actions action = new Actions(driver);
        action.moveToElement(searchInput).click().perform();

        Assert.assertTrue(searchInput.getAttribute("placeholder").contains("Tìm kiếm"));
    }


    @Test
    public void HP_case15() {
        homePage.navigate("https://daotao.vku.udn.vn/tin-tuc", "//body/div[1]/div[1]/div[1]/a[1]", "Tin tức sự kiện");
    }


    @Test
    public void HP_case16() {
        homePage.navigate("https://daotao.vku.udn.vn/thong-bao", "//body/div[1]/div[1]/div[1]/a[2]", "Thông báo");
    }


    @Test
    public void HP_case17() {
        homePage.navigate("https://daotao.vku.udn.vn/danh-ba", "//body/div[1]/div[1]/div[1]/a[3]", "Danh bạ");
    }


    @Test
    public void HP_case18() {
        homePage.navigate("https://daotao.vku.udn.vn/tai-nguyen", "//body/div[1]/div[1]/div[1]/a[4]", "Biểu mẫu");
    }


    @Test
    public void HP_case19() {
        homePage.navigate("https://lichtuan.vku.udn.vn/", "//body/div[1]/div[1]/div[1]/a[5]", "Lịch tuần");
    }


    @Test
    public void HP_case20() {
        homePage.navigate("https://daotao.vku.udn.vn/hinh-anh", "//body/div[1]/div[1]/div[1]/a[6]", "Hình ảnh");
    }


    @Test
    public void HP_case21() {
        homePage.navigate("https://daotao.vku.udn.vn/email", "//body/div[1]/div[1]/div[1]/a[7]", "Email");
    }


    @Test
    public void HP_case22() {
        Actions action = new Actions(driver);
        action.moveToElement(P).build().perform();

        homePage.navigate("https://vku.udn.vn/phong/tchc/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[1]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case23() {
        Actions action = new Actions(driver);
        action.moveToElement(P).build().perform();

        homePage.navigate("https://daotao.vku.udn.vn/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[2]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case24() {
        Actions action = new Actions(driver);
        action.moveToElement(P).build().perform();

        homePage.navigate("https://ctsv.vku.udn.vn/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[3]", "Phòng Công tác sinh viên - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case25() {
        Actions action = new Actions(driver);
        action.moveToElement(P).build().perform();

        homePage.navigate("https://stic.vku.udn.vn/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[4]", "Phòng Quản lý Khoa học và Hợp tác quốc tế - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case26() {
        Actions action = new Actions(driver);
        action.moveToElement(P).build().perform();

        homePage.navigate("https://ktdbcl.vku.udn.vn/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[5]", "Phòng Khảo thí và đảm bảo chất lượng giáo dục - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case27() {
        Actions action = new Actions(driver);
        action.moveToElement(P).build().perform();

        homePage.navigate("https://vku.udn.vn/phong/thanhtraphapche/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[6]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case28() {
        Actions action = new Actions(driver);
        action.moveToElement(P).build().perform();

        homePage.navigate("https://vku.udn.vn/phong/csvc/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[7]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }

    @Test
    public void HP_case29() {
        Actions action = new Actions(driver);
        action.moveToElement(P).build().perform();

        homePage.navigate("https://vku.udn.vn/phong/khtc/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[3]/ul[1]/li[8]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case30() {
        Actions action = new Actions(driver);
        action.moveToElement(TT).build().perform();

        homePage.navigate("https://vku.udn.vn/trungtam/hltt", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]/ul[1]/li[1]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case31() {
        Actions action = new Actions(driver);
        action.moveToElement(TT).build().perform();

        homePage.navigate("https://flic.vku.udn.vn/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]/ul[1]/li[2]", "Trung tâm ngoại ngữ tin hoc");
    }


    @Test
    public void HP_case32() {
        Actions action = new Actions(driver);
        action.moveToElement(TT).build().perform();

        homePage.navigate("https://vku.udn.vn/trungtam/ttqtcntt", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]/ul[1]/li[3]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case33() {
        Actions action = new Actions(driver);
        action.moveToElement(TT).build().perform();

        homePage.navigate("http://kytucxa.vku.udn.vn/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[2]/ul[1]/li[4]", "Trang chủ | VKU");
    }


    @Test
    public void HP_case34() {
        Actions action = new Actions(driver);
        action.moveToElement(DT).build().perform();

        homePage.navigate("#", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/ul[1]/li[1]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case35() {
        Actions action = new Actions(driver);
        action.moveToElement(DT).build().perform();

        homePage.navigate("#", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/ul[1]/li[2]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case36() {
        Actions action = new Actions(driver);
        action.moveToElement(DT).build().perform();

        homePage.navigate("#", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/ul[1]/li[3]", "Đoàn thanh niên - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case37() {
        Actions action = new Actions(driver);
        action.moveToElement(K).build().perform();

        homePage.navigate("https://cs.vku.udn.vn", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]/ul[1]/li[1]", "Khoa Khoa học máy tính - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case38() {
        Actions action = new Actions(driver);
        action.moveToElement(K).build().perform();

        homePage.navigate("https://ce.vku.udn.vn", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]/ul[1]/li[2]", "Khoa Kỹ thuật máy tính và Điện tử - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case39() {
        Actions action = new Actions(driver);
        action.moveToElement(K).build().perform();

        homePage.navigate("https://de.vku.udn.vn", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]/ul[1]/li[3]", "Khoa Kinh tế số & Thương mại điện tử - Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }

    @Test
    public void HP_case40() {
        Actions action = new Actions(driver);
        action.moveToElement(K).build().perform();

        homePage.navigate("https://vku.udn.vn/to/cb/", "//body[1]/div[1]/div[1]/div[3]/ul[1]/li[4]/ul[1]/li[4]", "Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case41(){
        homePage.navigate("#", "//body/div[2]/div[1]/div[1]/div[1]/a[1]/img[1]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case42(){
    }


    @Test
    public void HP_case43() throws InterruptedException {
    }

    @Test
    public void HP_case44(){
        GT.click();
        homePage.navigate("#", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[1]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }

    @Test
    public void HP_case45(){
        GT.click();
        homePage.navigate("#", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[2]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }

    @Test
    public void HP_case46(){
        GT.click();
        homePage.navigate("#", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[3]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }

    @Test
    public void HP_case47(){
        GT.click();
        homePage.navigate("#", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[4]", "1659495674_1647940335-225");
    }

    @Test
    public void HP_case48(){
        TB.click();
        homePage.navigate("https://daotao.vku.udn.vn/vku-thong-bao-chung", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[1]", "Thông báo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }

    @Test
    public void HP_case49(){
        TB.click();
        homePage.navigate("https://daotao.vku.udn.vn/vku-thong-bao-ctsv", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[2]", "Thông báo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }

    @Test
    public void HP_case50(){
        TB.click();
        homePage.navigate("https://daotao.vku.udn.vn/vku-thong-bao-khtc", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[3]", "Thông báo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case51(){
        homePage.TKB.click();
        homePage.navigate("https://daotao.vku.udn.vn/thoi-khoa-bieu", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[1]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case52(){
        homePage.TKB.click();
        homePage.navigate("https://daotao.vku.udn.vn/lich-thi", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[2]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case53(){
        TB.click();
        homePage.navigate("https://daotao.vku.udn.vn/lich-sinh-hoat-lop", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[3]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case54(){
        homePage.TKB.click();
        homePage.navigate("https://daotao.vku.udn.vn/bieu-do-giang-day", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[4]", "Thời khóa biểu giảng viên - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case55(){
        homePage.TKB.click();
        homePage.navigate("https://daotao.vku.udn.vn/danh-sach-lop-sinh-hoat-k21", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/ul[1]/li[5]", "Danh sách lớp sinh hoạt K21 - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case56(){
        homePage.QT.click();
        homePage.navigate("https://vku.udn.vn/tai-nguyen/dao-tao/", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[4]/ul[1]/li[1]", "Mẫu đơn | Trường Đại học Công nghệ Thông tin và Truyền thông Việt Hàn - Đại học Đà Nẵng");
    }


    @Test
    public void HP_case57(){
        homePage.QT.click();
        homePage.navigate("#", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[4]/ul[1]/li[2]", "Cổng Thông tin đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }

    @Test
    public void HP_case58(){
        homePage.QT.click();
        homePage.navigate("https://daotao.vku.udn.vn/quy-trinh-sinh-vien", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[4]/ul[1]/li[3]", "Quy trình sinh viên - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }

    @Test
    public void HP_case59(){
        homePage.CTDT.click();
        homePage.navigate("https://daotao.vku.udn.vn/ke-hoach-nam", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[5]/ul[1]/li[1]", "Kế hoạch năm học - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case60(){
        homePage.CTDT.click();
        homePage.navigate("https://daotao.vku.udn.vn/chuong-trinh-dao-tao", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[5]/ul[1]/li[2]", "Chương trình đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case61(){
        homePage.CTDT.click();
        homePage.navigate("http://global.vku.udn.vn/", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[5]/ul[1]/li[3]", "Global Career Program's VKU – Tự hào là Trường Đại học công lập hàng đầu và duy nhất tại Miền Trung – Tây Nguyên đào tạo chuyên sâu Công nghệ Thông tin – Truyền thông và Kinh tế số");
    }


    @Test
    public void HP_case62(){
        homePage.CTDT.click();
        homePage.navigate("https://drive.google.com/drive/folders/1v1lyPZPlVyLGV2BB8vpYoK_ldvjH6DKU", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[5]/ul[1]/li[2]", "ĐÈ ÁN MỞ NGÀNH 2022 - Google Drive");
    }


    @Test
    public void HP_case63(){
        homePage.L.click();
        homePage.navigate("https://daotao.vku.udn.vn/thoi-khoa-bieu", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[1]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case64(){
        homePage.L.click();
        homePage.navigate("https://daotao.vku.udn.vn/lich-sinh-hoat-lop", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[2]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case65(){
        homePage.L.click();
        homePage.navigate("https://daotao.vku.udn.vn/lich-seminar", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[3]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case66(){
        homePage.L.click();
        homePage.navigate("https://daotao.vku.udn.vn/lich-thi", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[6]/ul[1]/li[4]", "Thời khóa biểu - Danh sách lớp học phần - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case68(){
        homePage.DN.click();
        homePage.navigate("https://daotao.vku.udn.vn/sv", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[1]",  "TRƯỜNG ĐẠI HỌC CNTT & TT VIỆT - HÀN");
    }


    @Test
    public void HP_case69(){
        homePage.L.click();
        homePage.navigate("https://daotao.vku.udn.vn/gv", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[2]", "Hệ thống quản lý đào tạo - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case70(){
        homePage.L.click();
        homePage.navigate("https://daotao.vku.udn.vn/admincp", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[3]", "TRƯỜNG ĐẠI HỌC CNTT & TT VIỆT - HÀN");
    }


    @Test
    public void HP_case71(){
        homePage.L.click();
        homePage.navigate("https://daotao.vku.udn.vn/phuhuynh\n", "//body[1]/nav[1]/div[1]/div[2]/ul[1]/li[7]/ul[1]/li[3]", "Hệ thống dành cho phụ huynh VKU - Trường Đại học Công nghệ Thông tin và Truyền thông Việt - Hàn, Đại học Đà Nẵng");
    }


    @Test
    public void HP_case72() {
        Actions actions = new Actions(driver);
        searchInput.sendKeys("VKU");
        searchInput.sendKeys(Keys.ENTER);

        String validPageTitle = "VKU site:https://daotao.vku.udn.vn/ - Tìm trên Google";
        String expectedResult = driver.getTitle();

        Assert.assertTrue(validPageTitle.contains(expectedResult));
    }


    @Test
    public void HP_case73() {
        Actions actions = new Actions(driver);
        searchInput.sendKeys("VKU");
        searchBtn.click();

        String validPageTitle = "VKU site:https://daotao.vku.udn.vn/ - Tìm trên Google";
        String expectedResult = driver.getTitle();

        Assert.assertTrue(validPageTitle.contains(expectedResult));
    }


    @Test
    public void HP_case74() {
        Actions actions = new Actions(driver);
        searchInput.sendKeys("alo");
        searchInput.sendKeys(Keys.ENTER);

        WebElement result_stats = driver.findElement(By.id("result-stats"));
        Assert.assertTrue(result_stats.getText().contains("Khoảng 0 kết quả"));
    }

}
