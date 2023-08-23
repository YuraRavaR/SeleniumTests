package org.pages.rozetkaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

        WebDriver secondDriver;
        WebDriverWait wait;

        BasePage(WebDriver driver, WebDriverWait wait) {
            this.secondDriver = driver;
            this.wait = wait;
            PageFactory.initElements(driver, this);
        }


}
