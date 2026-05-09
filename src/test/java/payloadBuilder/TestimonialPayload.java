package payloadBuilder;

import org.json.simple.JSONObject;

public class TestimonialPayload {


//Constructor
    public static JSONObject userTestimonial(String title, String content, int rating, boolean isPublic) {
        JSONObject userTestimonial = new JSONObject();
        userTestimonial.put("title" , title);
        userTestimonial.put("content", content);
        userTestimonial.put("rating" , rating);
        userTestimonial.put("isPublic" ,isPublic);


        return userTestimonial;
    }

    public static JSONObject updateTestimonial(String title, String content, int rating) {
        JSONObject updateTestimonial = new JSONObject();
        updateTestimonial.put("title", title);
        updateTestimonial.put("content", content);
        updateTestimonial.put("rating", rating);

        return updateTestimonial;
    }

}






