package testcases.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class APIBaseClass {
	
	
	@BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8088";
    }
	
	
}

