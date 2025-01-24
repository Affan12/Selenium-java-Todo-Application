package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // Locators
    private By searchBox = By.name("q");
    private By searchButton = By.name("btnK");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void search(String query) {
        driver.findElement(searchBox).sendKeys(query);
        driver.findElement(searchButton).click();
    }
}