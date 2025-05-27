package pojo;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Authorization_1 {
    public static void main(String[] args) {
        String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
        String response =
                given()
                        .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .formParams("grant_type", "client_credentials")
                        .formParams("scope", "trust")
                        .when().log().all()
                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");
        System.out.println(accessToken);
        GetCourses r2= given()
                .queryParams("access_token", accessToken)
                .when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .as(GetCourses.class);
        System.out.println(r2.getLinkedIn().toString());
        System.out.println(r2.getInstructor().toString());
        System.out.println(r2.getCourses().getWebAutomation().get(1).getCourseTitle().toString());
        List<api> apicourses=r2.getCourses().getApi();
        for(int i=0;i<apicourses.size();i++)
        {
            if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println(apicourses.get(i).getPrice());
            }
        }

        ArrayList<String> a=new ArrayList<>();
        List<pojo.webAutomation> w=r2.getCourses().getWebAutomation();
        for(int j=0;j<w.size();j++)
        {
            a.add(w.get(j).getCourseTitle().toString());

        }
        List<String> expectedList=	Arrays.asList(courseTitles);
        Assert.assertTrue(a.equals(expectedList));









    }
}
