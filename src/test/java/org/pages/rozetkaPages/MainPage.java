package org.pages.rozetkaPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input[@name='search']")
    WebElement searchBox;


    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void searchItem(String item) {
        wait.until((ExpectedConditions.visibilityOf(searchBox)));
        searchBox.click();
        searchBox.sendKeys(item);
        searchBox.sendKeys(Keys.ENTER);
    }
}
