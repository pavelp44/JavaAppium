package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.iOSArticlePageObject;
import lib.ui.ios.iOSSearchPageObject;
import lib.ui.mobile_web.MVArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isIOS()){
            return new iOSArticlePageObject(driver);
        }
        else if (Platform.getInstance().isMV()){
            return new MVArticlePageObject(driver);
        }
        else return new AndroidArticlePageObject(driver);
    }
}

