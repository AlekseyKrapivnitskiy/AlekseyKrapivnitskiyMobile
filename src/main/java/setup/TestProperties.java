package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    Properties currentProps = new Properties();

    Properties getCurrentProps(PropertyFile propertyFile) throws IOException {
        FileInputStream in = new FileInputStream(System.getProperty("user.dir") + propertyFile.fileName);
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(PropertyFile propertyFile, String propKey) throws IOException {
        if(!currentProps.containsKey(propKey)) currentProps = getCurrentProps(propertyFile);
        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}
