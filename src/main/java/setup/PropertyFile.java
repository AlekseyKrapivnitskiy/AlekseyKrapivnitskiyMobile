package setup;

public enum PropertyFile {

    NATIVE_TEST_PROPERTIES("nativetest.properties"),
    WEB_TESTS_PROPERTIES("webtest.properties");

    public String fileName;

    PropertyFile(String fileName) {
        this.fileName = fileName;
    }
}
