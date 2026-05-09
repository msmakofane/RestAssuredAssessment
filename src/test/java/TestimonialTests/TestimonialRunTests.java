package TestimonialTests;

import RequestBuilder.TestimonialRequestBuilder;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TestimonialRunTests {

    Faker faker = new Faker();

    // 1. Testimonial Title (Using a sentence or a random commerce product name)
    String title = faker.lorem().sentence(3);

    // 2. Testimonial Content (A short paragraph)
    String content = faker.lorem().paragraph();

    // 3. Rating (Random number between 1 and 5)
    int rating = faker.number().numberBetween(1, 5);


//        System.out.println("Title: " + title);
//        System.out.println("Rating: " + rating + " Stars");
//        System.out.println("Content: " + content);

//    String title = "Test Automation";
//    String content = "Test Automation Journey";
//    int rating = 5;
    boolean isPublic = true;


    @Test(priority = 1)
    public void createTestimonialSuccessfully() {

        Response response = TestimonialRequestBuilder.createTestimonial(title, content, rating, isPublic);
        response.then().log().all();

        Assert.assertNotNull(TestimonialRequestBuilder.testimonialID, "testimonialID should not be null");
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println("Created ID: " + response.jsonPath().getString("data.Id"));

        }

    @Test
    public void verifyUpdateTestimonial() {
        // Data for the update
        String idToUpdate = TestimonialRequestBuilder.testimonialID;

        System.out.println("Updating ID: " + idToUpdate);
        String newTitle = "Title: Learning API";
        String newContent = "Updated testimonial content: In progress with assessment";
        int newRating = 5;

        // Call the builder

        Response response = TestimonialRequestBuilder.updateTestimonial(idToUpdate, newTitle, newContent, newRating);

        // Logs the full response for debugging
        response.then().log().all();

        // Standard assertions for PUT requests
        // Most APIs return 200 (OK) or 204 (No Content) for updates
        Assert.assertEquals(response.getStatusCode(), 200);
       Assert.assertEquals(response.jsonPath().getString("data.title"), newTitle);
        }
    }




