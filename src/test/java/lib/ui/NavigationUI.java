package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

abstract public class NavigationUI extends MainPageObject{

    public static String
    MY_LISTS_LINK,
    CLOSE_SYNC,
    OPEN_NAVIGATION;


    public NavigationUI (RemoteWebDriver driver){
        super(driver);
    }

    public void clickMyLists()
    {
        if (Platform.getInstance().isMV()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }

    public void openNavigation(){
        if (Platform.getInstance().isMV()){
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5);
        } else {
            System.out.println("Method openNavigation does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

}
