package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MVNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mv-mf-main-menu-button";

    }

    public MVNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

}
