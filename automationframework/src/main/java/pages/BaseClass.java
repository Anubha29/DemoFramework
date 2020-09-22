package pages;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JsonLibrary;

import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static util.WebLibrary.getElement;

public class BaseClass {
    public static WebDriver driver;
    public static NgWebDriver ngWebDriver;
    FileReader locatorPath;
    FileReader envPath;
    public static Properties projectProperties;
    public static Properties envProperties;
    HashMap<String, List<String>> commonLocatorList;
    public static String workingDir = System.getProperty("user.dir");


    public BaseClass() {
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/src/main/resources/chromedriver.exe");
            locatorPath = new FileReader(System.getProperty("user.dir") + "/src/main/resources/project.properties");
            projectProperties = new Properties();
            projectProperties.load(locatorPath);
            envPath = new FileReader(System.getProperty("user.dir") + "/src/main/resources/"
                    + projectProperties.get("env") + ".properties");
            envProperties = new Properties();
            envProperties.load(envPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTitle() {

    }


}
