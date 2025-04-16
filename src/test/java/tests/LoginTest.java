package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void testGoogle() {
        driver.get("https://google.com");
        System.out.println("Title: " + driver.getTitle());
    }

    @AfterMethod
    public void reportResult(ITestResult result) {
        boolean status = result.getStatus() == ITestResult.SUCCESS;
        System.out.println("Test result for " + result.getName() + ": " + (status ? "PASS" : "FAIL"));
        // qTestUploader.uploadResultToQTest(result.getName(), status);  // Optional
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}