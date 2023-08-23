package org.definitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.amazonPages.MainPage;
import org.pages.amazonPages.SearchPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseDefinitions {
    WebDriver driver;
    WebDriver secondDriver;
    WebDriverWait wait;
    WebDriverWait waitForSecondBrowser;
    private MainPage mainPage;
    private SearchPage searchPage;
    private org.pages.rozetkaPages.MainPage secondMainPage;
    private org.pages.rozetkaPages.SearchPage secondSearchPage;

    private Set<String> initialWindowHandles;

    @Given("SetUP and open URL: {string}")
    public void SetUP(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        mainPage = new MainPage(driver, wait);
    }

    @When("User find in search box {string}")
    public void searchItem(String item) {
        mainPage.searchItem(item);
        searchPage = new SearchPage(driver, wait);
    }

    @When("User find in second browser search box {string}")
    public void searchItemSecondBrowser(String item) {
        secondMainPage.searchItem(item);
        secondSearchPage = new org.pages.rozetkaPages.SearchPage(secondDriver, waitForSecondBrowser);
    }

    @And("User opens second browser for URL: {string}")
    public void openSecondBrowser(String MAIN_PAGE_URL) {
        WebDriverManager.chromedriver().setup();
        secondDriver = new ChromeDriver();
        secondDriver.get(MAIN_PAGE_URL);
        secondDriver.manage().window().maximize();
        waitForSecondBrowser = new WebDriverWait(secondDriver, Duration.ofSeconds(5));
        secondMainPage = new org.pages.rozetkaPages.MainPage(secondDriver, waitForSecondBrowser);
    }

    @Then("Check all items contains: {string} on {string}")
    public void checkAllItemsContainString(String substring, String site) {
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> itemList = null;

        if (site.equalsIgnoreCase("amazon")) {
            itemList = searchPage.getListSearchResult();
        } else if (site.equalsIgnoreCase("rozetka")) {
            itemList = secondSearchPage.getListSearchResult();
        }

        for (WebElement item : itemList) {
            String text = item.getText();
            Pattern pattern = Pattern.compile(".*" + substring + ".*");
            Matcher matcher = pattern.matcher(text);
            softAssert.assertTrue(matcher.find(), "Item doesn't contain " + substring + " : " + text);
            softAssert.assertAll();
        }
    }

    @When("User opens a new window with URL {string}")
    public void openNewWindow(String url) {
        String currentWindowHandle = driver.getWindowHandle();
        initialWindowHandles = driver.getWindowHandles();
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "');");
        wait.until(ExpectedConditions.numberOfWindowsToBe(initialWindowHandles.size() + 1));

    }

    @Then("User should be on the new window")
    public void verifyUserIsOnNewWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        String newWindowHandle = null;
        for (String handle : windowHandles) {
            if (!initialWindowHandles.contains(handle)) {
                newWindowHandle = handle;
                break;
            }
        }
        driver.switchTo().window(newWindowHandle);
        Assert.assertEquals(newWindowHandle, driver.getWindowHandle(), "User is not on the new window");
    }


    @After
    public void afterScenario() {
        if (driver != null)
            driver.quit();
        if (secondDriver != null)
            secondDriver.quit();
    }

}
