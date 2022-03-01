import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public static WebDriver driver;
    public static Properties config;
    public static Properties pagesConfig;

    public void setup() {
        config = initProps("./repository/config.properties");
        pagesConfig = initProps("./repository/pagesConfig.properties");
        try {
            driver = this.getDriver(config.getProperty("browser_type"));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitly_wait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("page_load_timeout")), TimeUnit.SECONDS);
    }

    public void closeSession() {
        driver.close();
        driver.quit();
    }

    public static Properties initProps(String path) {
        Properties prop = new Properties();
        try {
            FileInputStream inputFile = new FileInputStream(path);
            prop.load(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getElementLocator(String key) {
        return pagesConfig.getProperty(key);
    }

    public WebDriver getDriver(String browserType) {
        if (browserType == null) {
            throw new NullPointerException();
        }

        WebDriver tempDriver;
        String tempBrowser = browserType.toLowerCase();
        switch (tempBrowser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                tempDriver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                tempDriver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                tempDriver = new FirefoxDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                tempDriver = new InternetExplorerDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                tempDriver = new SafariDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                tempDriver = new OperaDriver();
                break;
            default:
                throw new RuntimeException("Unsupported Browser Type");
        }

        return tempDriver;
    }
}
