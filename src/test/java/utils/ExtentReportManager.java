package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Utility class for managing ExtentReports
public class ExtentReportManager {
    private static ExtentReports extent;

    /**
     * Creates or retrieves an instance of the ExtentReports.
     *
     * @param reportFilePath The file path where the report will be generated.
     * @return An ExtentReports instance.
     */
    public static ExtentReports createInstance(String reportFilePath) {
        if (extent == null) {
            // Create a Spark reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);

            // Configure the look and feel of the report
            sparkReporter.config().setDocumentTitle("Automation Test Report"); // Sets the title of the report
            sparkReporter.config().setReportName("Test Execution Report");    // Sets the name of the report

            // Attach the Spark reporter to the ExtentReports instance
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add environment-specific information (optional)
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
            extent.setSystemInfo("Browser", "Chrome"); // Specify dynamically as per test
        }
        return extent;
    }
}
