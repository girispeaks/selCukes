package zoho.teststeps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import zoho.context.TestContext;
import zoho.pages.TopMenuComponent;

public class TopMenu {

    public TestContext context;
    public TopMenuComponent topMenu;

    public TopMenu(TestContext context){
        this.context=context;
        this.topMenu = context.getPageObjectManager().getTopMenu();
    }

    @When("I click on {string} in the top menu")
    public void IClickOnLeadsInTheTopMenu(String menuOption){
        //System.out.println("I click on "+menuOption+" in the top menu");
        context.log("I click on "+menuOption+" in the top menu");
        topMenu.load(menuOption);
   }


}
