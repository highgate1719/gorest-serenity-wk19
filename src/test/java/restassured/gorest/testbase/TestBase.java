package restassured.gorest.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import restassured.gorest.utils.PropertyReader;

public class TestBase {
    @BeforeClass
    public static void inIt() {
       RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseUrl");
    }
}
