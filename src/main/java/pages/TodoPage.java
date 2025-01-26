package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TodoPage {
    private WebDriver driver;

    // Locators
    private By inputField = By.className("new-todo");
    private By todoItems = By.cssSelector("ul.todo-list li");
    private By todoLabel = By.cssSelector("ul.todo-list li label");
    private By clearCompletedButton = By.className("clear-completed");


    private By itemCounter = By.cssSelector("span.todo-count"); // Updates with the actual selector
    private By filters = By.cssSelector(".filters a");

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
        return driver.findElements(By.cssSelector("ul.todo-list li:not(.completed)"));
    }

    public List<WebElement> getCompletedItems() {
        return driver.findElements(By.cssSelector("ul.todo-list li.completed"));
    }

    public void clickCheckbox(String todoItem) {
        List<WebElement> todoItems = driver.findElements(By.cssSelector("ul.todo-list li"));
        for (WebElement item : todoItems) {
            WebElement label = item.findElement(By.cssSelector("label"));
            if (label.getText().equalsIgnoreCase(todoItem)) {
                WebElement checkbox = item.findElement(By.cssSelector("input.toggle"));
                checkbox.click();
                break;
            }
        }
    }


    // Method to return the number of items left as displayed in the counter
    public int getItemCount() {
        String counterText = driver.findElement(itemCounter).getText(); // e.g., "3 items left"
        return Integer.parseInt(counterText.split(" ")[0]); // Extract the number
    }

    // Method to delete a To-Do item by index (if not already implemented)
    public void deleteTodoItem(int index) {
        driver.findElements(By.cssSelector(".destroy")).get(index).click(); // Replace with actual locator for delete button
    }

    public void clickClearCompletedButton() {
        driver.findElement(clearCompletedButton).click();
    }

    // Add a To-Do item
    public void addTodoItem(String itemText) {
        driver.findElement(inputField).sendKeys(itemText + "\n"); // Add item by typing and pressing Enter
    }

//    // Click checkbox for a specific item
//    public void clickCheckbox(String todoItem) {
//        List<WebElement> todoItemsList = driver.findElements(todoItems);
//        for (WebElement item : todoItemsList) {
//            WebElement label = item.findElement(todoLabel);
//            if (label.getText().equalsIgnoreCase(todoItem)) {
//                item.findElement(By.cssSelector("input.toggle")).click(); // Click checkbox
//                break;
//            }
//        }
//    }
//
//    // Get number of items left from the counter
//    public int getItemCount() {
//        String counterText = driver.findElement(itemCounter).getText(); // E.g., "3 items left"
//        return Integer.parseInt(counterText); // Parse and return the number
//    }

    // Click a filter (All, Active, or Completed)
    public void clickFilter(String filterName) {
//        List<WebElement> filterLinks = driver.findElements(filters);
//        for (WebElement filter : filterLinks) {
//            if (filter.getText().equalsIgnoreCase(filterName)) {
//                filter.click(); // Click on the link matching filter name
//                break;
//            }
//        }

        String xpathExpression = String.format("//li/a[contains(text(), '%s')]", filterName);

        WebElement filterLink = driver.findElement(By.xpath(xpathExpression));
        filterLink.click();


    }

    // Get list of To-Do item labels for the current filter
    public List<String> getTodoItemsFromTab(String tabName) {
        List<WebElement> todoItemsList = driver.findElements(todoItems);
        List<String> items = new ArrayList<>();
        for (WebElement item : todoItemsList) {
            boolean isCompleted = item.getAttribute("class").contains("completed");

            // Logic to filter items based on the tab
            if (tabName.equalsIgnoreCase("All") ||
                    (tabName.equalsIgnoreCase("Active") && !isCompleted) ||
                    (tabName.equalsIgnoreCase("Completed") && isCompleted)) {
                items.add(item.findElement(todoLabel).getText()); // Get the label text
            }
        }
        return items; // Return the list of filtered items
    }

    public List<String> getItemsFromTab(String tabName) {
        List<WebElement> todoItemsList = driver.findElements(todoItems); // Common locator for all To-Do items
        List<String> items = new ArrayList<>();
        for (WebElement item : todoItemsList) {
            boolean isCompleted = item.getAttribute("class").contains("completed");

            // Logic to filter items based on the tab name
            if (tabName.equalsIgnoreCase("All") ||
                    (tabName.equalsIgnoreCase("Active") && !isCompleted) ||
                    (tabName.equalsIgnoreCase("Completed") && isCompleted)) {
                items.add(item.findElement(todoLabel).getText()); // Get the label text
            }
        }
        return items; // Return the list of filtered items
    }


}
