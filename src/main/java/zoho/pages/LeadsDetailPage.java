package zoho.pages;

import zoho.managers.WebDriverManager;

public class LeadsDetailPage {

    WebDriverManager app;
    public LeadsDetailPage(WebDriverManager app) {
        this.app=app;
    }

    public void goToCreateLeadPage() {
        app.click("createleadbutton_xpath");
        if(!app.isElementPresent("lead_first_name_id")) {
            app.logFailure("Create Lead Page did not open", true);
        }
    }

    public void selectLead(String leadName) {
        int rowNum = app.getLeadRowNumberWithCellData(leadName);
        if(rowNum==-1)
            app.logFailure("Lead not found in lead list", true);
        app.log(leadName +" lead Row Number is "+rowNum);

        // locator will be dynamic

        app.selectLeadCheckBox(rowNum);
    }

    public void validateLeadPresent(String leadName) {
        int rowNum = app.getLeadRowNumberWithCellData(leadName);
        if(rowNum==-1)
            app.logFailure("Lead not found inlead list", true);

    }

    public void validateLeadNotPresent(String leadName) {
        int rowNum = app.getLeadRowNumberWithCellData(leadName);
        if(rowNum!=-1)
            app.logFailure("Lead  found in lead list", true);

    }

}

//div[@class='container2']/crm-create-fields/div/