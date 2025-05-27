import files.payload;
import files.reusability;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DynamicJsonPayload {
//    @Test(dataProvider = "BooksData")
//    public void addBook(String isbn,String aisle) {
//        RestAssured.baseURI = "http://216.10.245.166";
//        String response = given().log().all().header("Content-Type", "application/json").body(payload.AddBook(isbn,aisle)).
//                when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
//        JsonPath js = reusability.rawToJson(response);
//        String ID = js.get("ID");
//        System.out.println(ID);
//    }
//
//
//    @DataProvider(name = "BooksData")
//    public Object[][] getdata() {
//        return new Object[][]{{"owtb1", "6784"},
//                {"ufgyur", "456"},
//                {"rtyu", "4567"}};
//
//    }
//
    //Passing Data from Json file
    @Test
    public void jsondataCheck() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String res=given().body(new String (Files.readAllBytes(Paths.get("src/main/java/files/data.json")))).
                when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = reusability.rawToJson(res);
        String ID = js.get("ID");
        System.out.println(ID);
    }

}
