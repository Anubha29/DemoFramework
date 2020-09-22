package pages;

import util.JsonLibrary;

import java.util.HashMap;
import java.util.List;

import static util.WebLibrary.getElement;

public class HomePage extends BaseClass {

    HashMap<String, List<String>> homeLocatorList;

    public HomePage() {
        homeLocatorList = JsonLibrary.getLocators(System.getProperty("user.dir") + projectProperties.get("locatorfile") + "/home.json");
    }

    public String getLoggedInUser(){
        return getElement(driver, homeLocatorList.get("userName")).getText();
    }

    public void signOut(){
        getElement(driver, homeLocatorList.get("signOut")).click();
    }
}
