package zoho.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.util.Date;

public class ExtentManager {
    static ExtentReports reports;
    public static String screenshotFolderPath;

    public static ExtentReports getReports() {
        if(reports == null) {
            reports = new ExtentReports();
            // init the report folder
            Date d = new Date();
            String reportsFolder=d.toString().replaceAll(":", "-") +"//screenshots";

            screenshotFolderPath = System.getProperty("user.dir") +"//reports//"+reportsFolder;
            String reportFolderPath = System.getProperty("user.dir") +"//reports//"+d.toString().replaceAll(":", "-");

            File f = new File(screenshotFolderPath);
            f.mkdirs();// create dynamic report folder name + screenshots

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolderPath);
            sparkReporter.config().setReportName("Production Regression Testing");
            sparkReporter.config().setDocumentTitle("Automation Reports");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setEncoding("utf-8");

            reports.attachReporter(sparkReporter);
        }

        return reports;

    }
}
