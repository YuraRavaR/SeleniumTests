package org.pages.amazonPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement searchBox;

    @FindBy(xpath = "//span[@class='a-button-inner']")
    WebElement popupCloseButton;
    @FindBy(xpath = "//div[@id='glow-toaster-body']")
    WebElement popupWindow;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getSearchBox() {
        return searchBox;
    }

    public void searchItem(String item) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.click();
        searchBox.sendKeys(item);
        searchBox.sendKeys(Keys.ENTER);
    }

}
