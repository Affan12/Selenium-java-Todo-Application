package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import pages.TodoPage;
import utils.DriverManager;
import java.util.List;

public class TodoSteps {
    private WebDriver driver;
    private TodoPage todoPage;

    @Given("I am on the To-Do MVC homepage")
    public void iAmOnTheHomePage() {
        driver = DriverManager.getDriver();
        driver.get("https://todomvc.com/examples/react/dist/");
        todoPage = new TodoPage(driver);
    }

    @When("I add a To-Do item {string}")
    public void iAddATodoItem(String todoItem) {
        todoPage.enterTodoItem(todoItem);
    }
//test commit
//    @When("I add the following To-Do items:")
//    public void iAddItems(io.cucumber.datatable.DataTable dataTable) {
//        dataTable.asList().forEach(todoPage::enterTodoItem);
//    }

    @Then("the To-Do list should contain {string}")
    public void theListShouldContain(String todoItem) {
        Assert.assertEquals(todoPage.getTodoLabelText(0), todoItem, "To-Do item was not added as expected!");
    }

    @Then("the To-Do list should contain the following items:")
    public void theListShouldContainItems(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<String> expectedItems = dataTable.asList();
        for (int i = 0; i < expectedItems.size(); i++) {
            Assert.assertEquals(todoPage.getTodoLabelText(i), expectedItems.get(i), "Mismatch at To-Do item index " + i);
        }
    }

    @When("I complete the first To-Do item")
    public void iCompleteTheFirstTodo() {
        todoPage.getTodoItems().get(0).click();
    }

    @When("I clear completed items")
    public void iClearCompletedItems() {
        todoPage.clickClearCompletedButton();
    }

//    @Then("the To-Do list should be empty")
//    public void theListShouldBeEmpty() {
//        Assert.assertTrue(todoPage.isTodoListEmpty(), "The To-Do list is not empty!");
//    }

    // New tests AC

    @When("I click the \"Active\" filter")
    public void iClickTheActiveFilter() {
        todoPage.clickFilter("Active");
    }

    @Then("I should see only the following active items:")
    public void iShouldSeeTheFollowingActiveItems(io.cucumber.datatable.DataTable dataTable) {
        List<String> activeItems = dataTable.asList();
        List<WebElement> visibleItems = todoPage.getActiveItems(); // Filtered active items

        for (String item : activeItems) {
            boolean found = visibleItems.stream().anyMatch(element -> element.getText().equals(item));
            Assert.assertTrue(found,"Active item not found: " + item);
        }
    }

    @When("I click the \"Completed\" filter")
    public void iClickTheCompletedFilter() {
        todoPage.clickFilter("Completed");
    }

    @Then("I should see only the following completed items:")
    public void iShouldSeeTheFollowingCompletedItems(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<String> completedItems = dataTable.asList();
        List<WebElement> visibleItems = todoPage.getCompletedItems(); // Filtered completed items

        for (String item : completedItems) {
            boolean found = visibleItems.stream().anyMatch(element -> element.getText().equals(item));
            Thread.sleep(500);
            Assert.assertTrue(found,"Completed item not found: " + item);
        }
    }

    @When("I click the checkbox for {string}")
    public void iClickTheCheckboxFor(String todoItem) {
//        todoPage.clickCheckbox(todoItem);
        todoPage.clickListItem(todoItem);
    }


    //Counter tests

    @Then("the counter should display {int} items left")
    public void theCounterShouldDisplayItemsLeft(int expectedCount) {
        int actualCount = todoPage.getItemCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Item counter mismatch! Expected: " + expectedCount + ", but was: " + actualCount);
    }

    @When("I add the following To-Do items and verify the counter:")
    public void iAddItemsAndVerifyCounter(io.cucumber.datatable.DataTable dataTable) {
        List<String> todoItems = dataTable.asList();
        for (int i = 0; i < todoItems.size(); i++) {
            todoPage.enterTodoItem(todoItems.get(i));
            int expectedCount = i + 1;
            int actualCount = todoPage.getItemCount();
            Assert.assertEquals(actualCount, expectedCount,
                    "Counter mismatch after adding item: " + todoItems.get(i));
        }
    }

    @When("I complete the first To-Do item and verify the counter")
    public void iCompleteTheFirstTodoAndVerifyCounter() throws InterruptedException {
        int initialCount = todoPage.getItemCount();
        todoPage.getTodoItems().get(0).click(); // Mark first item as complete
        int updatedCount = todoPage.getItemCount();
//        Thread.sleep(1000);
        Assert.assertEquals(updatedCount, initialCount - 1,
                "Counter did not decrease after completing the first To-Do item");
    }

    @When("I delete the first To-Do item and verify the counter")
    public void iDeleteTheFirstTodoAndVerifyCounter() {
        int initialCount = todoPage.getItemCount();
        todoPage.deleteTodoItem(0); // Delete the first item
        int updatedCount = todoPage.getItemCount();
        Assert.assertEquals(updatedCount, initialCount - 1,
                "Counter did not decrease after deleting the first To-Do item");
    }

//E2E

    @When("I add the following To-Do items:")
    public void iAddTheFollowingToDoItems(io.cucumber.datatable.DataTable dataTable) {
        List<String> todoItems = dataTable.asList();
        for (String item : todoItems) {
            todoPage.addTodoItem(item); // Simple function to add each item
        }
    }

//    @When("I click the checkbox for {string}")
//    public void iClickTheCheckboxFor(String todoItem) {
//        todoPage.clickCheckbox(todoItem); // Clicks checkbox for the given item text
//    }
//
//    @Then("the counter should display {int} items left")
//    public void theCounterShouldDisplayItemsLeft(int expectedCount) {
//        int actualCount = todoPage.getItemCount(); // Gets the current counter value
//        Assert.assertEquals(actualCount, expectedCount,
//                "Counter mismatch! Expected: " + expectedCount + ", but was: " + actualCount);
//    }

    @When("I click the {string} filter in page")
    public void iClickTheFilter(String filter) {
        todoPage.clickFilter(filter); // Clicks on the filter with the provided name
    }

    @Then("the To-Do list under {string} should contain the following items:")
    public void theToDoListUnderTabShouldContainTheFollowingItems(String tab, io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedItems = dataTable.asList();
        List<String> actualItems = todoPage.getItemsFromTab(tab); // Retrieves items for the specified tab

        // Check the size of the lists first
        Assert.assertEquals(actualItems.size(), expectedItems.size(),
                "Mismatch in the number of items under " + tab + " tab!");

        // Ensure all items match one by one
        for (int i = 0; i < expectedItems.size(); i++) {
            Assert.assertEquals(actualItems.get(i), expectedItems.get(i),
                    "Mismatch in item at index " + i + " under " + tab + " tab!");
        }
    }

//Edit Field Tests
@When("I edit the To-Do item {string} to {string}")
public void iEditTodoItem(String oldItem, String updatedItem) {
    todoPage.editTodoItemNew(oldItem, updatedItem); // Reuse the page actions for editing
}

    @Then("the To-Do list should contain {string} item")
    public void theListShouldContainItem(String todoItem) {
        boolean itemFound = false;
        List<WebElement> todoItems = todoPage.getTodoItems();
        for (WebElement item : todoItems) {
            if (item.getText().equalsIgnoreCase(todoItem)) {
                itemFound = true;
                break;
            }
        }
        Assert.assertTrue(itemFound, "The expected To-Do item was not found: " + todoItem);
    }

    @When("I delete the To-Do item {string}")
    public void iDeleteTheTodoItem(String todoItem) {
        String destroyButtonXpath = String.format("//label[text()='%s']/following-sibling::button[@class='destroy']", todoItem);
        WebElement destroyButton = driver.findElement(By.xpath(destroyButtonXpath));

        // Hover over the item to make the delete button visible
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(String.format("//label[text()='%s']", todoItem)))).perform();

        // Click the destroy button
        destroyButton.click();
    }

    @Then("the To-Do list should not contain {string}")
    public void theListShouldNotContain(String todoItem) {
        List<WebElement> todoItems = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
        boolean itemFound = false;

        for (WebElement item : todoItems) {
            if (item.getText().trim().equalsIgnoreCase(todoItem)) {
                itemFound = true;
                break;
            }
        }

        Assert.assertFalse(itemFound, "The To-Do item was not deleted: " + todoItem);
    }


}
