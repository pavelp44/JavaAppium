package lib.ui;


import com.sun.xml.internal.ws.policy.AssertionSet;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lib.Platform;

import static lib.ui.NavigationUI.CANCEL_BUTTON;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver  = driver;
    }

    public WebElement waitForElementPresent (String locator, String error_meesage, long timeoutInSeconds){

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_meesage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message){
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    public WebElement waitForElementAndSendKeys(String locator, CharSequence value, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void setAndroidElementValue(String locator, String value, String error_message){
        By by = this.getLocatorByString(locator);
        waitForElementPresent(locator, error_message);
        AndroidElement androidElement = new AndroidElement();
        androidElement = (AndroidElement) driver.findElement(by);
        androidElement.setValue(value);
    }

    public void waitAllTestViewsToRender(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        if (Platform.getInstance().isAndroid()){
            wait.until(presenceOfAllElementsLocatedBy(By.xpath("//*[@class='android.widget.TextView']")));
        } else {
            wait.until(presenceOfAllElementsLocatedBy(By.xpath("id:Wikipedia")));
        }
    }


    public void swipeUp(WaitOptions timeOfSwipe){

        if (driver instanceof AppiumDriver) {
            Dimension size = driver.manage().window().getSize();

            int x = size.width / 2;

            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            TouchAction action = new TouchAction((AppiumDriver)driver);
            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(timeOfSwipe)
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }
    public void swipeUpQuick(){
        swipeUp(WaitOptions.waitOptions(Duration.ofMillis(200)));
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){

        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresent(locator, "Cannot find element by swiping Up \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            already_swiped++;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator)){
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator",1).getLocation().getY();
        if (Platform.getInstance().isMV()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor)driver;
            Object jsResult = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(jsResult.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void clickToTheRightUpperCorner(String locator, String error_message){
        if (driver instanceof AppiumDriver) {
            WebElement element = this.waitForElementPresent(locator + "/..", error_message);
            int left_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (left_x + width) - 10;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver)driver);
            action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();
        } else {
            System.out.println("Method clickToTheRightUpperCorner does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeElementToLeft(String locator, String error_message){
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, error_message);
            System.out.println(element.getSize());
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver)driver);
            action.press(PointOption.point(right_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)));

            if (Platform.getInstance().isAndroid()) {
                action.moveTo(PointOption.point(left_x, middle_y));
            } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(PointOption.point(offset_x, 0));
            }
            action.release();
            action.perform();
        }
            else {
                System.out.println("Method swipeUp does nothing for platform" + Platform.getInstance().getPlatformVar());
                }
    }

    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = new ArrayList();
        elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator){
        List<WebElement> elements;
        By by = this.getLocatorByString(locator);
        elements = driver.findElements(by);
        Assert.assertTrue("Elements you provided by xpath" + by.toString() + "are presents", elements.size() == 0);
    }
        public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds){
            WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
            return element.getAttribute(attribute);
        }

    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        }
        else if (by_type.equals("id")) {
            return By.id(locator);
        }
        else if (by_type.equals("css")){
            return By.cssSelector(locator);
        } else throw new IllegalArgumentException("Cannot get type of locator. Locator "+ locator_with_type);
    }

    public void scrollWebPageUp(){
        if (Platform.getInstance().isMV()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        }
        else {
            System.out.println("Method scrollWebPageUp does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);
        while (!isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }

    }

    public boolean isElementPresent(String locator){
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts) throws InterruptedException {

        Thread.sleep(1000);
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts){
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e){
                if (current_attempts > amount_of_attempts){
                    this.waitForElementAndClick(locator, error_message, 1);
                }
            }

            ++ current_attempts;
        }
    }


    public void initSearch() throws Exception{
        this.tryClickElementUntilElementVisible(
                "css:#searchIcon",
                CANCEL_BUTTON,
                "Cannot find search input",
                5);
            Thread.sleep(2000);
    }


    public void tryClickElementUntilElementVisible(String locator, String locator_of_next_element, String error_message, int amount_of_attempts) throws InterruptedException {
        int current_attempts = 0;


        while (true) {
            try {
                ++current_attempts;
                driver.findElement(By.cssSelector(locator.replace("css:", ""))).click();
                System.out.println("clicked on element " + locator);
                Thread.sleep(2000);
                driver.findElement(By.cssSelector(locator_of_next_element.replace("css:", "")));

                if (current_attempts > amount_of_attempts) {
                    System.out.println("Exceeded amount of attempts");
                    return;
                }
                return;
            } catch (Exception ex) {
                System.out.println("Click failed on" + locator + "\nException: " + ex.getMessage());
                Thread.sleep(2000);
                if (current_attempts > amount_of_attempts) {
                    System.out.println("Exceeded amount of attempts");
                    ++current_attempts;
                    return;
                }
                }
            }
        }
}

