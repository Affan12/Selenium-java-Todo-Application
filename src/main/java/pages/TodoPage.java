package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TodoPage {
    private WebDriver driver;

    // Locators
    private By inputField = By.className("new-todo");
    private By todoItems = By.cssSelector("ul.todo-list li");
    private By todoLabel = By.cssSelector("ul.todo-list li label");
    private By clearCompletedButton = By.className("clear-completed");

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

    public void clickFilter(String filterName) {
        By filterLocator = By.linkText(filterName);
        driver.findElement(filterLocator).click();
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


    public void clickClearCompletedButton() {
        driver.findElement(clearCompletedButton).click();
    }
}
