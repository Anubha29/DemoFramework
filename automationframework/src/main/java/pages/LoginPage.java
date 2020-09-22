package pages;

import org.openqa.selenium.WebElement;
import util.JsonLibrary;

import java.util.HashMap;
import java.util.List;

import static util.WebLibrary.getElement;

public class LoginPage extends BaseClass {

    HashMap<String, List<String>> loginLocatorList;

    public LoginPage() {
        loginLocatorList = JsonLibrary.getLocators(System.getProperty("user.dir") + projectProperties.get("locatorfile") + "/login.json");
    }

    public String pageTitle() {
        return driver.getTitle();
    }

    public void inputNewEmail( String email){
        getElement(driver, loginLocatorList.get("createAccountInput")).clear();
        getElement(driver, loginLocatorList.get("createAccountInput")).sendKeys(email);
    }

    public void createNewAccount( String email){
        inputNewEmail(email);
        getElement(driver, loginLocatorList.get("createAccountButton")).click();
    }

    public void inputRegisteredEmail(String email){
        getElement(driver, loginLocatorList.get("loginEmail")).clear();
        getElement(driver, loginLocatorList.get("loginEmail")).sendKeys(email);
    }

    public void inputRegisteredPass( String email){
        getElement(driver, loginLocatorList.get("loginPassword")).clear();
        getElement(driver, loginLocatorList.get("loginPassword")).sendKeys(email);
    }
    public void loginExistingUser( String email, String pass){
        inputRegisteredEmail(email);
        inputRegisteredPass(email);
        getElement(driver, loginLocatorList.get("loginSubmitButton")).click();
    }


}
