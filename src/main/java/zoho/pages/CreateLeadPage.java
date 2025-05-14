package zoho.pages;

import io.cucumber.datatable.DataTable;
import zoho.managers.WebDriverManager;

import java.util.List;
import java.util.Map;

public class CreateLeadPage {

    WebDriverManager app;

    public CreateLeadPage(WebDriverManager app) {
        this.app=app;
    }

    public void submitLeadDetails(DataTable dataTable) {
        List<Map<String,String>> rows = dataTable.asMaps(String.class, String.class);
        app.type("lead_first_name_id", rows.get(0).get("FirstName"));
        app.type("lead_last_name_id", rows.get(0).get("LastName"));
        app.type("lead_company_id", rows.get(0).get("email"));
        app.type("lead_email_id", rows.get(0).get("Company"));
        app.click("save_btn_id");
    }
}
