package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static String name_of_folder = "Learn programming";
    private static String article_title = "Cat";

    private static final String
            LOGIN = "Pet792",
            PASSWORD = "HelpNow1995";

    @Test

    public void testSaveFirstArticleToMyList() throws InterruptedException {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.clickByArticleWithSubString("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
            articlePageObject.waitForTitleElement();
            article_title = articlePageObject.getArticleTitle();
            articlePageObject.close_article();
        } else if (Platform.getInstance().isIOS()) {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.waitForTitleElement();
            articlePageObject.close_article();
            searchPageObject.clickCancelSearch();
        }

        if (Platform.getInstance().isMV()) {

            articlePageObject.addArticlesToMySaved();

            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(LOGIN, PASSWORD);
            Auth.submitForm();

            articlePageObject.waitForTitleElement();

            articlePageObject.addArticlesToMySaved();

        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_title);
        } else if (Platform.getInstance().isMV()) {
            myListsPageObject.swipeByArticleToDelete(article_title);
        }
    }


    public void testSaveTwoArticlesToReadingList() throws Exception {

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);


        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Dog");

        searchPageObject.clickByArticleWithSubString("Dog");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
            articlePageObject.waitForTitleElement();
            article_title = articlePageObject.getArticleTitle();
            articlePageObject.close_article();
        } else if (Platform.getInstance().isIOS()) {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.close_article();
            searchPageObject.clickCancelSearch();
        } else {
            articlePageObject.addArticlesToMySaved();

            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(LOGIN, PASSWORD);
            Auth.submitForm();

            articlePageObject.waitForTitleElement();


            articlePageObject.addArticlesToMySaved();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        searchPageObject.initSearch();

        searchPageObject.typeSearchLine("Cat");
        Thread.sleep(5000);
        searchPageObject.clickByArticleWithSubString("Cat");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingList();
            articlePageObject.waitForTitleElement();
            article_title = articlePageObject.getArticleTitle();
            articlePageObject.close_article();
        } else if (Platform.getInstance().isMV()) {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.close_article();
            searchPageObject.clickCancelSearch();
            navigationUI.openNavigation();
            navigationUI.clickMyLists();

        } else {

            articlePageObject.addArticlesToMySaved();

            MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);

            if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
                myListsPageObject.openFolderByName(name_of_folder);
                myListsPageObject.swipeByArticleToDelete(article_title);
            } else if (Platform.getInstance().isMV()) {
                myListsPageObject.swipeByArticleToDelete(article_title);
            }
        }
    }
}

