package com.clicklabs.apicalls.activity.callback;

import com.clicklabs.apicalls.activity.model.User;
import com.clicklabs.apicalls.activity.model.UserPosts;
import com.clicklabs.apicalls.activity.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Callback class to connect to the API.
 */

public interface UsersApi {

    String BASE_URL = Constants.Url.BASE_URL;

    @GET("users")
    Call<ArrayList<User>> getUser();

    @GET("posts")
    Call<ArrayList<UserPosts>> getPosts(@Query("userId") int userId);

}
