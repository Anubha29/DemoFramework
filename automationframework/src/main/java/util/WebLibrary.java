package util;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class WebLibrary {

    static WebDriverWait wait;

    public static WebDriver getBrowser(String browserType) {
        WebDriver driver = null;

        if (browserType.equalsIgnoreCase("chrome")) {
            //setup browser
            ChromeDriverService cds = ChromeDriverService.createDefaultService();
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            ChromeOptions opts = new ChromeOptions();
            opts.addArguments("start-maximized");
            opts.merge(capabilities);
            driver = new ChromeDriver(cds, opts);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            //setup browser
            ChromeDriverService cds = ChromeDriverService.createDefaultService();
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            ChromeOptions opts = new ChromeOptions();
            opts.addArguments("start-maximized");
            opts.merge(capabilities);
            driver = new FirefoxDriver();
        }

        return driver;
    }

    public static Object getBy(WebDriver driver, List<String> locator) {
        String by = locator.get(0);
        String byDesc = locator.get(1);
        Object byObj=null;
        try {
            switch (by.toLowerCase()) {
                case "xpath":
                    byObj=By.xpath(byDesc);
                    break;
                case "id":
                    byObj=By.id(byDesc);
                    break;
                case "name":
                    byObj=By.name(byDesc);
                    break;
                case "angularmodel":
                    byObj=ByAngular.model(byDesc);
                    break;
                case "angularbutton":
                    byObj=ByAngular.buttonText(byDesc);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static WebElement getElement(WebDriver driver, List<String> locator) {
        String by = locator.get(0);
        String byDesc = locator.get(1);
        List<WebElement> element = null;
        int waitTime = 10;

        wait = new WebDriverWait(driver, waitTime);
        try {

            switch (by) {
                case "xpath":
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(byDesc)));
                    element = driver.findElements(By.xpath(byDesc));
                    break;
                case "id":
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(byDesc)));
                    element = driver.findElements(By.id(byDesc));
                    break;
                case "name":
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(byDesc)));
                    element = driver.findElements(By.name(byDesc));
                    break;
            }
            if (element.size() > 0)
                return element.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public static List<WebElement> getElements(WebDriver driver, List<String> locator) {
        String by = locator.get(0);
        String byDesc = locator.get(1);
        List<WebElement> element = null;
        int waitTime = 30;
        wait = new WebDriverWait(driver, waitTime);
        try {

            switch (by) {
                case "xpath":
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(byDesc)));
                    ByAngularModel byele= ByAngular.model("");
                    element = driver.findElements(By.xpath(byDesc));
                    break;
                case "id":
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(byDesc)));
                    element = driver.findElements(By.id(byDesc));
                    break;
                case "name":
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(byDesc)));
                    element = driver.findElements(By.name(byDesc));
                    break;
            }
            if (element.size() > 0)
                return element;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void clickElement(WebDriver driver, WebElement element) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public static void clickElement(WebDriver driver, List<String> locator) {
        String by = locator.get(0);
        String byDesc = locator.get(1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = getElement(driver,locator);
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
       // element.click();
    }

    public static void enterTextToElement(String inputText, WebDriver driver, List<String> locator) {
        String by = locator.get(0);
        String byDesc = locator.get(1);
        WebElement element = getElement(driver, locator);
        element.clear();
        element.sendKeys(inputText);
    }

    public static void appendTextToElement(String inputText, WebDriver driver, List<String> locator) {
        String by = locator.get(0);
        String byDesc = locator.get(1);
        WebElement element = getElement(driver, locator);
        element.sendKeys(inputText);
    }

    public static boolean checkColumns(WebDriver driver, String colHeaders, List<String> locator) {
        String by = locator.get(0);
        String byDesc = locator.get(1);
        boolean status = false;
        ArrayList<String> expectedCol = new ArrayList<String>(Arrays.asList(colHeaders.split(",")));
        ArrayList<String> actualCol = new ArrayList<String>();
        List<WebElement> actualColEle = getElements(driver, locator);
        for (WebElement ele : actualColEle)
            actualCol.add(ele.getText().trim());

        Collections.sort(expectedCol);
        Collections.sort(actualCol);

        boolean isEqual = expectedCol.equals(actualCol);      //false
        System.out.println(isEqual);
        //if (!isEqual) {
        System.out.println("Expected Columns: ");
        for (String text : expectedCol)
            System.out.print(text + "\t");
        System.out.println();
        System.out.println("Actual Columns: ");
        for (String text : actualCol)
            System.out.print(text + "\t");
        // }
        return isEqual;
    }

}
