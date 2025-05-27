package Section7;

import files.payload;
import files.reusability;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Sec7Example {

    @Test
    public void addBook(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        RestAssured.baseURI="http://216.10.245.166";
        Response response = reusability.givenParamHeader(null,headers,null)
                .body(payload.AddBook("prema","meeru")).when().post("Library/Addbook.php");
        String responseBody = reusability.thenAssertLogAll(response, 200, null, null);
        JsonPath js=reusability.rawToJson(responseBody);
        System.out.println(js.getString("ID"));
        System.out.println(responseBody);
    }
}
