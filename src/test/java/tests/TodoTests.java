package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.TodoPage;
import utils.ExtentReportManager;

public class TodoTests extends BaseTest {
    private static ExtentReports extent;
    private static ExtentTest test;

    private TodoPage todoPage;

    @BeforeSuite
    public void setupExtentReports() {
        // Initialize the ExtentReports instance
        extent = ExtentReportManager.createInstance("test-output/ExtentReport.html");
    }

    @Test
    public void testAddingTodoItem() {
        test = extent.createTest("Adding a To-Do Item Test");
        driver.get("https://todomvc.com/examples/react/dist/");
        test.info("Navigated to the TodoMVC application");

        todoPage = new TodoPage(driver);
        String todoItem = "Buy Milk";

        // Add a new To-Do item
        todoPage.enterTodoItem(todoItem);
        test.info("Added a To-Do item: " + todoItem);

        // Verify the item was added successfully
        try {
            Assert.assertEquals(todoPage.getTodoLabelText(0), todoItem, "The first item in the list does not match the input!");
            test.pass("Verified the added To-Do item is correct");
        } catch (AssertionError e) {
            test.fail("Verification failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testAddingMultipleTodoItems() {
        test = extent.createTest("Adding Multiple To-Do Items Test");
        driver.get("https://todomvc.com/examples/react/dist/");
        test.info("Navigated to the TodoMVC application");

        todoPage = new TodoPage(driver);
        String[] items = {"Buy Milk", "Exercise", "Meditate"};

        // Add multiple items
        for (String item : items) {
            todoPage.enterTodoItem(item);
            test.info("Added a To-Do item: " + item);
        }

        // Verify all items were added successfully
        try {
            for (int i = 0; i < items.length; i++) {
                Assert.assertEquals(todoPage.getTodoLabelText(i), items[i], "Item at index " + i + " doesn't match!");
            }
            test.pass("Successfully verified all To-Do items");
        } catch (AssertionError e) {
            test.fail("Verification of multiple To-Do items failed: " + e.getMessage());
            throw e;
        }
    }



    @AfterSuite
    public void tearDownExtentReports() {
        // Flush the ExtentReports to ensure the report is generated
        extent.flush();
    }
}
