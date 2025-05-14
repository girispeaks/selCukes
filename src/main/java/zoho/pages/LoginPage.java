package zoho.pages;

import zoho.managers.WebDriverManager;

public class LoginPage {

    WebDriverManager app;

    public LoginPage(WebDriverManager app){
        this.app = app;
    }

    public void doLogin(){
        //login to the site
        //success login - return ModuleSelectionPage
        //not success - return same login page
        app.log("Trying to login to Zoho");
        app.type("username_id", app.getProperty("username"));
        app.click("nextButton_xpath");
        app.type("password_id", app.getProperty("password"));
        app.click("signinButton_xpath");
        app.click("crm_xpath");
        //div[@class='app-nm'][3]
        //return new ModuleSelectionPage();
    }
}
