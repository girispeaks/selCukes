package zoho.pages;

import com.aventstack.extentreports.ExtentTest;
import zoho.managers.WebDriverManager;

public class HomePage {

    WebDriverManager app;
    ExtentTest test;

    public HomePage(WebDriverManager app){
        this.app = app;
    }

    public void load(String browser) { //open browser and navigate to home page(here home page is not the page after login)
        app.log("Loading home page after launching browser");
        app.openBrowser(browser);
        app.navigate("url");
        //verify if home page is loaded
        if(!app.verifyTitle("homePageTitle"))
            app.logFailure("Title not matching", false);

    }

    public void goToLoginPage(){
        app.log("Loading login page");
        app.click("signin_link_xpath");
        //app.logFailure("Some critical failure", false);
        //verify if login page has loaded
        if(!app.isElementPresent("username_id"))
            app.logFailure("Login page did not load", true);
    }

}
