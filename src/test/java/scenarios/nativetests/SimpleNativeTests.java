package scenarios.nativetests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import scenarios.Hooks;

import java.io.IOException;

import static app.ContactManagerPagesTitles.ADD_CONTACT_PAGE_TITLE;
import static app.ContactManagerPagesTitles.START_PAGE_TITLE;
import static org.testng.Assert.assertEquals;

@Test(groups = "native")
public class SimpleNativeTests extends Hooks {
    protected SimpleNativeTests() throws IOException {
        super();
    }

    @Test(description = "Just click on button 'Add contact'")
    public void simplestTest() throws Exception {
        //1.Assert title of start page
        assertEquals(driver().findElement(By.id("android:id/title")).getText(), START_PAGE_TITLE.title);

        //2.Click on 'Add Contact' button
        String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");
        driver().findElement(add_btn).click();

        //3.Assert title of form
        assertEquals(driver().findElement(By.id("android:id/title")).getText(), ADD_CONTACT_PAGE_TITLE.title);
        System.out.println("Simplest Appium test done");
    }
}
