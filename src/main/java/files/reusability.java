package files;


import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class reusability {
    public static JsonPath rawToJson(String response)
    {
        JsonPath js1 =new JsonPath(response);
        return js1;
    }
    public static String thenAssertLogAll(Response re,int statusCode,String bodyKey,String bodyValue){
        if(bodyKey!=null && bodyValue!=null){
            return re.then().assertThat().log().all().statusCode(statusCode).body(bodyKey,equalTo(bodyValue)).extract().response().asString();
        }
        else{
            return re.then().assertThat().log().all().statusCode(statusCode).extract().response().asString();
        }

    }
    public static RequestSpecification givenParamHeader(Map<String, String> params, Map<String, String> headers,Map<String, String> formParams) {
        RequestSpecification req = given().log().all();

        if (params != null) {
            req.queryParams(params);
        }

        if (headers != null) {
            req.headers(headers);
        }
        if (formParams != null) {
            req.formParams(formParams);
        }

        return req;
    }
    public static void assertcheck(String actual, String expected){
         Assert.assertEquals(actual,expected);
    }
}
