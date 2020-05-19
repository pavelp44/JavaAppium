package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MVMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}']";


        REMOVE_FROM_SAVED_BUTTON = "xpath://li[contains(@class,'with-watchstar')][contains(@title,'{TITLE}')]/a[contains(@class,'mw-ui-icon')]";
    }
    public MVMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }{

    }
}
