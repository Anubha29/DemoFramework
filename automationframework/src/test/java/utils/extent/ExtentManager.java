package utils.extent;

import com.relevantcodes.extentreports.ExtentReports;
import tests.SetupClass;

public class ExtentManager extends SetupClass {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            //Set HTML reporting file location
            extent = new ExtentReports(extentReportName, true);
        }
        return extent;
    }
}
