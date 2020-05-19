package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MVArticlePageObject extends ArticlePageObject {


    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#ca-watch[role='button']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:[title='Remove this page from your watchlist']";
        OPTIONS_HISTORY = "css:[data-event-name='menu.history']";
    }

    public MVArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
