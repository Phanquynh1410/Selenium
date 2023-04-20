package tests;

import utils.ExcelHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class test {
//read and write 1 line of excel
    ExcelHelpers excel = new ExcelHelpers();

    @Test
    public void signInPage() throws Exception {

        // Setup đường dẫn của file excel
        excel.setExcelFile("D:\\Selenium\\VKU\\test.xlsx", "Sheet1");
        WebDriver driver = new ChromeDriver();

        driver.get("https://crm.anhtester.com");

        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));

        System.out.println(excel.getCellData("username",1));
        // Đọc data từ file excel
//        email.sendKeys(excel.getCellData("username",1));
//        Thread.sleep(2000);
//        password.sendKeys(excel.getCellData("password",1));
//        Thread.sleep(2000);
//
//
//        //Ghi data vào file excel
////        excel.setCellData("anhtester.com", 5, 0);
//
//        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
//        btn.click();

        // Chú ý: dòng và cột trong code nó hiểu bắt đầu từ 0

        Thread.sleep(2000);
    }
}
