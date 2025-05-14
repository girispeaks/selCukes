package zoho.teststeps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import zoho.context.TestContext;
import zoho.pages.CreateLeadPage;
import zoho.pages.LeadsDetailPage;
import zoho.pages.LeadsDescriptionPage;

public class Leads {
    public TestContext context;
    public LeadsDetailPage leadsDetailPage;
    public CreateLeadPage createLeadPage;
    public LeadsDescriptionPage leadDescriptionPage;


        public Leads(TestContext context){
            this.context=context;
            System.out.println("Leads Constructor");
            this.leadsDetailPage=context.getPageObjectManager().getleadsDetailPage();
            this.createLeadPage=context.getPageObjectManager().getCreateLeadPage();
            this.leadDescriptionPage=context.getPageObjectManager().getLeadDescriptionPage();
        }

        @Before
        public void before(Scenario scenario){

            context.createScenario(scenario.getName());
            context.log("Starting scenario "+scenario.getName());
        }

        @After
        public void after(Scenario scenario){
            context.log("Ending scenario "+scenario.getName());
            context.endScenario();
            context.getPageObjectManager().getWebDriverManager().quit();
            System.out.println("---------------------------------------");
        }

    @When("I go to create lead page")
    public void iGoToCreateLeadPage(String pageName){
        context.log("I go to "+pageName+" page");
        //System.out.println("I go to "+pageName+" page");
    }

    @And("enter and submit the lead details")
    public void enterAndSubmitTheLeadDetails(DataTable dataTable){
        context.log("enter and submit the lead details");
       // System.out.println("enter and submit the lead details");
        createLeadPage.submitLeadDetails(dataTable);
    }

    @Then("Lead Description Page should load")
    public void verifyLeadDetailPage() {
        leadDescriptionPage.hasLoaded();
    }

    @And("I verify lead details")
    public void iVerifyLeadDetails(){
        context.log("I verify lead details");
       // System.out.println("I verify lead details");
    }

    @Then("lead {string} should {string} inside the grid")
    public void leadShouldBePresentInsideTheGrid(String leadName, String condition){
       // System.out.println("lead should be present inside the grid");
        context.log("lead should be present inside the grid");
        if (condition.equals("be present")) {
            leadsDetailPage.validateLeadPresent(leadName);
        }else {
            leadsDetailPage.validateLeadNotPresent(leadName);
        }
    }

    @When("I select the lead {string}")
    public void selectLead(String leadName) {
        context.log("Selecting the lead "+ leadName);
        leadsDetailPage.selectLead(leadName);
    }

    @And("I click on delete button")
    public void deleteLead() {

    }
}

