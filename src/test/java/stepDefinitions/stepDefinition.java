package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.IOException;


import static io.restassured.RestAssured.given;

public class stepDefinition extends Utils {

    RequestSpecification req;

    ResponseSpecification res;
    Response response;
    static String place_id;
    TestDataBuild data = new TestDataBuild();
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

       req = given().spec(requestSpecification())
               .body(data.addPlacePayLoad(name, language, address));

    }
    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) throws IOException {
        APIResources apiResources = APIResources.valueOf(resource);
        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST"))
            response = given().spec(req).log().all().when().post(apiResources.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = given().spec(req).log().all().when().get(apiResources.getResource());
    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int int1) {
        assertEquals(int1, response.getStatusCode());

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String path, String value) {
        assertEquals(getJsoanPath(response, path), value);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        place_id = getJsoanPath(response, "place_id");
        req = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        assertEquals(getJsoanPath(response, "name"), name);
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {

        req = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));

    }

}
