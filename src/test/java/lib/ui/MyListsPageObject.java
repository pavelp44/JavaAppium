package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

public static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,
    REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String name_of_Folder){

            return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_Folder);

    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }


    public MyListsPageObject (RemoteWebDriver driver){
            super(driver);
        }

    public void openFolderByName(String name_of_folder){

        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find reading list by provided name "+ name_of_folder,
                5);
    }

    public void waitForArticleToDisappearByTitle (String article_title){

        if (Platform.getInstance().isAndroid()) {
            String article_xpath = getFolderXpathByName(article_title);
            this.waitForElementNotPresent(article_xpath, "Saved article still present with title" + article_title, 5);
        }
        else {
            this.waitForElementNotPresent(article_title, "Saved article still present with title" + article_title, 5);
        }

    }

    public void waitForArticleToAppearByTitle (String article_title){

        if (Platform.getInstance().isAndroid()) {
            String article_xpath = getFolderXpathByName(article_title);
            this.waitForElementPresent(article_xpath, "Cannot find saved article with title" + article_title, 5);
        }
        else{
            this.waitForElementPresent(getSavedArticleXpathByTitle(article_title), "Cannot find saved article with title" + article_title, 5);
        }

    }

    public void swipeByArticleToDelete (String article_title){


        if (Platform.getInstance().isIOS()){
            String article_xpath = this.getSavedArticleXpathByTitle(article_title);
            this.swipeElementToLeft(article_xpath,
                    "Cannot swipe article in reading list");
            this.clickToTheRightUpperCorner(article_xpath, "Cannot find saved article");
            this.waitForArticleToDisappearByTitle(article_xpath);
        } else if (Platform.getInstance().isAndroid()){
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getSavedArticleXpathByTitle(article_title);
            this.swipeElementToLeft(article_xpath,
                    "Cannot swipe article in reading list");
            this.waitForArticleToDisappearByTitle(article_title);
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            System.out.println(remove_locator);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from my Saved",
                    10);
            driver.navigate().refresh();
        }

    }
}
