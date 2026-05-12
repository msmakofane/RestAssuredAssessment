package TestimonialTests;

import RequestBuilder.TestimonialRequestBuilder;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class TestimonialRunTests {

    Faker faker = new Faker();

    String email = "Group2+Ciara52@hotmail.com";

    String password = "12345678#";

    // 1. Testimonial Title (Using a sentence or a random commerce product name)
    String title = faker.programmingLanguage().name() + " Testimonial";

    // 2. Testimonial Content (A short paragraph)
    String content = faker.name().title() + " shares their experience with our API testing course. They found it " + faker.lorem().sentence() + " and would recommend it to others looking to enhance their skills in API automation.";

    // 3. Rating (Random number between 1 and 5)
    int rating = faker.number().numberBetween(1, 5);

    boolean isPublic = true;

    @Test(priority = 1)
    public void loginTest() {
        Response response  = TestimonialRequestBuilder.loginUser(email, password);
        response.then().log().all();

    }

    @Test(priority = 2)

    public void createTestimonialSuccessfully() {

        Response response = TestimonialRequestBuilder.createTestimonial(title, content, rating, isPublic);
        response.then().log().all();

        Assert.assertNotNull(TestimonialRequestBuilder.testimonialID, "testimonialID should not be null");
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println("Created ID: " + response.jsonPath().getString("data.Id"));

    }

    @Test(priority = 3)
    public void verifyUpdateTestimonial() {
        // Data for the update
        String idToUpdate = TestimonialRequestBuilder.testimonialID;

        System.out.println("Updating ID: " + idToUpdate);
        String newTitle = "Title: Learning API";
        String newContent = "Updated testimonial content: In progress with assessment";
        int newRating = 5;

        Response response = TestimonialRequestBuilder.updateTestimonial(idToUpdate, newTitle, newContent, newRating);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("data.Title"), newTitle);
    }

    @Test (priority = 4)
    public void userDeleteTesimonial() {
        TestimonialRequestBuilder.deleteTestimonial()
       // RequestBuilder.delete(TestimonialRequestBuilder.testimonialID)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }




    public void getCoursesSimple() {
        String fullUrl = "/APIDEV/courses?category=Automation&level=beginner";

        given()
                .when()
                .get(fullUrl)
                .then()
                .statusCode(200)
                .log().body();
    }

}


