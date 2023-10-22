package com.mycompany.Selenium_REST_TestNG;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Environment {

    public static final String BASE_URL = "https://www.saucedemo.com";
    public static final String USER_NAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final int EXPLICIT_WAIT = 10;



    public static WebDriver initializeDriver() {
        try {
            URL jsonResource = Environment.class.getClassLoader().getResource("browserConfig.json");
            String content = new String(Files.readAllBytes(Paths.get(jsonResource.toURI())));
            JSONObject config = new JSONObject(content);

            String browserType = config.getString("browser").toLowerCase();

            if ("firefox".equals(browserType)) {
                System.setProperty("webdriver.gecko.driver", config.getString("driverPath"));

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (config.has("headless")) {
                    firefoxOptions.setHeadless(config.getBoolean("headless"));
                }
                return new FirefoxDriver(firefoxOptions);
            } else if ("chrome".equals(browserType)) {
                System.setProperty("webdriver.chrome.driver", config.getString("driverPath"));

                ChromeOptions chromeOptions = new ChromeOptions();
                if (config.has("headless")) {
                    chromeOptions.setHeadless(config.getBoolean("headless"));
                }
                return new ChromeDriver(chromeOptions);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

