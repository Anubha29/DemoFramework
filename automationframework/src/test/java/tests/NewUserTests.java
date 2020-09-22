package tests;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.NewUserFormPage;
import util.CipherText;
import util.JsonLibrary;
import utils.extent.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * author: anubhamathur
 */

public class NewUserTests {

    Map<String, Map<String, String>> testData =new HashMap<String, Map<String, String>>();
    LoginPage lp=new LoginPage();
    NewUserFormPage nfp=new NewUserFormPage();
    HomePage hp=new HomePage();

    @BeforeClass
    public void loadTestData(){
        testData =new JsonLibrary().getTestData(System.getProperty("user.dir")+lp.projectProperties.getProperty("testdatafile")
                +"/newusertests.json");
    }

    @Test (description = "Create New User")
    public void createNewUser(Method method) {
        // Read Test Data
        Map<String, String> methodTestData = testData.get(method.getName());
        //Start of the test
        ExtentTestManager.startTest(method.getName(), methodTestData.get("testName"));
        try{
            lp.createNewAccount(methodTestData.get("email"));
            Thread.sleep(2000);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Navigate to New User Form",
                    "User Navigated to New User Form");
            nfp.inputFirstName(methodTestData.get("firstName"));
            nfp.inputLastName(methodTestData.get("lastName"));
            nfp.inputPass(methodTestData.get("password"));
            nfp.inputAddrL1(methodTestData.get("addressLine1"));
            nfp.inputCity(methodTestData.get("city"));
            nfp.selectState(methodTestData.get("state"));
            nfp.inputPostal(methodTestData.get("postCode"));
            nfp.selectCountry(methodTestData.get("country"));
            nfp.inputPhone(methodTestData.get("phone"));
            nfp.submitAccount();
            Thread.sleep(5000);
            Assert.assertEquals(hp.getLoggedInUser(), methodTestData.get("firstName")+" "+methodTestData.get("lastName"));
            hp.signOut();
        } catch(Exception e){
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception Occured.", e);
        }
    }

    @Test (description = "Login valid User")
    public void loginValidUser(Method method) {
        // Read Test Data
        Map<String, String> methodTestData = testData.get(method.getName());
        //Start of the test
        ExtentTestManager.startTest(method.getName(), methodTestData.get("testName"));

        lp.loginExistingUser(methodTestData.get("useremail"),
                methodTestData.get("userpwd"));
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Completed", "Test Passed");
        try{
            Thread.sleep(2000);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
