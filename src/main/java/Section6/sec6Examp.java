package Section6;

import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class sec6Examp {
    public static void main(String[] args) {
        JsonPath js=new JsonPath(payload.CoursePrice());
        // Test 1------------------------------------------------- To print no of courses in json
        int count=js.getInt("courses.size()");
        System.out.println(count);
        System.out.println("---------------------------------------------------------------------");

        //Test 2-------------------------------------------------- To print purchase Amount
        int dashboardAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println(dashboardAmount);
        System.out.println("---------------------------------------------------------------------");


        //Test 3--------------------------------------------------To print title of first course
        String firstCourseName=js.get("courses[1].title");
        System.out.println(firstCourseName);
        System.out.println("---------------------------------------------------------------------");


        //Test 4--------------------------------------------------To print all course titles
        for(int i=0;i<count;i++){
            String coursetitle=js.get("courses["+i+"].title");
            System.out.println("Course Title "+coursetitle);
        }
        System.out.println("---------------------------------------------------------------------");


        //Test 5-------------------------------------------------To print all the course price
        for(int i=0;i<count;i++){
            int courseprice=js.getInt("courses["+i+"].price");
            System.out.println("Course Title : "+courseprice);
        }
        System.out.println("---------------------------------------------------------------------");


        //Test 6-------------------------------------------------To print no fo copies sold by RPM
        for(int i=0;i<count;i++){
            String coursetitle=js.get("courses["+i+"].title");
            if(coursetitle.equalsIgnoreCase("RPA")){
                int copies=js.get("courses["+i+"].copies");
                System.out.println("RPM Copies are "+copies);
            }
        }
        System.out.println("---------------------------------------------------------------------");


        //Test 7-------------------------------------------------to validate the purchased price
        int sum=0;
        for(int i=0;i<count;i++){
            int copies=js.get("courses["+i+"].copies");
            int courseprice=js.getInt("courses["+i+"].price");
            sum=sum+(copies*courseprice);
        }
        System.out.println(sum);
        Assert.assertEquals(sum,dashboardAmount);

    }
}
