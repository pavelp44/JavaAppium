package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MVArticlePageObject extends ArticlePageObject {


    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@id='ca-watch']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:[href='/w/index.php?title=Java_(programming_language)&action=unwatch]'";
    }

    public MVArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
