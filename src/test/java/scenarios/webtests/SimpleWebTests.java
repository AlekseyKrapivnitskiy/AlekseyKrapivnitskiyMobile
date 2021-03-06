package scenarios.webtests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.Hooks;

import java.io.IOException;

@Test(groups = "web")
public class SimpleWebTests extends Hooks {
    protected SimpleWebTests() throws IOException {
        super();
    }

    @Test(description = "Open website")
    public void webTest() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT + "/"));
        System.out.println("Site opening done");
    }
}

