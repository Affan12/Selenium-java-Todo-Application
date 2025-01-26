package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class TodoPage {
    private WebDriver driver;

    // Locators (refactored from CSS to XPath)
    private By inputField = By.xpath("//input[@class='new-todo']");
    private By todoItems = By.xpath("//ul[@class='todo-list']/li");
    private By todoLabel = By.xpath("//ul[@class='todo-list']/li//label");
    private By clearCompletedButton = By.xpath("//button[@class='clear-completed']");
    private By itemCounter = By.xpath("//span[@class='todo-count']");
    private By filters = By.xpath("//ul[@class='filters']//a"); // Filters links like "All", "Active", "Completed"
    private By completedItems = By.xpath("//ul[@class='todo-list']/li[contains(@class, 'completed')]");
    private By activeItems = By.xpath("//ul[@class='todo-list']/li[not(contains(@class, 'completed'))]");
    private By deleteButtons = By.xpath("//button[@class='destroy']");
    private By checkboxes = By.xpath("//ul[@class='todo-list']/li//input[@class='toggle']");

    // Constructor
    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions

    public void enterTodoItem(String item) {
        driver.findElement(inputField).sendKeys(item);
        driver.findElement(inputField).sendKeys("\n");
    }

    public List<WebElement> getTodoItems() {
        return driver.findElements(todoItems);
    }

    public String getTodoLabelText(int index) {
        List<WebElement> todoLabels = driver.findElements(todoLabel);
        return todoLabels.get(index).getText();
    }

    public List<WebElement> getActiveItems() {
        return driver.findElements(activeItems);
    }

    public List<WebElement> getCompletedItems() {
        return driver.findElements(completedItems);
    }

    public void clickCheckbox(String todoItem) {
        List<WebElement> items = driver.findElements(todoItems);
        for (WebElement item : items) {
            WebElement label = item.findElement(todoLabel);
            if (label.getText().equalsIgnoreCase(todoItem)) {
                WebElement checkbox = item.findElement(checkboxes);
                checkbox.click();
                break;
            }
        }
    }

    public int getItemCount() {
        String counterText = driver.findElement(itemCounter).getText(); // e.g., "3 items left"
        return Integer.parseInt(counterText.split(" ")[0]); // Extract the number
    }

    public void deleteTodoItem(int index) {
        driver.findElements(deleteButtons).get(index).click();
    }

    public void clickClearCompletedButton() {
        driver.findElement(clearCompletedButton).click();
    }

    public void addTodoItem(String itemText) {
        driver.findElement(inputField).sendKeys(itemText + "\n"); // Add item by typing and pressing Enter
    }

    public void clickFilter(String filterName) {
        String filterXpath = String.format("//ul[@class='filters']//a[contains(text(), '%s')]", filterName);
        driver.findElement(By.xpath(filterXpath)).click();
    }

    public void clickListItem(String checkbox) {
        String formattedCheckbox = String.format("//label[contains(text(),'%s')]/..//input", checkbox);
        driver.findElement(By.xpath(formattedCheckbox)).click();
    }

    public List<String> getTodoItemsFromTab(String tabName) {
        List<WebElement> items = driver.findElements(todoItems);
        List<String> filteredItems = new ArrayList<>();
        for (WebElement item : items) {
            boolean isCompleted = item.getAttribute("class").contains("completed");

            if (tabName.equalsIgnoreCase("All") ||
                    (tabName.equalsIgnoreCase("Active") && !isCompleted) ||
                    (tabName.equalsIgnoreCase("Completed") && isCompleted)) {
                filteredItems.add(item.findElement(todoLabel).getText());
            }
        }
        return filteredItems;
    }

    public List<String> getItemsFromTab(String tabName) {
        List<WebElement> items = driver.findElements(todoItems);
        List<String> filteredItems = new ArrayList<>();

        for (WebElement item : items) {
            boolean isCompleted = item.getAttribute("class").contains("completed");

            if (tabName.equalsIgnoreCase("All") ||
                    (tabName.equalsIgnoreCase("Active") && !isCompleted) ||
                    (tabName.equalsIgnoreCase("Completed") && isCompleted)) {
                filteredItems.add(item.findElement(todoLabel).getText());
            }
        }
        return filteredItems;
    }


    public void editTodoItemNew(String oldItem, String updatedItem) {
        // Double-click the label of the To-Do item to start editing
        By itemLocatorNew = By.xpath(String.format("//label[text()='%s']", oldItem));
        WebElement itemLabel = driver.findElement(itemLocatorNew);
        Actions actions = new Actions(driver);
        actions.doubleClick(itemLabel).perform();

        // Enter the updated text into the editing field
//        By editField = By.xpath("//li[contains(@class, 'editing')]//input[@class='edit']");
//        WebElement editInput = driver.findElement(editField);
//        editInput.clear();

        driver.findElement(By.xpath("")).clear();

        itemLabel.clear();
        itemLabel.sendKeys(updatedItem);
    }

    public void pressEnterAfterEditing() {
        // Press Enter in the editing field
        By editField = By.xpath("//li[contains(@class, 'editing')]//input[@class='edit']");
        WebElement editInput = driver.findElement(editField);
        editInput.sendKeys("\n");
    }

    //Edit

    public void editTodoItem(String oldItem, String updatedItem) {
        // Double-click the label of the To-Do item to start editing
        By itemLabelNew = By.xpath(String.format("//label[text()='%s']", oldItem));
        WebElement itemLabelElement = driver.findElement(itemLabelNew);

        // Use Actions to perform a double-click
        Actions actions = new Actions(driver);
        actions.doubleClick(itemLabelElement).perform();

        // Locate the now-editable input field for the item
        By editField = By.xpath("//li[contains(@class, 'editing')]//input[@class='edit']");
        WebElement editInput = driver.findElement(editField);

        // Clear the field and enter the updated text
        editInput.clear();
        editInput.sendKeys(updatedItem);

        // Press Enter to save the changes
        editInput.sendKeys(Keys.ENTER);
    }


}
