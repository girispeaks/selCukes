package zoho.context;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import zoho.managers.PageObjectManager;
import zoho.reports.ExtentManager;

public class TestContext {

    ExtentReports report;
    ExtentTest test;
    private PageObjectManager pageObjectManager;

    public TestContext(){
        System.out.println("Constructor TestContext");
        report= ExtentManager.getReports(); //initialize the reports and this is done only once
        pageObjectManager = new PageObjectManager();
    }

    public PageObjectManager getPageObjectManager(){
        return pageObjectManager;
    }

    public void createScenario(String scenarioName){
        test=report.createTest(scenarioName); // this is needed for every scenario hence shouldnt be part of constructor initiation.
        this.pageObjectManager.getWebDriverManager().init(test);
    }

    public void endScenario(){
        report.flush();
    }

    public void log(String msg){
        this.pageObjectManager.getWebDriverManager().log(msg);
    }

}
