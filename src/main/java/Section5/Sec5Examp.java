package Section5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import files.payload;
import files.reusability;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class Sec5Examp {
    public static void main(String[] args) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("key", "qaclick123");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //post--------------------------------------------------------------------------------------------------
        Response response = reusability.givenParamHeader(queryParams, headers,null)
                .body(payload.AddPlace()).
                when().post("maps/api/place/add/json");

        String responseBody = reusability.thenAssertLogAll(response, 200, null, null);
        System.out.println(responseBody);
        //for parsing Json
        JsonPath js = reusability.rawToJson(responseBody);
        String placeId = js.getString("place_id");
        String newAddress = "Ponnur";

        //put----------------------------------------------------------------------------------------------------
        Response re = reusability.givenParamHeader(queryParams, headers,null).
                body(payload.updatePayload(placeId,newAddress)).when().put("maps/api/place/update/json");
        String responseBodyLocationUpdated = reusability.thenAssertLogAll(re, 200, "msg", "Address successfully updated");
        Map<String, String> queryParams1 = new HashMap<>();
        queryParams1.put("key", "qaclick123");
        queryParams1.put("place_id", placeId);

        //get----------------------------------------------------------------------------------------------------
        Response getPlaceResponse = reusability.givenParamHeader(queryParams1, headers,null)
                .when().get("maps/api/place/get/json");
        String AfterGetResponse = reusability.thenAssertLogAll(getPlaceResponse, 200, null, null);
        JsonPath jp = reusability.rawToJson(AfterGetResponse);
        System.out.println(AfterGetResponse);
        String actualAddress=jp.getString("address");
        reusability.assertcheck(actualAddress,newAddress);
    }
}
