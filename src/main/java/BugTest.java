import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class BugTest {

    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy-team.atlassian.net/";
        String createIssueResponse 	= given()
                .header("Content-Type","application/json")
                .header("Authorization","Basic bWVudG9yQHJhaHVsc2hldHR5YWNhZGVteS5jb206QVRBVFQzeEZmR0YwdFNlOHYzNUtILWQtU3U4NUFMckIyTjdDNXIwY0pJU0djdFIwRFBybUhfZjVlUmg4dE5UUVV6UVp1dTFkMXJHdkRjUzNHRnV4TVE4WklSNU9tdFlPbUszLUxBbVU4OEFTM3JrOGkwODFSYV9kQTlPQ3J5QjRERXlFWldJYXpwWGw3VDFTWnBLY0ZOSDBucjVBMUtLQ3FuWVBldzFLR2JSMWowa2JFdGVNVFZFPUZCMzhFM0JB")
                .body("{\n"	+ "\"fields\": {\n"				+ "       \"project\":\n"				+ "       {\n"				+ "          \"key\": \"SCRUM\"\n"				+ "       },\n"				+ "       \"summary\": \"Website items are not working- automation Rest Assured\",\n"				+ "       \"issuetype\": {\n"				+ "          \"name\": \"Bug\"\n"				+ "       }\n"				+ "   }\n"				+ "}")
                .log().all()
                .post("rest/api/3/issue").
                then().log().all().assertThat().statusCode(201).contentType("application/json")
                .extract().response().asString();
        JsonPath js = new JsonPath(createIssueResponse);
        String issueId = js.getString("id");
        System.out.println(issueId);
        given()
                .pathParam("key", issueId)
                .header("X-Atlassian-Token","no-check")
                .header("Authorization","Basic bWVudG9yQHJhaHVsc2hldHR5YWNhZGVteS5jb206QVRBVFQzeEZmR0YwdFNlOHYzNUtILWQtU3U4NUFMckIyTjdDNXIwY0pJU0djdFIwRFBybUhfZjVlUmg4dE5UUVV6UVp1dTFkMXJHdkRjUzNHRnV4TVE4WklSNU9tdFlPbUszLUxBbVU4OEFTM3JrOGkwODFSYV9kQTlPQ3J5QjRERXlFWldJYXpwWGw3VDFTWnBLY0ZOSDBucjVBMUtLQ3FuWVBldzFLR2JSMWowa2JFdGVNVFZFPUZCMzhFM0JB")			.multiPart("file",new File("/Users/rahulshetty/Downloads/IMG_0926.jpeg")).log().all()			.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
        //Add attachment		 		 		 		 		 							}

    }
}
