package tests;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import pages.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import util.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SetupClass extends BaseClass {


    static Map<String, Map<String, String>> testData = new HashMap<String, Map<String, String>>();
    //HomePage hp;
    public static String reportingDir;
    public static String extentReportName;

//    public void loadTestData() {
//        hp = new HomePage();
//        testData = new JsonLibrary().getTestData(workingDir + projectProperties.get("testdatafile")
//                + "/setup.json");
//    }

    @BeforeSuite
    public void init() {
        System.out.println("=============Init Method Started===============");
        try {
            //setup browser
            ChromeDriverService cds = ChromeDriverService.createDefaultService();
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            ChromeOptions opts = new ChromeOptions();
            opts.addArguments("start-maximized");
            opts.merge(capabilities);
            driver = WebLibrary.getBrowser(projectProperties.get("browser").toString());
            driver.get((String) envProperties.get("loginurl"));
            ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
            ngWebDriver.waitForAngularRequestsToFinish();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            reportingDir = workingDir + projectProperties.get("reportFolder");
            String reportDate= DateTimeLibrary.getDateInFormat(new Date(), "YYYYMMdd_hhmmss");
            extentReportName = reportingDir +
                    projectProperties.get("extentReportName").toString().replace(".html","_"+reportDate+".html");
            System.out.println("Extent Report is: "+extentReportName);
            //Load test data
            //loadTestData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=============End of Init Method===============");
    }


//    @AfterMethod
//    public void logoutOfApplication() {
//        try {
//            hp.signOut();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @AfterSuite
    public void cleanup() {
        System.out.println("=============Cleanup Method Started===============");

        //Closing Browser
        driver.close();

        //Attach execution report to JIRA
        try {
            if (!(projectProperties.get("jiraId") == null)) {
                //CipherText class for encryption and decryption of password.
                CipherText c=new CipherText();
                System.out.println("Attempting to attach execution report to JIRA ID: "+projectProperties.get("jiraId"));
                JiraLibrary.addAttachmentToIssue(projectProperties.get("jiraUser").toString(),
                        c.decrypt(projectProperties.get("jiraPass").toString()),
                        extentReportName,
                        projectProperties.get("jiraId").toString(),
                        workingDir + "/attachFile.bat");
                Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
            }
        } catch (Exception e) {
            System.out.println("Failed to attach Execution report to JIRA, Please do it manually if required.");
        }

        System.out.println("=============End of Cleanup Method===============");
    }
}
