//
// Decompiled by Procyon v0.5.36
//

package com.oracle.acs.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BrowserDriverUtil
{
    private static WebDriver driver;
    private static String browser;
    private static String version;
    private static String os;
    private static String userHome;
    private static String execPlatform;
    private static String deviceName;
    private static String driverPath;
    private static String defaultBrowserDownloadPath;
    static final Logger loggerBrowser;
    private static WebDriverWait implicitWait;
    private static WebDriverWait explicitWait;
    private static WebDriverWait shortestExplicitWait;
    private static WebDriverWait shortExplicitWait;
    private static WebDriverWait mediumExplicitWait;
    private static WebDriverWait longExplicitWait;
    private static FluentWait<WebDriver> shortestFluentWait;
    private static FluentWait<WebDriver> shortFluentWait;
    private static FluentWait<WebDriver> mediumFluentWait;
    private static FluentWait<WebDriver> longFluentWait;
    private static Long shortSleep;
    private static Long mediumSleep;
    private static Long longSleep;
    private static Long shortestSleep;
    private static int totalSleepTime;

    public static void init() {
        try {

//            WebDriverManager.chromedriver().setup();
//            WebDriver driver = new ChromeDriver();
//            driver.get("http://www.total-qa.com");
            System.out.println("Browser launched");
            try{
                PropertyUtils.loadProperties(new FileInputStream("src/test/resources/propertyFiles/default/system-default.properties"),true);
                PropertyUtils.loadProperties(new FileInputStream("src/test/resources/propertyFiles/ofs-dev/dev.properties"),true);
                System.out.println("Browser launched");
            }
            catch(IOException IOE)
            {
            }
            final String platform = System.getProperty("os.name");
            BrowserDriverUtil.loggerBrowser.info("OS Platform: " + platform);
            if (BrowserDriverUtil.os.contains("Windows")) {
                BrowserDriverUtil.defaultBrowserDownloadPath = BrowserDriverUtil.userHome + "\\Downloads\\";
                System.setProperty("webdriver.chrome.driver", PropertyUtils.getProperty("chromeDriverPath"));
            }
            else if (BrowserDriverUtil.os.contains("inux")) {
                System.out.println("<<<<<<<<<<<<<<<<<<<<"+PropertyUtils.getProperty("chromeDriverPathLinux"));
                BrowserDriverUtil.driverPath = PropertyUtils.getProperty("chromeDriverPathLinux");
                System.setProperty("webdriver.chrome.driver", PropertyUtils.getProperty("chromeDriverPathLinux"));
               // System.setProperty("webdriver.gecko.driver", PropertyUtils.getProperty("firefoxDriverPathLinux"));
                BrowserDriverUtil.defaultBrowserDownloadPath = BrowserDriverUtil.userHome + "/Downloads/";
            }
            else if (BrowserDriverUtil.os.contains("Mac")) {
                BrowserDriverUtil.defaultBrowserDownloadPath = BrowserDriverUtil.userHome + "/Downloads/";
            }
            BrowserDriverUtil.loggerBrowser.info("OS IS::: " + BrowserDriverUtil.os);
            BrowserDriverUtil.loggerBrowser.info("path:" + BrowserDriverUtil.driverPath);
        }
        catch (NullPointerException e) {
            BrowserDriverUtil.loggerBrowser.error("FAILED TO READ PLATFORM.", (Throwable)e);
        }
    }

    public static void loadPropertiesFile(final String file, final boolean importAll) throws IOException {
        String path = null;
        InputStream is = null;
        try {
            is = new FileInputStream("src/main/resources/propertyFiles/default/GatewayPortal-default.properties");
            path = "src/main/resources/propertyFiles/default/GatewayPortal-default.properties";
            BrowserDriverUtil.loggerBrowser.info("PROPERTY FILES ARE DEFINED UNDER:::src/main/resources");
        }
        catch (Exception e) {
            path = "src/test/resources/propertyFiles/default/GatewayPortal-default.properties";
            BrowserDriverUtil.loggerBrowser.info("PROPERTY FILES ARE DEFINED UNDER:::src/test/resources");
        }
        BrowserDriverUtil.loggerBrowser.info("going to read property file :::" + file);
        if (System.getProperty("propertyFile") != null) {
            BrowserDriverUtil.loggerBrowser.info("Reading file from User defined location.");
            is = new FileInputStream(System.getProperty("propertyFile"));
        }
        else {
            is = new FileInputStream(path);
            BrowserDriverUtil.loggerBrowser.info("Reading properties from: " + path);
        }
        PropertyUtils.loadProperties(is, importAll);
        PropertyUtils.dumpProperties();
    }

    private static WebDriver createChromeDriver(final boolean headless) throws IOException {
        final ChromeOptions options = new ChromeOptions();
        if (headless) {
            BrowserDriverUtil.loggerBrowser.info("HEADLESS MODE FOR CHROME:::");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            options.addArguments(new String[] { "--start-maximized" });
            options.addArguments(new String[] { "enable-automation" });
            options.addArguments(new String[] { "--headless" });
            options.addArguments(new String[] { "--no-sandbox" });
            options.addArguments(new String[] { "--disable-infobars" });
            options.addArguments(new String[] { "--disable-dev-shm-usage" });
            options.addArguments(new String[] { "--disable-browser-side-navigation" });
            options.addArguments(new String[] { "--disable-gpu" });
            options.addArguments(new String[] { "--log-net-log" });
        }
        else {
            BrowserDriverUtil.loggerBrowser.info("REGULAR MODE FOR CHROME:::");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            options.addArguments(new String[] { "window-size=1920,1080" });
            options.addArguments(new String[] { "enable-automation" });
            options.addArguments(new String[] { "--no-sandbox" });
            options.addArguments(new String[] { "--disable-infobars" });
            options.addArguments(new String[] { "--disable-dev-shm-usage" });
            options.addArguments(new String[] { "--disable-browser-side-navigation" });
            options.addArguments(new String[] { "--disable-gpu" });
            options.addArguments(new String[] { "--allow-running-insecure-content" });
            options.addArguments(new String[] { "--log-net-log" });
        }
        final Map<String, String> env = System.getenv();
        if (env.containsKey("http_proxy") || env.containsKey("https_proxy") || env.containsKey("no_proxy")) {
            final Proxy proxy = new Proxy();
            proxy.setHttpProxy((String)env.get("http_proxy"));
            proxy.setSslProxy((String)env.get("https_proxy"));
            proxy.setNoProxy((String)env.get("no_proxy"));
            BrowserDriverUtil.loggerBrowser.info("Setting proxy: " + proxy);
            options.setProxy(proxy);
        }
        else {
            BrowserDriverUtil.loggerBrowser.info("Proxy environment variables not set, bypassing proxy configration");
        }
        final Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        final LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable("performance", Level.ALL);
        options.setExperimentalOption("prefs", (Object)prefs);
        options.setExperimentalOption("w3c", (Object)false);
        options.setCapability("goog:loggingPrefs", (Object)logPrefs);
        final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("goog:chromeOptions", (Object)options);
        capabilities.setCapability("acceptInsecureCerts", true);

        //Added on 20-ct-2021
        //WebDriverManager.chromedriver().setup();
        final ChromeDriverService driverService = ChromeDriverService.createDefaultService();



        BrowserDriverUtil.loggerBrowser.info("Chrome Driver Capabilities: {}", (Object)capabilities);
        final ChromeDriver chromeDriver = new ChromeDriver(driverService, (Capabilities)capabilities);
        chromeDriver.manage().deleteAllCookies();
        if (headless) {
            final Map<String, String> params = new HashMap<String, String>();
            final Map<String, Object> commandParams = new HashMap<String, Object>();
            params.put("behavior", "allow");
            params.put("downloadPath", getDefaultBrowserDownloadPath());
            commandParams.put("cmd", "Page.setDownloadBehavior");
            commandParams.put("params", params);
            final ObjectMapper objectMapper = new ObjectMapper();
            final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            final String command = objectMapper.writeValueAsString((Object)commandParams);
            final String url = driverService.getUrl().toString() + "/session/" + chromeDriver.getSessionId() + "/chromium/send_command";
            final HttpPost request = new HttpPost(url);
            request.addHeader("content-type", "application/json");
            request.setEntity((HttpEntity)new StringEntity(command));
            httpClient.execute((HttpUriRequest)request);
        }
        return (WebDriver)chromeDriver;
    }

    private static WebDriver createFirefoxDriver(final boolean headless) {
        final FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
        firefoxProfile.setPreference("network.proxy.type", 0);
        final FirefoxBinary firefoxBinary = new FirefoxBinary();
        if (headless) {
            firefoxBinary.addCommandLineOptions(new String[] { "--headless" });
        }
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        firefoxOptions.setCapability("acceptSslCerts", true);
        firefoxOptions.setCapability("firefox_profile", (Object)firefoxProfile);
        final WebDriver firefoxDriver = (WebDriver)new FirefoxDriver(firefoxOptions);
        firefoxDriver.manage().deleteAllCookies();
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }

    private static WebDriver createEdgeDriver() {
        final EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("acceptInsecureCerts", true);
        edgeOptions.setCapability("acceptSslCerts", true);
        System.setProperty("webdriver.edge.driver", PropertyUtils.getProperty("ieDriverPath"));
        //WebDriverManager.edgedriver().setup();  // Added on 22-OCT as part of change
        final WebDriver edgeDriver = (WebDriver)new EdgeDriver(edgeOptions);
        edgeDriver.manage().window().maximize();
        return edgeDriver;
    }

    private static WebDriver createSafariDriver() {
        final SafariOptions safariOptions = new SafariOptions();
        final SafariDriver safariDriver = new SafariDriver(safariOptions);
        if (BrowserDriverUtil.os.contains("inux") || BrowserDriverUtil.os.contains("Mac")) {
            safariOptions.setCapability("acceptSslCerts", true);
            safariDriver.manage().window().maximize();
        }
        else {
            Assert.fail("SAFARI IS NOT SUPPORTED ON");
        }
        return (WebDriver)safariDriver;
    }

    public static WebDriver launchBrowser() {
        init();
        try {
            BrowserDriverUtil.browser = System.getProperty("browserName");
            if (BrowserDriverUtil.browser == null) {
                BrowserDriverUtil.browser = "chrome";
//            	BrowserDriverUtil.browser = "ie";
            }
        }
        catch (Exception e1) {
            BrowserDriverUtil.loggerBrowser.info("FAILED TO READ BROWSER NAME.", (Throwable)e1);
        }
        try {
          final String browser = BrowserDriverUtil.browser;
           // final String browser = "chromeHeadless";


            switch (browser) {
                case "chrome": {
                    BrowserDriverUtil.loggerBrowser.info("CHROME BROWSER SELECTED");
                    setDriver(createChromeDriver(false));
                    break;
                }
                case "chromeHeadless": {
                    BrowserDriverUtil.loggerBrowser.info("HEADLESS CHROME BROWSER SELECTED.");
                    setDriver(createChromeDriver(true));
                    break;
                }
                case "firefox": {
                    BrowserDriverUtil.loggerBrowser.info("FIREFOX BROWSER SELECTED");
                    setDriver(createFirefoxDriver(false));
                    getDriver().manage().window().setPosition(new Point(0, 0));
                    getDriver().manage().window().setSize(new Dimension(1920, 1080));
                    break;
                }
                case "firefoxHeadless": {
                    BrowserDriverUtil.loggerBrowser.info("HEADLESS FIREFOX BROWSER SELECTED.");
                    setDriver(createFirefoxDriver(true));
                    break;
                }
                case "ie": {
                    BrowserDriverUtil.loggerBrowser.info("LAUNCHING IE EDGE DRIVER.");
                    setDriver(createEdgeDriver());
                    break;
                }
                case "grid": {
                    BrowserDriverUtil.loggerBrowser.info("LAUNCHING SELENIUM GRID.");
                    break;
                }
                case "safari": {
                    BrowserDriverUtil.loggerBrowser.info("LAUNCHING SAFARI BROWSER***");
                    setDriver(createSafariDriver());
                    break;
                }
            }
        }
        catch (Exception e2) {
            BrowserDriverUtil.loggerBrowser.error("*** FAILED TO LAUNCH BROWSER ***", (Object)e2.getMessage());
            Assert.fail("*** FAILED TO LAUNCH BROWSER ***" + e2.getMessage());
        }
        setImplicitWait(new WebDriverWait(getDriver(), (long)Integer.parseInt(PropertyUtils.getProperty("acs.implicit.wait").trim())));
        setExplicitWait(new WebDriverWait(getDriver(), (long)Integer.parseInt(PropertyUtils.getProperty("acs.explicit.wait").trim())));
        setShortestExplicitWait(new WebDriverWait(getDriver(), (long)Integer.parseInt(PropertyUtils.getProperty("acs.explicitWait.shortest").trim())));
        setShortExplicitWait(new WebDriverWait(getDriver(), (long)Integer.parseInt(PropertyUtils.getProperty("acs.explicitWait.short").trim())));
        setMediumExplicitWait(new WebDriverWait(getDriver(), (long)Integer.parseInt(PropertyUtils.getProperty("acs.explicitWait.medium").trim())));
        setLongExplicitWait(new WebDriverWait(getDriver(), (long)Integer.parseInt(PropertyUtils.getProperty("acs.explicitWait.long").trim())));
        setShortestFluentWait((FluentWait<WebDriver>)new FluentWait((Object)getDriver()).withTimeout(Duration.ofSeconds(Integer.parseInt(PropertyUtils.getProperty("acs.explicitWait.shortest").trim()))).pollingEvery(Duration.ofMillis(200L)).ignoring((Class)NoSuchElementException.class, (Class)TimeoutException.class).ignoring((Class)StaleElementReferenceException.class));
        setShortFluentWait((FluentWait<WebDriver>)new FluentWait((Object)getDriver()).withTimeout(Duration.ofSeconds(Integer.parseInt(PropertyUtils.getProperty("acs.explicitWait.short").trim()))).pollingEvery(Duration.ofMillis(200L)).ignoring((Class)NoSuchElementException.class, (Class)TimeoutException.class).ignoring((Class)StaleElementReferenceException.class));
        setMediumFluentWait((FluentWait<WebDriver>)new FluentWait((Object)getDriver()).withTimeout(Duration.ofSeconds(Integer.parseInt(PropertyUtils.getProperty("acs.explicitWait.medium").trim()))).pollingEvery(Duration.ofMillis(200L)).ignoring((Class)NoSuchElementException.class, (Class)TimeoutException.class).ignoring((Class)StaleElementReferenceException.class));
        setLongFluentWait((FluentWait<WebDriver>)new FluentWait((Object)getDriver()).withTimeout(Duration.ofSeconds(Integer.parseInt(PropertyUtils.getProperty("acs.explicitWait.long").trim()))).pollingEvery(Duration.ofMillis(200L)).ignoring((Class)NoSuchElementException.class, (Class)TimeoutException.class).ignoring((Class)StaleElementReferenceException.class));
        setShortestSleep(TimeUnit.SECONDS.toMillis(Long.parseLong(PropertyUtils.getProperty("acs.Sleep.shortest").trim())));
        setShortSleep(TimeUnit.SECONDS.toMillis(Long.parseLong(PropertyUtils.getProperty("acs.Sleep.short").trim())));
        setMediumSleep(TimeUnit.SECONDS.toMillis(Long.parseLong(PropertyUtils.getProperty("acs.Sleep.medium").trim())));
        setLongSleep(TimeUnit.SECONDS.toMillis(Long.parseLong(PropertyUtils.getProperty("acs.Sleep.long").trim())));
        return getDriver();
    }

    public static void navigateToURL(final String url) {
        try {
            BrowserDriverUtil.loggerBrowser.info("URL IS::: " + url);
            getDriver().navigate().to(url);
            if (System.getProperty("broserName") != null && System.getProperty("browserName").equals("ie")) {
                try {
                    Thread.sleep(getShortestSleep());
                    getDriver().findElement(By.id("moreInformationDropdownSpan")).click();
                    Thread.sleep(getShortestSleep());
                    getDriver().findElement(By.id("invalidcert_continue")).click();
                    Thread.sleep(getShortestSleep());
                }
                catch (Exception e2) {
                    BrowserDriverUtil.loggerBrowser.warn("FAILED TO ACCEPT CERTIFICATE");
                }
            }
        }
        catch (Exception e) {
            BrowserDriverUtil.loggerBrowser.error(e.getMessage(), (Throwable)e);
            Assert.assertTrue(false, "FAILED TO NAVIGATE TO THE URL.");
        }
    }

    public static void closeBrwoser() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
            }
        }
        catch (Exception e) {
            Assert.fail("BROWSER NOT FOUND!?" + e.toString());
        }
    }

    public static WebDriver getDriver() {
        if(BrowserDriverUtil.driver==null)
            launchBrowser();
        return BrowserDriverUtil.driver;
    }

    public static void setDriver(final WebDriver driver) {
        BrowserDriverUtil.driver = driver;
    }

    public static WebDriverWait getImplicitWait() {
        return BrowserDriverUtil.implicitWait;
    }

    public static void setImplicitWait(final WebDriverWait implicitWait) {
        BrowserDriverUtil.implicitWait = implicitWait;
    }

    public static WebDriverWait getExplicitWait() {
        return BrowserDriverUtil.explicitWait;
    }

    public static void setExplicitWait(final WebDriverWait explicitWait) {
        BrowserDriverUtil.explicitWait = explicitWait;
    }

    public static WebDriverWait getShortestExplicitWait() {
        return BrowserDriverUtil.shortestExplicitWait;
    }

    public static void setShortestExplicitWait(final WebDriverWait shortestExplicitWait) {
        BrowserDriverUtil.shortestExplicitWait = shortestExplicitWait;
    }

    public static WebDriverWait getShortExplicitWait() {
        return BrowserDriverUtil.shortExplicitWait;
    }

    public static void setShortExplicitWait(final WebDriverWait shortExplicitWait) {
        BrowserDriverUtil.shortExplicitWait = shortExplicitWait;
    }

    public static WebDriverWait getMediumExplicitWait() {
        return BrowserDriverUtil.mediumExplicitWait;
    }

    public static void setMediumExplicitWait(final WebDriverWait mediumExplicitWait) {
        BrowserDriverUtil.mediumExplicitWait = mediumExplicitWait;
    }

    public static WebDriverWait getLongExplicitWait() {
        return BrowserDriverUtil.longExplicitWait;
    }

    public static void setLongExplicitWait(final WebDriverWait longExplicitWait) {
        BrowserDriverUtil.longExplicitWait = longExplicitWait;
    }

    public static Long getShortSleep() {
        BrowserDriverUtil.loggerBrowser.info("SHORT SLEEP CALLED:::" + PropertyUtils.getProperty("acs.Sleep.short"));
        setSleepTime(Integer.parseInt(PropertyUtils.getProperty("acs.Sleep.short")));
        return BrowserDriverUtil.shortSleep;
    }

    public static void setShortSleep(final Long shortSleep) {
        BrowserDriverUtil.shortSleep = shortSleep;
    }

    public static Long getMediumSleep() {
        BrowserDriverUtil.loggerBrowser.info("MEDIUM SLEEP CALLED:::" + PropertyUtils.getProperty("acs.Sleep.medium"));
        setSleepTime(Integer.parseInt(PropertyUtils.getProperty("acs.Sleep.medium")));
        return BrowserDriverUtil.mediumSleep;
    }

    public static void setMediumSleep(final Long mediumSleep) {
        BrowserDriverUtil.mediumSleep = mediumSleep;
    }

    public static Long getLongSleep() {
        BrowserDriverUtil.loggerBrowser.info("LONG SLEEP CALLED:::" + PropertyUtils.getProperty("acs.Sleep.long"));
        setSleepTime(Integer.parseInt(PropertyUtils.getProperty("acs.Sleep.long")));
        return BrowserDriverUtil.longSleep;
    }

    public static void setLongSleep(final Long longSleep) {
        BrowserDriverUtil.longSleep = longSleep;
    }

    public static Long getShortestSleep() {
        BrowserDriverUtil.loggerBrowser.info("Shortest sleep called:::" + PropertyUtils.getProperty("acs.Sleep.shortest"));
        setSleepTime(Integer.parseInt(PropertyUtils.getProperty("acs.Sleep.shortest")));
        return BrowserDriverUtil.shortestSleep;
    }

    public static void setShortestSleep(final Long shortestSleep) {
        BrowserDriverUtil.shortestSleep = shortestSleep;
    }

    public static String getDefaultBrowserDownloadPath() {
        return BrowserDriverUtil.defaultBrowserDownloadPath;
    }

    public static FluentWait<WebDriver> getShortestFluentWait() {
        return BrowserDriverUtil.shortestFluentWait;
    }

    public static FluentWait<WebDriver> getShortFluentWait() {
        return BrowserDriverUtil.shortFluentWait;
    }

    public static void setShortestFluentWait(final FluentWait<WebDriver> shortFluentWait) {
        BrowserDriverUtil.shortestFluentWait = BrowserDriverUtil.shortestFluentWait;
    }

    public static void setShortFluentWait(final FluentWait<WebDriver> shortFluentWait) {
        BrowserDriverUtil.shortFluentWait = shortFluentWait;
    }

    public static FluentWait<WebDriver> getMediumFluentWait() {
        return BrowserDriverUtil.mediumFluentWait;
    }

    public static void setMediumFluentWait(final FluentWait<WebDriver> mediumFluentWait) {
        BrowserDriverUtil.mediumFluentWait = mediumFluentWait;
    }

    public static FluentWait<WebDriver> getLongFluentWait() {
        return BrowserDriverUtil.longFluentWait;
    }

    public static void setLongFluentWait(final FluentWait<WebDriver> longFluentWait) {
        BrowserDriverUtil.longFluentWait = longFluentWait;
    }

    public static void setSleepTime(final int sleepTime) {
        BrowserDriverUtil.totalSleepTime += sleepTime;
    }

    public static int getTotalSleepTime() {
        return BrowserDriverUtil.totalSleepTime;
    }

    public static void resetTotalSleepTime() {
        BrowserDriverUtil.loggerBrowser.info("Resetting Total sleep count");
        BrowserDriverUtil.totalSleepTime = 0;
    }

    static {
        BrowserDriverUtil.driver = null;
        BrowserDriverUtil.browser = System.getProperty("browserName");
        BrowserDriverUtil.version = System.getProperty("version");
        BrowserDriverUtil.os = System.getProperty("os.name");
        BrowserDriverUtil.userHome = System.getProperty("user.home");
        BrowserDriverUtil.execPlatform = System.getProperty("execPlatform");
        BrowserDriverUtil.deviceName = System.getProperty("deviceName");
        BrowserDriverUtil.driverPath = null;
        loggerBrowser = LoggerFactory.getLogger((Class)BrowserDriverUtil.class);
        BrowserDriverUtil.totalSleepTime = 0;
    }
}
