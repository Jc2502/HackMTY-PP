package controllers;

import com.parse.ParseObject;

/**
 * Created by marinsalinas on 3/21/15.
 */
public class UsersController {


    public static void saveUser(String userId){
        ParseObject testObject = new ParseObject("User");
        testObject.put("userId", userId);
        testObject.saveInBackground();
    }


}
