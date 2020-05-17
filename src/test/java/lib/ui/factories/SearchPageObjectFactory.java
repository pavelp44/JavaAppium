package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.iOSSearchPageObject;
import lib.ui.mobile_web.MVSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);
        }
        else if (Platform.getInstance().isMV()){
            return new MVSearchPageObject(driver);

        }
        else return new iOSSearchPageObject(driver);
    }
}
