package tests;

import com.google.common.base.Predicates;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
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

    //case 1
    @Test (priority = 1)
    public void case1() throws InterruptedException {
        //zoom out page 50%
        homePage.zoom(50);
        //zoom in page 150%
        homePage.zoom(150);
        //zoom in page 100%
        homePage.zoom(100);
    }

    //case 2
    @Test (priority = 2)
    public void case2() throws Exception {
        //number of items
        Assert.assertTrue(topBarLeftMenuItems.size() == 7);

        List<String> expectedTopBarLeft = new ArrayList<>();
        for (int i = 1; i < topBarLeftMenuItems.size() +1; i++) {
            expectedTopBarLeft.add(excel().getCellData("topBar_leftMenu", i));
        }

        List<String> actualTopBarLeft = new ArrayList<>();
        for (WebElement item : topBarLeftMenuItems) {
            actualTopBarLeft.add(item.getText());
        }

        //check content
        Assert.assertTrue(expectedTopBarLeft.equals(actualTopBarLeft));
    }


//    case 3
    @Test (priority = 3)
    public void case3() throws InterruptedException {
        for (WebElement item : topBarLeftMenuItems) {

            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check underline item when hover
            Assert.assertTrue(item.getCssValue("text-decoration").contains("underline"));

            //check mouse hover

            Thread.sleep(1000);
        }
    }


    @Test (priority = 4)
    public void case4() throws Exception {
        //number of items
        Assert.assertEquals(topBarRightMenuItems.size(), 4);

        List<String> expectedTopBarRight = new ArrayList<>();
        for (int i = 1; i < topBarRightMenuItems.size() +1; i++) {
            expectedTopBarRight.add(excel().getCellData("topBar_RightMenu", i));
        }

        List<String> actualTopBarRight = new ArrayList<>();
        for (WebElement item : topBarRightMenuItems) {
            actualTopBarRight.add(item.getText());
        }

        //check content
        Assert.assertTrue(expectedTopBarRight.equals(actualTopBarRight));
    }


    @Test (priority = 5)
    public void case5() throws InterruptedException {
        int i = 1;
        for (WebElement item : topBarRightMenuItems) {

            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

//            check hover BG color
            Assert.assertTrue(item.getCssValue("background-color").contains("rgba(40, 96, 144, 1)"));

//            check hover border color
            Assert.assertTrue(item.getCssValue("border-color").contains("rgb(32, 77, 116)"));

            //check display subitem when hover item
            WebElement subMenu = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]/ul"));
            Assert.assertTrue(subMenu.isDisplayed());

            i++;
            //check mouse hover

            Thread.sleep(1000);
        }
    }

    //case 6
//    @Test (priority = 6)
//    public void case6() throws Exception {
//        //Expected result
//
//        int i = 1;
//        for (WebElement item : topBarRightMenuItems) {
//
//            WebElement link = driver.findElement(By.xpath("//body/div[1]/div[1]/div[3]/ul[1]/li[" + i + "]"));
//
//            Actions action = new Actions(driver);
//            action.moveToElement(item).build().perform();
//
//            //check content of subitem
//            List<WebElement> subItem = link.findElements(By.xpath("./ul/li/a"));
//
//            List<String> actualSubItemDT = new ArrayList<>();
//            List<String> actualSubItemTT = new ArrayList<>();
//            List<String> actualSubItemP = new ArrayList<>();
//            List<String> actualSubItemK = new ArrayList<>();
//
//            for (WebElement subItems : subItem) {
//                System.out.println(subItems.getText());
//                switch (i) {
//                    case 1:
//                        actualSubItemDT.add(item.getText());
//                        break;
//                    case 2:
//                        actualSubItemTT.add(item.getText());
//                        break;
//                    case 3:
//                        actualSubItemP.add(item.getText());
//                        break;
//                    case 4:
//                        actualSubItemK.add(item.getText());
//                        break;
//                }
//            }
//            System.out.println(actualSubItemDT);
//            i++;
//            Thread.sleep(1000);
//        }
//    }

    //case 7
    @Test (priority = 7)
    public void case7() throws InterruptedException {

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
                Assert.assertTrue(subItems.getCssValue("color").contains("rgba(255, 255, 255, 1)"));

                Actions action1 = new Actions(driver);
                action1.moveToElement(subItemText).build().perform();

                //check BG of subitem when hover
                Assert.assertTrue(subItems.getCssValue("background-color").contains("rgba(254, 208, 20, 1)"));

                //check color of subitem when hover
                Assert.assertTrue(subItems.getCssValue("color").contains("rgba(0, 0, 0, 1)"));
            }

            i++;
            Thread.sleep(1000);
        }
    }

    //case 8
    @Test (priority = 8)
    public void case8() throws Exception {
        //check size of navbar
        Assert.assertTrue(navBarItems.size() == 7);

        List<String> expectedNavbarItem = new ArrayList<>();
        for (int i = 1; i < navBarItems.size() +1; i++) {
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
    public void case9() throws InterruptedException {
        for (WebElement item : navBarItemsText) {
            Actions action = new Actions(driver);
            action.moveToElement(item).build().perform();

            //check BG of navbar when hover
            Assert.assertTrue(item.getCssValue("background-color").contains("rgba(255, 255, 255, 1)"));
        }
    }


    //case 10
    @Test (priority = 10)
    public void case10() throws InterruptedException {
        int i = 1;
        for (WebElement item : navBarItems) {
            System.out.println(item.getText());

            Actions action = new Actions(driver);
            action.moveToElement(item).click().perform();

            List<WebElement> subMenu = driver.findElements(By.xpath("//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li["+i+"]/ul"));
            //display subitem when click item
            for(WebElement subItems : subMenu){
                Assert.assertTrue(subItems.isDisplayed());
            }
            i++;

            Thread.sleep(1000);
        }
    }

//    //case 11 same case 6
//    @Test (priority = 11)
//    public void case11() throws InterruptedException {
//        int i = 1;
//        for (WebElement item : navBarItems) {
//            System.out.print(item.getText()+ ": ");
//
//            Actions action = new Actions(driver);
//            action.moveToElement(item).click().perform();
//
//            List<WebElement> subMenu = driver.findElements(By.xpath("//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li["+i+"]/ul"));
//            List<WebElement> subMenuItems = driver.findElements(By.xpath("//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li["+i+"]/ul/li"));
//
//            System.out.println(subMenuItems.size());
//
//
//            for(WebElement subItems : subMenu){
//                System.out.println(subItems.getText());
//
//                //check BG of subitem
//                //check content of subitem
//            }
//            i++;
//
//            Thread.sleep(1000);
//        }
//    }

    //case 12
    @Test (priority = 12)
    public void case12() throws InterruptedException {
        int i = 1;
        for (WebElement item : navBarItems) {
            System.out.print(item.getText()+ ": ");

            Actions action = new Actions(driver);
            action.moveToElement(item).click().perform();

            List<WebElement> subMenu = driver.findElements(By.xpath("//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li["+i+"]/ul"));
            List<WebElement> subMenuItems = driver.findElements(By.xpath("//body/nav[@id='top-navigator']/div[1]/div[2]/ul[1]/li["+i+"]/ul/li"));

            for(WebElement subItems : subMenu){
                Actions action1 = new Actions(driver);
                action1.moveToElement(subItems).build().perform();

                Assert.assertTrue(subItems.getCssValue("background-color").contains("rgba(254, 208, 20, 1)"));
            }
            i++;

            Thread.sleep(1000);
        }
    }

    //case 13
    @Test (priority = 13)
    public void case13(){
        //check initial
        Assert.assertTrue(homePage.searchInput.getAttribute("placeholder").equals("Tìm kiếm..."));

        Actions action = new Actions(driver);
        action.moveToElement(homePage.searchInput).click().perform();

        //check when click
        Assert.assertTrue(homePage.searchInput.getAttribute("placeholder").equals("Tìm kiếm..."));
    }

//    check link
    //case 14

//    @Test (priority = 14)
//    public void case14(){
//        for (WebElement item : topBarLeftMenuItems) {
//            Actions action = new Actions(driver);
//
//            //navigate when click item navigateToWebPage in basepage
//            System.out.println(item.getText());
//        }
//    }

//    //case 15
//    @Test (priority = 15)
//    public void case15(){
//
//    }
//
//    //case 16
//    @Test (priority = 16)
//    public void case16(){
//
//    }
//
//    //case 17
//    @Test (priority = 17)
//    public void case17(){
//
//    }
//
//    //case 18
//    @Test (priority = 18)
//    public void case18(){
//
//    }








}
