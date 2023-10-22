package testcases.web;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class WebBaseClass {
	public WebDriver driver;
    public ExtentReports report;
    public ExtentTest test;

    @BeforeTest
    public void reportSetup() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
        report = new ExtentReports();
        report.attachReporter(sparkReporter);
    }

    @AfterTest
    public void reportClean() {
        report.flush();
    }
}

