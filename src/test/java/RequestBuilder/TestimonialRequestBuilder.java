package RequestBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloadBuilder.TestimonialPayload;

import static commons.Paths.BASE_URL;
import static io.restassured.RestAssured.given;

public class TestimonialRequestBuilder {
    static String AdminToken;
    static String usertestimonialId;


    public static Response createTestimonial() throws InterruptedException {
        String title = "title";
        String content = "content";
        int rating = 5;
        boolean isPublic = true;


        String apiPath = "/APIDEV/testimonials";

        Response response =  given()
                .baseUri(BASE_URL)
                .basePath(apiPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(TestimonialPayload.userTestimonial(title, content, rating, isPublic))
                .post()
                .then()
                .extract().response();

        usertestimonialId = response.jsonPath().getString("data.Id");
        AdminToken = response.jsonPath().getString("data.token");


        return response;

    }






}
