

package zoho.runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;



@CucumberOptions(
        features = "src/test/resources/zoho",
        glue = {"zoho.teststeps"},
        tags = "@CreateLead",
        plugin = {"html:target/cucumber-reports.html","rerun:rerun/failed_scenario.txt"},
        dryRun = false

)
public class MyRunner extends AbstractTestNGCucumberTests{
}



