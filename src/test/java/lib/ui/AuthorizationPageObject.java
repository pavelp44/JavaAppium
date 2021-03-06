package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends ArticlePageObject {

    public final static String
    LOGIN_BUTTON="css:a.mw-ui-progressive";

    private final static String
    LOGIN_INPUT="css:input[name='wpName']",
    PASSWORD_INPUT="css:input[name='wpPassword']",
    SUBMIT_BUTTON="css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }


    public void clickAuthButton() throws InterruptedException {
        this.tryClickElementUntilElementVisible(LOGIN_BUTTON, LOGIN_INPUT, "Cannot find auth button", 12);
    }

    public void enterLoginData(String login, String password) throws InterruptedException {
        Thread.sleep(1000);
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot put data to login field", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot put data to password field", 5);
    }

    public void submitForm() throws InterruptedException {
        Thread.sleep(1000);
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit button", 5);
    }
}
