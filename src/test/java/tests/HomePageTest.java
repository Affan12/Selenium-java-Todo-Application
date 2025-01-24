package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ExtentReportManager;

public class HomePageTest extends BaseTest {
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setupExtentReports() {
        extent = ExtentReportManager.createInstance("test-output/ExtentReport.html");
    }

    @Test
    public void testGoogleSearch() {
        test = extent.createTest("Google Search Test");
        driver.get("https://www.google.com");

        HomePage homePage = new HomePage(driver);
        test.info("Navigated to Google");
        homePage.search("Selenium WebDriver");
        test.info("Performed Google search");

        // Validate the search result
        if (driver.getTitle().contains("Selenium WebDriver")) {
            test.pass("Google Search Test Passed");
        } else {
            test.fail("Google Search Test Failed");
        }
    }

    @AfterSuite
    public void tearDownExtentReports() {
        extent.flush();
    }
}