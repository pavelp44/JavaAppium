package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private final static String
    LOGIN_BUTTON="xpath:/html/body/div[4]/div[2]/a",
    LOGIN_INPUT="css:input[name='wpName']",
    PASSWORD_INPUT="css:input[name='wpPassword']",
    SUBMIT_BUTTON="css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }


    public void clickAuthButton(){
        this.tryClickElementWithFewAttempts(LOGIN_BUTTON, "Cannot find auth button", 5);
    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot put data to login field", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot put data to password field", 5);
    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit button", 5);
    }
}
