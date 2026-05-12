package Courses;

import io.restassured.RestAssured;

import static commons.Paths.BASE_URL;
import static io.restassured.RestAssured.given;

public class GetCourses {


    public class CourseAutomation {


        public void getBeginnerAutomationCourses() {
            // The base part of your URL
            RestAssured.baseURI =  BASE_URL;

            given()
                    // Rest-Assured will automatically format these into: ?category=Automation&level=beginner
                    .queryParam("category", "Automation")
                    .queryParam("level", "beginner")
                    .header("Content-Type", "application/json")
                    .when()
                    .get("/APIDEV/courses")
                    .then()
                    .log().all() // Prints the response to the IntelliJ console
                    .statusCode(200);
        }
    }

}
