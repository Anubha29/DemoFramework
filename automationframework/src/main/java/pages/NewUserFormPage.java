package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import util.JsonLibrary;

import java.util.HashMap;
import java.util.List;

import static util.WebLibrary.getElement;

public class NewUserFormPage extends BaseClass {

    HashMap<String, List<String>> homeLocatorList;

    By abc= By.xpath("");

    public NewUserFormPage() {
        homeLocatorList = JsonLibrary.getLocators(System.getProperty("user.dir") + projectProperties.get("locatorfile") + "/newuserform.json");
    }
    public void inputFirstName(String firstname){
        getElement(driver, homeLocatorList.get("firstName")).clear();
        getElement(driver, homeLocatorList.get("firstName")).sendKeys(firstname);
    }

    public void inputLastName(String lastname){
        getElement(driver, homeLocatorList.get("lastName")).clear();
        getElement(driver, homeLocatorList.get("lastName")).sendKeys(lastname);
    }

    public void inputPass(String pwd){
        getElement(driver, homeLocatorList.get("password")).clear();
        getElement(driver, homeLocatorList.get("password")).sendKeys(pwd);
    }

    public void inputAddrL1(String addressL1){
        getElement(driver, homeLocatorList.get("addressLine1")).clear();
        getElement(driver, homeLocatorList.get("addressLine1")).sendKeys(addressL1);
    }

    public void inputCity(String cityName){
        getElement(driver, homeLocatorList.get("city")).clear();
        getElement(driver, homeLocatorList.get("city")).sendKeys(cityName);
    }

    public void inputPostal(String postal){
        getElement(driver, homeLocatorList.get("postCode")).clear();
        getElement(driver, homeLocatorList.get("postCode")).sendKeys(postal);
    }

    public void inputPhone(String phoneNum){
        getElement(driver, homeLocatorList.get("phone")).clear();
        getElement(driver, homeLocatorList.get("phone")).sendKeys(phoneNum);
    }

    public void selectState(String stateName){
        Select stateSelect=new Select(getElement(driver, homeLocatorList.get("state")));
        stateSelect.selectByValue(stateName);
    }

    public void selectCountry(String countryName){
        Select stateSelect=new Select(getElement(driver, homeLocatorList.get("country")));
        stateSelect.selectByValue(countryName);
    }

    public void submitAccount(){
        getElement(driver, homeLocatorList.get("SubmitButton")).click();
    }
}
