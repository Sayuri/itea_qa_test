package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {


    private static WebDriver driver = null;

    /**
     * Returns the WebDriver object that was initiated before or creates one if is wasn't initiated before
     *
     * @return WebDriver object
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            ConfigurationManager.init();
        }
        return driver;
    }

    /**
     * Creates new WebDriver
     *
     * @throws IOException that can be thrown while reading from the file where the properties lie
     */
    private static void init() {
        Properties prop = new Properties();
        FileInputStream fp = null;
        try {
            fp = new FileInputStream(new File(ClassLoader.getSystemResource("config.properties").getFile()).getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (osIsWindows()) {
            setTheDriverForWindows(prop);
        } else if (osIsMacOS()) {
            setTheDriverForMacOS(prop);
        } else if (osIsLinux()) {
            setTheDriverForLinux(prop);
        }
    }

    /**
     * Checks whether OS that the app is running on is Windows
     *
     * @return tru if the OS the app is running on is Windows
     */
    private static boolean osIsWindows() {
        return System.getProperty("os.name").contains("Windows");
    }

    /**
     * Checks whether OS that the app is running on is Mac OS
     *
     * @return tru if the OS the app is running on is Mac OS
     */
    private static boolean osIsMacOS() {
        return System.getProperty("os.name").contains("Mac");
    }

    /**
     * Checks whether OS that the app is running on is Linux
     *
     * @return tru if the OS the app is running on is Linux
     */
    private static boolean osIsLinux() {
        return System.getProperty("os.name").contains("Linux");
    }

    /**
     * Creates new WebDriver for Windows depends on the properties
     * Creates new FirefoxDriver or new ChromeDriver or new InternetExplorerDriver
     *
     * @param prop is the properties file where the driver that should be created is written
     */
    private static void setTheDriverForWindows(Properties prop) {
        if (prop.getProperty("driver").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", new File(ClassLoader.getSystemResource("geckodriver.exe").getFile()).getAbsolutePath());
            driver = new FirefoxDriver();
        } else if (prop.getProperty("driver").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", new File(ClassLoader.getSystemResource("chromedriver.exe").getFile()).getAbsolutePath());
            driver = new ChromeDriver();
        } else if (prop.getProperty("driver").equals("ie")) {
            System.setProperty("webdriver.ie.driver", new File(ClassLoader.getSystemResource("IEDriverServer.exe").getFile()).getAbsolutePath());
            driver = new InternetExplorerDriver();
        }
    }

    /**
     * Creates new WebDriver depends on the properties
     * Creates new FirefoxDriver or new ChromeDriver
     *
     * @param prop is the properties file where the driver that should be created is written
     */
    private static void setTheDriverForMacOS(Properties prop) {
        if (prop.getProperty("driver").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", new File(ClassLoader.getSystemResource("geckodriver").getFile()).getAbsolutePath());
            driver = new FirefoxDriver();
        } else if (prop.getProperty("driver").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", new File(ClassLoader.getSystemResource("chromedriver").getFile()).getAbsolutePath());
            driver = new ChromeDriver();
        }
    }

    /**
     * Creates new WebDriver depends on the properties
     * Creates new FirefoxDriver or new ChromeDriver
     *
     * @param prop is the properties file where the driver that should be created is written
     */
    private static void setTheDriverForLinux(Properties prop) {
        if (prop.getProperty("driver").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", new File(ClassLoader.getSystemResource("geckodriverLinux").getFile()).getAbsolutePath());
            driver = new FirefoxDriver();
        } else if (prop.getProperty("driver").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", new File(ClassLoader.getSystemResource("chromedriverLinux").getFile()).getAbsolutePath());
            driver = new ChromeDriver();
        }
    }
}

