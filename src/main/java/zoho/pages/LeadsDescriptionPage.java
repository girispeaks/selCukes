package zoho.pages;

import zoho.managers.WebDriverManager;

public class LeadsDescriptionPage {
    WebDriverManager app;

    public LeadsDescriptionPage(WebDriverManager app) {
        this.app=app;
    }

    public void hasLoaded() {
        if(!app.isElementPresent("last_name_id")) {
            app.logFailure("Lead Description Page has not loaded", true);
        }

    }
}
