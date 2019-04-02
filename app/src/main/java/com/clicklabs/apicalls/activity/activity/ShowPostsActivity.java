package com.clicklabs.apicalls.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.clicklabs.apicalls.R;
import com.clicklabs.apicalls.activity.adapter.UserPostsRecyclerAdapter;
import com.clicklabs.apicalls.activity.callback.UsersApi;
import com.clicklabs.apicalls.activity.model.UserPosts;
import com.clicklabs.apicalls.activity.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPostsActivity extends AppCompatActivity {

    private RecyclerView rvUserPosts;
    private ArrayList<UserPosts> userPostsList = new ArrayList<>();
    private UserPostsRecyclerAdapter adapter;
    private TextView tvUserId;
    private Integer userId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_posts);
        userId =  Integer.parseInt(getIntent().getStringExtra(Constants.Keys.USERID));
        fetchPostFromApi(userId);

    }

    private void init() {
        rvUserPosts = findViewById(R.id.rvUserPosts);
        tvUserId = findViewById(R.id.tvUserIdPosts);
        tvUserId.setText(getIntent().getStringExtra(Constants.Keys.USERID));
        adapter = new UserPostsRecyclerAdapter(userPostsList);
        rvUserPosts.setLayoutManager(new LinearLayoutManager(this));
        rvUserPosts.setAdapter(adapter);
        Log.d("yyyyyy", "init: " +userPostsList.size());
    }

    /**Hits the API to get the Posts of the selected user
     *
     * @param userId from the user that is selected in the Recycler View User List's on click.
     */
    private void fetchPostFromApi(int userId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UsersApi usersApi = retrofit.create(UsersApi.class);

        Call<ArrayList<UserPosts>> call = usersApi.getPosts(userId);

        call.enqueue(new Callback<ArrayList<UserPosts>>() {
            @Override
            public void onResponse(Call<ArrayList<UserPosts>> call,
                                   Response<ArrayList<UserPosts>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(ShowPostsActivity.this,
                            "Code: " + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                userPostsList = response.body();
                init();

            }

            @Override
            public void onFailure(Call<ArrayList<UserPosts>> call, Throwable t) {
                Toast.makeText(ShowPostsActivity.this,
                        "Couldn't get Response " + t + " got thrown",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
