package org.pages.amazonPages;

import com.google.j2objc.annotations.Weak;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//div[contains(@cel_widget_id, 'MAIN-SEARCH_RESULTS')]")
    List<WebElement> listSearchResult;

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> getListSearchResult(){
        return listSearchResult;
    }
}
