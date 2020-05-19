package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MVNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "css:a[data-event-name='menu.unStar']";
        HOME_LINK = "css:menu.home";
        OPEN_NAVIGATION = "css:[data-event-name='ui.mainmenu']";
        HOME_PAGE_TITLE = "css:#section_0";
        CANCEL_BUTTON = "css:button.cancel";
    }

    public MVNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

}
