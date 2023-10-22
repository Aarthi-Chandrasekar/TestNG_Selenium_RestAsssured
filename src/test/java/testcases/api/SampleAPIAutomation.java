package testcases.api;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class SampleAPIAutomation extends APIBaseClass {

    private ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite
    public void setUpExtentReports() {
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
        extent.attachReporter(sparkReporter);

    }

    @AfterSuite
    public void tearDownExtentReports() {
        extent.flush();
    }

    @Test
    public void createEmployeeTest() {
        test = extent.createTest("Create New Employee (POST)");

        HashMap<String, Object> requestBody = new HashMap<String, Object>();

        requestBody.put("firstName", "Krishna");
        requestBody.put("lastName", "Radha");
        requestBody.put("salary", "10000");
        requestBody.put("email", "Krishna@abc.com");
        RequestSpecification request = given();
        request.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody);
        Response response = request.post("/employees");
        test.info("Response Body: " + response.getBody().asString());
        test.info("Response Code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 201);
    }


    @Test
    public void updateEmployeeTest() {
        test = extent.createTest("Update Employee (PUT)");

        HashMap<String, Object> requestBody = new HashMap<String, Object>();

        requestBody.put("firstName", "Tom");
        requestBody.put("lastName", "Jerry");
        requestBody.put("salary", "10000");
        requestBody.put("email", "jerry2@abc.com");
        RequestSpecification request = given();
        request.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody);
        Response response = request.put("/employees/2"); // assuming 7 is the employeeId to be updated
        test.info("Response Body: " + response.getBody().asString());
        test.info("Response Code: " + response.statusCode());

        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test
    public void deleteEmployeeTest() {
        test = extent.createTest("Delete Employee (DELETE)");

        RequestSpecification request = given();
        Response response = request.delete("/employees/1"); // assuming 1 is the employeeId to be deleted

        test.info("Response Body: " + response.getBody().asString());
        test.info("Response Code: " + response.statusCode());

        Assert.assertEquals(response.statusCode(), 200);
    }
    @Test
    public void getEmployeesTest() {
        test = extent.createTest("View all Employees (GET)");

        RequestSpecification request = given();
        request.contentType(ContentType.JSON);

        Response response = request.get("/employees");

        test.info("Response Body: " + response.getBody().asString());
        test.info("Response Code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
    }

}

