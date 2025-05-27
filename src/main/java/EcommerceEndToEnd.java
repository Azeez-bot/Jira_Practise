import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommerceEndToEnd {
    public static void main(String[] args) {
        RequestSpecification res=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        LoginRequest lg=new LoginRequest();
        lg.setUserEmail("azeez@gmail.com");
        lg.setUserPassword("ThinkPad@123");
        RequestSpecification rve=given().log().all().relaxedHTTPSValidation().spec(res).body(lg);
        LoginResponse lr=rve.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
        System.out.println(lr.getToken());
        String token = lr.getToken();
        System.out.println(lr.getUserId());
        String userId =lr.getUserId();


        //Add Product
        RequestSpecification addProductBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token).build();
        RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
                .param("productAddedBy", userId).param("productCategory", "fashion")
                .param("productSubCategory", "shirts").param("productPrice", "11500")
                .param("productDescription", "Lenova").param("productFor", "men")
                .multiPart("productImage",new File("C:\\Users\\azeez\\OneDrive\\Pictures\\amilya.png"));
        String ree=reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
        JsonPath js = new JsonPath(ree);
        String productId =js.get("productId");

        //Create Order
        RequestSpecification createOrderBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).setContentType(ContentType.JSON)
                .build();
        OrderDetail rd=new OrderDetail();
        rd.setCountry("India");
        rd.setProductOrderedId(productId);
        List<OrderDetail> or=new ArrayList<OrderDetail>();
        or.add(rd);
        Orders orr=new Orders();
        orr.setOrders(or);
        RequestSpecification createOrderReq=given().log().all().spec(createOrderBaseReq).body(orr);
        String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(responseAddOrder);

        //Delete Product
        RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token).setContentType(ContentType.JSON)
                .build();
        RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("productId",productId);
        String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
                extract().response().asString();
        JsonPath js1 = new JsonPath(deleteProductResponse);
        Assert.assertEquals("Product Deleted Successfully",js1.get("message"));







    }
}
