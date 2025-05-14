package zoho.teststeps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import zoho.context.TestContext;
import zoho.pages.HomePage;
import zoho.pages.LoginPage;

public class Session {

    public TestContext context;
    public HomePage homePage;
    public LoginPage loginPage;


    public Session(TestContext context){
        this.context=context;
        this.homePage = this.context.getPageObjectManager().getHomePage();
        this.loginPage = this.context.getPageObjectManager().getLoginPage();
        System.out.println("Session Constructor");
    }

    @Given("I am logged in Zoho.com")
    public void IamloggedinZohocom(){
        context.log("I am logged in Zoho.com");
        homePage.load("Chrome");
        homePage.goToLoginPage();
        loginPage.doLogin();
    }


}
