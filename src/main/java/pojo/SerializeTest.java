package pojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        AddPlace p=new AddPlace();
        p.setName("Azeez");
        p.setAccuracy(40);
        p.setAddress("Sharaf Bazar");
        p.setLanguage("Hindhi");
        p.setPhone_number("123456789");
        p.setWebsite("https://rahulshettyacademy.com");
        List<String> m=new ArrayList<String>();
        m.add("shoes");
        m.add("sandals");
        location l=new location();
        l.setLat(12);
        l.setLng(34);
        p.setLocation(l);
        Response res=given().log().all().queryParams("key","qaclick123").body(p).when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
        String resposeString= res.asString();
        System.out.println(resposeString);
    }
}
