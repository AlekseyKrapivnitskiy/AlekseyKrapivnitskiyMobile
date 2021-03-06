package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static setup.PropertyFile.*;

/**
 *
 * Initialize a driver with test properties
 */

public class Driver extends TestProperties {
    private static AppiumDriver driverSingle = null;
    protected DesiredCapabilities capabilities;
    private static WebDriverWait waitSingle;

    // Properties to be read
    protected String AUT; // (mobile) app under testing
    protected String SUT; // site under testing
    protected String TEST_PLATFORM;
    protected String DRIVER;

    // Constructor for driver
    protected Driver() throws IOException {
    }

    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws Exception
     */
    protected void prepareDriver(PropertyFile propertyFile) throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;

        // Get Test Properties according to test type
        if(propertyFile == WEB_TEST_PROPERTIES) {
            String t_sut = getProp(WEB_TEST_PROPERTIES, "sut");
            SUT = t_sut == null ? null : "http://" + t_sut;
            TEST_PLATFORM = getProp(WEB_TEST_PROPERTIES, "platform");
            DRIVER = getProp(WEB_TEST_PROPERTIES, "driver");
        } else {
            AUT = getProp(NATIVE_TEST_PROPERTIES, "aut");
            TEST_PLATFORM = getProp(NATIVE_TEST_PROPERTIES, "platform");
            DRIVER = getProp(NATIVE_TEST_PROPERTIES, "driver");
        }

        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); // default Android emulator
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application: mobile, web (or hybrid)
        if(AUT != null && SUT == null){
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else if(SUT != null && AUT == null){
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        } else{
            throw new Exception("Unclear type of mobile app");
        }

        // Init driver for local Appium server with capabilities have been set
        if(driverSingle == null) driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
        // Set an object to handle timeouts
        if(waitSingle == null) waitSingle = new WebDriverWait(driver(), 10);
    }

    protected AppiumDriver driver() throws Exception {
        return driverSingle;
    }

    protected WebDriverWait driverWait() throws Exception {
        return waitSingle;
    }
}
