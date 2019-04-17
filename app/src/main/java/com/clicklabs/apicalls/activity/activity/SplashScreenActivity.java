package com.clicklabs.apicalls.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.clicklabs.apicalls.R;
import com.clicklabs.apicalls.activity.callback.UsersApi;
import com.clicklabs.apicalls.activity.model.User;
import com.clicklabs.apicalls.activity.util.Constants;
import com.clicklabs.apicalls.activity.util.InternetCheckUtil;

import java.util.ArrayList;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreenActivity extends Activity {
    private ProgressBar progressBar;

    ArrayList<User> userArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_splash_screen);
                progressBar = findViewById(R.id.progresssplash);
                progressBar.drawableHotspotChanged(10,10);

        checkInternet();
        fetchDataFromApi();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        checkInternet();
        fetchDataFromApi();
        return true;
    }


    /**
     * When the screen starts, this function will try to connect to internet and wait till
     * the device is connected.
     */
    private void checkInternet() {
        Timer t = new Timer();
        boolean checkConnection=new InternetCheckUtil().checkConnection(this);
        if(checkConnection) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.putParcelableArrayListExtra(Constants.Keys.USERLISTFROMAPI, userArrayList);
                    startActivity(intent);
                    finish();
                }
            }, Constants.Keys.SPLASH_SCREEN_TIME);
        }
        else{
            Toast.makeText(SplashScreenActivity.this,
                    getString(R.string.no_internet_toast),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Fetches the User data from the API.
     */
    private void fetchDataFromApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UsersApi usersApi = retrofit.create(UsersApi.class);

        Call<ArrayList<User>> call = usersApi.getUser();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(SplashScreenActivity.this,getString(R.string.response_toast) + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                userArrayList = response.body();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(SplashScreenActivity.this,getString(R.string.failure_toast) + t + getString(R.string.failure_toast_1),Toast.LENGTH_SHORT).show();
            }
        });


    }
}