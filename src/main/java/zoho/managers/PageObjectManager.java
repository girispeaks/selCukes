package zoho.managers;

import zoho.pages.*;

public class PageObjectManager {


    WebDriverManager app;
    HomePage homePage;
    LoginPage loginPage;
    TopMenuComponent topMenu;
    LeadsDetailPage leadsDetailPage;
    CreateLeadPage createLeadPage;
    LeadsDescriptionPage leadsDescriptionPage;

    public PageObjectManager(){
        this.app = new WebDriverManager();
    }

    public WebDriverManager getWebDriverManager(){
        return app;
    }

    public HomePage getHomePage() {
     if(homePage ==null)
        homePage=new HomePage(app);
     return homePage;
    }

    public LoginPage getLoginPage() {
        if(loginPage==null)
            loginPage=new LoginPage(app);
        return loginPage;
    }

    public TopMenuComponent getTopMenu() {
        if(topMenu == null)
            topMenu = new TopMenuComponent(app);
        return topMenu;
    }
    public LeadsDetailPage getleadsDetailPage() {
        if(leadsDetailPage == null)
            leadsDetailPage	= new LeadsDetailPage(app);
        return leadsDetailPage;
    }

    public CreateLeadPage getCreateLeadPage() {
        if(createLeadPage == null)
            createLeadPage	= new CreateLeadPage(app);
        return createLeadPage;
    }

    public LeadsDescriptionPage getLeadDescriptionPage() {
        if(leadsDescriptionPage == null)
            leadsDescriptionPage	= new LeadsDescriptionPage(app);
        return leadsDescriptionPage;
    }

}
//a[@id='Visible_Leads']/span