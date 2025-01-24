package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    @When("I add the following To-Do items:")
    public void iAddItems(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asList().forEach(todoPage::enterTodoItem);
    }

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
        todoPage.clickCheckbox(todoItem);
    }


}
