package payloadBuilder;

import org.json.simple.JSONObject;

public class TestimonialPayload {


    public static JSONObject userLoginPayload(String email, String password) {
        JSONObject userLogin = new JSONObject(); //instantiate userLogin object of type JSONObject
        userLogin.put("email", email); //putting key-value pairs in the userLogin object
        userLogin.put("password", password);

        return userLogin;

    }
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






