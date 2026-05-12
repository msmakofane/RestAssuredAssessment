package RequestBuilder;

import commons.Paths;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloadBuilder.TestimonialPayload;

import static commons.Paths.BASE_URL;
import static io.restassured.RestAssured.given;

public class TestimonialRequestBuilder {

    public static String userToken;
    public static String testimonialID;

    public static Response loginUser(String email, String password) {

        String apiPath = "/APIDEV/login";
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(apiPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(TestimonialPayload.userLoginPayload(email, password))
                .post()
                .then()
                .extract().response();
        userToken = response.jsonPath().getString("data.token");
        return response;

    }

    public static Response createTestimonial(String title, String content, int rating, boolean isPublic) {

        Response response = given()
                .header("Authorization", "Bearer " +userToken) // Uses token from Paths
                .baseUri(Paths.BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(TestimonialPayload.userTestimonial(title, content, rating, isPublic))
                .log().all() // This shows the URL in console - check it!
                .post("/APIDEV/testimonials") // Path is ONLY here now
                .then()
                .extract().response();
        testimonialID = response.jsonPath().getString("data.Id");
        System.out.println(STR."Captured testimonialID tumi:\{testimonialID}");

        return response;
    }

    public static Response updateTestimonial(String id, String title, String content, int rating) {

        return given()
                .header("Authorization", "Bearer " + userToken)
                .baseUri(Paths.BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                // Using the Payload builder to generate the update body
                .body(TestimonialPayload.updateTestimonial(title, content, rating))
                .log().all()
                // The URL pattern: BASE_URL + /APIDEV/testimonials/{id}
                .put("/APIDEV/testimonials/" + testimonialID)
                .then()
                .extract().response();

//        static void  beginnerAutomationCourses() {

        //                    .baseUri(BASE_URL)R
//
//
//
//            given()
//                    // Rest-Assured will automatically format these into: ?category=Automation&level=beginner
//                    .header("Content-Type", "application/json")
//                    .baseUri(BASE_URL)
//                    .queryParam("category", "Automation")
//                    .queryParam("level", "beginner")
//                    .when()
//                    .get("/APIDEV/courses")
//                    .then()
//                    .log().all() // Prints the response to the IntelliJ console
//                    .statusCode(200);
//        }

    }


}
