package utils.listners;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.SetupClass;
import utils.extent.ExtentTestManager;

public class TestListener extends SetupClass implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Starting test: " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }

//    @Override
//    public void onFinish(ITestContext iTestContext) {
//        System.out.println("Finished method " + iTestContext.getName());
//        //Do tier down operations for extentreports reporting!
//        ExtentTestManager.endTest();
//        ExtentManager.getReporter().flush();
//    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Starting method " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("TestSuccess method " + getTestMethodName(iTestResult) + " succeed");
        //ExtentReports log operation for passed tests.
        //ExtentTestManager.getTest().log(LogStatus.PASS, "Test Completed", "Test Passed");
        ExtentTestManager.writeToFile();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("TestFailure method " + getTestMethodName(iTestResult) + " failed");

        //Get driver from BaseTest and assign to local webDriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = SetupClass.driver;

        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
                getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed, Screenshot Taken.",
                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
        ExtentTestManager.writeToFile();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("TestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        //ExtentReports log operation for skipped tests.
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        ExtentTestManager.writeToFile();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
