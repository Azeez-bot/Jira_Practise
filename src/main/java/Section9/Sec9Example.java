package Section9;

import files.reusability;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static io.restassured.RestAssured.given;

public class Sec9Example {
    public static void main(String[] args) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
        formParams.put("client_secret", "erZOWM9g3UtwNRj340YYaK_W");
        formParams.put("grant_type", "client_credentials");
        formParams.put("scope", "trust");
        //------------------------------------------------------------------------------------------
        Response re = reusability.givenParamHeader(null, null, formParams).when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token");
        System.out.println(re);
        String responseBody = reusability.thenAssertLogAll(re, 200, null, null);
        System.out.println(responseBody);
        JsonPath jsonPath = new JsonPath(responseBody);
        String accessToken = jsonPath.getString("access_token");
        System.out.println(accessToken);
        //------------------------------------------------------------------------------------------
        Map<String,String>mp=new HashMap<>();
        mp.put("access_token", accessToken);


        Response res=reusability.givenParamHeader(null,null,mp).when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails");
        String toBePrinted=reusability.thenAssertLogAll(res,401,null,null);
        System.out.println(toBePrinted);

    }
}
