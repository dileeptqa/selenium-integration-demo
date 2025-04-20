package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.JiraUploader;
import utils.QTestUploader;

import java.io.IOException;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void testGoogle() throws InterruptedException {
        driver.get("https://google.com");
        System.out.println("Title: " + driver.getTitle());
        WebElement searchBar = driver.findElement(By.xpath("//textarea[@class=\"gLFyf\"]"));
        searchBar.sendKeys("amazon");
        searchBar.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void reportResult(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                JiraUploader.createBug("Test Failed: " + result.getName(),
                        "Failure in test case: " + result.getThrowable());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        boolean status = result.getStatus() == ITestResult.SUCCESS;
        System.out.println("Test result for " + result.getName() + ": " + (status ? "PASS" : "FAIL"));
//        QTestUploader.createDefect(result.getName(), result.getThrowable().toString());
        //
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}