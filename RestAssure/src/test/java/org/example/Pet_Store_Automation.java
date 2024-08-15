package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Pet_Store_Automation {

    @Test
    public void createUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 11,\n" +
                        "  \"username\": \"harshal\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"harshal\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/user");

        response.prettyPrint();
        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(), "200");
    }
    @Test
    public void createWithArray(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 100,\n" +
                        "    \"username\": \"Rohit\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"Rohit\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithArray\n");
        response.prettyPrint();
    }

    @Test
    public void userLogout(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/logout");
        response.prettyPrint();

    }

    @Test
    public void userLogin(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .queryParam("username", "harshal")
                .queryParam("password", "harshal")
                .get("https://petstore.swagger.io/v2/user/login");
        response.prettyPrint();
    }

    @Test
    public void deleteUser(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/user/Rohit");
        response.prettyPrint();
    }

    @Test
    public void updateUser(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 11,\n" +
                        "  \"username\": \"harshal\",\n" +
                        "  \"firstName\": \"harshal\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"harshal\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/user/harshal");
        response.prettyPrint();
    }

    @Test
    public void getUserByUsername(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/harshal");
        response.prettyPrint();
    }

    @Test
    public void createWithList(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 200,\n" +
                        "    \"username\": \"nitish\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"nitish\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList");
        response.prettyPrint();
    }

    @Test
    public void placeAnOrderForAPet(){
        Response response = given()
                .header("'accept", "application/json")
                .header("'Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"petId\": 2,\n" +
                        "  \"quantity\": 10,\n" +
                        "  \"shipDate\": \"2024-08-12T08:48:56.618Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/store/order");
        response.prettyPrint();
    }

    @Test
    public void storeInventory(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory");
        response.prettyPrint();
    }

    @Test
    public void findOrderById(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/order/1");
        response.prettyPrint();
    }

    @Test
    public void deleteOrderById(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/20");
        response.prettyPrint();
    }

//    @Test
//    public void uploadImage(){
//        Response response = given()
//                .header("accept","application/json")
//                .header("Content-Type", "multipart/form-data")
//                .formParam("file=@chi.jpg", "type=image/jpeg")
//                .when()
//                .post("https://petstore.swagger.io/v2/pet/1/uploadImage");
//        response.prettyPrint();
//    }

    @Test
    public void addPetToTheStore(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 2,\n" +
                        "    \"name\": \"romel\"\n" +
                        "  },\n" +
                        "  \"name\": \"romel\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 2,\n" +
                        "      \"name\": \"romel\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
    }

    @Test
    public void updateAnExistingPet(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 2,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 2,\n" +
                        "    \"name\": \"pappu\"\n" +
                        "  },\n" +
                        "  \"name\": \"pappu\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 2,\n" +
                        "      \"name\": \"pappu\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
    }

    @Test
    public void findPetByStatus(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
        response.prettyPrint();
    }

    @Test
    public void findPetById(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/2");
        response.prettyPrint();
    }

    @Test
    public void updateAPetFromTheStore(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/x-www-form-urlencoded")
                .body("name=romel&status=available")
                .when()
                .post("https://petstore.swagger.io/v2/pet/1");
        response.prettyPrint();
    }

    @Test
    public void deleteAPet(){
        Response response = given()
                .header("accept","application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/pet/1");
        response.prettyPrint();
    }


}
