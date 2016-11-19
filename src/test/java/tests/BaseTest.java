package tests;


import org.testng.annotations.*;
import utils.ConfigurationManager;

public class BaseTest {

    /**
     * Method that is run before each class
     */
    @BeforeClass
    public void beforeClass() {
        ConfigurationManager.getDriver().get("https://www.google.com");
    }

    /**
     * Method that is run after each class
     */
    @AfterClass
    public void afterClass() {
        if(ConfigurationManager.getDriver() != null) {
            ConfigurationManager.getDriver().quit();
        }
    }
}
