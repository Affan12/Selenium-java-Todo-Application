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

    public void clickClearCompletedButton() {
        driver.findElement(clearCompletedButton).click();
    }
}
