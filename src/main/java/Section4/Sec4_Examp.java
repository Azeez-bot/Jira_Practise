package Section4;
import static io.restassured.RestAssured.*;

import files.payload;
import io.restassured.RestAssured;
public class Sec4_Examp {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().log().all().queryParams("key","qaclick123").header("Content-Type","application/json")
                .body(payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }
}
