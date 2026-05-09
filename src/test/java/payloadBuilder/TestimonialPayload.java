package payloadBuilder;

import org.json.simple.JSONObject;

public class TestimonialPayload {


//Constructor
    public static JSONObject userTestimonial(String title, String content, int rating, boolean isPublic) {
        JSONObject userTestimonial = new JSONObject();
        userTestimonial.put("title" , title);
        userTestimonial.put("content" , content);
        userTestimonial.put("rating" , rating);
        userTestimonial.put("isPublic" ,isPublic);


        return userTestimonial;
    }

}






