package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class json {

    @Test
    public void get(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type","application/json")
                .when()
                .get("http://localhost:3000/employees");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 1)
    public void post(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "        \"id\": \"1000\",\n" +
                        "        \"name\": \"Nitish\",\n" +
                        "        \"salary\": \"500\"\n" +
                        "    }")
                .when()
                .post("http://localhost:3000/employees");
        response.prettyPrint();
        response.then().statusCode(201);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 2)
    public void put(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "        \"id\": \"1000\",\n" +
                        "        \"name\": \"Nitish Prajapati\",\n" +
                        "        \"salary\": \"5000\"\n" +
                        "    }")
                .when()
                .put("http://localhost:3000/employees/1000");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3)
    public void patch(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "        \"salary\": \"10\"\n" +
                        "    }")
                .when()
                .patch("http://localhost:3000/employees/1000");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void delete(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type","application/json")
                .when()
                .delete("http://localhost:3000/employees/1000");
        response.prettyPrint();
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
    }
}
